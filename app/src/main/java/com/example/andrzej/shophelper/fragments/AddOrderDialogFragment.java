package com.example.andrzej.shophelper.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrzej.shophelper.MainActivity;
import com.example.andrzej.shophelper.R;
import com.example.andrzej.shophelper.ScanBarcodeActvity;
import com.example.andrzej.shophelper.db.sql.OrderDAO;
import com.example.andrzej.shophelper.model.Order;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddOrderDialogFragment extends DialogFragment {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @BindView(R.id.quantity_items_seekbar)
    SeekBar mQuantityItemsSeekBar;
    @BindView(R.id.additional_items_counter)
    TextView mAdditionalItemsCounter;
    @BindView(R.id.name_edit)
    EditText mName;
    @BindView(R.id.address_edit)
    EditText mAddress;
    @BindView(R.id.numberOfLanding_edit)
    EditText mNumberOfLanding;
    @BindView(R.id.description_edit)
    EditText mDescription;
    @BindView(R.id.send_switch)
    Switch mSendSwitch;

    private Order mOrder;
    private OrderDAO mOrderDao;
    private Context context;

    public interface EditNameDialogListener {
        void onFinishEditDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static AddOrderDialogFragment newInstance(int num) {
        AddOrderDialogFragment f = new AddOrderDialogFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderDao = new OrderDAO(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_order_dialog, container, false);
        ButterKnife.bind(this, v);
        mQuantityItemsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAdditionalItemsCounter.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }

    @OnClick(R.id.save)
    void onSaveClicked(View view) {
        String name = mName.getText().toString();
        String address = mAddress.getText().toString();
        String description = mDescription.getText().toString();
        String numbersOfLanding = mNumberOfLanding.getText().toString();
        int quantityItems = mQuantityItemsSeekBar.getProgress();
        boolean sent = mSendSwitch.isChecked();
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setDescription(description);
        order.setNumberOfLanding(numbersOfLanding);
        order.setQuantity(quantityItems);
        order.setSent(sent);
        mOrderDao.insertOrder(order);
        dismiss();
    }

    @OnClick(R.id.cancel)
    void onCancelClicked(View view) {
        dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.scanButton)
    void scanZxing(View view) {
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "We need permissions to acces your CAMERA!", Toast.LENGTH_SHORT).show();
            ((MainActivity) context).checkCameraPermission();
        } else {
            if (view.getId() == R.id.scanButton) {
                Intent intent = new Intent(getActivity(), ScanBarcodeActvity.class);
                startActivityForResult(intent, 0);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    mNumberOfLanding.setText(barcode.displayValue);
                } else {
                    Toast.makeText(getActivity(), R.string.no_barcode_found, Toast.LENGTH_LONG).show();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Activity activity = getActivity();
        if(activity instanceof MyDialogCloseListener) {
            ((MyDialogCloseListener)activity).handleDialogClose(dialog);
        }

    }
}





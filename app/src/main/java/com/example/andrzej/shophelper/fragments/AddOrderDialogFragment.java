package com.example.andrzej.shophelper.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.andrzej.shophelper.R;
import com.example.andrzej.shophelper.db.sql.OrderDAO;
import com.example.andrzej.shophelper.model.Order;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddOrderDialogFragment extends DialogFragment {

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

//        if (mOrder == null) {
//            Order order = new Order(name, address, description, numbersOfLanding, quantityItems, send);
//            mOrderDao.insertOrder(order);
//        } else {
//
////            mOrder.setName(name);
////            mOrderRepository.saveVisitor(mOrder);
//        }

        dismiss();
    }

    @OnClick(R.id.cancel)
    void onCancelClicked(View view) {
        dismiss();

    }

}

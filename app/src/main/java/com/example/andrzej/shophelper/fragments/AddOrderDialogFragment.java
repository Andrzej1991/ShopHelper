package com.example.andrzej.shophelper.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.andrzej.shophelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddOrderDialogFragment extends DialogFragment {

    @BindView(R.id.quantity_items_seekbar)
    SeekBar mQuantityItemsSeekBar;

    @BindView(R.id.additional_items_counter)
    TextView mAdditionalItemsCounter;



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

    @OnClick(R.id.cancel)
    void onCancelClicked(View view) {
        dismiss();

    }

}

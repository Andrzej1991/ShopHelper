package com.example.andrzej.shophelper.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andrzej.shophelper.R;


public class AddOrderDialogFragment extends DialogFragment {

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
        return v;
    }


}

package com.example.andrzej.shophelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.andrzej.shophelper.fragments.AddOrderDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_order_btn)
    public void showDialog() {
        int mStackLevel = 0;
        mStackLevel++;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddOrderDialogFragment addOrderFragment = AddOrderDialogFragment.newInstance(mStackLevel);
        addOrderFragment.show(ft, "dialog");
    }

    @OnClick(R.id.show_orders_btn)
    public void onShowOrderClicked(View view) {
        Intent intent = new Intent(this, ShowOrderActivity.class);
        startActivity(intent);
    }

}

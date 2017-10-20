package com.example.andrzej.shophelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.andrzej.shophelper.db.sql.OrderDAO;
import com.example.andrzej.shophelper.fragments.AddOrderDialogFragment;
import com.example.andrzej.shophelper.fragments.MyDialogCloseListener;
import com.example.andrzej.shophelper.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root  on 18.09.17.
 */

public class ShowOrderActivity extends AppCompatActivity implements MyDialogCloseListener {

    @BindView(R.id.show_orders_recycle_view)
    RecyclerView mShowOrdersRecyclerView;

    @BindView(R.id.allOrders)
    TextView allOrdesButton;

    private ShowOrderAdapter mShowOrderAdapter;
    private OrderDAO orderDAO;
    private static int mRecyclerViewState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        ButterKnife.bind(this);
        orderDAO = new OrderDAO(this);
        setupRecyclerView();
        displayData(0);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        mShowOrdersRecyclerView.setHasFixedSize(true);
        mShowOrdersRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mShowOrdersRecyclerView.addItemDecoration(dividerItemDecoration);
        mShowOrderAdapter = new ShowOrderAdapter(this);
        mShowOrderAdapter.setOnLongClickListener(mOnLongClickListener);
        mShowOrderAdapter.setOnClickListener(mOnClickListener);
        mShowOrdersRecyclerView.setAdapter(mShowOrderAdapter);
    }

    private void displayData(int showOrderMarker) {
        List<Order> allOrders;
        String where = "sent = ?";
        String[] trueArg = new String[]{"1"};
        String[] falseArg = new String[]{"0"};
        switch (showOrderMarker) {
            case 0:
                allOrders = orderDAO.getAllOrder(null, null);
                mShowOrderAdapter.setData(allOrders);
                break;
            case 1:
                allOrders = orderDAO.getAllOrder(where, trueArg);
                mShowOrderAdapter.setData(allOrders);
                break;
            case 2:
                allOrders = orderDAO.getAllOrder(where, falseArg);
                mShowOrderAdapter.setData(allOrders);
                break;
        }

    }

    @OnClick(R.id.allOrders)
    void ShowAllOrders(View view) {
        mRecyclerViewState = 0;
        displayData(mRecyclerViewState);
    }

    @OnClick(R.id.sentOrders)
    void ShowSentOrders(View view) {
        mRecyclerViewState = 1;
        displayData(mRecyclerViewState);
    }

    @OnClick(R.id.notSentOrders)
    void ShowNotSentOrders(View view) {
        mRecyclerViewState = 2;
        displayData(mRecyclerViewState);
    }

    void editOrder(Order order) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddOrderDialogFragment addOrderFragment = AddOrderDialogFragment.newInstance(order.getId());
        addOrderFragment.show(ft, "dialog");

    }

    private final View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(final View v) {
            Order order = (Order) v.getTag();
            showAskDialog(order);
            return false;
        }


    };

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Order order = (Order) v.getTag();
            editOrder(order);
        }
    };

    private void showAskDialog(final Order order) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_order)
                .setMessage(getString(R.string.delete_order_question) + order.getName())
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orderDAO.deleteOrder(order.getId());
                        displayData(mRecyclerViewState);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @OnClick(R.id.add_new_order)
    public void addNewOrder() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddOrderDialogFragment addOrderFragment = AddOrderDialogFragment.newInstance("New Record");
        addOrderFragment.show(ft, "dialog");
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        displayData(mRecyclerViewState);
    }
}

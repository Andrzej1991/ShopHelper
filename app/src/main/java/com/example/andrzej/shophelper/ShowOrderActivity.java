package com.example.andrzej.shophelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andrzej.shophelper.db.sql.OrderDAO;
import com.example.andrzej.shophelper.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root  on 18.09.17.
 */

public class ShowOrderActivity extends AppCompatActivity {

    @BindView(R.id.show_orders_recycle_view)
    RecyclerView mShowOrdersRecyclerView;

    private ShowOrderAdapter mShowOrderAdapter;
    private OrderDAO orderDAO;

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
        displayData(0);
    }

    @OnClick(R.id.sentOrders)
    void ShowSentOrders(View view) {
        displayData(1);
    }

    @OnClick(R.id.notSentOrders)
    void ShowNotSentOrders(View view) {
        displayData(2);
    }
}

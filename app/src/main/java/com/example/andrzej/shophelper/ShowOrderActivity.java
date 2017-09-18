package com.example.andrzej.shophelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.andrzej.shophelper.db.sql.OrderDAO;
import com.example.andrzej.shophelper.model.Order;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        displayData();
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

    private void displayData() {
        List<Order> allOrders;
        allOrders = orderDAO.getAllPlaces();
        mShowOrderAdapter.setData(allOrders);
    }
}

package com.example.andrzej.shophelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrzej.shophelper.model.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 18.09.17.
 */

public class ShowOrderAdapter extends RecyclerView.Adapter<ShowOrderAdapter.ShowOrderViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<Order> mData = new ArrayList<>();
    private View.OnLongClickListener mOnLongClickListener;
    private View.OnClickListener mOnClickListener;

    public ShowOrderAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ShowOrderViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View rowView = mLayoutInflater.inflate(R.layout.row_order, parent, false);
        ShowOrderViewHolder viewHolder = new ShowOrderViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowOrderViewHolder holder, int position) {
        Order order = mData.get(position);
        holder.mAdress.setText(order.getAddress());
        holder.mName.setText(order.getName());
        holder.mDescription.setText(order.getDescription());
        holder.mNumberOfLanding.setText(order.getNumberOfLanding());
        holder.mQuantity.setText(String.valueOf(order.getQuantity()));
        holder.itemView.setTag(order);
        holder.itemView.setOnClickListener(mOnClickListener);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener.onLongClick(v);
            }
        });


    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ShowOrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adress)
        TextView mAdress;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.description)
        TextView mDescription;
        @BindView(R.id.quantity)
        TextView mQuantity;
        @BindView(R.id.numberOfLanding)
        TextView mNumberOfLanding;

        public ShowOrderViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<Order> orders) {
        mData.clear();
        mData.addAll(orders);
        notifyDataSetChanged();
    }
}

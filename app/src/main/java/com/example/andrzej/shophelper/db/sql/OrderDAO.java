package com.example.andrzej.shophelper.db.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.andrzej.shophelper.db.sql.table.Orders;
import com.example.andrzej.shophelper.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18.09.17.
 */

public class OrderDAO {

    private DbHelper dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void insertOrder(final Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Orders.Columns.ORDER_NAME, order.getName());
        contentValues.put(Orders.Columns.ORDER_ADDRESS, order.getAddress());
        contentValues.put(Orders.Columns.ORDER_DESCRIPTION, order.getDescription());
        contentValues.put(Orders.Columns.ORDER_NUMBERS_OF_LANDING, order.getNumberOfLanding());
        contentValues.put(Orders.Columns.ORDER_QUANTITY, order.getQuantity());
        contentValues.put(Orders.Columns.ORDER_SENT, order.getSent());
        dbHelper.getWritableDatabase().insert(Orders.TABLE_NAME, null, contentValues);
    }

    public List getAllOrder(String where, String[] selectionArg) {
        Cursor cursor = dbHelper.getReadableDatabase().query(Orders.TABLE_NAME,
                new String[]{Orders.Columns.ORDER_ID,
                        Orders.Columns.ORDER_NAME,
                        Orders.Columns.ORDER_ADDRESS,
                        Orders.Columns.ORDER_DESCRIPTION,
                        Orders.Columns.ORDER_QUANTITY,
                        Orders.Columns.ORDER_NUMBERS_OF_LANDING,
                        Orders.Columns.ORDER_SENT},
                where, selectionArg, null, null, null
        );
        List results = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                results.add(mapCursorToOrder(cursor));
            }
        }
        return results;
    }

    private Order mapCursorToOrder(final Cursor cursor) {
        int idColumnId = cursor.getColumnIndex(Orders.Columns.ORDER_ID);
        int nameId = cursor.getColumnIndex(Orders.Columns.ORDER_NAME);
        int adressColumnId = cursor.getColumnIndex(Orders.Columns.ORDER_ADDRESS);
        int descriptionColumnId = cursor.getColumnIndex(Orders.Columns.ORDER_DESCRIPTION);
        int qtyId = cursor.getColumnIndex(Orders.Columns.ORDER_QUANTITY);
        int numbersOfLandingId = cursor.getColumnIndex(Orders.Columns.ORDER_NUMBERS_OF_LANDING);
        int sentId = cursor.getColumnIndex(Orders.Columns.ORDER_SENT);

        Order order = new Order();
        order.setId(cursor.getInt(idColumnId));
        order.setName(cursor.getString(nameId));
        order.setAddress(cursor.getString(adressColumnId));
        order.setDescription(cursor.getString(descriptionColumnId));
        order.setQuantity(cursor.getInt(qtyId));
        order.setNumberOfLanding(cursor.getString(numbersOfLandingId));
        order.setSent(Boolean.valueOf(cursor.getString(sentId)));
        return order;
    }
}

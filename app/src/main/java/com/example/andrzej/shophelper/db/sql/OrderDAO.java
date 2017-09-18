package com.example.andrzej.shophelper.db.sql;

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

    public List getAllPlaces() {
        Cursor cursor = dbHelper.getReadableDatabase().query(Orders.TABLE_NAME,
                new String[]{Orders.Columns.NAME,
                             Orders.Columns.ADDRESS,
                             Orders.Columns.DESCRIPTION,
                             Orders.Columns.QTY,
                             Orders.Columns.NUMBERS_OF_LANDING,
                             Orders.Columns.SENT},
                null, null, null, null, null
                            );

        List results = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                results.add(mapCursorToOrder(cursor));
            }
        };

        return results;
    }

    private Order mapCursorToOrder(final Cursor cursor) {
        int nameId = cursor.getColumnIndex(Orders.Columns.NAME);
        int adressColumnId = cursor.getColumnIndex(Orders.Columns.ADDRESS);
        int descriptionColumnId = cursor.getColumnIndex(Orders.Columns.DESCRIPTION);
        int qtyId = cursor.getColumnIndex(Orders.Columns.QTY);
        int numbersOfLandingId = cursor.getColumnIndex(Orders.Columns.NUMBERS_OF_LANDING);
        int sentId = cursor.getColumnIndex(Orders.Columns.SENT);

        Order order = new Order();
        order.setName(cursor.getString(nameId));
        order.setAddress(cursor.getString(adressColumnId));
        order.setDescription(cursor.getString(descriptionColumnId));
        order.setQuantity(cursor.getInt(qtyId));
        order.setNumberOfLanding(cursor.getString(numbersOfLandingId));
        order.setSent(Boolean.valueOf(cursor.getString(sentId)));
        return order;
    }


}

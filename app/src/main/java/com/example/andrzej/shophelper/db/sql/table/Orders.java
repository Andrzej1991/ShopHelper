package com.example.andrzej.shophelper.db.sql.table;

/**
 * Created by root on 18.09.17.
 */

public interface Orders {

    String TABLE_NAME = "orders";

    interface Columns {
        String ORDER_ID = "_id";
        String ORDER_ADDRESS = "address";
        String ORDER_NAME = "name";
        String ORDER_DESCRIPTION = "description";
        String ORDER_NUMBERS_OF_LANDING = "numbers_of_landing";
        String ORDER_SENT = "sent";
        String ORDER_QUANTITY = "qty";
    }
}

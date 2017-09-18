package com.example.andrzej.shophelper.db.sql.table;

/**
 * Created by root on 18.09.17.
 */

public interface Orders {

    String TABLE_NAME = "orders";

    interface Columns {
        String ID = "_id";
        String ADDRESS = "address";
        String NAME = "name";
        String DESCRIPTION = "description" ;
        String NUMBERS_OF_LANDING = "numbers_of_landing";
        String SENT = "sent";
        String QTY = "qty";
    }

}

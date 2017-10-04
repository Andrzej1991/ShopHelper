package com.example.andrzej.shophelper.db.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andrzej.shophelper.db.sql.table.Orders;

/**
 * Created by root on 18.09.17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private final static int DB_VERSION = 4;
    private final static String DB_NAME = "ShoperHelperDB.db";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "
                        + Orders.TABLE_NAME
                        + " ( "
                        + Orders.Columns.ORDER_ID
                        + " integer primary key, "
                        + Orders.Columns.ORDER_NAME
                        + " text, "
                        + Orders.Columns.ORDER_ADDRESS
                        + " text, "
                        + Orders.Columns.ORDER_DESCRIPTION
                        + " text, "
                        + Orders.Columns.ORDER_NUMBERS_OF_LANDING
                        + " text, "
                        + Orders.Columns.ORDER_QUANTITY
                        + " integer, "
                        + Orders.Columns.ORDER_SENT
                        + " boolean ) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Orders.TABLE_NAME);
        onCreate(db);
    }
}

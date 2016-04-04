package com.quickshoppe.charlie.quickshoppe;
import android.provider.BaseColumns;
/**
 * Created by Charlie on 4/1/16.
 */
public final class QuickShoppeUserContract {
    public QuickShoppeUserContract() {

    }

    /* Inner class that defines the table contents */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_NAME = "userName";
        public static final String COLUMN_PASSWORD = "userPassword";
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_EMAIL = "userEmail";
        public static final String COLUMN_ACCOUNT_NUMBER = "userAccountNumber";
        public static final String COLUMN_CREDIT_CARD = "userCreditCard";
        public static final String COLUMN_NAME_NULLABLE ="NOT NULL";
    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_CREDIT_CARD + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_ACCOUNT_NUMBER + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;



}

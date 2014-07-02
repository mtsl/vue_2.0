package com.lateralthoughts.vue.models;

//android and java imports
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class PersistentDatabase extends SQLiteOpenHelper {

    public static final String TABLE_CONTENT_MODEL = "TransientContents";
    public static final String COLUMN_AISLE_ID = "aisleId";
    public static final String COLUMN_AISLE_METADATA = "aisleMetaData";
    public static final String COLUMN_EXPIRY = "aisleExpiryTimestamp";
    public static final String COLUMN_USER_ASSOCIATION_TIMESTAMP = "aisleUserAssociationTimestamp";

    private static final String DATABASE_NAME = "transientAppContent.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase mDatabase;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CONTENT_MODEL + "(" + COLUMN_AISLE_ID
            + " integer primary key autoincrement, " + COLUMN_AISLE_METADATA
            + " text not null, " + COLUMN_EXPIRY + " text not null, " + COLUMN_USER_ASSOCIATION_TIMESTAMP
            + "text not null);";

    public PersistentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        mDatabase = database;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT_MODEL);
        onCreate(db);
    }

    public int getNumItems() {
        int count = 0;
        if(null != mDatabase) {
            String[] selectors = {"",""};
            Cursor cursor = getReadableDatabase().rawQuery("",selectors);
            count = cursor.getCount();
        }
        return count;
    }

}
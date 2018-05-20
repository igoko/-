package info.fandroid.p0341_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="DBForApp";
    public static final String TABLE_CONTACTS = "Mark";

    public static final String KEY_ID="_id";
    public static final String KEY_MARK="mark";
    public static final String KEY_TIME="time";
    public static final String KEY_DAY_OF_WEEK="week";
    public static final String KEY_DAY_AND_MONTH="Month";

    public dbhelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + KEY_MARK + " text," + KEY_TIME + " text," + KEY_DAY_OF_WEEK + " text," + KEY_DAY_AND_MONTH + " text"+")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

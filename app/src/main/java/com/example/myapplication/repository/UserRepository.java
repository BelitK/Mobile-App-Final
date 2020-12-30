package com.example.myapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserRepository extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersData";
    private static final String TABLE_USER = "Users";

    private static final String PRIMARY_KEY_ID = "id";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "userPassword";

    public UserRepository(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + PRIMARY_KEY_ID + " INTEGER PRIMARY KEY,"
                + USER_NAME + " TEXT,"
                + USER_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_COUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public boolean checkUser(String username,String password){
        String checkQUERY = "SELECT 1 FROM "+TABLE_USER+" WHERE "+USER_NAME+"='"+username+"' and "+USER_PASSWORD+"='"+password+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(checkQUERY, null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public boolean saveUser(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, username);
        values.put(USER_PASSWORD, password);

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
        return true;
    }



}

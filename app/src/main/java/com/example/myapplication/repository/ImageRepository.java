package com.example.myapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ImagesData";
    private static final String TABLE_IMAGE = "Images";

    private static final String PRIMARY_KEY_ID = "id";
    private static final String IMAGE_PATH = "imagePath";
    private static final String IMAGE_NAME = "imageName";
    private static final String IMAGE_COMMENT = "imageComment";


    public ImageRepository(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_IMAGE + "("
                + PRIMARY_KEY_ID + " INTEGER PRIMARY KEY," + IMAGE_NAME + " TEXT," + IMAGE_PATH + " TEXT,"
                + IMAGE_COMMENT + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_COUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        onCreate(sqLiteDatabase);
    }

    public void save(Image image){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IMAGE_PATH, image.getPath());
        values.put(IMAGE_NAME, image.getName());
        values.put(IMAGE_COMMENT, image.getComment());

        // Inserting Row
        db.insert(TABLE_IMAGE, null, values);
        db.close();
    }

    public Image findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_IMAGE, new String[] { PRIMARY_KEY_ID, IMAGE_NAME,IMAGE_PATH, IMAGE_COMMENT }, PRIMARY_KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Image image = new Image(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return image;

    }
    public List<Image> findAll(){
        List imageList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_IMAGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Image image = new Image();
                image.setId(Integer.parseInt(cursor.getString(0)));
                image.setName(cursor.getString(1));
                image.setPath(cursor.getString(2));
                image.setComment(cursor.getString(3));
                // Adding country to list
                imageList.add(image);
            } while (cursor.moveToNext());
        }

        return imageList;

    }

    public int updateCountry(Image image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IMAGE_PATH, image.getPath());
        values.put(IMAGE_NAME, image.getName());
        values.put(IMAGE_COMMENT, image.getComment());

        // updating row
        return db.update(TABLE_IMAGE, values, PRIMARY_KEY_ID + " = ?", new String[] { String.valueOf(image.getId()) });
    }

    public void deleteCountry(Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_IMAGE, PRIMARY_KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }








}

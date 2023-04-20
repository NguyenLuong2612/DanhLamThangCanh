package com.example.danhlamthangcanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class DLTC_DB {
    private static final String TABLE_NAME = "tblDanhLamThangCanh";
    private static final String COLUMN_ID = "ID";

    private SQLiteDatabase db;
    //ham tao bang
    // CODE FIRST
    public DLTC_DB(Context context) {
        db = context.openOrCreateDatabase("DanhLamThangCanhDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY)");
    }

    // Thực hiện thêm id vào database
    public void insert(int id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        db.insert(TABLE_NAME, null, values);
    }

    //Xóa id trong database
    public void delete(int id) {
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        //phải đưa về (String) dạng chuỗi
    }

    public void close() {
        db.close();
    }

    //Lấy toàn bộ id trong database lưu trữ dưới kiểu ArrayList<Integer>
    public ArrayList<Integer> getAllIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            ids.add(id);
        }
        cursor.close();
        return ids;
    }
}

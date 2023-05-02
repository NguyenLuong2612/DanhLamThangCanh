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
    private static final String COLUMN_DOCUMENT_ID = "DocumentID";

    private SQLiteDatabase db;

    public DLTC_DB(Context context) {
        db = context.openOrCreateDatabase("DanhLamThangCanhDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DOCUMENT_ID + " TEXT UNIQUE)");
    }

    public void insert(String documentId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCUMENT_ID, documentId);
        db.insert(TABLE_NAME, null, values);
    }

    public void delete(String documentId) {
        db.delete(TABLE_NAME, COLUMN_DOCUMENT_ID + " = ?", new String[]{documentId});
    }

    public void close() {
        db.close();
    }

    public ArrayList<String> getAllDocumentIds() {
        ArrayList<String> documentIds = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_DOCUMENT_ID}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String documentId = cursor.getString(cursor.getColumnIndex(COLUMN_DOCUMENT_ID));
            documentIds.add(documentId);
        }
        cursor.close();
        return documentIds;
    }
}

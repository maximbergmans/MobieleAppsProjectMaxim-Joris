package com.ucll.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joris on 23/11/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "testDatabase";
    public static final String TABLE_NAME = "tabel1";
    public static final String COL_1 = "id";
    public static final String COL_2 = "col1";
    public static final String COL_3 = "col2";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT, col1 TEXT, col2 TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME );
        onCreate(db);
    }



    public boolean insertData(String col2, String col3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2 ,col2);
        cv.put(COL_3 ,col3);
        long result =db.insert(TABLE_NAME,null,cv);

        if ( result == -1 ){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME ,null);


        return res;
    }

    public boolean update(String id, String col2, String col3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1 ,id);
        cv.put(COL_2 ,col2);
        cv.put(COL_3 ,col3);
        db.update(TABLE_NAME,cv,"id = ?",new String[]{id} );

        return true;
    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?",new String[]{id});
    }
}

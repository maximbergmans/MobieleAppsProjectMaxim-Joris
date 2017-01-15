package com.ucll.geofencenew;

/**
 * Created by Joris on 13/01/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Joris on 13/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "testDatabase";
    public static final String TABLE_NAME = "tabel1";
    public static final String COL_1 = "id";
    public static final String COL_2 = "longitude";
    public static final String COL_3 = "latitude";
    public static final String COL_4 = "description";
    //public static final String COL_5 = "col5";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT, longitude TEXT, latitude TEXT, description TEXT)" );

        insertOnStart(db,"50.937216", "5.417169", "testplaats1");
        insertOnStart(db,"50.937216", "5.417169", "testplaats2");
        insertOnStart(db,"50.774656", "5.442534", "testplaats3");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME );
        onCreate(db);
    }

    public void insertOnStart(SQLiteDatabase db, String longitude, String latitude, String description){
        ContentValues cv = new ContentValues();
        cv.put(COL_2 ,longitude);
        cv.put(COL_3 ,latitude);
        cv.put(COL_4 ,description);
        long result =db.insert(TABLE_NAME,null,cv);
    }

    public boolean insertData(String longitude, String latitude, String description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2 ,longitude);
        cv.put(COL_3 ,latitude);
        cv.put(COL_4 ,description);
        long result =db.insert(TABLE_NAME,null,cv);

        if ( result == -1 ){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor getDataById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME +" WHERE id = ?",new String[]{id});
        return res;
    }

    public boolean update(String id, String longitude, String latitude, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1 ,id);
        cv.put(COL_2 ,longitude);
        cv.put(COL_3 ,latitude);
        cv.put(COL_4 ,description);
        db.update(TABLE_NAME,cv,"id = ?",new String[]{id} );

        return true;
    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?",new String[]{id});
    }


}


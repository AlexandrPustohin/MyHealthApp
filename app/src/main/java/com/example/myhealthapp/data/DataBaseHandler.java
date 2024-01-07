package com.example.myhealthapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myhealthapp.model.Type;
import com.example.myhealthapp.utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TYPE_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_TYPES + " ( "
                + Util.TYPE_ID+ " INTEGER PRIMARY KEY, "
                + Util.TYPE_NAME+ " TEXT,"
                + Util.TYPE_DESCRIPTION+ " TEXT )";
        sqLiteDatabase.execSQL(CREATE_TYPE_TABLE);

        String CREATE_INFO_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_INFO + " ( "
                + Util.INFO_ID+ " INTEGER PRIMARY KEY, "
                + Util.INFO_DATA+ " TEXT,"
                + Util.INFO_TYPE+ " TEXT,"
                + Util.INFO_INFO+ " TEXT )";

        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO
    }

    public void addType(Type type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TYPE_NAME, type.getName());
        contentValues.put(Util.TYPE_DESCRIPTION, type.getDescription());
        db.insert(Util.TABLE_NAME_TYPES,null, contentValues);
        db.close();

    }

    public Type getTypeByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME_TYPES,
                                 new String[]{
                                            Util.TYPE_ID
                                         ,  Util.TYPE_NAME
                                         ,  Util.TYPE_DESCRIPTION
                                 },
                Util.TYPE_NAME+"=?"
                ,  new String[]{name}
                , null
                ,  null
                , null
                ,   null
                );
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Type type = new Type(Integer.parseInt(cursor.getString(0))
                            , cursor.getString(1)
                            , cursor.getString(2) );
        return type;
    }
}

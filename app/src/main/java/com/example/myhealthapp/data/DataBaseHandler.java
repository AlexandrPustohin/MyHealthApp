package com.example.myhealthapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;
import com.example.myhealthapp.utils.DateTransform;
import com.example.myhealthapp.utils.Util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
                + Util.INFO_DATA+ " INTEGER,"
                + Util.INFO_TYPE+ " TEXT,"
                + Util.INFO_INFO+ " TEXT )";

        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME_INFO );
        String CREATE_INFO_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_INFO + " ( "
                + Util.INFO_ID+ " INTEGER PRIMARY KEY, "
                + Util.INFO_DATA+ " INTEGER,"
                + Util.INFO_TYPE+ " TEXT,"
                + Util.INFO_INFO+ " TEXT )";

        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);
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
        db.close();
        return type;
    }

    public List<Type> getAllTypes(){
        ArrayList<Type> arrayListType = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursors = db.query(Util.TABLE_NAME_TYPES,
                new String[]{
                        Util.TYPE_ID
                        ,  Util.TYPE_NAME
                        ,  Util.TYPE_DESCRIPTION
                },
                null
                ,  null
                , null
                ,  null
                , null
                ,   null
        );
        if(cursors!=null){
            while (cursors.moveToNext()){
                arrayListType.add(new Type(Integer.parseInt(cursors.getString(0))
                        , cursors.getString(1)
                        , cursors.getString(2) ));
            }
        }

        return arrayListType;
    }

    public void addInfo(HealthInfoItem infoItem) throws ParseException {
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.INFO_DATA, DateTransform.getMilsFromDate(infoItem.getTextDate()));
        contentValues.put(Util.INFO_TYPE, infoItem.getTextType());
        contentValues.put(Util.INFO_INFO, infoItem.getTextInfo());
        db.insert(Util.TABLE_NAME_INFO,null, contentValues);*/
        String inquery = "INSERT INTO "+ Util.TABLE_NAME_INFO+"( "
                + Util.INFO_DATA +" , "
                + Util.INFO_TYPE +" , "
                + Util.INFO_INFO +") VALUES ( "
                + " strftime('%s', '"+ infoItem.getTextDate()+"')  ,'"
                + infoItem.getTextType() +"' ,'"
                + infoItem.getTextInfo()+"' )";
        db.execSQL(inquery);
        db.close();
    }
    public List<HealthInfoItem> getAllInfo(){
        ArrayList<HealthInfoItem> listHealth = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME_INFO,
                new String[]{
                        Util.INFO_ID
                        , Util.INFO_DATA
                        , Util.INFO_TYPE
                        , Util.INFO_INFO
                },
                null
                ,  null
                , null
                ,  null
                , null
                ,   null
        );

        if (cursor!=null){
            while (cursor.moveToNext()){
                listHealth.add(new HealthInfoItem(Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , ""));
            }
        }
        return listHealth;
    }

    public List<HealthInfoItem> getAllInfoWithMeasurement(){
        ArrayList<HealthInfoItem> listHealth = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();//datetime(hi.data, 'unixepoch')
        String query = "select hi.id as id" +
                ", strftime('%d.%m.%Y %H:%M',datetime(hi.data, 'unixepoch')) as data" +
                ", hi.type as type" +
                ", hi.info as info" +
                ", t.description as description " +
                " from "+ Util.TABLE_NAME_INFO + " as hi "+
                " inner join "+ Util.TABLE_NAME_TYPES+ " as t on ( t." +Util.TYPE_NAME + " = hi." +
                Util.INFO_TYPE+" ) " ;

        Log.d("sql: ", query);

        Cursor cursor = db.rawQuery(query, null);

        //String table = "health_info AS hi inner join types AS t on t.description = hi.type";
        //String columns[] = { "hi.id", "hi.data", "hi.type", "hi.info", "t.description" };
        //String selection = "salary < ?";
        //String[] selectionArgs = {"12000"};
        //Cursor cursor = db.query(table, columns, null, null, null, null, null);

        if (cursor!=null){
            while (cursor.moveToNext()){
                listHealth.add(new HealthInfoItem(
                        Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)));
            }
        }
        db.close();
        return listHealth;
    }
}

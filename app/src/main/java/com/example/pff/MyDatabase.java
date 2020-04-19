package com.example.pff;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
public class MyDatabase extends SQLiteAssetHelper {
    public static final  String DB_Name="partmes.db";
    public static final  String table_name="partments";
    public static final String part_column_superficier="superficier";
    public static final String  part_column_nombre_chambre="nmbrchmr";
    public static final String part_coloumn_Adress="paradress";
    public static final String part_column_id="id";
    public static final String part_column_prix="prix";
    public static final String part_colum_image="image";
    public static final String table_user_name="users";
    public static final  String user_column_id="id";
    public static final String user_column_Tel="Tel_user";
    public static final String user_column_Adress="Adress_user";
    public static final  String user_column_image="Image_user";
    public static final String Password_user="Password_user";
    public static final String User_column_name="Nom_user";
    public static final  int  DB_version=9;

     public MyDatabase(Context context)
     {
         super(context ,DB_Name,null,1);
     }
   /* @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(" CREATE TABLE "+table_name+"("+part_column_id+"INTEGER PRIMARY KEY AUTOINCREMENT ,"+part_column_superficier+" TEXT NOT NULL,"+part_coloumn_Adress+" TEXT NOT NULL,"+part_column_nombre_chambre+" INTEGER  NOT NULL); ");
        db.execSQL(" CREATE TABLE partments(id INTEGER PRIMARY KEY AUTOINCREMENT ,superficier VARCHAR NOT NULL,nmbrchmr INTEGER ,paradress VARCHAR NOT NULL); ");
     }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF exists "+table_name);
        onCreate(db);
    }*/

}

package com.example.pff;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
public class DatabaseAcces_user {
    private SQLiteDatabase database;
    private SQLiteAssetHelper openHelper;// nafta7 w nsakar database
    private static DatabaseAcces_user instance;
    private   DatabaseAcces_user(Context context)
    {
        this.openHelper=new MyDatabase(context);
    }
    public static DatabaseAcces_user getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new DatabaseAcces_user(context);
        }
        return instance;
    }
    public void open()
    {
        this.database=this.openHelper.getWritableDatabase();
    }
    public void close()
    {
        if(this.database!=null)
        {
            this.database.close();
        }
    }
    public boolean insertuser(User u)
    {
        ContentValues values=new ContentValues();
        values.put(MyDatabase.user_column_id,u.getId_user());
        values.put(MyDatabase.User_column_name,u.getName());
        values.put(MyDatabase.Password_user,u.getPassword_user());
        values.put(MyDatabase.user_column_Adress,u.getAdress_user());
        values.put(MyDatabase.user_column_Tel,u.getTel_user());
        long result =database.insert(MyDatabase.table_user_name,null,values);
        return  result!=-1;
    }
    public boolean updateuser(User u)
    {
        ContentValues values=new ContentValues();
        values.put(MyDatabase.user_column_id,u.getId_user());
        values.put(MyDatabase.User_column_name,u.getName());
        values.put(MyDatabase.Password_user,u.getPassword_user());
        values.put(MyDatabase.user_column_Adress,u.getAdress_user());
        values.put(MyDatabase.user_column_Tel,u.getTel_user());
        String[] tabb = {u.getId_user() + ""};
        int result =database.update(MyDatabase.table_user_name,values ,"id=?",tabb);
        return  result>0;
    }
    public long getcount_table_user()
    {
        return  DatabaseUtils.queryNumEntries(database,MyDatabase.table_user_name);
    }
    public boolean DeletUser(User u)
    {
        String[] tabb = {u.getId_user() + ""};
        int result =database.delete(MyDatabase.table_user_name,"id=?",tabb);
        return  result>0;
    }
    public ArrayList<User> getAllUsers()
    {
        ArrayList<User>users=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_user_name,null);
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.user_column_id));
                String Tel_user=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Tel));
                String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Adress));
                String img=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_image));
                String User_Name=cursor.getString(cursor.getColumnIndex(MyDatabase.User_column_name));
                String User_pass=cursor.getString(cursor.getColumnIndex(MyDatabase.Password_user));
                User object_user=new User(id,User_Name,Adr,Tel_user,img,User_pass);
                users.add(object_user);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return users;
    }
    public ArrayList<User>getuser(String name)
    {
        ArrayList<User>users=new ArrayList<>();
        // SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_user_name+" WHERE "+MyDatabase.User_column_name+"=?",new String[]{name});
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.user_column_id));
                String Tel_user=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Tel));
                String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Adress));
                String img=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_image));
                String User_Name=cursor.getString(cursor.getColumnIndex(MyDatabase.User_column_name));
                String User_pass=cursor.getString(cursor.getColumnIndex(MyDatabase.Password_user));
                User object_user=new User(id,User_Name,Adr,Tel_user,img,User_pass);
                users.add(object_user);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return users;
    }
    public User getUsr_id(int User_id)
    {
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_user_name+" WHERE "+MyDatabase.user_column_id+"=?"
                ,new String[]{String.valueOf(User_id)});
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
            int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.user_column_id));
            String Tel_user=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Tel));
            String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_Adress));
            String img=cursor.getString(cursor.getColumnIndex(MyDatabase.user_column_image));
            String User_Name=cursor.getString(cursor.getColumnIndex(MyDatabase.User_column_name));
            String User_pass=cursor.getString(cursor.getColumnIndex(MyDatabase.Password_user));
            User object_user=new User(id,User_Name,Adr,Tel_user,img,User_pass);
            cursor.close();
            return object_user;
        }
        return null;
    }
}

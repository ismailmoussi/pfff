package com.example.pff;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
public class DatabaseAcces {
    private SQLiteDatabase database;
    private SQLiteAssetHelper openHelper;// nafta7 w nsakar database
    private static DatabaseAcces instance;
    private   DatabaseAcces(Context context)
    {
        this.openHelper=new MyDatabase(context);
    }
    public static DatabaseAcces getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new DatabaseAcces(context);
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
    public boolean insertPartm(Partement p)
    {

        ContentValues values=new ContentValues();
        values.put(MyDatabase.part_coloumn_Adress,p.getAdresse());
        values.put(MyDatabase.part_column_nombre_chambre,p.getNombre_de_chambre());
        values.put(MyDatabase.part_column_superficier,p.getSuperficier());
        values.put(MyDatabase.part_column_prix,p.getPrix_part());
        values.put(MyDatabase.part_colum_image,p.getImage());
        long result =database.insert(MyDatabase.table_name,null,values);
        return  result!=-1;
    }
    public boolean insertPartmtwo(Partement p)
    {
        ContentValues values=new ContentValues();
        values.put(MyDatabase.part_coloumn_Adress,p.getAdresse());
        values.put(MyDatabase.part_column_superficier,p.getSuperficier());
        long result =database.insert(MyDatabase.table_name,null,values);
        return  result!=-1;
    }
    public boolean updatepartment(Partement p)
    {
        ContentValues values=new ContentValues();
        values.put(MyDatabase.part_coloumn_Adress,p.getAdresse());
        values.put(MyDatabase.part_column_nombre_chambre,p.getNombre_de_chambre());
        values.put(MyDatabase.part_column_superficier,p.getSuperficier());
        values.put(MyDatabase.part_column_prix,p.getPrix_part());
        values.put(MyDatabase.part_colum_image,p.getImage());
        String[] tabb = {p.getId() + ""};
        int result =database.update(MyDatabase.table_name,values ,"id=?",tabb);
        return  result>0;
    }
    public long getcount()
    {

        return  DatabaseUtils.queryNumEntries(database,MyDatabase.table_name);

    }
    public boolean Deletpart(Partement p)
    {
        String[] tabb = {p.getId() + ""};
        int result =database.delete(MyDatabase.table_name,"id=?",tabb);
        return  result>0;
    }
    public ArrayList<Partement> getAllpartm()
    {
        ArrayList<Partement>partments=new ArrayList<>();

        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_name,null);
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_id));
                String superfi=cursor.getString(cursor.getColumnIndex(MyDatabase.part_column_superficier));
                int nbchm=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_nombre_chambre));
                int prix=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_prix));
                String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.part_coloumn_Adress));
                String img=cursor.getString(cursor.getColumnIndex(MyDatabase.part_colum_image));
                Partement par=new Partement(id,superfi,Adr,nbchm,prix,img);
                partments.add(par);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return partments;
    }
    public int pfftest()
    {
        return 1;
    }
    public ArrayList<Partement>getPartme(String Super)
    {
        ArrayList<Partement>partments=new ArrayList<>();
       // SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_name+" WHERE "+MyDatabase.part_column_superficier+"=?",new String[]{Super});
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_id));
                String superfi=cursor.getString(cursor.getColumnIndex(MyDatabase.part_column_superficier));
                int nbchm=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_nombre_chambre));
                int prix=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_prix));
                String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.part_coloumn_Adress));
                String img=cursor.getString(cursor.getColumnIndex(MyDatabase.part_colum_image));
                Partement par=new Partement(id,superfi,Adr,nbchm,prix,img);
                partments.add(par);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return partments;
    }




    public Partement getPartment(int PartId)
    {
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.table_name+" WHERE "+MyDatabase.part_column_id+"=?"
                ,new String[]{String.valueOf(PartId)});
        if( cursor!=null &&cursor.moveToFirst())//idha ka L cursor f lawal
        {
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_id));
                String superfi=cursor.getString(cursor.getColumnIndex(MyDatabase.part_column_superficier));
                int nbchm=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_nombre_chambre));
                int prix=cursor.getInt(cursor.getColumnIndex(MyDatabase.part_column_prix));
                String Adr=cursor.getString(cursor.getColumnIndex(MyDatabase.part_coloumn_Adress));
                String img=cursor.getString(cursor.getColumnIndex(MyDatabase.part_colum_image));
                Partement par=new Partement(id,superfi,Adr,nbchm,prix,img);
            cursor.close();
            return par;
        }
        return null;
    }
}

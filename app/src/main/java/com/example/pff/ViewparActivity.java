package com.example.pff;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
public class ViewparActivity extends AppCompatActivity {
    private   static final  int PICK_IMAGE_REQ_CODE =1;
   public   static final  int ADD_PART_RESULT_CODE =2;
    public   static  final int EDIT_PART_RESULT_CODE =3;
    private Toolbar toolbar;
    private TextInputEditText prix,superficier,Adress,nb_chambre;
    private ImageView iv;
    private int partId=-1;
    private DatabaseAcces db;
    Uri ImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpar);
       toolbar=findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
      iv  =findViewById(R.id.image_textt);
       Adress =findViewById(R.id.Adress_text);
       superficier =findViewById(R.id.Superficier_text);
      prix=  findViewById(R.id.prix_text);
      nb_chambre=findViewById(R.id.nb_text);
              db=DatabaseAcces.getInstance(this);
     Intent intent=getIntent();
      partId=intent.getIntExtra(MainActivity.PART_KEY,-1);
      if(partId==-1)
      {
          //ADD 3AMAliyat idhafa
          enablblefields();
          clearfields();
      }else
      {
          //hna affiche update
          disablefields();
          db.open();
          Partement p=db.getPartment(partId);
          db.close();
          if(p!=null)
          {
              fillparttofields(p);
          }
      }
      iv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent in= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//njib Lphoto man Lgalerie
              startActivityForResult(in,PICK_IMAGE_REQ_CODE);
          }
      });
    }
    public void  fillparttofields (Partement p)
    {
        if(p.getImage()!=null&&!p.getImage().equals(""))
       iv.setImageURI(Uri.parse(p.getImage()));
       prix.setText(p.getPrix_part()+"");
       nb_chambre.setText(p.getNombre_de_chambre()+"");
       superficier.setText(p.getSuperficier());
       Adress.setText(p.getAdresse());
    }
    public void disablefields()
    {
        iv.setEnabled(false);
        prix.setEnabled(false);
        superficier.setEnabled(false);
        nb_chambre.setEnabled(false);
    }
    public void  enablblefields()
    {
        iv.setEnabled(true);
        prix.setEnabled(true);
        superficier.setEnabled(true);
        nb_chambre.setEnabled(true);
    }
    public void  clearfields()
    {
        iv.setImageURI(null);
       prix.setText("");
       superficier.setText("");
       nb_chambre.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        MenuItem Save=menu.findItem(R.id.details_menu_save);
        MenuItem edit=menu.findItem(R.id.details_menu_edit);
        MenuItem delete=menu.findItem(R.id.details_menu_delite);
        if(partId==-1)
        {
            //ADD 3AMAliyat idhafa
            Save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);
        }else
        {
            //hna affiche update
            Save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String Adress_p,Superficier_p,Image_p="";
                double Prix_p; int nombre_chambre_p;

        switch (item.getItemId())
        {

            case R.id.details_menu_save:
                boolean res;
                Adress_p=Adress.getText().toString();
                Superficier_p=superficier.getText().toString();
                Prix_p=Double.parseDouble(prix.getText().toString());
                nombre_chambre_p=Integer.parseInt(nb_chambre.getText().toString());

                if(ImageUri!=null)
                {
                    Image_p=ImageUri.toString();
                }
               /// Image_p=ImageUri != null ? ImageUri.toString() : null;;

                //( String superficier, String adresse, int nombre_de_chambre,double prix_part,String image)
                Partement p=new Partement( partId,Superficier_p,Adress_p,nombre_chambre_p,Prix_p,Image_p);
                db.open();
               if(partId==-1)
               {
                   res= db.insertPartm(p);
                   setResult(ADD_PART_RESULT_CODE,null);
                   finish();
               }else
               {
                   res=db.updatepartment(p);
                   setResult(EDIT_PART_RESULT_CODE,null);
                   finish();
               }
                db.close();
                return true;
            case R.id.details_menu_edit:
                enablblefields();
                MenuItem Save=toolbar.getMenu().findItem(R.id.details_menu_save);
                MenuItem edit=toolbar.getMenu().findItem(R.id.details_menu_edit);
                MenuItem delete=toolbar.getMenu().findItem(R.id.details_menu_delite);
                Save.setVisible(true);
                edit.setVisible(false);
                delete.setVisible(false);
//
//                Save.setVisible(true);
//
                return true;
            case R.id.details_menu_delite:

                db.open();
                p=new Partement( partId,null,0,0,null);
                    res = db.Deletpart(p);
                    if(res)
                    {
                        setResult(EDIT_PART_RESULT_CODE,null);
                        finish();
                    }

                db.close();
                return true;
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQ_CODE&& resultCode==RESULT_OK)
        {
            ImageUri=data.getData();// na7fadh Lmasar ta3 Lphoto
       if(data!=null)
       {
           iv.setImageURI(ImageUri);
        }
        }
    }
}

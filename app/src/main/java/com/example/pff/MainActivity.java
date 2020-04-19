package com.example.pff;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    static final int ADD_PART_REQ_CODE = 1;
     static final int EDIT_PART_REQ_CODE = 1;
     static final String PART_KEY="part_key";
     private static final int PERMISSION_REQ_CODE=5;
    RecyclerView rv;
    FloatingActionButton fab;
    Toolbar toolbar;
    PartRvAdapter adapter;
    DatabaseAcces db;
    int k = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_CODE);
        }
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
       /// getSupportActionBar().setDisplayShowTitleEnabled(false);
        //  setSupportActionBar(toolbar);
        rv = findViewById(R.id.main_rv);

        db = DatabaseAcces.getInstance(this);
        db.open();
        ArrayList<Partement> pr = new ArrayList<>();
        pr=db.getAllpartm();
        db.close();
        PartRvAdapter adapter = new PartRvAdapter(pr, new OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemclick(int PartID) {
            Intent i=new Intent(getBaseContext(),ViewparActivity.class);    //bach ki naclicki f F cardview 3la Lphoto yadhharLi Lmo7tawat
            i.putExtra(PART_KEY,PartID);
            startActivityForResult(i,EDIT_PART_REQ_CODE);

            }
        });
        fab=findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ViewparActivity.class);
               startActivityForResult(intent,ADD_PART_REQ_CODE);//bach ki narja3 tat7adath Lista
            }
        });
        RecyclerView.LayoutManager lm = new GridLayoutManager(this, 2);
        rv.setHasFixedSize(true);// recycle view thabat L3ordh
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_menu,menu);
      SearchView searchView=(SearchView)menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);//affiche le >
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 db.open();

                 ArrayList<Partement>p=db.getPartme(query);
                 db.close();
                 adapter.setPartements(p);
                 adapter.notifyDataSetChanged();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                db.open();

                ArrayList<Partement>p=db.getPartme(newText);
                db.close();
                adapter.setPartements(p);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        return true ;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_PART_REQ_CODE&&resultCode==ViewparActivity.ADD_PART_RESULT_CODE)
        {
                  // N7ADAD LISTA
            db = DatabaseAcces.getInstance(this);
            db.open();
            ArrayList<Partement> pr = db.getAllpartm();
            db.close();

            adapter.setPartements(pr);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode)
        {
            case PERMISSION_REQ_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {

                }else
                {

                }
        }
    }
}

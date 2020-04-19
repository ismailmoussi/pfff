package com.example.pff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class LoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
//        final FloatingActionButton btn=findViewById(R.id.btn_snack);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar s=Snackbar.make(btn,"click me",Snackbar.LENGTH_LONG);
//                s.setAction("undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });
//                s.show();
//            }
//        });
    }
}

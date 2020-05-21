package com.jtlai0921.pass.Main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;

public class sign_up extends AppCompatActivity {

    private EditText id,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);
    }
    public void registerok(View view){
        SharedPreferences result = getSharedPreferences("personal",0);
        String userid = id.getText().toString();
        String userpwd = pwd.getText().toString();
        result.edit()
                .putString("uid",userid)
                .putString("upwd",userpwd)
                .commit();
        finish();

    }
    public void cancel(View view){
        finish();
    }
}
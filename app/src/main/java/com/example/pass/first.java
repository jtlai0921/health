package com.example.pass;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class first extends AppCompatActivity {

    private EditText id,pwd;
    private String loginid,loginpwd;
    private  Boolean loggedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);
        Button cancel = (Button) findViewById(R.id.cancel);
        SharedPreferences result = getSharedPreferences("personal",0);
        loggedin = false;
        if (loggedin){
            Intent it = new Intent(first.this, bmi.class);
            startActivity(it);
        }
    }
    public void login(View view){
        SharedPreferences result = getSharedPreferences("personal",0);
        loginid = result.getString("uid","無");
        loginpwd = result.getString("upwd","無");
        if (loginid.equals(id.getText().toString())){
            if (loginpwd.equals(pwd.getText().toString())){
                result.edit().putBoolean("loggedin",true).commit();
                Intent it = new Intent(first.this, bmi.class);
                startActivity(it);
            }
        }
        }
    public void register(View view){
        SharedPreferences result = getSharedPreferences("personal",0);
            Intent it = new Intent(first.this, sign_up.class);
            startActivity(it);

    }
}
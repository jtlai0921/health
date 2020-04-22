package com.example.pass;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Main3Activity extends AppCompatActivity {

    private int i=0;
    private static final String[] mStrings={"海景房","山景房","海砂屋","帳篷","組合屋","鐵皮屋","打地鋪","小木屋"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Notify();
        ListView list = (ListView)findViewById(R.id.listView1);
        list.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,mStrings));
        list.setTextFilterEnabled(true);
    }
    public void Notify(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("颱風特價")
                .setContentTitle("颱風限定")
                .setContentText("海砂屋任你住");
        Notification notification = builder.build();
        notificationManager.cancel(i);                 //移除ID值為0的通知
        notificationManager.notify(i,notification);    //發送ID值為0的通知
    }
    public void logout(View view){
        SharedPreferences result = getSharedPreferences("personal",0);
        result.edit().putBoolean("loggedin",false).commit();
        finish();
    }
}

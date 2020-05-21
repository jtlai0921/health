package com.jtlai0921.pass.Main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;

public class author extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        WebView webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setContentView(webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://jtlai.weebly.com/");
        final String[] Visitors = {"帥哥","美女"};

        AlertDialog.Builder dialog_list = new AlertDialog.Builder(author.this);
        dialog_list.setTitle("請問您的身分是?");
        dialog_list.setItems(Visitors, new DialogInterface.OnClickListener(){
            @Override

            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(author.this, "歡迎光臨" + Visitors[which], Toast.LENGTH_SHORT).show();
            }
        });
        dialog_list.show();

//        AlertDialog.Builder objdbr = new AlertDialog.Builder(this);
//
//        //取得自訂的版面。
//
//        objdbr.setMessage("作者資料");
//
//        //設定AlertDialog的View。
//
//        objdbr.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //回傳輸入的值，再用Toast顯示。
//            }
//        }).show();
    }
}


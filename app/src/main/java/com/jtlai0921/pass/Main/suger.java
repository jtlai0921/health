package com.jtlai0921.pass.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;

import java.text.NumberFormat;

public class suger extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    private Button button;
    private CountDownTimer timer;
    EditText g;                //宣告全域變數
                 //宣告全域變數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suger);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer(10);
            }
        });
//        listView = (ListView) findViewById(R.id.listView);
//        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"一、量測前須空腹8小時", "二、單位為mg/dI", "三、此APP僅參考用，實際仍需專業醫師為主"});
//        listView.setAdapter(listAdapter);
        g= (EditText)findViewById(R.id.et1);                           // 取得bg物件
                          // 取得體重物件
        Button submit = (Button)findViewById(R.id.button4);             // 取得按鈕物件 如果ID有誤請回到res->Layout查看預設Button是多少

        // 按下按鈕 觸發事件
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                //判斷條件 身高 跟 體重 都有輸入值才執行
                if ( !("".equals(g.getText().toString())))
                {
                    float fg = Float.parseFloat(g.getEditableText().toString());      // 取得 輸入值
                    float fresult;                                     // bg值 計算結果
                    TextView result = (TextView)findViewById(R.id.tv3);// 取得 顯示結果 物件
                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                    fresult = fg;                                // 計算bg
                    result.setText(nf.format(fg) +"");           // 顯示bg計算結果
                    TextView dia = (TextView)findViewById(R.id.tv4);// 取得 顯示診斷 物件

                    // 診斷結果 顯示
                    if (fresult<80)
                        dia.setText("經過血糖指數判斷\n您的血糖太低");
                    else if (80 <= fresult && fresult< 110)
                        dia.setText("經過血糖指數判斷\n您為正常");
                    else if (130 <=fresult && fresult< 160)
                        dia.setText("經過血糖指數判斷\n您可能有糖尿病");
                    else if (fresult >= 160)
                        dia.setText("經過血糖指數判斷\n您可能有嚴重糖尿病");
                }
            }

        });

        AlertDialog.Builder objdbr = new AlertDialog.Builder(this);

        //取得自訂的版面。

        objdbr.setMessage("一、量測前須空腹8小時,\n二、單位為mg/dI,\n三、此APP僅參考用,實際仍需專業醫師為主");

        //設定AlertDialog的View。

        objdbr.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //回傳輸入的值，再用Toast顯示。
            }
        }).show();
    }

    private  void startTimer(int time) {

        timer = new CountDownTimer(time*1000,1000){

            @Override
            public void onTick(long l) {
                button.setEnabled(false);
                button.setText((int)l/1000+"");
            }

            @Override
            public void onFinish() {
                button.setEnabled(true);
                button.setText("空腹倒數計時");
            }
        };
        timer.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer!=null){

            timer.cancel();
        }
    }
    public void index (View view) {
        Intent intent = new Intent(this,index.class);
        startActivity(intent);
    }
}

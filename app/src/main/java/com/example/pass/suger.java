package com.example.pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;

public class suger extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;

    EditText g;                //宣告全域變數
                 //宣告全域變數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suger);
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"一、量測前須空腹8小時", "二、單位為mg/dI", "三、此APP僅參考用，實際仍需專業醫師為主"});
        listView.setAdapter(listAdapter);
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
                        dia.setText("血糖太低");
                    else if (80 <= fresult && fresult< 110)
                        dia.setText("正常");
                    else if (130 <=fresult && fresult< 160)
                        dia.setText("糖尿病可控制");
                    else if (fresult >= 160)
                        dia.setText("嚴重糖尿病");
                }
            }

        });


    }

    public void bmi(View view) {
        Intent intent = new Intent(this,bmi.class);
        startActivity(intent);
    }
    public void pressure(View view) {
        Intent intent = new Intent(this,pressure.class);
        startActivity(intent);
    }
    public void login(View view) {
        Intent intent = new Intent(this, first.class);
        startActivity(intent);
    }
}

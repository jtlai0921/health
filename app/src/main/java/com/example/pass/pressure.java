package com.example.pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class pressure extends AppCompatActivity {

    EditText p;                //宣告全域變數收縮壓
    EditText d;                //宣告全域變數舒張壓
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        p = (EditText)findViewById(R.id.et1);                           // 取得身高物件
        d = (EditText)findViewById(R.id.et2);                           // 取得體重物件
        Button submit = findViewById(R.id.button4);             // 取得按鈕物件 如果ID有誤請回到res->Layout查看預設Button是多少

        // 按下按鈕 觸發事件
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {

                if ( !("".equals(p.getText().toString())
                        || "".equals(d.getText().toString())) )
                {
                    float fp = Float.parseFloat(p.getEditableText().toString());      // 取得 收縮壓輸入值
                    float fd = Float.parseFloat(d.getEditableText().toString());     // 取得 舒張輸入值

                    TextView result = findViewById(R.id.tv3);// 取得 顯示結果 物件


                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(0);                 // 限制小數第二位

                    result.setText("收縮壓"+nf.format(fp) +"舒張壓"+nf.format(fd)+"");
                      // 顯示結果
                    TextView dia = (TextView)findViewById(R.id.tv4);// 取得 顯示診斷 物件

                    // 診斷結果 顯示
                    if (fp<120 && fd<80)
                        dia.setText("血壓正常");
                    else if (129<fp && fp<139||fp<89 && 80<fp )
                        dia.setText("高血壓前期(警示用)");
                    else if (140<fp && fp<159||fp<99 && 90<fp)
                        dia.setText("第一期高血壓");
                    else if (160<fp || 100<fp)
                        dia.setText("第2期高血壓");
                    else dia.setText("請輸入正確值");
                }
            }

        });

    }
    public void bmi(View view) {
        Intent intent = new Intent(this,bmi.class);
        startActivity(intent);
    }
    public void suger(View view) {
        Intent intent = new Intent(this,suger.class);
        startActivity(intent);
    }
    public void login(View view) {
        Intent intent = new Intent(this, first.class);
        startActivity(intent);
    }
}

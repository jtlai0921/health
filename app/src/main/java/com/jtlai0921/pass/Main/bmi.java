package com.jtlai0921.pass.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;

import java.text.NumberFormat;

public class bmi extends AppCompatActivity {
    EditText h;                //宣告全域變數
    EditText w;                //宣告全域變數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        h = (EditText)findViewById(R.id.et1);                           // 取得身高物件
        w = (EditText)findViewById(R.id.et2);                           // 取得體重物件
        Button submit = (Button)findViewById(R.id.button4);             // 取得按鈕物件 如果ID有誤請回到res->Layout查看預設Button是多少

        // 按下按鈕 觸發事件
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                //判斷條件 身高 跟 體重 都有輸入值才執行
                if ( !("".equals(h.getText().toString())
                        || "".equals(w.getText().toString())) )
                {
                    float fh = Float.parseFloat(h.getEditableText().toString());      // 取得 身高輸入值
                    float fw = Float.parseFloat(w.getEditableText().toString());     // 取得 體重輸入值
                    float fresult;                                     // BMI值 計算結果
                    TextView result = (TextView)findViewById(R.id.tv3);// 取得 顯示結果 物件
                    fh = fh/100; // 計算BMI
                    fh = fh*fh;  // 計算BMI

                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                    fresult = fw/fh;                                // 計算BMI
                    result.setText(nf.format(fw/fh) +"");           // 顯示BMI計算結果
                    TextView dia = (TextView)findViewById(R.id.tv4);// 取得 顯示診斷 物件

                    // 診斷結果 顯示
                    if (fresult<18.5)
                        dia.setText("經過BMI指數判斷\n您的體重過輕");
                    else if (18.5 <= fresult && fresult< 24)
                        dia.setText("經過BMI指數判斷\n您的體重為正常範圍");
                    else if (24 <=fresult && fresult < 27)
                        dia.setText("經過BMI指數判斷\n您的體重過重");
                    else if (27 <=fresult && fresult < 30)
                        dia.setText("經過BMI指數判斷\n您的體重為輕度肥胖");
                    else if (30 <= fresult && fresult < 35)
                        dia.setText("經過BMI指數判斷\n您的體重為中度肥胖");
                    else if (fresult >= 35)
                        dia.setText("經過BMI指數判斷\n您的體重為重度肥胖        ");
                }
            }

        });

    }
    public void index (View view) {
        Intent intent = new Intent(this,index.class);
        startActivity(intent);
    }

}

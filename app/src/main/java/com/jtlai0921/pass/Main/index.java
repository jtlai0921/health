package com.jtlai0921.pass.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        CalendarView calendarview = (CalendarView) findViewById(R.id.calendarView);

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(index.this,"您選的時間是："+ year + "年" + month + "月" + dayOfMonth + "日",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pressure(View view) {
        Intent intent = new Intent(this,pressure.class);
        startActivity(intent);
    }
    public void suger(View view) {
        Intent intent = new Intent(this,suger.class);
        startActivity(intent);
    }
    public void first(View view) {
        Intent intent = new Intent(this, first.class);
        startActivity(intent);
        finish();
    }
    public void record(View view) {
        Intent intent = new Intent(this, record.class);
        startActivity(intent);
        finish();
    }
    public void graph(View view) {
        Intent intent = new Intent(this, graph.class);
        startActivity(intent);
        finish();
    }
    public void author(View view) {
        Intent intent = new Intent(this, author.class);
        startActivity(intent);
        finish();
    }
    public void bmi(View view) {
        Intent intent = new Intent(this,bmi.class);
        startActivity(intent);
    }
}

package com.jtlai0921.pass.Main;

import android.icu.text.Collator;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showdata extends AppCompatActivity {

    private Button btnlogout;
    private ListView listview;

    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        setTitle("ShowData");

        btnlogout = (Button)findViewById(R.id.btnlogout);
        listview = (ListView)findViewById(R.id.listview);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);



        btnlogout.setOnClickListener(new View.OnClickListener() {
            private Collator FirebaseAuth;

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().cloneAsThawed();
                finish();
            }
        });

        getData();
    }

    private void getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("員工");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot contact : dataSnapshot.getChildren()){
                    adapter.add(contact.getKey().toString()+"-"+contact.child("phone").getValue().toString());
                }

                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
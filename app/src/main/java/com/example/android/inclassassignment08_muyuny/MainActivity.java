package com.example.android.inclassassignment08_muyuny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;

    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText keyField;
    EditText valueField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        keyField = findViewById(R.id.text_view_key);
        valueField = findViewById(R.id.text_view_value);
        //write a message to the database
        database = FirebaseDatabase.getInstance();
    }
    public void writetoCloud(View view){
        myRef = database.getReference(keyField.getText().toString());
        myRef.setValue(valueField.getText().toString());
    }
    public void readfromCloud(View view){
        myRef = database.getReference(keyField.getText().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String exisitingData = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                    valueField.setText(exisitingData);
                }
                else{
                    valueField.setText(null);
                    Toast.makeText(MainActivity.this,"Can not find the key",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MainActivity.this,"Failed loading value",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

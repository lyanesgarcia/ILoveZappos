package com.example.ilovezappos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button1, button2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.button1){
            intent= new Intent(this, Activity1.class);
        }else if(view.getId() == R.id.button2){
            intent= new Intent(this, Activity2.class);
        }else{
            intent= new Intent(this, Activity3.class);
        }
        startActivity(intent);
    }
}

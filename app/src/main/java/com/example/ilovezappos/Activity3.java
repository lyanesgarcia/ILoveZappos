package com.example.ilovezappos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;

public class Activity3 extends Activity implements View.OnClickListener {
    Bundle bundle;
    EditText editText;
    ListView listView;
    FirebaseJobDispatcher jobDispatcher;
    ArrayList<String> array = new ArrayList<String>();



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        editText = (EditText) findViewById(R.id.edittext);
        listView = (ListView) findViewById(R.id.list);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            String list_value =" ";
            Double list_number= 0.0;
            String getInput = editText.getText().toString();

            if(getInput == null || getInput.trim().equals("")) {
                Toast.makeText(getBaseContext(), "Edit Text is empty", Toast.LENGTH_LONG).show();
            } else {
                array.add(0, getInput);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this, android.R.layout.simple_list_item_1, array);
                listView.setAdapter(adapter);
                list_value = (String) listView.getItemAtPosition(0);
                list_number = Double.parseDouble(list_value);
                ((EditText) findViewById(R.id.edittext)).setText(" ");
            }

            bundle = new Bundle();
            bundle.putDouble("input", list_number);

            jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(Activity3.this));
            jobDispatcher.mustSchedule(
                    jobDispatcher.newJobBuilder()
                            .setService(Service.class)
                            .setExtras(bundle)
                            .setTag("DeviceSpaceService")
                            .setRecurring(true)
                            .setReplaceCurrent(true)
                            .setTrigger(Trigger.executionWindow(3600, 3610))
                            .build()
            );

        }
    }
}
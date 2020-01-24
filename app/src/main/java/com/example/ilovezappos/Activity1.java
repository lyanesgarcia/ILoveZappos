package com.example.ilovezappos;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Activity1 extends AppCompatActivity {
    LineChart graph;
    ArrayList<Entry> data;
    JSONArray jsonArray;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        getData updateTask= new getData();
        updateTask.execute();

        try {
            data= updateTask.get();
            graph=(LineChart)findViewById(R.id.line_chart);

            LineDataSet lineDataSet1 = new LineDataSet(data, "Price history over time");

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet1);
            lineDataSet1.setColor(Color.MAGENTA);
            lineDataSet1.setCircleColor(Color.MAGENTA);
            LineData data = new LineData(dataSets);
            graph.setData(data);
            graph.getDescription().setEnabled(false);

            graph.invalidate();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public class getData extends AsyncTask<String, Void, ArrayList<Entry>> {
        String data ="";
        @Override
        protected ArrayList<Entry> doInBackground(String... strings) {
            ArrayList<Entry> datavalues= new ArrayList<Entry>();
            try {
                URL url = new URL("https://www.bitstamp.net/api/v2/transactions/btcusd/");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                jsonArray = new JSONArray(data);
                for(int i =0 ;i <jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    datavalues.add(new Entry(jsonObject.getInt("date"), jsonObject.getInt("price")));
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return datavalues;
        }

        @Override
        protected void onPostExecute(ArrayList<Entry> datavalues) {
            super.onPostExecute(datavalues);
        }
    }
}

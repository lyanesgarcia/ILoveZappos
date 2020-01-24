package com.example.ilovezappos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity2 extends Activity {
    private RecyclerView view1, view2;
    private Adapter adapter1, adapter2;
    private ArrayList<Item> list1,list2;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        view1 = findViewById(R.id.view1);
        view1.setHasFixedSize(true);
        view1.setLayoutManager(new LinearLayoutManager(this));

        view2 = findViewById(R.id.view2);
        view2.setHasFixedSize(true);
        view2.setLayoutManager(new LinearLayoutManager(this));


        queue = Volley.newRequestQueue(this);

        String url = "https://www.bitstamp.net/api/v2/order_book/btcusd/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray1 = response.getJSONArray("bids");
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                String bid=jsonArray1.getString(i);

                                list1.add(new Item(bid));
                            }
                            JSONArray jsonArray2 = response.getJSONArray("asks");
                            for (int i = 0; i < jsonArray2.length(); i++) {
                                String asks=jsonArray2.getString(i);

                                Log.i("ssss","este: "+ asks);
                                list2.add(new Item(asks));
                            }


                            adapter1 = new Adapter(Activity2.this, list1);
                            view1.setAdapter(adapter1);

                            adapter2 = new Adapter(Activity2.this, list2);
                            view2.setAdapter(adapter2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}

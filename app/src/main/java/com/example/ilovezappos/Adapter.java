package com.example.ilovezappos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {
    private Context context;
    private ArrayList<Item> arrayList;

    public Adapter(Context context_adapter, ArrayList<Item> arrayList_adapter) {
        context = context_adapter;
        arrayList = arrayList_adapter;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ExampleViewHolder(View view) {
            super(view);
            textView = itemView.findViewById(R.id.text_view_creator);
        }
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Item currentItem = arrayList.get(position);
        String segundo = currentItem.getmSegundo();
        holder.textView.setText(segundo);

    }

}

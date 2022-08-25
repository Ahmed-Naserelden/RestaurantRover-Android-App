package com.example.restaurantmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class OrderBasedAdabter extends BaseAdapter {
    Map<String, Integer> values;
    LayoutInflater inflater;
    Context context;
    DBModule db;
    public OrderBasedAdabter(Context context, Map<String, Integer> values){
        db = new DBModule();
        this.values = values;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        Toast.makeText(context, "I'm in View", Toast.LENGTH_SHORT).show();
        view = inflater.inflate(R.layout.cart_item_layout, null);
        TextView textView = view.findViewById(R.id.ProductName);
        TextView price = view.findViewById(R.id.price);
        TextView detail = view.findViewById(R.id.deltail);
        ImageView imageView = view.findViewById(R.id.productIcon);

        textView.setText(values.keySet().toArray()[i].toString());
        price.setText(String.format( "%d", values.get(values.keySet().toArray()[i].toString())));

       db.displayPicture(Product.path,values.keySet().toArray()[i].toString(), imageView, context);
        return view;
    }

}

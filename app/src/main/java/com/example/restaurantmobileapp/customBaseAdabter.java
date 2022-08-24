package com.example.restaurantmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class customBaseAdabter extends BaseAdapter {


    ArrayList<Product> productArrayList;
    LayoutInflater inflater;
    Context context;
    DBModule db;
    public  customBaseAdabter(Context context, ArrayList<Product> productArrayList){
        db = new DBModule();
        this.productArrayList = productArrayList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return productArrayList.toArray().length;
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
        view = inflater.inflate(R.layout.item_layout, null);
        TextView textView = view.findViewById(R.id.personName);
        TextView price = view.findViewById(R.id.mstime);
        TextView detail = view.findViewById(R.id.lastMessage);

        ImageView imageView = view.findViewById(R.id.profile_pic);
        textView.setText(productArrayList.get(i).getName());
        detail.setText(productArrayList.get(i).getDetail());
        price.setText(String.format("%f",productArrayList.get(i).getPrice()));
        db.displayPicture(Product.path,productArrayList.get(i).getName(), imageView, context);
        return view;
    }
}

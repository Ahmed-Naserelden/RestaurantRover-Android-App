package com.example.restaurantmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Product> {
    Context context;
    DBModule db;
    public ListAdapter(Context context, ArrayList<Product> productArrayList){
        super(context, R.layout.item_layout, productArrayList);
        this.context = context;
        db = new DBModule();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product product = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);

        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView productName = convertView.findViewById(R.id.personName);
        TextView detail = convertView.findViewById(R.id.lastMessage);
        TextView price = convertView.findViewById(R.id.mstime);

        // upload image;

        db.displayPicture(Product.path, product.getName(), imageView, this.context);
        productName.setText(product.getName());
        price.setText(String.format("%f", product.getPrice()));
        detail.setText(product.getDetail());

        return super.getView(position, convertView, parent);
    }
}

package com.example.restaurantmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomBaseAdabter extends BaseAdapter implements Filterable {
    ArrayList<Product> productArrayList;
    ArrayList<Product> mOriginalValues;
    LayoutInflater inflater;
    Context context;
    DBModule db;
    public CustomBaseAdabter(Context context, ArrayList<Product> productArrayList){
        db = new DBModule();
        this.productArrayList = productArrayList;
        this.mOriginalValues = productArrayList;
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
        price.setText(String.format("%.2f $",productArrayList.get(i).getPrice()));
        db.displayPicture(Product.path,productArrayList.get(i).getName(), imageView, context);
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter()
        {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results)
            {

                productArrayList = (ArrayList<Product>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<Product> FilteredArrList = new ArrayList<Product>();

                ArrayList<Product> mOriginalValues = null;
                if (mOriginalValues == null)
                {
//                    System.out.println("");
                    mOriginalValues = new ArrayList<Product>(productArrayList); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0)
                {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                }
                else
                {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++)
                    {
                        Product data = mOriginalValues.get(i);
                        if (data.getName().toLowerCase().startsWith(constraint.toString()))
                        {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }

                return results;
            }
        };
        this.productArrayList = this.mOriginalValues;

        return filter;

    }
}

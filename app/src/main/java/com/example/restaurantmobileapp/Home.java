package com.example.restaurantmobileapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener {
    DBModule db;
    ListView listView;
    CustomBaseAdabter customAdabter;
    Intent intent;

    User user;
    boolean bed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = new Intent(this, CartActivity.class);
        findViewById(R.id.food).setOnClickListener(this);
        findViewById(R.id.drinks).setOnClickListener(this);
        findViewById(R.id.seafood).setOnClickListener(this);
        findViewById(R.id.fastfood).setOnClickListener(this);
        findViewById(R.id.salad).setOnClickListener(this);
        findViewById(R.id.dessert).setOnClickListener(this);
        invalidateOptionsMenu();
        db = new DBModule();
        work("cool drink");
    }

    public void work(String type){
        listView = findViewById(R.id.list_viewcart);
        ArrayList<Product> productArrayList = new ArrayList<>();
        db.getDbRef().child("Users/"+db.mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        db.getDbRef().child("Products/"+type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot i : snapshot.getChildren()){
                    Product product = i.getValue(Product.class);
                    productArrayList.add(product);
                }
                customAdabter = new CustomBaseAdabter(getApplicationContext(), productArrayList);
                listView.setAdapter(customAdabter);





                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(Home.this, "product added to Cart", Toast.LENGTH_SHORT).show();
//                        user.addProductToOrder(productArrayList.get(i));
                        db.addProductToOrder(productArrayList.get(i), user, Home.this);
                        return false;
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menuapp, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdabter.getFilter().filter(newText);
                return false;
            }

        });

        bed = true;
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.food:
                work("food");
                break;
            case R.id.drinks:
                work("drink");
                break;
            case R.id.seafood:
                work("sea food");
                break;
            case R.id.salad:
                work("salad");
                break;
            case R.id.fastfood:
                work("fast food");
                break;
            case R.id.dessert:
                work("dessert");
                break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // change name and email corr the current user

        MenuItem item = menu.findItem(R.id.item2);
        if (item.getTitle().equals("Set to 'In bed'")) {
            item.setTitle("Set to 'Out of bed'");
            bed = false;
        } else {
            item.setTitle(db.getmAuth().getCurrentUser().getEmail());
            bed = true;
        }
        MenuItem item1 = menu.findItem(R.id.item1);
        if (item1.getTitle().equals("Set to 'In bed'")) {
            item1.setTitle("Set to 'Out of bed'");
            bed = false;
        } else {

            db.getDbRef().child("Users/"+db.getmAuth().getUid()+"/name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue().toString();
                    item1.setTitle(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            bed = true;
        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.bag:
                startActivity(intent);
                return true;
            case R.id.item3:
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), SignIn.class));
                            }
                        }).create().show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

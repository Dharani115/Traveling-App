package com.android.trip_planning.states;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.trip_planning.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Gujarath extends AppCompatActivity {

    // Variable declarations
    private String userEmail;
    private TextView textView;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    private List<Product> products;
    private ProgressBar progressBar;
    private static  final String BASE_URL = "http://192.168.43.42/travel/state06.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.dashboard_toolbar);
        progressBar = findViewById(R.id.progressbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));

        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(Gujarath.this, 1);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<>();

        getProducts();

    }

    private void getProducts (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String place_name = object.getString("place_name");
                                String state_name = object.getString("state_name");
                                String map = object.getString("map");
                                String description = object.getString("description");
                                String photo = object.getString("photo");

                                Product product = new Product(place_name,state_name, map,description,photo);
                                products.add(product);
                            }

                        }catch (Exception e){
                            Toast.makeText(Gujarath.this,"error",Toast.LENGTH_LONG).show();

                        }

                        mAdapter = new RecyclerAdapter(Gujarath.this,products);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(Gujarath.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(Gujarath.this).add(stringRequest);

    }

}

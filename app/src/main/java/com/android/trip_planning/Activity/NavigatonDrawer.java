package com.android.trip_planning.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.trip_planning.Help;
import com.android.trip_planning.R;
import com.android.trip_planning.states.StateView;
import com.android.trip_planning.top_places.adventure;
import com.android.trip_planning.top_places.beaches;
import com.android.trip_planning.top_places.falls;
import com.android.trip_planning.top_places.hill_stations;
import com.android.trip_planning.top_places.historic_places;
import com.android.trip_planning.top_places.temples;
import com.android.trip_planning.top_places.trucking;
import com.android.trip_planning.top_places.zoos;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class NavigatonDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    ImageView profilePic;
    private TextView userName, userEmail;
    View header;
    RecyclerView rv;
    ViewPager2 vhorizantal;
    int[] imagesbeach = {R.drawable.beach1,
            R.drawable.beach2,
            R.drawable.beach3};
    int[] imagesadventure = {R.drawable.adventure1,
            R.drawable.adventure2,
            R.drawable.adventure3};
    int[] imageshistoric = {R.drawable.historic1,
            R.drawable.historic2,
            R.drawable.historic3};
    int[] imagesfalls = {R.drawable.falls4,
            R.drawable.falls2,
            R.drawable.falls3};
    int[] imageshills = {R.drawable.hills1,
            R.drawable.hills2,
            R.drawable.hills3};
    int[] imagestemple = {R.drawable.temple1,
            R.drawable.temple2,
            R.drawable.temple3};
    int[] imageszoo = {R.drawable.zoo1,
            R.drawable.zoo2,
            R.drawable.zoo3};
    int[] imagestrucking = {R.drawable.trucking11,
            R.drawable.trucking2,
            R.drawable.trucking3};
    TopActivity adapter;
    Button button;
    SliderView myslider;
    int[] mylist = {
            R.drawable.chhattisgarh,
            R.drawable.himachalpradesh,
            R.drawable.maharastra
    };
    private static final String TAG = AccountActivity.class.getSimpleName();
    private static String url = "http://192.168.43.42/travel/readdetails.php";
    String getemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigaton_drawer);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu2);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.getHeaderView(0);
        //mail
        userName = (TextView) header.findViewById(R.id.userName);
        profilePic = (ImageView) header.findViewById(R.id.profilePic);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(SessionManager.NAME);
        getemail = user.get(sessionManager.EMAIL);
        userName.setText(mName);
        //homepage
        myslider = findViewById(R.id.sliderImage);
        Slider_Adapter sliderAdapter = new Slider_Adapter(mylist);
        myslider.setSliderAdapter(sliderAdapter);
        myslider.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        myslider.setSliderTransformAnimation(SliderAnimations.GATETRANSFORMATION);
        myslider.startAutoCycle();

        button = (Button) findViewById(R.id.selectstates);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, StateView.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");
            }
        });

    //top beaches
        vhorizantal = findViewById(R.id.beaches);
        adapter = new TopActivity(imagesbeach);
        Button buttonbeach=findViewById(R.id.viewallbeach);
        buttonbeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, beaches.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");

            }
        });
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        CompositePageTransformer transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        //top adventure
        vhorizantal = findViewById(R.id.Adventureplace);
        Button buttonadv=findViewById(R.id.viewalladventure);
        buttonadv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, adventure.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imagesadventure);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        vhorizantal.setPageTransformer(transformer);

        //top Historic
        vhorizantal = findViewById(R.id.Historic);
        Button buttonhistoric=findViewById(R.id.viewallhistoric);
        buttonhistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, historic_places.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imageshistoric);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        vhorizantal.setPageTransformer(transformer);

        //top Falls
        vhorizantal = findViewById(R.id.falls);
        Button buttonfalls=findViewById(R.id.viewallfalls);
        buttonfalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, falls.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imagesfalls);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        vhorizantal.setPageTransformer(transformer);

        //top Hills
        vhorizantal = findViewById(R.id.hills);
        Button button=findViewById(R.id.viewallhills);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, hill_stations.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imageshills);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);


        vhorizantal.setPageTransformer(transformer);

        //top Temples
        vhorizantal = findViewById(R.id.temples);
        Button buttontemples=findViewById(R.id.viewalltemples);
        buttontemples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, temples.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imagestemple);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        vhorizantal.setPageTransformer(transformer);

        //top Zoos
        vhorizantal = findViewById(R.id.zoos);
        Button buttonzoo=findViewById(R.id.viewallzoos);
        buttonzoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, zoos.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imageszoo);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);


        vhorizantal.setPageTransformer(transformer);

        //top Trucking places
        vhorizantal = findViewById(R.id.trucking);
        Button buttontrucking=findViewById(R.id.viewalltrucking);
        buttontrucking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigatonDrawer.this, trucking.class);
                startActivity(intent);
                customType(NavigatonDrawer.this,"left-to-right");


            }
        });
        adapter = new TopActivity(imagestrucking);
        vhorizantal.setClipToPadding(false);
        vhorizantal.setClipChildren(false);
        vhorizantal.setOffscreenPageLimit(3);
        vhorizantal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        vhorizantal.setAdapter(adapter);
        transformer = new CompositePageTransformer();
        //margin btn page
        transformer.addTransformer(new MarginPageTransformer(28));
        //increase select page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        vhorizantal.setPageTransformer(transformer);

        vhorizantal.setPageTransformer(transformer);
    }
    //getting details
    public void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strphoto = object.getString("photo").trim();

                                    Picasso.get().load(strphoto).into(profilePic);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(NavigatonDrawer.this, "Error on Loading" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(NavigatonDrawer.this, "Error on Loading data " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", getemail);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }



    private void goLoginScreen() {
        Intent intent = new Intent(this, NavigatonDrawer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        customType(NavigatonDrawer.this,"right-to-left");

    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigaton_drawer, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profileIntent = new Intent(NavigatonDrawer.this, NavigatonDrawer.class);
            startActivity(profileIntent);
            customType(NavigatonDrawer.this,"left-to-right");
            finish();

        }
        else if (id == R.id.nav_feedback) {
            Intent profileIntent = new Intent(NavigatonDrawer.this, com.android.trip_planning.feedback.class);
            startActivity(profileIntent);
            customType(NavigatonDrawer.this,"left-to-right");

        }
        else if (id == R.id.nav_settings) {
            Intent profileIntent = new Intent(NavigatonDrawer.this, AccountActivity.class);
            startActivity(profileIntent);
            customType(NavigatonDrawer.this,"left-to-right");

        }
        else if (id == R.id.nav_help) {
            Intent historyIntent;
            historyIntent = new Intent(NavigatonDrawer.this, Help.class);
            startActivity(historyIntent);
            customType(NavigatonDrawer.this,"left-to-right");

        }
        else if (id == R.id.nav_share) {
            Intent share=new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/stores/play/details?id= hi");
            share.setType("text/plain");
            startActivity(share);
            customType(NavigatonDrawer.this,"bottom-to-up");

        }
        else if (id == R.id.nav_logout) {
            Intent intent = new Intent(NavigatonDrawer.this, login.class);
            sessionManager.logout();
            startActivity(intent);
            customType(NavigatonDrawer.this,"fadein-to-fadeout");


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
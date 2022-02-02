package com.android.trip_planning.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.trip_planning.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity implements OnConnectionFailedListener{
    Dialog myDialog;
    private EditText email, password;
    private Button login;
    private TextView register;
    Vibrator v;
    private ProgressBar loading;
    private static String url = "http://192.168.43.42/travel/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sessionManager=new SessionManager(this);
        loading = (ProgressBar) findViewById(R.id.loading);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();
                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    loginn(mEmail, mPassword);
                } else {
                    email.setError("please insert mail");
                    password.setError("please insert password");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    private void loginn(String email, String password) {
        loading.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String photo = object.getString("photo").trim();
                                    String phone = object.getString("phone").trim();
                                    String id = object.getString("id").trim();

                                    sessionManager.createSession(name,email,photo,phone);
                                    Toast.makeText(com.android.trip_planning.Activity.login.this, "Login success" , Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this, NavigatonDrawer.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("email",email);
                                    intent.putExtra("photo",photo);
                                    startActivity(intent);
                                    loading.setVisibility(View.GONE);
                                    finish();
                                }
                            }
                        } catch (JSONException e) {
                            loading.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                            Toast.makeText(com.android.trip_planning.Activity.login.this, "Login error" , Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(com.android.trip_planning.Activity.login.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
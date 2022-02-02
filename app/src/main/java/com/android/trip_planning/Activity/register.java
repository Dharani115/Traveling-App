package com.android.trip_planning.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.trip_planning.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class register extends AppCompatActivity{
private EditText name,email,phone,password,cpassword;
private Button register,login;
private ProgressBar loading;
private static String url="http://192.168.43.42/travel/register.php";
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);
    loading = (ProgressBar) findViewById(R.id.loading);
    name = (EditText) findViewById(R.id.name);
    email = (EditText) findViewById(R.id.email);
    phone = (EditText) findViewById(R.id.phone);
    password = (EditText) findViewById(R.id.password);
    cpassword = (EditText) findViewById(R.id.cpassword);
    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    login = (Button) findViewById(R.id.login);
    register = (Button) findViewById(R.id.register);
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pass = password.getText().toString();
            String cpass = cpassword.getText().toString();
            String eml = email.getText().toString();
            if (name.getText().toString().isEmpty()) {
                name.setError("enter name");
            }
            else if(password.getText().toString().isEmpty())
            {
                password.setError("enter passowrd");
            }
            else if (!pass.equals(cpass))
            {
                cpassword.setError("Password Does not Match");
            }
            else if(email.getText().toString().isEmpty())
            {
                email.setError("enter email");
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {
                email.setError("Enter a valid email");
            }
            else if(phone.getText().toString().isEmpty())
            {
                phone.setError("enter phone");
            }
            else {
                Regist();
            }
        }
    });
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), com.android.trip_planning.Activity.login.class);
            startActivity(intent);
            customType(register.this,"right-to-left");

        }
    });
}
private void Regist() {
    loading.setVisibility(View.VISIBLE);
    final String name = this.name.getText().toString().trim();
    final String email = this.email.getText().toString().trim();
    final String phone = this.phone.getText().toString().trim();
    final String password = this.password.getText().toString().trim();
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject=new JSONObject(response);
                String success=jsonObject.getString("success");
                if (success.equals("2")){
                    Toast.makeText(com.android.trip_planning.Activity.register.this, "Email Already Exist,Please use other email",Toast.LENGTH_SHORT).show();
                }
                else if  (success.equals("1")){
                    Toast.makeText(com.android.trip_planning.Activity.register.this, "Sucessfully Registered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), com.android.trip_planning.Activity.login.class);
                    startActivity(intent);
                    customType(register.this,"left-to-right");

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(com.android.trip_planning.Activity.register.this, "Register Error "+e.toString(),Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                register.setVisibility(View.VISIBLE);
            }
        }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(com.android.trip_planning.Activity.register.this, "Register error",Toast.LENGTH_SHORT).show();
            loading.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String, String>();
            params.put("name",name);
            params.put("password",password);
            params.put("email",email);
            params.put("phone",phone);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
}
}

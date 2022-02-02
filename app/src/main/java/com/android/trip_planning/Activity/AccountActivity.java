package com.android.trip_planning.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.trip_planning.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;


public class AccountActivity extends AppCompatActivity {

    private static final String TAG = AccountActivity.class.getSimpleName();//getting information
    private EditText name,phone;
    private TextView email;
    private ImageView profile;
    private Button update,passwordChange;
    SessionManager sessionManager;
    String getemail;
    private Bitmap bitmap;
    private static String url = "http://192.168.43.42/travel/readdetails.php";
    private static String url_edit = "http://192.168.43.42/travel/editdetails.php";
    private static String upload_url = "http://192.168.43.42/travel/uploadimage.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_setting);




        sessionManager= new SessionManager( this);
        name = (EditText)findViewById(R.id.userName);
        email = (TextView)findViewById(R.id.userEmail);
        phone = (EditText)findViewById(R.id.userPhone);
        update = (Button)findViewById(R.id.update);
//        passwordChange = (Button)findViewById(R.id.updatepassword);
        profile = (ImageView)findViewById(R.id.profilePic);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(SessionManager.NAME);
        String mEmail = user.get(SessionManager.EMAIL);
        String mPhone = user.get(SessionManager.PHONE);
        getemail=user.get(sessionManager.EMAIL);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditDetails();

            }
        });
//        passwordChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AccountActivity.this, passwordchange.class);
//                startActivity(intent);
//            }
//        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                chooseFile();
                chooseImage();

            }
        });
    }

    public void getUserDetail(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i(TAG ,response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("read");

                    if (success.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String strname = object.getString("name").trim();
                            String stremail = object.getString("email").trim();
                            String strphoto = object.getString("photo").trim();
                            String strphone = object.getString("phone").trim();
                            name.setText(strname);
                            email.setText(stremail);
                            phone.setText(strphone);
                            Picasso.get().load(strphoto).into(profile);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(AccountActivity.this, "Error on Loading" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AccountActivity.this, "Error on Loading data "+ error.toString() , Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > params=new HashMap<>();
                params.put("email",getemail);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }
    private void SaveEditDetails(){
        final String name=this.name.getText().toString().trim();
        final String email=this.email.getText().toString().trim();
        final String phone=this.phone.getText().toString().trim();
        final String photo=this.profile.getResources().toString().trim();
        final String editmail=getemail;

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(AccountActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AccountActivity.this, NavigatonDrawer.class);
                                intent.putExtra("name",name);
                                intent.putExtra("photo",photo);
                                startActivity(intent);
                                finish();
                                sessionManager.createSession(name,email,phone,photo);
                            }
                            } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(AccountActivity.this, "Error on Loading" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AccountActivity.this, "Error on Loading data "+ error.toString() , Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > params=new HashMap<>();
                params.put("name",name);
                params.put("phone",phone);
                params.put("email",getemail);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void chooseImage(){
        CropImage.activity().start(AccountActivity.this);

    }

    private void chooseFile(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                Uri resultUri = result.getUri();
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);
                    profile.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                profile.setImageURI(resultUri);
            }
            else if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception e = result.getError();
                Toast.makeText(this, "Possible error " + e, Toast.LENGTH_SHORT).show();
            }
            UploadPicture(getemail,getStringImage(bitmap));

        }
    }

    private void UploadPicture(final String email,final String photo) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, upload_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressDialog.dismiss();
                                Toast.makeText(AccountActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                             e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(AccountActivity.this, "Error on Loading" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AccountActivity.this, "Error on Loading image" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms=new HashMap<>();
                parms.put("email",email);
                parms.put("photo",photo);
                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] imageByteArray=byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);

        return encodedImage;
    }

    public void homeback(View view) {
        Intent intent = new Intent(AccountActivity.this, NavigatonDrawer.class);
        startActivity(intent);
        finish();
        customType(AccountActivity.this,"right-to-left");
    }

    public void email(View view) {
        Toast.makeText(this, "Sorry u can't edit email " , Toast.LENGTH_SHORT).show();
    }
}
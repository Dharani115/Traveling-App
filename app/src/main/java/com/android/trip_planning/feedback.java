package com.android.trip_planning;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.trip_planning.Activity.NavigatonDrawer;
import com.android.trip_planning.Activity.SessionManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static maes.tech.intentanim.CustomIntent.customType;

public class feedback extends AppCompatActivity {
    Button mSendFeedback;
    private static String url = "http://192.168.43.42/travel/feedback.php";
    private EditText mFeedback;
    private TextView name,email;
    private SmileRating mRatingBar;
    SessionManager sessionManager;
    private String mName,mEmail;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        myDialog = new Dialog(this);

        mRatingBar = (SmileRating) findViewById(R.id.ratingBar);
//        TextView mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        ImageView back=findViewById(R.id.stateback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feedback.this, NavigatonDrawer.class);
                startActivity(intent);
                customType(feedback.this,"right-to-left");
            }
        });
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFeedback.getText().toString().isEmpty()) {
                    mFeedback.setError("please enter message");
                } else {
                    send();
                }
            }
        });
        mRatingBar.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        break;
                    case SmileRating.GOOD:
                         Log.i(TAG, "Good");
                         break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        break;
                }
            }
        });
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mName = user.get(SessionManager.NAME);
        mEmail = user.get(SessionManager.EMAIL);
    }
    private void send() {
        final String myname = mName;
        final String myemail =mEmail;
        final String myfeedback = this.mFeedback.getText().toString().trim();
        final String star=String.valueOf(mRatingBar.getRating());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {

                                Toast.makeText(feedback.this, "Thanks for the feedback", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(feedback.this, NavigatonDrawer.class);
                                startActivity(intent);
                                customType(feedback.this,"right-to-left");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(feedback.this, "Error while submiting ", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(feedback.this, "error while submiting", Toast.LENGTH_SHORT).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", mName);
                params.put("email", mEmail);
                params.put("feedback", myfeedback);
                params.put("star", star);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

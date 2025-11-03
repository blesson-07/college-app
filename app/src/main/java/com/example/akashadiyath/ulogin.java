package com.example.akashadiyath;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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


public class ulogin extends AppCompatActivity {
    EditText email,password;
    Button button;
    TextView login;
    String semail,spassword,status,message,url=config.baseurl+"login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ulogin);
        email=findViewById(R.id.uemail);
        password=findViewById(R.id.upassword);
        button=findViewById(R.id.ubutton);
        login=findViewById(R.id.ulogin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent aka=new Intent(ulogin.this,register.class);
            startActivity(aka);

        }
    });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lakashandiyath();

            }
        });
    }

    private void lakashandiyath() {
        semail = email.getText().toString();
        spassword = password.getText().toString();
        if (TextUtils.isEmpty(semail)) {
            email.requestFocus();
            email.setError("error ahda mone");
            return;
        }

        if (TextUtils.isEmpty(spassword)) {
            password.requestFocus();
            password.setError("error ahda mone");
            return;

        }


        StringRequest StringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("status");
                            message = c.getString("message");
                            checklogin();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //run cheyikkumbo error indo ennu nokkan
                        Toast.makeText(ulogin.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", semail);
                params.put("password", spassword);
                return params;
            }


        };

        //string reqt ne execute cheyan aanu requestqueue
        Volley volley =  null;
        RequestQueue requestQueue = volley.newRequestQueue(this);
        requestQueue.add(StringRequest);
    }


    private void checklogin() {
        if (status.equals("0")){
            Toast.makeText(this, "Invalied", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "login  successfully", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(ulogin.this,home.class);
            startActivity(i);
            finish();
        }

    }
}


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

public class register extends AppCompatActivity {
 EditText email,password,name,phonenumber;
 Button button;
 String semail,spassword,sname,sphonenumber,status,message,url=config.baseurl+"register.php";
 TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        phonenumber=findViewById(R.id.phonenumber);
        button=findViewById(R.id.button);
        login=findViewById(R.id.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aka=new Intent(register.this,ulogin.class);
                startActivity(aka);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                akashandiyath();

            }
        });
    }
            private void akashandiyath() {
               semail=email.getText().toString();
               spassword=password.getText().toString();
               sphonenumber=phonenumber.getText().toString();
               sname=name.getText().toString();
               if(TextUtils.isEmpty(semail)){
                   email.requestFocus();
                   email.setError("error ahda mone");
                   return;
               }
                if(TextUtils.isEmpty(sname)){
                    name.requestFocus();
                    name.setError("error ahda mone");
                    return;
                }
                if(TextUtils.isEmpty(spassword)){
                   password.requestFocus();
                    password.setError("error ahda mone");
                    return;
                }
                if(TextUtils.isEmpty(sphonenumber)){
                    phonenumber.requestFocus();
                    phonenumber.setError("error ahda mone");
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
                                Toast.makeText(register.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                            }

                        }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", sname);
                        params.put("email", semail);
                        params.put("phonenumber", sphonenumber);
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
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(register.this,ulogin.class);
            startActivity(i);
            finish();
        }

    }
}




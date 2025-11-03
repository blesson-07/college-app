package com.example.akashadiyath;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class productfullactivity extends AppCompatActivity {
    TextView pname, price, fabric, des, pattern, color, packag, text, fname, wish, returntime;
    ImageView image;
    Button wishl, buy, cart;
    Button btndec, btnincr;
    int mininteger = 0;
    int calculate;
    RatingBar rating;
    TextView displayInteger;
    int p, q;
    TextView tamount;
    String url = config.baseurl + "buy.php",iid;
    String url2 = config.baseurl + "wishlist.php";
    String url3 = config.baseurl + "cartproduct.php";
    String display, uphone, total, am, status, message;
    String pname1, price1, fabric1, material1, pattern1, returntime1, rating1, color1, packag1, image1, sid, uname, uid, text1, shop1, id, sfname, fid, flocation, fphone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productfullactivity);

        // Initialize views
        pname = findViewById(R.id.nameb1);
        cart = findViewById(R.id.cart5b1);
        price = findViewById(R.id.priceb1);
        des = findViewById(R.id.des);
        btnincr = findViewById(R.id.btnincb1);
        btndec = findViewById(R.id.btndecb1);
        displayInteger = findViewById(R.id.valueb1);
        tamount = findViewById(R.id.amountb1);
//        pattern = findViewById(R.id.patternb1);
        color = findViewById(R.id.colorb1);
//        fname = findViewById(R.id.shopb1);
        returntime = findViewById(R.id.returnb1);
//        packag = findViewById(R.id.packagb1);
        image = findViewById(R.id.image1b1);
        buy = findViewById(R.id.buyb1);
        wishl=findViewById(R.id.wwish);

        // Increment button click listener
        btnincr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mininteger++;
                display(mininteger);
            }
        });

        // Decrement button click listener
        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mininteger > 0) {
                    mininteger--;
                    display(mininteger);
                }
            }
        });

        // Increment button click listener
        btnincr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mininteger++;
                display1(mininteger);
            }
        });

        // Decrement button click listener
        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mininteger > 0) {
                    mininteger--;
                    display1(mininteger);
                }
            }
        });

        // Buy button click listener
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (displayInteger.getText().toString().equals("0"))
                    Toast.makeText(productfullactivity.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
                else {
                    buys();
                }
            }
        });
        wishl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wishl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFC0CB"))); // Pink color

                //   wishhhhh();
                wishl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wishl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE)); // White color
                    }
                }, 2000); // Reset color after 2 seconds
            }
        });


        // Get user details from SessionManagerUser
//        HashMap<String, String> show = new HashMap<>(new SessionManagerUser(ProductFullActivity.this).getUserDetails());
//        uid = show.get("id");
//        uname = show.get("username");
//        uphone = show.get("phone");

        // Get product details from the Intent
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pname1 = intent.getStringExtra("productname");
        pname.setText(pname1);
        price1 = intent.getStringExtra("price");
        price.setText(price1);
        sfname = intent.getStringExtra("description");
        des.setText("Product by " + sfname);
        image1 = intent.getStringExtra("image");
        Picasso.get().load(config.imageurl + image1).into(image);

//        material1 = intent.getStringExtra("description");
//        material.setText(material1);
//        pattern1 = intent.getStringExtra("shopcategory");
//        pattern.setText(pattern1);
//
//        fid = intent.getStringExtra("id");
//        color1 = intent.getStringExtra("shopname");
//        //    flocation = intent.getStringExtra("location");
//        //   fphone = intent.getStringExtra("shopnumber");
    }

    // Function to handle buying a product
    private void buys() {
        price1 = price.getText().toString();
        if (TextUtils.isEmpty(price1)) {
            price.requestFocus();
            price.setError("ENTER QUANTITY");
            return;
        }

        StringRequest st = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    Toast.makeText(productfullactivity.this, "DATA IS NOT LOADED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(productfullactivity.this, "BUYING PRODUCT", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ProductFullActivity.this, paymentmethod.class);
//                    intent.putExtra("totalprice",am);
////                    intent.putExtra("id",iid);
//
//
////                    intent.putExtra("shopname",sfname);
////                    intent.putExtra("shoplocation",flocation);
////                    intent.putExtra("shopid",fid);
////                    intent.putExtra("username", uname);
//                    intent.putExtra("userid",uid);
////                    intent.putExtra("proid",id);
//                    startActivity(intent);
//                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> user = new HashMap<>();
                user.put("id", uid);
                user.put("username", uname);
                user.put("usernumber", uphone);
                user.put("productname", pname1);
                user.put("productprice", price1);
                user.put("shopid", fid);
                user.put("shopname", color1);
                //  user.put("location", flocation);
                // user.put("shopnumber", fphone);
                user.put("totalquantity", display);
                user.put("totalprice", am);
                return user;
            }
        };
        RequestQueue ex = Volley.newRequestQueue(this);
        ex.add(st);
    }

    // Method to decrement the display
    private void decrement(int number) {
        displayInteger.setText("" + number);
    }

    // Method to display the quantity and total amount
    private void display(int number) {
        displayInteger.setText("" + number);
        price1 = price.getText().toString();
        total = displayInteger.getText().toString();
        p = Integer.parseInt(price1);
        q = Integer.parseInt(total);
        calculate = p * q;
        tamount.setText(String.valueOf(calculate));
        display = displayInteger.getText().toString();
        am = tamount.getText().toString();
    }
//    private void wishhhhh() {
//        StringRequest st = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    status = jsonObject.getString("status");
//                    message = jsonObject.getString("message");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if ("0".equals(status)) {
//                    Toast.makeText(Productfullactivity.this, "DATA IS NOT LOADED", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Productfullactivity.this, " Add Wishlist", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Productfullactivity.this, Home.class);
//                    startActivity(intent);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Handle error
//            }
//        }) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> user = new HashMap<>();
//                user.put("userid", uid);
//                user.put("username", uname);
//                user.put("usernumber", uphone);
//                user.put("productname", pname1);
//                user.put("productprice", price1);
//                user.put("shopid", fid);
//                user.put("shopname", color1);
//                //  user.put("location", flocation);
//                // user.put("shopnumber", fphone);
    ////                user.put("totalquantity", display);
    ////                user.put("totalprice", am);
//                return user;
//            }
//        };
//        RequestQueue ex = Volley.newRequestQueue(this);
//        ex.add(st);
//    }

    // Method to decrement the display
    private void decrement1(int number) {
        displayInteger.setText("" + number);
    }

    // Method to display the quantity and total amount
    private void display1(int number) {
        displayInteger.setText("" + number);
        price1 = price.getText().toString();
        total = displayInteger.getText().toString();
        p = Integer.parseInt(price1);
        q = Integer.parseInt(total);
        calculate = p * q;
        tamount.setText(String.valueOf(calculate));
        display = displayInteger.getText().toString();
        am = tamount.getText().toString();
    }
}
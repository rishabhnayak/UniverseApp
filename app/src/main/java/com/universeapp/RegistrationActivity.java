package com.universeapp;

import static com.universeapp.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.libizo.CustomEditText;
import com.universeapp.Model.ResponseMain;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    CustomEditText username,email,password;
    String s_username,s_email,s_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        username= findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void register(View view) {
      s_username = username.getText().toString();
      s_email = email.getText().toString();
      s_password=password.getText().toString();

      if (s_username.length()>0){
          if (s_email.length()>0){
              if (password.length()>9){
                  registration();
              }else {
                  password.setError("Password must contain more than 8 characters");
              }
          }else {
              email.setError("email must not empty");
          }
      }else {
          username.setError("Username must not empty");
      }

    }

    public void registration(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,BASE_URL+"registration", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Registration response is : "+response);
                Gson gson = new Gson();
                ResponseMain res = gson.fromJson(response,ResponseMain.class);

                if (res.getError().equals("false")) {
                    Toast.makeText(getApplicationContext(), "Registration Success Please login", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",s_username);
                params.put("email",s_email);
                params.put("password",s_password);
                return params;
            }
        };
        queue.add(sr);
    }

}
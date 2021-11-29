package com.universeapp;

import static com.universeapp.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
import com.universeapp.Model.LoginResponse;
import com.universeapp.Model.ResponseMain;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    CustomEditText email,password;
    String s_email,s_password;
   public static SharedPreferences prefs;
   public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        prefs = getSharedPreferences("mypref", MODE_PRIVATE);
       editor = getSharedPreferences("mypref", MODE_PRIVATE).edit();
    }


    public void login(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,BASE_URL+"login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Login response is : "+response);
                Gson gson = new Gson();
                LoginResponse res = gson.fromJson(response,LoginResponse.class);

                try {

                    if (res.getId().length()>0){
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        editor.putString("id",res.getId());
                        editor.putString("name",res.getName());
                        editor.putString("email",res.getEmail());
                        editor.commit();
                        editor.apply();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Invalid login details", Toast.LENGTH_SHORT).show();
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
                params.put("email",s_email);
                params.put("password",s_password);
                return params;
            }
        };
        queue.add(sr);
    }

    public void loginmain(View view) {
        s_email = email.getText().toString();
        s_password=password.getText().toString();
        if (s_email.length()>0){
            if (password.length()>9){
                login();
            }else {
                password.setError("Password must contain more than 8 characters");
            }
        }else {
            email.setError("email must not empty");
        }
    }
}
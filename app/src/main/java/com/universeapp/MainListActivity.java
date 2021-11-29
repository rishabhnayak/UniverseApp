package com.universeapp;

import static com.universeapp.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.universeapp.Adapter.MainListAdapter;
import com.universeapp.Model.LoginResponse;
import com.universeapp.Model.MainListModel;

import java.util.HashMap;
import java.util.Map;

public class MainListActivity extends AppCompatActivity {
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        getSupportActionBar().hide();
        recycler=findViewById(R.id.main_list_recycler);
        //vertical list
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //Grid list
      //  recycler.setLayoutManager(new GridLayoutManager(this,2));
        get_mainlist();
    }


    public void get_mainlist(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,BASE_URL+"get_mainlist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("get_mainlist response is : "+response);
                Gson gson = new Gson();

                MainListModel[] list = gson.fromJson(response,MainListModel[].class);

                MainListAdapter adapter = new MainListAdapter(getApplicationContext(),list);
                recycler.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
//                params.put("email",s_email);
//                params.put("password",s_password);
                return params;
            }
        };
        queue.add(sr);
    }
}
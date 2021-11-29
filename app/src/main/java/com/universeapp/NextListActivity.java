package com.universeapp;

import static com.universeapp.Constant.BASE_URL;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.universeapp.Adapter.MainListAdapter;
import com.universeapp.Adapter.NextListAdapter;
import com.universeapp.Model.MainListModel;

import java.util.HashMap;
import java.util.Map;

public class NextListActivity extends AppCompatActivity {
    RecyclerView recycler;
    String mainlistid;
    String maintitle;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_list);
        getSupportActionBar().hide();
        title = findViewById(R.id.title);
        mainlistid = getIntent().getStringExtra("id");
        maintitle = getIntent().getStringExtra("title");

        title.setText(maintitle);


        recycler=findViewById(R.id.next_list_recycler);
        //vertical list
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //Grid list
      //  recycler.setLayoutManager(new GridLayoutManager(this,2));
        get_nextlist();
    }


    public void get_nextlist(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,BASE_URL+"get_nextlist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("get_nextlist response is : "+response);
                Gson gson = new Gson();

                MainListModel[] list = gson.fromJson(response,MainListModel[].class);

                NextListAdapter adapter = new NextListAdapter(getApplicationContext(),list);
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
                params.put("maintitle",maintitle);
//                params.put("password",s_password);
                return params;
            }
        };
        queue.add(sr);
    }
}
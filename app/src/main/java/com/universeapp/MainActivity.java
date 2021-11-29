package com.universeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("mypref", MODE_PRIVATE);
        editor = getSharedPreferences("mypref", MODE_PRIVATE).edit();
        getSupportActionBar().hide();
try {

    if (!prefs.getString("id","0").equals(0)){
        startActivity(new Intent(getApplicationContext(),MainListActivity.class));
    }

}catch (Exception e){
    Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
}

    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }
}
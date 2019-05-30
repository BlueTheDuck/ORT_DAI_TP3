package com.ducklings_corp.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchActivity(View view) {
        int id;
        Intent intent;

        id = view.getId();
        intent = null;

        switch (id) {
            case R.id.show_categories:
                intent = new Intent(MainActivity.this,Categories.class);
                break;
            case R.id.search_by_name:
                intent = new Intent(MainActivity.this,GetName.class);
                break;
            case R.id.search_by_place:
                intent = new Intent(MainActivity.this,GetLocation.class);
                break;
            default:
                Log.d("Activity","Unimplemented activity");
        }

        startActivity(intent);
    }
}

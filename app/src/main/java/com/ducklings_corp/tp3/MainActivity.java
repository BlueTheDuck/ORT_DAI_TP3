package com.ducklings_corp.tp3;

import android.app.Activity;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL api;
        HttpURLConnection connection;
        try {
            api = new URL("http://epok.buenosaires.gob.ar/getCategorias");
            connection = (HttpURLConnection) api.openConnection();
        } catch (Exception e) {

        }

        pri
    }
}

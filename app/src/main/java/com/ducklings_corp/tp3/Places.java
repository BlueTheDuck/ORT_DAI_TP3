package com.ducklings_corp.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Places extends Activity {
    String place;
    String baseUrl = "http://epok.buenosaires.gob.ar/buscar/?texto=%s";
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        place = this.getIntent().getExtras().getString("selected");


    }

    private class GetPlaces extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL(String.format(baseUrl,place));
                cnx = (HttpURLConnection) url.openConnection();
                Log.d("EPOK","Cnx");
                if(cnx.getResponseCode()==200) {
                    InputStream body;
                    InputStreamReader reader;

                    body = cnx.getInputStream();
                    reader = new InputStreamReader(body,"UTF-8");
                    streamToJson(reader);
                }
            } catch (Exception e) {
                Log.d("EPOK","Error: "+e.getMessage());
            }
            return null;
        }
    }

    private void streamToJson(InputStreamReader stream) {
        JsonReader jsonReader;

        jsonReader = new JsonReader(stream);
        try {
            jsonReader.beginObject();
            
            jsonReader.endObject();
        } catch (Exception e) {
            Log.d("Json","Error parsing");
        }
    }
}

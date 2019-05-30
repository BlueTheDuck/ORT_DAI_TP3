package com.ducklings_corp.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PlacesByLocation extends Activity {
    float coordX, coordY;
    int rad;
    String baseUrl = "http://epok.buenosaires.gob.ar/reverseGeocoderLugares/?x=%s&y=%s&radio=%s&categorias=";
    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_by_location);

        Bundle paqutovich;
        paqutovich = this.getIntent().getExtras();

        coordX = paqutovich.getFloat("coordX");
        coordY = paqutovich.getFloat("coordY");
        rad = paqutovich.getInt("rad");

        names = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        (new PlacesByLocation.GetPlacesByLocation()).execute();
    }

    private class GetPlacesByLocation extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL(String.format(baseUrl, coordX,coordY,rad));
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
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            ListView listPlaces;
            listPlaces = findViewById(R.id.places_by_location);
            listPlaces.setAdapter(adapter);
        }
    }
    private void streamToJson(InputStreamReader stream) {
        JsonReader jsonReader;
        int totalFull;

        jsonReader = new JsonReader(stream);
        try {
            jsonReader.beginObject();

            jsonReader.nextName();//totalFull
            totalFull = jsonReader.nextInt();
            if(totalFull==0) {
                noPlacesFound();
                return;
            }

            jsonReader.nextName();//clasesEncontradas
            jsonReader.skipValue();

            jsonReader.nextName();//instancias

            jsonReader.beginArray();
            for(int i=0;i<totalFull;i++) {
                jsonReader.beginObject();//one instance
                jsonReader.nextName();//headline
                jsonReader.skipValue();
                jsonReader.nextName();//nombre
                names.add(jsonReader.nextString());//<---
                jsonReader.nextName();//claseId
                jsonReader.skipValue();
                jsonReader.nextName();//clase
                jsonReader.skipValue();
                jsonReader.nextName();//id
                jsonReader.skipValue();
                jsonReader.endObject();
            }
            jsonReader.endArray();

            jsonReader.nextName();
            jsonReader.skipValue();

            jsonReader.endObject();
        } catch (Exception e) {
            Log.d("Json","Error: "+e.getMessage());
        }
    }
    void noPlacesFound() {
        TextView categoriesError;

        categoriesError = findViewById(R.id.places_by_location_error);
        categoriesError.setVisibility(View.VISIBLE);

        Log.d("Json","No places found");
    }
}

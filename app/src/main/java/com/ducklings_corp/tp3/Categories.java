package com.ducklings_corp.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Categories extends Activity {
    ArrayList<String> categories;
    ArrayAdapter<String> categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        categories = new ArrayList<>();
        categoriesAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,categories);

        (new GetCategories()).execute();


    }

    private class GetCategories extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL("http://epok.buenosaires.gob.ar/getCategorias/");
                cnx = (HttpURLConnection) url.openConnection();
                Log.d("EPOK", "Cnx");
                if (cnx.getResponseCode() == 200) {
                    InputStream body;
                    InputStreamReader reader;

                    Log.d("EPOK", "200 OK");

                    body = cnx.getInputStream();
                    reader = new InputStreamReader(body, "UTF-8");
                    streamToJson(reader);
                } else {
                    Log.d("EPOK", "Non 200 code");
                }
                cnx.disconnect();
            } catch (Exception error) {
                Log.d ("EPOK",error.getMessage());
            }
            return null;
        }
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            ListView listCategories;
            listCategories = findViewById(R.id.categories);
            listCategories.setAdapter(categoriesAdapter);
        }
    }

    private void streamToJson(InputStreamReader stream) {
        JsonReader jsonReader = new JsonReader(stream);
        int categoriesDownloaded = -1;
        try {
            jsonReader.beginObject();

            jsonReader.nextName();//cantidad_de_categorias
            categoriesDownloaded = jsonReader.nextInt();
            Log.d("EPOK",String.format("Categories downloaded: %s",categoriesDownloaded));

            jsonReader.nextName();//categorias
            jsonReader.beginArray();
            for(int i=0;i<categoriesDownloaded;i++) {
                jsonReader.beginObject();
                //Log.d("Json",jsonReader.nextName());
                while (jsonReader.hasNext()) {
                    if(jsonReader.nextName()=="nombre") {
                        categories.add(jsonReader.nextString());
                        Log.d("Json","Storing name");
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
            jsonReader.endObject();
            Log.d("Json","Finished parsing");
        } catch(Exception e) {
            Log.d("Json stream reader",e.toString());
        }
    }
}

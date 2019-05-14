package com.ducklings_corp.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
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
    }

    private class AsyncTaskJson extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL("http://epok.buenosaires.gob.ar/getCategorias");
                cnx = (HttpURLConnection) url.openConnection();
                Log.d("AccesoAPI", "Cnx");
                if (cnx.getResponseCode() == 200) {
                    Log.d("AccesoAPI", "200 OK");

                    InputStream body;
                    InputStreamReader reader;

                    body = cnx.getInputStream();
                    reader = new InputStreamReader(body, "UTF-8");
                    streamToJson(reader);
                } else {
                    Log.d("AccesoAPI", "Error en la conexión");
                }
                cnx.disconnect();
            }catch (Exception error){
                Log.d ("AccesoAPI","Hubo un error al conectarme:"+error.getMessage());
            }
            return null;
        }
        protected Void onPostExecute(Void void) {
            super.onPostExecute(void);

        }
    }

    private void streamToJson(InputStreamReader streamReader) {

    }
}

package com.ducklings_corp.tp3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GetName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);
    }

    public void searchByName(View view) {
        EditText inputName;
        String toSearch;
        Bundle paquetovich;
        Intent activity_Places;

        inputName = findViewById(R.id.inputName);
        toSearch = inputName.getText().toString();


        paquetovich = new Bundle();
        paquetovich.putString("selected", toSearch);

        activity_Places = new Intent(GetName.this, Places.class);
        activity_Places.putExtras(paquetovich);

        startActivity(activity_Places);
    }
}

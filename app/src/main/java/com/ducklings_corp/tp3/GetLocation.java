package com.ducklings_corp.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class GetLocation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
    }
    public void searchByPlace(View view) {
        EditText inputX, inputY;
        SeekBar inputRad;
        float coordX, coordY;
        int rad;

        inputX = findViewById(R.id.coord_x);
        inputY = findViewById(R.id.coord_y);
        inputRad = findViewById(R.id.radio);

        coordX = Float.parseFloat(inputX.getText().toString());
        coordY = Float.parseFloat(inputY.getText().toString());
        rad = inputRad.getProgress();

        goToList(coordX,coordY,rad);
    }
    private void goToList(float coordX,float coordY, int rad) {
        Bundle paquetovich;
        Intent intent;

        paquetovich = new Bundle();

        paquetovich.putFloat("X",coordX);
        paquetovich.putFloat("Y",coordY);
        paquetovich.putInt("rad",rad);
        paquetovich.putString("selected",getIntent().getExtras().getString("selected"));

        intent = new Intent(GetLocation.this,PlacesByLocation.class);
        intent.putExtras(paquetovich);

        startActivity(intent);
    }
}

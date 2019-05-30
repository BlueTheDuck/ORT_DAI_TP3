package com.ducklings_corp.tp3;

import android.app.Activity;
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
        Float coordX, coordY;
        int rad;

        inputX = findViewById(R.id.coord_x);
        inputY = findViewById(R.id.coord_y);
        inputRad = findViewById(R.id.radio);

        coordX = Float.parseFloat(inputX.getText().toString());
        coordY = Float.parseFloat(inputY.getText().toString());
        rad = inputRad.getProgress();

        
    }

}

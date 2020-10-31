package com.example.oblopgave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SingleBikeActivity extends AppCompatActivity {
    public static final String BICYCLE = "BICYCLE";
    private Bicycle currentBicycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bike);

        Intent intent = getIntent();
        currentBicycle = (Bicycle) intent.getSerializableExtra(BICYCLE);

        TextView idField = findViewById(R.id.infoId);
        TextView frameNumberField = findViewById(R.id.infoFrameNumber);
        TextView kindOfBicycleField = findViewById(R.id.infoKindOfBicycle);
        TextView brandField = findViewById(R.id.infoBrand);
        TextView colorsField = findViewById(R.id.infoColors);
        TextView placeField = findViewById(R.id.infoPlace);
        TextView dateField = findViewById(R.id.infoDate);
        TextView userIdField = findViewById(R.id.infoUserId);
        TextView missingFoundField = findViewById(R.id.infoMissingFound);

        Toast.makeText(getBaseContext(), currentBicycle.toString(), Toast.LENGTH_SHORT).show();

        idField.setText(currentBicycle.getId().toString());
        frameNumberField.setText(currentBicycle.getFrameNumber().toString());
        kindOfBicycleField.setText(currentBicycle.getKindOfBicycle().toString());
        brandField.setText(currentBicycle.getBrand().toString());
        colorsField.setText(currentBicycle.getColors().toString());
        placeField.setText(currentBicycle.getPlace().toString());
        dateField.setText(currentBicycle.getDate().toString());
        userIdField.setText(currentBicycle.getUserId().toString());
        missingFoundField.setText(currentBicycle.getMissingFound().toString());


    }
}
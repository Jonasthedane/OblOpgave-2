package com.example.oblopgave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBicycle extends AppCompatActivity {
    EditText inputFrameNumber;
    EditText inputKindOfBicycle;
    EditText inputBrand;
    EditText inputColors;
    EditText inputPlace;
    EditText inputMissingFound;
    String firebaseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bicycle);

        inputFrameNumber = findViewById(R.id.addFrameNumber);
         inputKindOfBicycle = findViewById(R.id.addKindOfBicycle);
         inputBrand = findViewById(R.id.addBrand);
         inputColors = findViewById(R.id.addColors);
         inputPlace = findViewById(R.id.addPlace);
         inputMissingFound = findViewById(R.id.addMissingFound);

       firebaseId =  FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    public void AddBike(View view) {

        AddBikeToRest(inputFrameNumber.getText().toString(), inputKindOfBicycle.getText().toString(), inputBrand.getText().toString(), inputColors.getText().toString(), inputPlace.getText().toString(), inputMissingFound.getText().toString(), firebaseId) ;
    }

    public void AddBikeToRest(String frameNumber, String kindOfBicycle, String brand, String colors, String place, String missingFound, String firebaseUserId) {
        Bicycle bikeToAdd = new Bicycle(frameNumber, kindOfBicycle, brand, colors, place, missingFound, firebaseUserId);

        Call<Bicycle> callAddBike = ApiUtils.getBicycleService().postBicycle(bikeToAdd);
        callAddBike.enqueue(new Callback<Bicycle>() {
            @Override
            public void onResponse(Call<Bicycle> call, Response<Bicycle> response) {
                Toast.makeText(AddBicycle.this, bikeToAdd.toString(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    Toast.makeText(AddBicycle.this, "Bike added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddBicycle.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Bicycle> call, Throwable t) {

            }
        });
    }


    public void GetUser(String idFirebase) {


        Call<Users> callSingleUser = ApiUtils.getBicycleService().getOneUser(firebaseId);
        callSingleUser.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()) {
                    Users userToReturn = response.body();

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }

}
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

        //AddBikeToRest(inputFrameNumber.getText().toString(), inputKindOfBicycle.getText().toString(), inputBrand.getText().toString(), inputColors.getText().toString(), inputPlace.getText().toString(), inputMissingFound.getText().toString(), firebaseId) ;
        GetUserAndPostBicycle(firebaseId);
    }

    public void AddBikeToRest(Bicycle b) {

        Call<Bicycle> callAddBike = ApiUtils.getBicycleService().postBicycle(b);
        callAddBike.enqueue(new Callback<Bicycle>() {
            @Override
            public void onResponse(Call<Bicycle> call, Response<Bicycle> response) {
                Toast.makeText(AddBicycle.this, b.toString(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    //Toast.makeText(AddBicycle.this, "Bike added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddBicycle.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Bicycle> call, Throwable t) {

            }
        });
    }


    public void GetUserAndPostBicycle(String idFirebase) {


        Call<Users> callSingleUser = ApiUtils.getBicycleService().getOneUser(firebaseId);
        callSingleUser.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        Bicycle bike = new Bicycle(inputFrameNumber.getText().toString(),
                                inputKindOfBicycle.getText().toString(),
                                inputBrand.getText().toString(),
                                inputColors.getText().toString(),
                                inputPlace.getText().toString(),
                                inputMissingFound.getText().toString(),
                                firebaseId,
                                response.body().getName(),
                                response.body().getPhone());
                        AddBikeToRest(bike);
                        //Toast.makeText(AddBicycle.this, "Bike added", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(AddBicycle.this, "Bike broke", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
        //return userToReturn;

    }

}
package com.example.oblopgave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Index extends AppCompatActivity {
private static TextView messageView;
private static final String  LOG_TAG = "MYBIKES";
private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    protected void onStart() {
        super.onStart();

        GetBikes();

    }

    public void GetBikes() {
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>>  getAllBicyclesCall = bicycleService.getAllBicycles();


        getAllBicyclesCall.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Call<List<Bicycle>> call, Response<List<Bicycle>> response) {
                Log.d(LOG_TAG, response.raw().toString());

                if (response.isSuccessful()) {
                    List<Bicycle> allBicycles = response.body();
                    Log.d(LOG_TAG, allBicycles.toString());
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                }
            }

            @Override
            public void onFailure(Call<List<Bicycle>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }


    public void SignOut(View view) {
        mAuth.getInstance().signOut();
        Intent intent = new Intent(Index.this, MainActivity.class);
        startActivity(intent);
    }
}
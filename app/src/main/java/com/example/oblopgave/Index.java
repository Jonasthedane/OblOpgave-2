package com.example.oblopgave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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



    }

    public void GetBikes(View view) {
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>>  getAllBicyclesCall = bicycleService.getAllBicycles();


        getAllBicyclesCall.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Call<List<Bicycle>> call, Response<List<Bicycle>> response) {
                Log.d(LOG_TAG, response.raw().toString());

                if (response.isSuccessful()) {
                    List<Bicycle> allBicycles = response.body();
                    Log.d(LOG_TAG, allBicycles.toString());
                    populateRecyclerView(allBicycles);
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

    private void populateRecyclerView(List<Bicycle> allBicycles) {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter<Bicycle> adapter = new RecyclerViewSimpleAdapter<>(allBicycles);
        recyclerView.setAdapter(adapter);

    }

    public void GetFoundBicycles(View view) {
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>>  getAllFoundBikesCall = bicycleService.getAllFoundBikes();


        getAllFoundBikesCall.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Call<List<Bicycle>> call, Response<List<Bicycle>> response) {
                Log.d(LOG_TAG, response.raw().toString());

                if (response.isSuccessful()) {
                    List<Bicycle> allBicycles = response.body();
                    Log.d(LOG_TAG, allBicycles.toString());
                    populateRecyclerView(allBicycles);
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

    public void GetMissingBicycles(View view) {
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>>  getAllMissingBikesCall = bicycleService.getAllMissingBikes();


        getAllMissingBikesCall.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Call<List<Bicycle>> call, Response<List<Bicycle>> response) {
                Log.d(LOG_TAG, response.raw().toString());

                if (response.isSuccessful()) {
                    List<Bicycle> allBicycles = response.body();
                    Log.d(LOG_TAG, allBicycles.toString());
                    populateRecyclerView(allBicycles);
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
}
package com.example.oblopgave;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BicycleList extends AppCompatActivity {

    private static TextView messageView;
    private static final String  LOG_TAG = "MYBIKES";
    private FirebaseAuth mAuth;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BicycleList.this, AddBicycle.class);
                startActivity(intent);
            }
        });

        RecyclerView r1 = findViewById(R.id.MainRecyclerView);

        r1.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                //return super.onInterceptTouchEvent(rv, e);
                return gestureDetector.onTouchEvent(e);
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                super.onTouchEvent(rv, e);
            }
        });

        gestureDetector = new GestureDetector(getBaseContext(), new GestureDetector.SimpleOnGestureListener() {

//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//                                    float distanceY) {
//                return DoIt(e1, e2);
//            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return DoIt(e1, e2);
            }

            private boolean DoIt(MotionEvent e1, MotionEvent e2) {
                float horizontalDistance = Math.abs(e1.getX() - e2.getX());
                float verticalDistance = Math.abs(e1.getY() - e2.getY());
                if (horizontalDistance > verticalDistance) {
                    if (e1.getX() < e2.getX()) {
                        Log.d(LOG_TAG, "right");
                    } else {
                        Log.d(LOG_TAG, "left");
                    }
                    boolean leftMovement = e1.getX() < e2.getX();
                    if (leftMovement) {
                        finish();
                    }
                } else {
                    if (e1.getY() < e2.getY()) {
                        Log.d(LOG_TAG, "down");
                        GetBikesREST();
                    } else {
                        Log.d(LOG_TAG, "up");
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                if (mAuth.getInstance().getCurrentUser() == null) {
                    finish();
                } else {
                    mAuth.getInstance().signOut();
                    finish();
                }
                return true;


            default:

                return super.onOptionsItemSelected(item);

        }
    }


    public void GetBikes(View view) {
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>> getAllBicyclesCall = bicycleService.getAllBicycles();


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

    public void GetBikesREST(){
        BicycleService bicycleService = ApiUtils.getBicycleService();
        Call<List<Bicycle>> getAllBicyclesCall = bicycleService.getAllBicycles();


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
        if (mAuth.getInstance().getCurrentUser() == null) {
            finish();
        } else {
            mAuth.getInstance().signOut();
            finish();
        }
    }

    private void populateRecyclerView(List<Bicycle> allBicycles) {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter<Bicycle> adapter = new RecyclerViewSimpleAdapter<>(allBicycles);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Bicycle bicycle = (Bicycle) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(this, SingleBikeActivityKotlin.class);
            intent.putExtra(SingleBikeActivityKotlin.BICYCLE, bicycle);
            Log.d(LOG_TAG, "putExtra " + bicycle.toString());
            startActivity(intent);
        });

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

    public void GoToAddBicycle(View view) {
        Intent intent = new Intent(BicycleList.this, AddBicycle.class);
        startActivity(intent);
    }
}
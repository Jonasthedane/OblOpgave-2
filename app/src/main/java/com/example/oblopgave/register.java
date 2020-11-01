package com.example.oblopgave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    private static final String TAG = "Registrer";
    private FirebaseAuth mAuth;
    private TextView MessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        MessageView = findViewById(R.id.RegistrerMessageView);
    }

    public void OnStart() {
        super.onStart();
    }

    public void Registrer(View view) {
        EditText EmailInput = findViewById(R.id.RegistrerEmail);
        EditText PasswordInput = findViewById(R.id.RegistrerPassword);
        EditText NameInput = findViewById(R.id.RegisterName);
        EditText PhoneInput = findViewById(R.id.RegisterPhone);
        String email = EmailInput.getText().toString();
        String password = PasswordInput.getText().toString();
        String name = NameInput.getText().toString();
        String phone = PhoneInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            MessageView.setText("Din bruger blev registreret");
                            AddUserToREST(name, phone, user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            MessageView.setText("Der skete en fejl prøv igen");
                        }

                        // ...
                    }
                });

    }


    public void AddUserToREST(String name, String phone, String fireBaseId) {
        Users UsersToAdd = new Users(name, phone, fireBaseId);

        Call<Users> callAddUsers = ApiUtils.getBicycleService().postUser(UsersToAdd);
        callAddUsers.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    MessageView.setText("User Added");

                } else {
                    MessageView.setText("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                MessageView.setText("Something went wrong");
            }
        });

    }


    public void GoToMain(View view) {
        Intent intent = new Intent(register.this, MainActivity.class);
        startActivity(intent);
    }
}
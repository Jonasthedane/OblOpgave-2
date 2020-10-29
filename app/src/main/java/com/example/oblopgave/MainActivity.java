package com.example.oblopgave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lukind";
    private FirebaseAuth mAuth;
    private TextView MessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        MessageView = findViewById(R.id.MainMessageView);
    }

    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            MessageView.setText("Ingen er logget ind");
        } else {
            Intent intent = new Intent(MainActivity.this, Index.class);
            startActivity(intent);
        }
    }

    public void Login(View view) {
        EditText EmailInput = findViewById(R.id.MainEmailInput);
        EditText PasswordInput = findViewById(R.id.MainPasswordInput);
        String email = EmailInput.getText().toString();
        String password = PasswordInput.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            MessageView.setText("Velkommen" + user.getEmail());
                            Intent intent = new Intent(MainActivity.this, Index.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            MessageView.setText("Login Failed");
                        }

                        // ...
                    }
                });
    }

    public void GoToRegister(View view) {
        Intent intent = new Intent(MainActivity.this, register.class);
        startActivity(intent);
    }



}
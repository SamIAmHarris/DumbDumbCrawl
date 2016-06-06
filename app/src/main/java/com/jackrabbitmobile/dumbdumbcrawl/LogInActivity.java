package com.jackrabbitmobile.dumbdumbcrawl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class LogInActivity extends AppCompatActivity {


    @BindView(R.id.activity_login_loginImageView)
    ImageView loginImageView;
    @BindView(R.id.activity_login_emailLoginButton)
    Button emailLoginButton;
    @BindView(R.id.activity_login_facebookLoginButton)
    Button facebookLoginButton;

    private String TAG = "LogInActivity";

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private String userInputValue = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth =  FirebaseAuth.getInstance();

        authStateListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };

        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEmailEntryEditTextDialog();
            }
        });

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpFacebookUser();
            }
        });
    }

    private void signUpFacebookUser() {

    }

    private void signUpAnonymousEmailUser() {
        auth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i(TAG, "Anonymous User Sign Up Success");
                            FirebaseUser user = auth.getCurrentUser();
                            if(user != null) {
                                user.updateEmail(userInputValue);
                            }
                            Intent mainIntent = new Intent(LogInActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    public void launchEmailEntryEditTextDialog() {
        //set the userEmail variable and call signUpAnonymousEmailUser
        final AlertDialog.Builder inputAlert = new AlertDialog.Builder(this);
        inputAlert.setTitle("Please Enter In Your Email");
        inputAlert.setMessage("Enter email here:");
        final EditText userInput = new EditText(this);
        inputAlert.setView(userInput);
        inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userInputValue = userInput.getText().toString();
                signUpAnonymousEmailUser();
            }
        });
        inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = inputAlert.create();
        alertDialog.show();
    }
}

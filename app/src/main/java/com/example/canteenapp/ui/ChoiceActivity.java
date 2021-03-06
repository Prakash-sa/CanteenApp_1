package com.example.canteenapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.canteenapp.R;
import com.example.canteenapp.ui.mess.registermess.RegisterMess;
import com.example.canteenapp.ui.student.registerstudent.RegisterStudent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class ChoiceActivity extends AppCompatActivity {
    private Class nextClass = null;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Splash";
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_acivity);

        RelativeLayout studentbt = findViewById(R.id.choice_student);
        RelativeLayout messbt = findViewById(R.id.choice_mess);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.getidtoken))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        studentbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClass = RegisterStudent.class;
                role = "student";
                signIn();
            }
        });

        messbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClass = RegisterMess.class;
                role = "mess";
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Something went wrong. Restart application", Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {

                                if (!task.getResult().getAdditionalUserInfo().isNewUser()){
                                    Log.d(TAG, "Not a new user");
                                    startActivity(new Intent(ChoiceActivity.this, SplashActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .putExtra("role", role));
                                    nextClass = null;
                                    finish();
                                }

                            } catch (Exception e) { Log.d(TAG, "" + e.getLocalizedMessage());
                            } finally { updateUI(); }

                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(ChoiceActivity.this, "Authentication Failed. Try again", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void updateUI() {

        if (nextClass != null) startActivity(new Intent(ChoiceActivity.this, nextClass));
        finish();
    }

}

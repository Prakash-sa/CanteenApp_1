package com.example.canteenapp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.canteenapp.ui.ChoiceActivity;
import com.example.canteenapp.ui.mess.MessMainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Logout {
    private static GoogleSignInClient googleSignInClient;

    public static void init(Context context) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getResources().getString(R.string.getidtoken))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public static void CompleteSignOut() {
        FirebaseSignOut();
        GoogleSignOut();
    }

    public static void FirebaseSignOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public static void GoogleSignOut() {
        googleSignInClient.signOut();
    }
}

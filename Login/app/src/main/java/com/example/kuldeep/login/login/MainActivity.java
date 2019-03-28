package com.example.kuldeep.login.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.kuldeep.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {
   /* private Button changeEmail;
    private Button changePassword;
    private Button sendEmail;
    private Button remove;

    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;*/
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent((MainActivity.this), LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    protected void onResume()
    {
        super.onResume();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        auth.addAuthStateListener(authListener);
        Intent intent =new Intent(MainActivity.this,com.example.kuldeep.login.note.NoteListActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_main );
    }
    @Override
    public void onStop()
    {
        super.onStop();
        if (authListener != null)
        {
            auth.removeAuthStateListener(authListener);
        }
    }
}

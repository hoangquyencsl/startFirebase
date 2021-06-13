package com.example.hoangthequyen_project.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoangthequyen_project.AddImgActivity;
import com.example.hoangthequyen_project.R;
import com.example.hoangthequyen_project.ReportActivity;
import com.example.hoangthequyen_project.SettingsActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFrg extends Fragment {

    private FirebaseAuth firebaseAuth;

    private FloatingActionButton fab;
    private String name, email;
    private TextView dpName, dpEmail;
    private Button btnLogout;
    private ImageButton btnaddimg;

    GoogleSignInClient googleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile_frg, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

//        if (firebaseUser != null){
//            dpName.setText(firebaseUser.getDisplayName());
//            dpEmail.setText(firebaseUser.getEmail());
//        }
        fab=(FloatingActionButton) rootView.findViewById(R.id.fab);

        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            firebaseAuth.signOut();
                            Toast.makeText(ProfileFrg.this.getContext(),
                                    "Logout Successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        googleSignInClient = GoogleSignIn.getClient(ProfileFrg.this.getContext(),
                GoogleSignInOptions.DEFAULT_SIGN_IN);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileFrg.this.getContext(),
                        AddImgActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
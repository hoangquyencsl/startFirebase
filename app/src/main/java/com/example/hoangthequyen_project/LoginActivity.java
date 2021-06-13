package com.example.hoangthequyen_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hoangthequyen_project.Fragment.ProfileFrg;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private Button btLogin,btRegister;
    private ImageButton signinGG;
    private TextView textbtn;
    private EditText email,password;
    protected FirebaseAuth  mFirebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        mFirebaseAuth = FirebaseAuth.getInstance();


        //đăng nhập bằng tài khoản đki
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=email.getText().toString();
                String p=password.getText().toString();
                if(e.isEmpty()){
                    email.setError("email is not blank");
                    return;
                }
                if(p.isEmpty()){
                    password.setError("password is not blank");
                    return;
                }
                if(p.length()<6){
                    password.setError("password must be >= 6 characters");
                    return;
                }

                mFirebaseAuth.signInWithEmailAndPassword(e,p).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Toast.makeText(com.example.hoangthequyen_project.LoginActivity
                                        .this, "Login Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(com.example.hoangthequyen_project.LoginActivity.this,
                                        MainActivity.class);

                                startActivity (intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(com.example.hoangthequyen_project.LoginActivity.this,
                                "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Đăng kí
        textbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.hoangthequyen_project.LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Đăng nhập bằng GG
        signinGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // kết quả trả về khi khởi chạy Intent từ GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()){
                Toast.makeText(com.example.hoangthequyen_project.LoginActivity
                        .this, "Google sign in successful", Toast.LENGTH_LONG).show();
            }

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                if(account != null){
                    AuthCredential authCredential = GoogleAuthProvider
                            .getCredential(account.getIdToken(),null);
                    mFirebaseAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        Intent intent = new Intent(LoginActivity.this,
                                                MainActivity.class);
                                        Toast.makeText(com.example.hoangthequyen_project.LoginActivity
                                                .this, "Login Successfully", Toast.LENGTH_LONG).show();

                                        startActivity (intent);
                                    }else {
                                        Toast.makeText(com.example.hoangthequyen_project.LoginActivity
                                                .this, "Login Fail :"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            } catch (ApiException e) {
                return;
            }
        }
    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mFirebaseAuth.signInWithCredential(credential)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                // Sign in success
//
//                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
//                                String uid = user.getUid();
//                                String email = user.getEmail();
//
//                                if (authResult.getAdditionalUserInfo().isNewUser()){
//                                    Toast.makeText(LoginActivity.this,
//                                            "Account Create ...\n"+email , Toast.LENGTH_LONG).show();
//                                }else {
//                                    Toast.makeText(LoginActivity.this,
//                                            "Existing User...\n"+email , Toast.LENGTH_LONG).show();
//                                }
//                                startActivity(new Intent(LoginActivity.this,ProfileFrg.class));
//                                finish();
//                            }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(com.example.hoangthequyen_project.LoginActivity.this,
//                                "Auth Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }

    private void initView() {
        btLogin=findViewById(R.id.btLogin);
        signinGG=findViewById(R.id.signGG);
        email=findViewById(R.id.txtEmail);
        textbtn=findViewById(R.id.dangky);
        password=findViewById(R.id.txtPass);
    }
}

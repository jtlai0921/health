package com.jtlai0921.pass.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.jtlai0921.pass.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class first extends AppCompatActivity {
    private SignInButton signInButton;
    private  static final  int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Button signOutButton = (Button) findViewById(R.id.signoutButton);


        signInButton = (SignInButton)findViewById(R.id.signinButton);

        // 設定 FirebaseAuth 介面
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(task -> {
           /* if(task.isSuccessful()){
                Toast.makeText(first.this,"匿名登入成功 uid:\n" + mAuth.getCurrentUser().getUid(),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(first.this,"匿名登入失敗",Toast.LENGTH_LONG).show();
            }*/
        });
        // 設定 Google 登入 Client
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, connectionResult -> Toast.makeText(first.this,"Google 連線異常",Toast.LENGTH_LONG).show())
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    Intent intent = new Intent();
                    intent.setClass(first.this, index.class);
                    startActivity(intent);
                } catch(Exception e) {
                    Log.d("@@@@@@@@@@@", e.toString());
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                //取得使用者並試登入
                firebaseAuthWithGoogle(account);
            }
        }
    }

    //登入 Firebase
    private  void firebaseAuthWithGoogle(final GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(first.this, "Failed", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(first.this, "SingIn name:"+account.getDisplayName(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    public void firebaseSingOut(View view){
        // Firebase 登出
        mAuth.signOut();
        FirebaseAuth.getInstance().signOut();

        // Google 登出
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignIn.getClient(this, gso).signOut().addOnCompleteListener(task -> Toast.makeText(first.this, "SingOut", Toast.LENGTH_LONG).show());
    }
}
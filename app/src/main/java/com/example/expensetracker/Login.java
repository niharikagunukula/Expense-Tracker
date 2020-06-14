package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN =0;
    private TextView tv, forgot;
    EditText memail, mpassword;
    Button mlogin;
    TextView mgoogle;
    FirebaseAuth fauth;
    CheckBox show;
    ProgressDialog dialog;
   @Override
   protected void onStart() {
        super.onStart();
        FirebaseUser user=fauth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(Login.this,Home.class));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv = findViewById(R.id.t1);
        forgot = findViewById(R.id.forgot);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mlogin = findViewById(R.id.login);
       mgoogle = findViewById(R.id.google);
show=findViewById(R.id.showpassword);
        fauth = FirebaseAuth.getInstance();
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    mpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e, pass;
                e = memail.getText().toString();
                pass = mpassword.getText().toString();
                if (e.equals("")) {
                    Toast.makeText(Login.this, "email required", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals("")) {
                    Toast.makeText(Login.this, "password required", Toast.LENGTH_SHORT).show();
                }

                else if(pass.length()<=6){
                    Toast.makeText(Login.this,"password must have more than 6 characters",Toast.LENGTH_SHORT).show();
                }
                else {
                    fauth.signInWithEmailAndPassword(e, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, Home.class));
                            } else {
                                Toast.makeText(Login.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Enter_email.class));
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(RegisterIntent);
                finish();

            }
        });
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(Login.this,gso );
        mgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = fauth.getCurrentUser();
                            final String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            final FirebaseFirestore db=FirebaseFirestore.getInstance();


                            final Map<String, Object> data = new HashMap<>();
                              data.put("username",fauth.getCurrentUser().getDisplayName());
                              data.put("email", fauth.getCurrentUser().getEmail());
                            db.collection("users").document(id).get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot documentSnapshot=task.getResult();
                                            if(documentSnapshot.exists()){

                                            }
                                            else{
                                                db.collection("users").document(id)
                                                        .set(data);

                                            }

                                        }
                                    });
// Add a new document with a generated ID
                            Intent intent=new Intent(Login.this,Home.class);
                            intent.putExtra("key","google");
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this,"sorry auth failed",Toast.LENGTH_SHORT).show();                        }
                        // ...
                    }
                });
    }
}
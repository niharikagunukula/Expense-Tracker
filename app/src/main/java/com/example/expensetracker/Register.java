package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity{
    EditText musername,memail,mpassword,mconpassword;
    Button mregister;
    TextView textView;
    FirebaseAuth fauth;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        musername=findViewById(R.id.username);
        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mconpassword=findViewById(R.id.conpassword);
        mregister=findViewById(R.id.register);
        textView=findViewById(R.id.t);
        fauth=FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        mregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String un,e,pass,conpas;
                un=musername.getText().toString();
                e=memail.getText().toString();
                pass=mpassword.getText().toString();
                conpas=mconpassword.getText().toString();
                if(un.equals("")){
                    Toast.makeText(Register.this,"username required",Toast.LENGTH_SHORT).show();
                }
              else if(e.equals("")){
                    Toast.makeText(Register.this,"email required",Toast.LENGTH_SHORT).show();
                }
               else if(pass.equals("")){
                    Toast.makeText(Register.this,"password required",Toast.LENGTH_SHORT).show();
                }
               else if(conpas.equals("")){
                    Toast.makeText(Register.this,"confirm password required",Toast.LENGTH_SHORT).show();
                }
                 else if(!conpas.equals(pass)){
                    Toast.makeText(Register.this,"password incorrect",Toast.LENGTH_SHORT).show();
                }
               else if(pass.length()<=6){
                    Toast.makeText(Register.this,"password must have more than 6 characters",Toast.LENGTH_SHORT).show();
                }
               else {
                    fauth.createUserWithEmailAndPassword(e, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) throws NullPointerException {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
// Create a new user with a first and last name
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", un);
                                user.put("email", e);

// Add a new document with a generated ID
                                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                db.collection("users").document(id)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });

                                startActivity(new Intent(Register.this, Home.class));
                            } else {
                                Toast.makeText(Register.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(getApplicationContext(),Login.class);
                startActivity(LoginIntent);
                finish();
            }
        });
    }
}

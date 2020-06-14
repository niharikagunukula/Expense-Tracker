package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Enter_email extends AppCompatActivity {
    EditText regEmail;
    Button sendEmail;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_email);
        regEmail = findViewById(R.id.regemail);
        sendEmail = findViewById(R.id.sendemail);

        fAuth = FirebaseAuth.getInstance();

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString().trim();
                if(email.isEmpty())
                {
                    regEmail.setError("Enter Email");
                }
                else
                {
                    fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Enter_email.this,"E-mail has been sent",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Enter_email.this,Login.class));
                                finish();
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(Enter_email.this,"Error: " + message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

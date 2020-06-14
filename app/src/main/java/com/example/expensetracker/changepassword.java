package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends Fragment {
EditText currentpass,newpass,conpass;
Button change;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_changepassword, container, false);
        currentpass=v.findViewById(R.id.current);
        newpass=v.findViewById(R.id.newpass);
        conpass=v.findViewById(R.id.conpass);
        change=v.findViewById(R.id.btnchange);
      TextView password=v.findViewById(R.id.textView);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangePassword();
            }
        });
        return  v;
    }

    private void ChangePassword() {
        String currpass=currentpass.getText().toString();
        final String newp=newpass.getText().toString();
        String conp=conpass.getText().toString();
    if((currpass.equals(""))){
            Toast.makeText(getActivity(),"passworrd required",Toast.LENGTH_SHORT).show();
        }
       else if(newp.equals("")){
        Toast.makeText(getActivity()," new passworrd required",Toast.LENGTH_SHORT).show();
    }
       else if(conp.equals("")){
        Toast.makeText(getActivity()," confirm passworrd required",Toast.LENGTH_SHORT).show();
    }
       else if(!newp.equals(conp)){
        Toast.makeText(getActivity(),"  passworrd incorrect",Toast.LENGTH_SHORT).show();
    }
       else if(newp.length()<=6){
        Toast.makeText(getActivity(),"  passworrd must have more than 6 characters",Toast.LENGTH_SHORT).show();
    }
       else {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        if ((user!=null)) {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), currpass);

// Prompt the user to re-provide their sign-in credentials
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {

                                Toast.makeText(getActivity(),"re-authentication successful",Toast.LENGTH_SHORT).show();

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                //String newPassword = "SOME-SECURE-PASSWORD";

                                user.updatePassword(newp)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getActivity(),"Password changed successfully",Toast.LENGTH_SHORT).show();

                                                    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
                                                    firebaseAuth.signOut();
                                                    startActivity(new Intent(getActivity(),Login.class));
                                                }
                                            }
                                        });

                            }
                            else {
                                Toast.makeText(getActivity()," re-authentication failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            startActivity(new Intent(getActivity(),Login.class));
        }
    }
    }
}

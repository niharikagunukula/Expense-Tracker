package com.example.expensetracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SetLimit extends Fragment {

    private EditText totlimit;
private Button button;
private static String tot;
public static String getValue(){
    return tot;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View v= inflater.inflate(R.layout.fragment_set_limit, container, false);
        totlimit=v.findViewById(R.id.totlimit);
        button=v.findViewById(R.id.btn);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
         tot=totlimit.getText().toString();
        Map<String,Object> limit=new HashMap<>();
        limit.put("Limit",Double.parseDouble(tot));
       String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("users").document(id).collection("Transactions").document("categories")
                .set(limit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"successful",Toast.LENGTH_SHORT).show();
                    }
                });
    }
});

    return v;
    }
}

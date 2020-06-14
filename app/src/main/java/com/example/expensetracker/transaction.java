package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class transaction extends Fragment {
Button food,electricity,groceries,loan,transport,entertainment,maid,maintenance,water,education,shopping,other;

    public transaction() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_transaction, container, false);
food=v.findViewById(R.id.btn_food);
electricity=v.findViewById(R.id.btn_electricity);
groceries=v.findViewById(R.id.btn_groceries);
loan=v.findViewById(R.id.btn_loan);
transport=v.findViewById(R.id.btn_transport);
entertainment=v.findViewById(R.id.btn_entertainment);
maid=v.findViewById(R.id.btn_maid);
maintenance=v.findViewById(R.id.btn_maintenance);
water=v.findViewById(R.id.btn_water);
education=v.findViewById(R.id.btn_education);
shopping=v.findViewById(R.id.btn_shopping);
other=v.findViewById(R.id.btn_other);

food.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent= new Intent(getActivity(),category.class);
        intent.putExtra("key","FOOD");
        startActivity(intent);

    }
});
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","ELECTRICITY");
                startActivity(intent);


            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","GROCERIES");

                startActivity(intent);


            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","LOAN");
                startActivity(intent);

            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","TRANSPORT");
                startActivity(intent);

            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","ENTERTAINMENT");
                startActivity(intent);

            }
        });
        maid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","MAID");
                startActivity(intent);
            }
        });
        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","MAINTENANCE");
                startActivity(intent);

            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","WATER");
                startActivity(intent);

            }
        });
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","EDUCATION");
                startActivity(intent);

            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","SHOPPING");
                startActivity(intent);

            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),category.class);
                intent.putExtra("key","OTHER");
                startActivity(intent);

            }
        });
    return v;
    }

}

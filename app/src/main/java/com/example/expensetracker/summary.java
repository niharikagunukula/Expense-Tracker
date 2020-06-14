package com.example.expensetracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class summary extends Fragment  {
private TextView food,electricity,groceries,entertainment,loans,maid,transport,maintenance,shopping,water,other,total,education;
private Double f;
    private Double elec;
    private Double g;
    private Double enter;
    private Double l;
    private Double m;
    private Double trans;
    private Double main;
    private Double edu;
    private Double shop;
    private Double wat;
    private Double oth;
    private Button bargraph,piechart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_summary, container, false);
        food=v.findViewById(R.id.food_amt);
        electricity=v.findViewById(R.id.electricity_amt);
        groceries=v.findViewById(R.id.groceries_amt);
        entertainment=v.findViewById(R.id.entertainment_amt);
        loans=v.findViewById(R.id.emi_amt);
        maid=v.findViewById(R.id.househelp_amt);
        transport=v.findViewById(R.id.transport_amt);
        maintenance=v.findViewById(R.id.maintenance_amt);
        education=v.findViewById(R.id.education_amt);
        shopping=v.findViewById(R.id.shopping_amt);
        water=v.findViewById(R.id.water_amt);
        other=v.findViewById(R.id.others_amt);
        total=v.findViewById(R.id.total_amt);
        bargraph=v.findViewById(R.id.bar);
        piechart=v.findViewById(R.id.pie);
        bargraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("key","bar");
                sumgraph graph=new sumgraph();
                graph.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.fragment_container,new sumgraph());
                 fragmentTransaction.addToBackStack(null).commit();
            }
        });
        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("key","pie");
                sumgraph graph=new sumgraph();
                graph.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new sumpie());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String id=FirebaseAuth.getInstance().getCurrentUser().getUid();

        Date current=Calendar.getInstance().getTime();
        String currentdate=DateFormat.getDateInstance().format(current);
            db.collection("users").document(id).collection("Transactions").document("categories")
                    .collection("date").document(currentdate)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    try {

                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            f = documentSnapshot.getDouble("FOOD-moneyspent");
                            food.setText(f.toString());
                            elec = documentSnapshot.getDouble("ELECTRICITY-moneyspent");
                            electricity.setText(elec.toString());
                            g = documentSnapshot.getDouble("GROCERIES-moneyspent");
                            groceries.setText(g.toString());
                            l = documentSnapshot.getDouble("LOAN-moneyspent");
                            loans.setText(l.toString());
                            trans = documentSnapshot.getDouble("TRANSPORT-moneyspent");
                            transport.setText(trans.toString());
                            enter = documentSnapshot.getDouble("ENTERTAINMENT-moneyspent");
                            entertainment.setText(enter.toString());
                            m = documentSnapshot.getDouble("MAID-moneyspent");
                            maid.setText(m.toString());
                            main = documentSnapshot.getDouble("MAINTENANCE-moneyspent");
                            maintenance.setText(main.toString());
                            wat = documentSnapshot.getDouble("WATER-moneyspent");
                            water.setText(wat.toString());
                            edu = documentSnapshot.getDouble("EDUCATION-moneyspent");
                            education.setText(edu.toString());
                            shop = documentSnapshot.getDouble("SHOPPING-moneyspent");
                            shopping.setText(shop.toString());
                            oth = documentSnapshot.getDouble("OTHER-moneyspent");
                            other.setText(oth.toString());
                            Double tot = f + elec + g + l + trans + enter + m + main + wat + edu + shop + oth;
                            total.setText(tot.toString());
                        }else{
                            total.setText("No data");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        return v;

    }
}

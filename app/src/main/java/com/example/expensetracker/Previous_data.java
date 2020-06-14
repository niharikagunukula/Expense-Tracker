package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Previous_data extends AppCompatActivity {
    private TextView food, electricity, groceries, entertainment, loans, maid, transport, maintenance, shopping, water, other, total, education;
    private Double f ;
    private Double elec ;
    private Double g ;
    private Double enter ;
    private Double l ;
    private Double m ;
    private Double trans ;
    private Double main ;
    private Double edu ;
    private Double shop ;
    private Double wat ;
    private Double oth ;
    private Button bar,pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_data);
           bar=findViewById(R.id.bargraph);
           pie=findViewById(R.id.pie);
        food=findViewById(R.id.food_amt);
        electricity=findViewById(R.id.electricity_amt);
        groceries=findViewById(R.id.groceries_amt);
        entertainment=findViewById(R.id.entertainment_amt);
        loans=findViewById(R.id.emi_amt);
        maid=findViewById(R.id.househelp_amt);
        transport=findViewById(R.id.transport_amt);
        maintenance=findViewById(R.id.maintenance_amt);
        education=findViewById(R.id.education_amt);
        shopping=findViewById(R.id.shopping_amt);
        water=findViewById(R.id.water_amt);
        other=findViewById(R.id.others_amt);
        total=findViewById(R.id.total_amt);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        Bundle bundle = getIntent().getExtras();
        int month = bundle.getInt("Month");
        int day = bundle.getInt("Day");
        int year = bundle.getInt("Year");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        final String date_w = DateFormat.getDateInstance().format(cal.getTime());
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Previous_data.this,pdGraphs.class);
                intent.putExtra("key",date_w);

                startActivity(intent);
            }
        });
        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Previous_data.this,PdPie.class);
                intent.putExtra("key",date_w);

                startActivity(intent);
            }
        });



        if (date_w != null) {
            db.collection("users").document(id).collection("Transactions").document("categories")
                    .collection("date").document(date_w)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        try {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                f = documentSnapshot.getDouble("FOOD-moneyspent");
                                elec = documentSnapshot.getDouble("ELECTRICITY-moneyspent");
                                g = documentSnapshot.getDouble("GROCERIES-moneyspent");
                                l = documentSnapshot.getDouble("LOAN-moneyspent");
                                trans = documentSnapshot.getDouble("TRANSPORT-moneyspent");
                                enter = documentSnapshot.getDouble("ENTERTAINMENT-moneyspent");
                                m = documentSnapshot.getDouble("MAID-moneyspent");
                                main = documentSnapshot.getDouble("MAINTENANCE-moneyspent");
                                wat = documentSnapshot.getDouble("WATER-moneyspent");
                                edu = documentSnapshot.getDouble("EDUCATION-moneyspent");
                                shop = documentSnapshot.getDouble("SHOPPING-moneyspent");
                                oth = documentSnapshot.getDouble("OTHER-moneyspent");
                                food.setText(f.toString());
                                electricity.setText(elec.toString());
                                groceries.setText(g.toString());
                                loans.setText(l.toString());
                                transport.setText(trans.toString());
                                entertainment.setText(enter.toString());
                                maid.setText(m.toString());
                                maintenance.setText(main.toString());
                                water.setText(wat.toString());
                                education.setText(edu.toString());
                                shopping.setText(shop.toString());
                                other.setText(oth.toString());
                                Double tot = f + elec + g + l + trans + enter + m + main + wat + edu + shop + oth;
                                total.setText(tot.toString());


                            } else {
                                total.setText("No data");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }
            });
        }


    }
}

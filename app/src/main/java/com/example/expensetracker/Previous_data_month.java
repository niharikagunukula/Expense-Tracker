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

public class Previous_data_month extends AppCompatActivity {
    private TextView food, electricity, groceries, entertainment, loans, maid, transport, maintenance, shopping, water, other, total, education;
   private Button bargraph,piechart;
    private Double f =0.0;
    private Double elec =0.0;
    private Double g =0.0;
    private Double enter =0.0;
    private Double l =0.0;
    private Double m =0.0;
    private Double trans =0.0;
    private Double main =0.0;
    private Double edu =0.0;
    private Double shop =0.0;
    private Double wat =0.0;
    private Double oth =0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_data_month);
        food = findViewById(R.id.food_mamt);
        electricity = findViewById(R.id.electricity_mamt);
        groceries = findViewById(R.id.groceries_mamt);
        entertainment = findViewById(R.id.entertainment_mamt);
        loans = findViewById(R.id.emi_mamt);
        maid = findViewById(R.id.househelp_mamt);
        transport = findViewById(R.id.transport_mamt);
        maintenance = findViewById(R.id.maintenance_mamt);
        education = findViewById(R.id.education_mamt);
        shopping = findViewById(R.id.shopping_mamt);
        water = findViewById(R.id.water_mamt);
        other = findViewById(R.id.others_mamt);
        total = findViewById(R.id.total_mamt);
        bargraph = findViewById(R.id.pdm_bargraph);
        piechart =findViewById(R.id.pdm_pie);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Bundle bundle = getIntent().getExtras();
        final int month = bundle.getInt("Month");
        final int day = bundle.getInt("Day");
        final int year = bundle.getInt("Year");

        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 30; i++) {
            cal.set(year, month, day);
            cal.add(Calendar.DAY_OF_MONTH, -1 * i);
            String date_w = DateFormat.getDateInstance().format(cal.getTime());
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
                                    f = f + documentSnapshot.getDouble("FOOD-moneyspent");
                                    elec = elec + documentSnapshot.getDouble("ELECTRICITY-moneyspent");
                                    g = g + documentSnapshot.getDouble("GROCERIES-moneyspent");
                                    l = l + documentSnapshot.getDouble("LOAN-moneyspent");
                                    trans = trans + documentSnapshot.getDouble("TRANSPORT-moneyspent");
                                    enter = enter + documentSnapshot.getDouble("ENTERTAINMENT-moneyspent");
                                    m = m + documentSnapshot.getDouble("MAID-moneyspent");
                                    main = main + documentSnapshot.getDouble("MAINTENANCE-moneyspent");
                                    wat = wat + documentSnapshot.getDouble("WATER-moneyspent");
                                    edu = edu + documentSnapshot.getDouble("EDUCATION-moneyspent");
                                    shop = shop + documentSnapshot.getDouble("SHOPPING-moneyspent");
                                    oth = oth + documentSnapshot.getDouble("OTHER-moneyspent");
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
        bargraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("Day", day);
                bundle.putInt("Month", month);
                bundle.putInt("Year", year);
                Intent intent = new Intent(Previous_data_month.this, pd_month_graph.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("Day", day);
                bundle.putInt("Month", month);
                bundle.putInt("Year", year);
                Intent intent = new Intent(Previous_data_month.this, pd_month_pie.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}

package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class pd_week_graph extends AppCompatActivity {
    BarChart barChart;


    ArrayList<BarEntry> barEntries;
    ArrayList<String> labelnames;
    private Double f=0.0;
    private Double elec=0.0;
    private Double g=0.0;
    private Double enter=0.0;
    private Double l=0.0;
    private Double m=0.0;
    private Double trans=0.0;
    private Double main=0.0;
    private Double edu=0.;
    private Double shop=0.0;
    private Double wat=0.0;
    private Double oth=0.0;
    private int i=0;
    ArrayList<graphdata> graphdataArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_graphs);
        barChart=findViewById(R.id.bargraph);
        barEntries=new ArrayList<>();
        labelnames=new ArrayList<>();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        Bundle bundle = getIntent().getExtras();
        int month = bundle.getInt("Month");
        int day = bundle.getInt("Day");
        int year = bundle.getInt("Year");
        for ( ; i<7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            cal.add(Calendar.DAY_OF_MONTH, i * (-1));
            String date = DateFormat.getDateInstance().format(cal.getTime());
            if (date != null) {
                db.collection("users").document(id).collection("Transactions").document("categories")
                        .collection("date").document(date)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
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
                                graphdataArrayList.clear();
                                graphdataArrayList.add(new graphdata("FOOD", f));
                                graphdataArrayList.add(new graphdata("ELECTRICITY", elec));
                                graphdataArrayList.add(new graphdata("GROCERIES", g));
                                graphdataArrayList.add(new graphdata("LOAN", l));
                                graphdataArrayList.add(new graphdata("TRANSPORT", trans));
                                graphdataArrayList.add(new graphdata("ENTERTAINMENT", enter));
                                graphdataArrayList.add(new graphdata("MAID", m));
                                graphdataArrayList.add(new graphdata("MAINTENANCE", main));
                                graphdataArrayList.add(new graphdata("WATER", wat));
                                graphdataArrayList.add(new graphdata("EDUCATION", edu));
                                graphdataArrayList.add(new graphdata("SHOPPING", shop));
                                graphdataArrayList.add(new graphdata("OTHER", oth));
                                barEntries.clear();

                                for (int i = 0; i < graphdataArrayList.size(); i++) {
                                    String cat = graphdataArrayList.get(i).getCategory();
                                    Double amount = graphdataArrayList.get(i).getAmt();
                                    String amt = amount.toString();
                                    float a = Float.parseFloat(amt);
                                    barEntries.add(new BarEntry(i, a));
                                    labelnames.add(cat);
                                }


                                BarDataSet barDataSet = new BarDataSet(barEntries, "DAILY EXPENSES");
                                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                Description description = new Description();
                                description.setText("CATEGORIES");
                                barChart.setDescription(description);
                                BarData barData = new BarData(barDataSet);
                                barChart.setData(barData);
                                XAxis xAxis = barChart.getXAxis();
                                xAxis.setValueFormatter(new IndexAxisValueFormatter(labelnames));
                                xAxis.setPosition(XAxis.XAxisPosition.TOP);
                                xAxis.setDrawGridLines(false);
                                xAxis.setDrawAxisLine(false);
                                xAxis.setGranularity(1f);
                                xAxis.setLabelCount(labelnames.size());
                                xAxis.setLabelRotationAngle(270);
                                barChart.animateY(2000);
                                barChart.invalidate();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                     }
                });
            }
        }


    }
}

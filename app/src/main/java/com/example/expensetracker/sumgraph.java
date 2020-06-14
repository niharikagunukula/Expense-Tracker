package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.series.DataPoint;
//import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class sumgraph extends Fragment {
   private BarChart barChart;


    private ArrayList<BarEntry> barEntries;
    private ArrayList<String> labelnames;
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
  ArrayList<graphdata> graphdataArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View v= inflater.inflate(R.layout.fragment_sumgraph, container, false);
       barChart=v.findViewById(R.id.bargraph);
         barEntries=new ArrayList<>();
         labelnames=new ArrayList<>();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Date current= Calendar.getInstance().getTime();
        String currentdate= DateFormat.getDateInstance().format(current);
        if(currentdate!=null) {
            db.collection("users").document(id).collection("Transactions").document("categories")
                    .collection("date").document(currentdate)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
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
        return v;
    }


}

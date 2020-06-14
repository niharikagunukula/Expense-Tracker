package com.example.expensetracker;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class fragment_home1 extends Fragment implements  DatePickerDialog.OnDateSetListener {
    private Double f;
    private Double elec;
    private Double g ;
    private Double enter;
    private Double l ;
    private Double m ;
    private Double trans;
    private Double main ;
    private Double edu ;
    private Double shop;
    private Double wat ;
    private Double oth ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home1, container, false);
        Toast.makeText(getActivity(),"Double tap the Back button to exit",Toast.LENGTH_SHORT).show();
        Date current= Calendar.getInstance().getTime();
        final String currentdate= DateFormat.getDateInstance().format(current);
        final TextView view_r = v.findViewById(R.id.view_r);
        TextView edit=v.findViewById(R.id.edit);
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
        final String id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("users").document(id).collection("Transactions").document("categories")
                .collection("date").document(currentdate).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        if (documentSnapshot.exists()) {
                            f =  documentSnapshot.getDouble("FOOD-moneyspent");
                            elec =  documentSnapshot.getDouble("ELECTRICITY-moneyspent");
                            g =  documentSnapshot.getDouble("GROCERIES-moneyspent");
                            l =  documentSnapshot.getDouble("LOAN-moneyspent");
                            trans = documentSnapshot.getDouble("TRANSPORT-moneyspent");
                            enter = documentSnapshot.getDouble("ENTERTAINMENT-moneyspent");
                            m = documentSnapshot.getDouble("MAID-moneyspent");
                            main =  documentSnapshot.getDouble("MAINTENANCE-moneyspent");
                            wat =  documentSnapshot.getDouble("WATER-moneyspent");
                            edu = documentSnapshot.getDouble("EDUCATION-moneyspent");
                            shop =  documentSnapshot.getDouble("SHOPPING-moneyspent");
                            oth =  documentSnapshot.getDouble("OTHER-moneyspent");
                            final Double tot = f + elec + g + l + trans + enter + m + main + wat + edu + shop + oth;
                            //view_r.setText(tot.toString());
                            db.collection("users").document(id).collection("Transactions").document("categories")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot documentSnapshot=task.getResult();
                                            if(documentSnapshot.exists()) {

                                                Double limit = documentSnapshot.getDouble("Limit");
                                                if (limit != null) {
                                                    if (limit.equals(tot)) {
                                                        if (getActivity() != null) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
                                                            builder.setMessage("Max Limit reached!!!")
                                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                        }
                                                                    });
                                                            AlertDialog alert = builder.create();
                                                            alert.show();
                                                        }
                                                    }
                                                  else if (Double.compare(limit,tot)<0) {
                                                        if (getActivity() != null) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
                                                            builder.setMessage("Max Limit exceeded!!!")
                                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                        }
                                                                    });
                                                            AlertDialog alert = builder.create();
                                                            alert.show();
                                                        }
                                                    }
                                                }


                                            }
                                        }
                                    });
                        }
                    }
                });

        view_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Reports.class);
                startActivity(intent);

            }


        });
edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "SELECT THE DAY", Toast.LENGTH_SHORT).show();

        showDatePickerDialog();
    }
});
        return v;
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Bundle bundle = new Bundle();
        bundle.putInt("Day", dayOfMonth);
        bundle.putInt("Month", month);
        bundle.putInt("Year", year);
        Edit_transaction edit_transaction=new Edit_transaction();
        edit_transaction.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,edit_transaction);
        fragmentTransaction.addToBackStack(null).commit();
    }
}

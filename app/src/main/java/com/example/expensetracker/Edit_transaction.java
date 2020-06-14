package com.example.expensetracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.DateFormat;
import java.util.Calendar;

public class Edit_transaction extends Fragment {
private TextView food,electricity,groceries,loan,transport,entertainment,maid,maintenance,water,education,shopping,other;
String s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_edit_transaction, container, false);
      food=v.findViewById(R.id.edit_Food);
        electricity=v.findViewById(R.id.edit_electricity);
        groceries=v.findViewById(R.id.edit_groceries);
        loan=v.findViewById(R.id.edit_loan);
        transport=v.findViewById(R.id.edit_transport);
        entertainment=v.findViewById(R.id.edit_entertainment);
        maid=v.findViewById(R.id.edit_maid);
        maintenance=v.findViewById(R.id.edit_maintenance);
        water=v.findViewById(R.id.edit_water);
        education=v.findViewById(R.id.edit_education);
        shopping=v.findViewById(R.id.edit_shopping);
        other=v.findViewById(R.id.edit_other);
        Bundle bundle = getArguments();
        int month = bundle.getInt("Month");
        int day = bundle.getInt("Day");
        int year = bundle.getInt("Year");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        final String date_w = DateFormat.getDateInstance().format(cal.getTime());
        final String m = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("FOOD" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                    Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                                }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("ELECTRICITY" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("GROCERIES" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("LOAN" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("TRANSPORT" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("ENTERTAINMENT" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        maid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Add or Subtract previous data");
                    final EditText edt = new EditText(getActivity());
                    builder.setView(edt);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                s = edt.getText().toString();
                                DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                        .document("categories").collection("date").document(date_w);
                                dr.update("MAID" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null);
                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });
        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Add or Subtract previous data");
                final EditText edt=new EditText(getActivity());
                builder.setView(edt);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            s = edt.getText().toString();
                            DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                    .document("categories").collection("date").document(date_w);
                            dr.update("MAINTENANCE" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Add or Subtract previous data");                final EditText edt=new EditText(getActivity());
                builder.setView(edt);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            s = edt.getText().toString();
                            DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                    .document("categories").collection("date").document(date_w);
                            dr.update("WATER" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Add or Subtract previous data");
                final EditText edt=new EditText(getActivity());
                builder.setView(edt);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            s = edt.getText().toString();
                            DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                    .document("categories").collection("date").document(date_w);
                            dr.update("EDUCATION" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Add or Subtract previous data");
                final EditText edt=new EditText(getActivity());
                builder.setView(edt);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            s = edt.getText().toString();
                            DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                    .document("categories").collection("date").document(date_w);
                            dr.update("SHOPPING" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Add or Subtract previous data");
                final EditText edt=new EditText(getActivity());
                builder.setView(edt);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            s = edt.getText().toString();
                            DocumentReference dr = db.collection("users").document(m).collection("Transactions")
                                    .document("categories").collection("date").document(date_w);
                            dr.update("OTHER" + "-moneyspent", FieldValue.increment(Double.parseDouble(s)));
                            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"Enter only numbers",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert= builder.create();
                alert.show();
            }
        });

        return v;
    }
}

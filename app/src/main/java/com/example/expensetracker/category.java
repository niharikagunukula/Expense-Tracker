package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class category extends AppCompatActivity {
    private EditText money_spent;
    private TextView choose_category;
    private Button confirm;
    private Spinner payment_mode;
    private static Double tot=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        choose_category= findViewById(R.id.Choose_category);
        payment_mode= findViewById(R.id.payment_method);
        money_spent= findViewById(R.id.text_input_moneyspent);

        confirm=findViewById(R.id.confirm);
        final String key=getIntent().getStringExtra("key");
         choose_category.setText(key);
        final ArrayAdapter<CharSequence> adapter_payment= ArrayAdapter.createFromResource(category.this,
                R.array.payment, android.R.layout.simple_spinner_item);
        adapter_payment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_mode.setAdapter(adapter_payment);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final String ms = money_spent.getText().toString();
                    final String m = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    final FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Date current = Calendar.getInstance().getTime();
                    final String currentdate = DateFormat.getDateInstance().format(current);
                    db.collection("users").document(m).collection("Transactions").document("categories")
                            .collection("date").document(currentdate).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    if (documentSnapshot != null) {
                                        if (documentSnapshot.exists()) {
                                            try {
                                                DocumentReference p = db.collection("users").document(m).collection("Transactions")
                                                        .document("categories").collection("date").document(currentdate);
                                                p.update(key + "-moneyspent", FieldValue.increment(Double.parseDouble(ms)));
                                            }catch (Exception e){
                                                e.printStackTrace();
                                                Toast.makeText(category.this,"Enter only numbers",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            try {
                                                if (key != null) {
                                                    if (key.equals("FOOD")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("ELECTRICITY")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("GROCERIES")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("LOAN")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("TRANSPORT")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("ENTERTAINMENT")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("MAID")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("MAINTENANCE")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("WATER")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("EDUCATION")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("SHOPPING")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        doc.put("OTHER-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    } else if (key.equals("OTHER")) {
                                                        Map<String, Object> doc = new HashMap<>();
                                                        doc.put(key + "-moneyspent", Double.parseDouble(ms));
                                                        doc.put("FOOD-moneyspent", 0);
                                                        doc.put("GROCERIES-moneyspent", 0);
                                                        doc.put("LOAN-moneyspent", 0);
                                                        doc.put("TRANSPORT-moneyspent", 0);
                                                        doc.put("ENTERTAINMENT-moneyspent", 0);
                                                        doc.put("MAID-moneyspent", 0);
                                                        doc.put("MAINTENANCE-moneyspent", 0);
                                                        doc.put("WATER-moneyspent", 0);
                                                        doc.put("EDUCATION-moneyspent", 0);
                                                        doc.put("SHOPPING-moneyspent", 0);
                                                        doc.put("ELECTRICITY-moneyspent", 0);
                                                        db.collection("users").document(m).collection("Transactions")
                                                                .document("categories").collection("date").document(currentdate).set(doc);
                                                    }
                                                }
                                            }catch (Exception e){
                                                Toast.makeText(category.this,"Enter only numbers",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                    Toast.makeText(category.this, "confirmed", Toast.LENGTH_SHORT).show();
                    money_spent.setText(null);
            }
        });

        payment_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text= parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}

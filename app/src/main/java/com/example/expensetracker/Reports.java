package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Reports extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {
    TextView daily, monthly, weekly;
    pass p=new pass();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        daily = findViewById(R.id.daily);
        monthly = findViewById(R.id.monthly);
        weekly = findViewById(R.id.weekly);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reports.this, "SELECT THE  DAY", Toast.LENGTH_SHORT).show();
                               p.pass1("daily");
                showDatePickerDialog();

            }

        });
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reports.this, "SELECT THE LAST DAY OF THE MONTH", Toast.LENGTH_SHORT).show();

              p.pass1("monthly");
                showDatePickerDialog();


            }
        });
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(Reports.this, "SELECT THE LAST DAY OF THE WEEK", Toast.LENGTH_SHORT).show();

                p.pass1("weekly");
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Reports.this, this,
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



        String data=p.getS();

        if(data.equals("daily")){
        Intent intent = new Intent(Reports.this, Previous_data.class);
        intent.putExtras(bundle);
        startActivity(intent);
        }
        if(data.equals("monthly")){
            Intent intent = new Intent(Reports.this, Previous_data_month.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(data.equals("weekly")){
            Intent intent = new Intent(Reports.this, Previous_data_week.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

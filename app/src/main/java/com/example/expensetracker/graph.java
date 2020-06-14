package com.example.expensetracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class graph extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_graph, container, false);
        TextView textView=v.findViewById(R.id.app);
        textView.setText("https://drive.google.com/drive/folders/1mYBg_4hVx9i9wTWnlzIGt24T8INM8kEO?usp=sharing");
        return v;
    }
}

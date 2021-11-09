package com.dev.androximeter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class secondFragment extends Fragment {

    private int p;

    public secondFragment() {
        // Required empty public constructor
    }

    public static secondFragment newInstance(String param1, String param2) {
        secondFragment fragment = new secondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button HeartRate = view.findViewById(R.id.HR);
        Button Ox2 = view.findViewById(R.id.O2);

        HeartRate.setOnClickListener(v -> {
            p = 1;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Page", p);
            startActivity(i);
        });

        Ox2.setOnClickListener(v -> {
            p = 2;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Page", p);
            startActivity(i);
        });


        return view;
    }
}
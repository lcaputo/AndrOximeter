package com.dev.androximeter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class thirdFragment extends Fragment {

    public thirdFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static thirdFragment newInstance(String param1, String param2) {
        thirdFragment fragment = new thirdFragment();
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
        // LOGICA DEL FRAGMENTO
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        ListView example = view.findViewById(R.id.examplelist);

        ResultsDB DB = new ResultsDB(view.getContext());

        ArrayList lista = DB.getAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, lista);
        example.setAdapter(adapter);

        return view;
    }
}
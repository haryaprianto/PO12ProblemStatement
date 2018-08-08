package com.example.a16022596.po12problemstatement;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {
    ActionBar ab;
    EditText etBio;
    Button btnEdit;
    TextView tvResult;
    DrawerLayout mDrawerLayout;


    public BioFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);


        btnEdit = (Button)view.findViewById(R.id.buttonEdit);
        tvResult = (TextView)view.findViewById(R.id.textViewResult);

        onResume();



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterBio();

            }
        });


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void enterBio(){
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase = (LinearLayout) inflater.inflate(R.layout.edit_page, null);
        etBio = (EditText) passPhrase.findViewById(R.id.editTextBio);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please Enter")
                .setView(passPhrase)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String result = etBio.getText().toString();
                        tvResult.setText(result);
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        prefs.edit().putString("data", tvResult.getText().toString()).commit();
                    }
                })
                // Set text for the negative button and the corresponding
                //  OnClickListener when it is clicked
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String reCallData = prefs.getString("data","");
        tvResult.setText(reCallData);
    }

}

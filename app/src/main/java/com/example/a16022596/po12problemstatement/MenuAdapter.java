package com.example.a16022596.po12problemstatement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuClass> {
    private ArrayList<MenuClass> titls;
    private Context context;
    private TextView tvName;
    private ImageView img;

    public MenuAdapter(Context context, int resource, ArrayList<MenuClass> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        titls = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);


        tvName = (TextView) rowView.findViewById(R.id.textViewName);
        img = (ImageView) rowView.findViewById(R.id.imageView);


        MenuClass currentMenu = titls.get(position);
        tvName.setText(currentMenu.getTitle());
        if (tvName.getText().toString().equalsIgnoreCase("bio")){
            img.setImageResource(R.drawable.info);
        }
        else if (tvName.getText().toString().equalsIgnoreCase("anniversary")){
            img.setImageResource(R.drawable.anniversary);
        }
        else if (tvName.getText().toString().equalsIgnoreCase("vaccination")){
            img.setImageResource(R.drawable.pencil);
        }
        else{
            img.setImageResource(R.drawable.star2);
        }
        return rowView;
    }
}

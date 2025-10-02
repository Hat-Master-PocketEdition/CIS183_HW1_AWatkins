package com.example.cis183_hw1_awatkins;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;
    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }

    @Override
    public int getCount() {return listOfColors.size();}

    @Override
    public Object getItem(int position) {return listOfColors.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.activity_color_cell, null);
        }

        //find the GUI elements in our custom cell
        TextView redText = view.findViewById(R.id.widget_red_out);
        TextView bluText = view.findViewById(R.id.widget_blu_out);
        TextView grnText  = view.findViewById(R.id.widget_grn_out);
        TextView hexText  = view.findViewById(R.id.widget_hex_out);


        //Get data for this pet from the listOfPets
        //we can access different elements based off the
        //position value that is passed to this function
        ColorInfo col = listOfColors.get(position);

        //set the GUI text for the custom cells
        if(!col.getTextColor())
        {
            redText.setTextColor(Color.parseColor("#FFFFFF"));
            bluText.setTextColor(Color.parseColor("#FFFFFF"));
            grnText.setTextColor(Color.parseColor("#FFFFFF"));
            hexText.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            redText.setTextColor(Color.parseColor("#000000"));
            bluText.setTextColor(Color.parseColor("#000000"));
            grnText.setTextColor(Color.parseColor("#000000"));
            hexText.setTextColor(Color.parseColor("#000000"));
        }
        redText.setText("RED: " + col.getRed());
        grnText.setText("GREEN: " + col.getGrn());
        bluText.setText("BLUE: " + col.getBlu());
        hexText.setText("HEX: " + col.getHex());
        view.setBackgroundColor(Color.parseColor(col.getHex()));

        return view;
    }
}

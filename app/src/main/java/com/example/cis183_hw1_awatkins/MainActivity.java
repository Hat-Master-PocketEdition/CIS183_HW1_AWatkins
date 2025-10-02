package com.example.cis183_hw1_awatkins;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<ColorInfo> listOfColors;
    TextView title;
    SeekBar redBar;
    SeekBar bluBar;
    SeekBar grnBar;
    TextView redDisplay;
    TextView bluDisplay;
    TextView grnDisplay;
    TextView output;
    ListView colorList;
    ColorListAdapter clAdapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        redBar = findViewById(R.id.gui_seekbar_red);
        bluBar = findViewById(R.id.gui_seekbar_blu);
        grnBar = findViewById(R.id.gui_seekbar_grn);

        title = findViewById(R.id.gui_title);
        redDisplay = findViewById(R.id.gui_red_output);
        bluDisplay = findViewById(R.id.gui_blu_output);
        grnDisplay = findViewById(R.id.gui_grn_output);
        output = findViewById(R.id.gui_output);

        colorList = findViewById(R.id.color_listView);
        addButton = findViewById(R.id.addButton);

        listOfColors = new ArrayList<>();

        setBackgroundColor(redBar.getProgress(), grnBar.getProgress(), bluBar.getProgress());
        addDummyData();
        fillListView();
        displayAllColorData();
        onChangeListener();
        onClickListener();
        onClickListenerListView();
    }
    private void onClickListenerListView()
    {
        colorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ColorInfo selected = listOfColors.get(position);
                setBackgroundColor(selected.getRed(), selected.getGrn(), selected.getBlu());
            }
        });
    }
    public void onClickListener()
    {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hexR = Integer.toHexString(redBar.getProgress());
                if (hexR.length() < 2) {hexR = "0" + hexR;}
                String hexG = Integer.toHexString(grnBar.getProgress());
                if (hexG.length() < 2) {hexG = "0" + hexG;}
                String hexB = Integer.toHexString(bluBar.getProgress());
                if (hexB.length() < 2) {hexB = "0" + hexB;}
                String hexFull = "#" + hexR + hexG + hexB;
                ColorInfo addingColor = new ColorInfo(redBar.getProgress(), grnBar.getProgress(), bluBar.getProgress(), hexFull);
                listOfColors.add(addingColor);
                fillListView();
            }
        });
    }
    public void setBackgroundColor(int r, int g, int b)
    {
        String hexR = Integer.toHexString(r);
        if (hexR.length() < 2) {hexR = "0" + hexR;}
        String hexG = Integer.toHexString(g);
        if (hexG.length() < 2) {hexG = "0" + hexG;}
        String hexB = Integer.toHexString(b);
        if (hexB.length() < 2) {hexB = "0" + hexB;}
        String hexFull = "#" + hexR + hexG + hexB;
        if(r <= 128 && g <= 128 && b <= 128)
        {
            redDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            bluDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            grnDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            title.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            output.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        }
        else
        {
            redDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            bluDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            grnDisplay.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            title.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            output.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        }
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(hexFull));
        output.setText("RGB HEX VALUE: " + hexFull);
    }
    private void addDummyData()
    {
        ColorInfo dummyColorData = new ColorInfo(255, 255, 255, "#FFFFFF");
        listOfColors.add(dummyColorData);
        dummyColorData = new ColorInfo(132,37,147,"#842593");
        Log.d("HEX INPUT: ", "#842593");
        listOfColors.add(dummyColorData);
        Log.d("HEX OUTPUT: ", " " + dummyColorData.getHex());
        dummyColorData = new ColorInfo(4, 4, 112, "#040470");
        listOfColors.add(dummyColorData);
    }
    public void onChangeListener()
    {
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                String hexTest = Integer.toHexString(progress);
                if (hexTest.length() < 2)
                {
                    hexTest = "0" + hexTest;
                }
                if (progress < 0) {progress = 0;}
                redDisplay.setText("RED: " + progress);
                //Log.d("UPDATE: ", "SET RED PROGRESS TO " + hexTest);
                setBackgroundColor(redBar.getProgress(), grnBar.getProgress(), bluBar.getProgress());

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        grnBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (progress < 0) {progress = 0;}
                grnDisplay.setText("GREEN: " + progress);
                //Log.d("UPDATE: ", "SET GREEN PROGRESS TO" + progress);
                setBackgroundColor(redBar.getProgress(), grnBar.getProgress(), bluBar.getProgress());

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        bluBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (progress < 0) {progress = 0;}
                bluDisplay.setText("BLUE: " + progress);
                //Log.d("UPDATE: ", "SET BLUE PROGRESS TO" + progress);
                setBackgroundColor(redBar.getProgress(), grnBar.getProgress(), bluBar.getProgress());

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void fillListView()
    {
        clAdapter = new ColorListAdapter(this, listOfColors);
        colorList.setAdapter(clAdapter);
    }
    private void displayAllColorData()
    {
        for(int i = 0; i < listOfColors.size(); i++)
        {
            //Log.d("Color Info", listOfColors.get(i).getHex().toString());
        }
    }
}

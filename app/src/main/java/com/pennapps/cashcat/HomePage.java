package com.pennapps.cashcat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.parse.ParseUser;

import java.util.ArrayList;


public class HomePage extends Activity {
    // button to create new category
    Button newCategory;
    public PieChart chart;
    private LinearLayout mainLayout;

    private float[] ydata = {5, 10, 15, 30, 40};
    private String[] xdata = {"A", "B", "C", "D", "E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mainLayout = (LinearLayout) findViewById(R.id.home_layout);

        newCategory = (Button) findViewById(R.id.new_category_button);

        newCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, NewCategory.class);
                startActivity(i);
            }
        });

        chart = (PieChart) findViewById(R.id.pie);
        //add piechart to main view
//        mainLayout.addView(chart);

        //configure piechart
        chart.setUsePercentValues(true);
        chart.setDescription("Letters Test");

        //piechart hole
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.LTGRAY);
        chart.setHoleRadius(7);
        chart.setTransparentCircleRadius(10);

        //enable rotation by touch
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);

        //set a chart value selected listener
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i) {
                if (entry == null)
                    return;
            }

            @Override
            public void onNothingSelected() {
                // left blank
            }
        });

        addData();

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.transactions:
                Intent intent = new Intent(this, ShowTransactions.class);
                startActivity(intent);
                return true;

            case R.id.logout:
                ParseUser.logOut();
                Intent myIntent = new Intent(HomePage.this, DispatchActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < ydata.length; i++)
            yVals.add(new Entry(ydata[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xdata.length; i++)
            xVals.add(xdata[i]);

        PieDataSet dataSet = new PieDataSet(yVals, "Pie Chart");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);

        chart.setData(data);

        chart.highlightValues(null);

        chart.invalidate();
    }
}

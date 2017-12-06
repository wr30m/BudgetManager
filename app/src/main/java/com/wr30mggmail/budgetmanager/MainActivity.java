package com.wr30mggmail.budgetmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.wr30mggmail.budgetmanager.model.PurchaseCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public PieChart chart;
    private CategoryManager categoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryManager = new CategoryManager();
        chart = (PieChart) findViewById(R.id.chartPie);
        mockChart();
    }

    public void mockChart() {
        List<PieEntry> entries = new ArrayList<>();
        categoryManager.initCategories();
        for (PurchaseCategory category : categoryManager.purchaseCategories) {
            String label = category.getLabel();
            Float value = category.getAmount().floatValue();
            entries.add(new PieEntry(value, label));
        }

        PieDataSet set = new PieDataSet(entries, "Result");
        set.setSliceSpace(5.6f);
        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();
    }
}

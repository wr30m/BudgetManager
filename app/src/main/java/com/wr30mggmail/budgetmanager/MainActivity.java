package com.wr30mggmail.budgetmanager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.wr30mggmail.budgetmanager.model.PurchaseCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddCashDialogFragment.AddCashDialogListener {

    public PieChart chart;
    private CategoryManager categoryManager;
    private Button changeCashBtn;
    private TextView cashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryManager = new CategoryManager();

        cashView = (TextView) findViewById(R.id.cashAmount);

        changeCashBtn = (Button) findViewById(R.id.changeCashBtn);
        changeCashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddCashDialogFragment();
            }
        });

        chart = (PieChart) findViewById(R.id.chartPie);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
            }
        });
        mockChart();
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, inputText, Toast.LENGTH_LONG).show();
        CashManager handleCash = CashManager.getInstance();
        handleCash.addToCash(Double.parseDouble(inputText));
        cashView.setText(handleCash.getAmountCash().toString());
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

    private void startAddCashDialogFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddCashDialogFragment addCashDialogFragment = AddCashDialogFragment.newInstance();
        addCashDialogFragment.show(fragmentManager, "fragment_add_cash");
    }

    public void showToast() {
        Toast.makeText(this, "work", Toast.LENGTH_LONG).show();
    }
}

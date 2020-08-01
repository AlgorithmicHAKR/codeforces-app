package com.hakr.aman.codeforcesapp;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class Formatter implements IAxisValueFormatter{

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return "" + ((int) value);
    }
}

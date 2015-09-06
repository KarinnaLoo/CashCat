package com.pennapps.cashcat;

import android.util.Log;

import com.parse.ui.ParseLoginDispatchActivity;

public class DispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        Log.d("REACHED THIS POINT", "HIHIHIH");
        return HomePage.class;
    }
}

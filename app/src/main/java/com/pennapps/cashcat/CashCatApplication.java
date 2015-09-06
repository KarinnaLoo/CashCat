package com.pennapps.cashcat;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

/**
 * Created by kloo on 9/6/15.
 */
public class CashCatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "9aMog2nNexpD9lz0H2SUMdRgaguiy1RIwM9fAKVE", "7qAadPFxNyjUn9tuSIRkHL4F8VmoW1AbN51rYgEt");
//        PushService.startServiceIfRequired(getApplicationContext());
        ParseInstallation parseInstallation = ParseInstallation.getCurrentInstallation();

        // Save the updated installation object
        parseInstallation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("PushApplication", "Installation saved successfully");
                } else {
                    Log.e("PushApplication", "Installation save failed", e);
                }
            }
        });

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}


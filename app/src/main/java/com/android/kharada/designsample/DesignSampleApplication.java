package com.android.kharada.designsample;

import android.app.Application;
import android.util.Log;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by haradakazumi on 2017/01/29.
 */

public class DesignSampleApplication extends Application{

    private static final String TAG = "";

    @Override
    public void onCreate() {
        /** Called when the Application-class is first created. */
        Log.v(TAG,"--- onCreate() in ---");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public void onTerminate() {
        /** This Method Called when this Application finished. */
        Log.v(TAG,"--- onTerminate() in ---");
    }


}

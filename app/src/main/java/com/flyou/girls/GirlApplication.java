package com.flyou.girls;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by luffy on 16/4/23.
 */
public class GirlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), "900027091", false);
    }
}

package com.shnaboo.troll.watsonvr.System;

import android.app.Application;
import android.content.Context;







public class GlobalManager extends Application {

    private static Context context;


    @Override
    public void onCreate() {

        super.onCreate();

        GlobalManager.context = getApplicationContext();


    }




    public static Context getAppContext() {
        return GlobalManager.context;
    }




}

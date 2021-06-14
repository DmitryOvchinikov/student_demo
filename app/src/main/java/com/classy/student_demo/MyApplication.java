package com.classy.student_demo;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    private static final String AF_DEV_KEY = "zFmHvByRjSNRKGWkHXbvcn";

    @Override
    public void onCreate() {
        super.onCreate();
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {
                for (String attrName : map.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + map.get(attrName));
                }
            }

            @Override
            public void onConversionDataFail(String s) {
                Log.d("LOG_TAG", "error getting conversion data: " + s);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {
                for (String attrName : map.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + map.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String s) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + s);
            }
        };

        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
    }
}

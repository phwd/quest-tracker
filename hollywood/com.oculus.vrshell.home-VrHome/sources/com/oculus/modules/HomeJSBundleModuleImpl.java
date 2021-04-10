package com.oculus.modules;

import android.content.Context;
import com.oculus.vrshell.home.BuildConfig;

public class HomeJSBundleModuleImpl extends JSBundleModuleImpl {
    public HomeJSBundleModuleImpl(Context context) {
        super(context, BuildConfig.APP_BUILD_TIME_MILLIS);
    }
}

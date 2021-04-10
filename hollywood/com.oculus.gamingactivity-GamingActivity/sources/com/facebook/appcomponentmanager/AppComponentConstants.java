package com.facebook.appcomponentmanager;

import com.facebook.appcomponentmanager.build.BuildConfig;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppComponentConstants {
    public static final String INTENT_ACTION_CMP_ENABLING_DONE = "com.facebook.appcomponentmanager.ENABLING_CMPS_DONE";
    private static final boolean IS_PRE_TOS_BUILD = BuildConfig.IS_PRE_TOS_BUILD;
    public static final String PPRETOS_PROCESS_NAME = "pretosproc";

    public static final boolean isPreTosBuild() {
        return IS_PRE_TOS_BUILD;
    }
}

package com.oculus.util;

public class WakeupUtil {
    public static final String HOME_WAKEUP_ACTION = "com.oculus.home.wakeup";
    public static final String TAG = "WakeupUtil";

    public enum Action {
        DO_LOGGING("com.oculus.home.wakeup.logging"),
        FETCH_GK("com.oculus.home.wakeup.gkfetch"),
        FETCH_QE("com.oculus.home.wakeup.qefetch"),
        ON_LOGOUT("com.oculus.home.wakeup.logout"),
        FETCH_CONFIG("com.oculus.home.wakeup.configfetch");
        
        public final String extraKey;

        /* access modifiers changed from: public */
        Action(String str) {
            this.extraKey = str;
        }
    }
}

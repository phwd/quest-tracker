package com.oculus.util;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass0kG;
import X.AnonymousClass0kU;
import android.content.Context;
import android.content.Intent;

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

    public static void wakeupHomeForAction(Context context, Action action) {
        Intent intent = new Intent();
        intent.setAction(HOME_WAKEUP_ACTION);
        intent.putExtra(action.extraKey, true);
        intent.setFlags(32);
        try {
            AnonymousClass0kG.A01(intent, context, AnonymousClass006.A07(WakeupUtil.class.getSimpleName(), "::wakeupHomeForAction()"));
            context.sendBroadcast(intent);
        } catch (AnonymousClass0kU e) {
            AnonymousClass0MD.A07(TAG, "Error sending wakeup intent.", e);
        }
    }
}

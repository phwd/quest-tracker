package com.oculus.util;

import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.exception.CannotAttachCallerInfoException;

public class WakeupUtil {
    public static final String HOME_WAKEUP_ACTION = "com.oculus.home.wakeup";
    private static final String TAG = "WakeupUtil";

    public enum Action {
        DO_LOGGING("com.oculus.home.wakeup.logging"),
        FETCH_GK("com.oculus.home.wakeup.gkfetch"),
        FETCH_QE("com.oculus.home.wakeup.qefetch"),
        ON_LOGOUT("com.oculus.home.wakeup.logout"),
        FETCH_CONFIG("com.oculus.home.wakeup.configfetch");
        
        public final String extraKey;

        private Action(String str) {
            this.extraKey = str;
        }
    }

    public static void wakeupHomeForAction(Context context, Action action) {
        BLog.i(TAG, "Call Home wakeup for: %s", action.toString());
        Intent intent = new Intent();
        intent.setAction(HOME_WAKEUP_ACTION);
        intent.putExtra(action.extraKey, true);
        intent.setFlags(32);
        try {
            CallerInfoHelper.attachCallerInfo(intent, context, WakeupUtil.class.getSimpleName() + "::wakeupHomeForAction()");
            context.sendBroadcast(intent);
        } catch (CannotAttachCallerInfoException e) {
            BLog.e(TAG, "Error sending wakeup intent.", e);
        }
    }
}

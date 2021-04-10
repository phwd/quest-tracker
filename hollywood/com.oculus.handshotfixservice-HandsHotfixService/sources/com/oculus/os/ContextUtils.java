package com.oculus.os;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;

public class ContextUtils {
    public static IBinder getWindowToken(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getDecorView().getWindowId().getTarget().asBinder();
        }
        if (context instanceof DialogContext) {
            return ((DialogContext) context).getWindow().getDecorView().getWindowId().getTarget().asBinder();
        }
        if (context instanceof WindowTokenContext) {
            return ((WindowTokenContext) context).getWindowToken();
        }
        return null;
    }
}

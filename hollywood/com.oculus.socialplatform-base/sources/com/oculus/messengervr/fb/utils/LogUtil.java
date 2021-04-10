package com.oculus.messengervr.fb.utils;

import android.util.Log;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class LogUtil {
    public static final String LOG_TAG = "VR_MESSENGER_API";
    public static final String MQTT = "MQTT";
    public static final String REGULAR = "REGULAR";

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        Log.e(StringFormatUtil.formatStrLocaleSafe("[%s]%s", "VR_MESSENGER_API", str), StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
    }

    public static void d(String str, String str2, Object... objArr) {
    }

    public static void v(String str, String str2, Object... objArr) {
    }
}

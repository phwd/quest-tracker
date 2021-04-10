package com.oculus.platform.util;

import android.content.Context;
import android.provider.Settings;

public class DeviceUtil {
    public static String getDeviceId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        }
        return padStart(string, 16, '0');
    }

    public static String padStart(String str, int i, char c) {
        int length = str.length();
        if (length >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i);
        while (length < i) {
            sb.append(c);
            length++;
        }
        sb.append(str);
        return sb.toString();
    }
}

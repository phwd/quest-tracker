package com.facebook.acra;

import X.C0139Dd;
import android.content.Context;
import android.content.pm.PackageManager;

public class DeviceFeaturesCollector {
    public static String getFeatures(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Object[] objArr = (Object[]) PackageManager.class.getMethod("getSystemAvailableFeatures", null).invoke(context.getPackageManager(), new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    String str = (String) obj.getClass().getField(AppComponentStats.ATTRIBUTE_NAME).get(obj);
                    if (str != null) {
                        stringBuffer.append(str);
                    } else {
                        stringBuffer.append("glEsVersion = ");
                        stringBuffer.append((String) obj.getClass().getMethod("getGlEsVersion", null).invoke(obj, new Object[0]));
                    }
                    stringBuffer.append("\n");
                }
            }
        } catch (Throwable th) {
            C0139Dd.A0W("ACRA", th, "Couldn't retrieve device features for %s", context.getPackageName());
            stringBuffer.append("Could not retrieve data: ");
            stringBuffer.append(th.getMessage());
        }
        return stringBuffer.toString();
    }
}

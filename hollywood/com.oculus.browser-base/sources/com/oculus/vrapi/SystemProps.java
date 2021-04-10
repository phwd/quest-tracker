package com.oculus.vrapi;

import android.os.Build;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SystemProps {

    /* renamed from: a  reason: collision with root package name */
    public static Class f9720a;

    static {
        try {
            f9720a = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            return ((Boolean) f9720a.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception unused) {
            return z;
        }
    }

    public static boolean getDeviceDocked() {
        return a("sys.hmt.connected", false);
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static boolean getHmtMounted() {
        return a("sys.hmt.mounted", true);
    }
}

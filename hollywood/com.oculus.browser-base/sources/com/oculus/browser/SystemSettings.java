package com.oculus.browser;

import com.oculus.os.SettingsManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SystemSettings {

    /* renamed from: a  reason: collision with root package name */
    public static SettingsManager f9712a;

    public static boolean getBoolean(String str, boolean z) {
        try {
            if (f9712a == null) {
                f9712a = new SettingsManager();
            }
            return f9712a.getBoolean(str, z);
        } catch (RuntimeException e) {
            StringBuilder k = AbstractC2531fV.k("Failed to get system setting ", str, " due to error ");
            k.append(e.toString());
            AbstractC1220Ua0.d("SystemSettings", k.toString(), new Object[0]);
            return z;
        }
    }

    public static void setBoolean(String str, boolean z) {
        try {
            if (f9712a == null) {
                f9712a = new SettingsManager();
            }
            f9712a.setBoolean(str, z);
            AbstractC1220Ua0.d("SystemSettings", "Set System Value " + str + ": " + z, new Object[0]);
        } catch (RuntimeException e) {
            StringBuilder k = AbstractC2531fV.k("Failed to set system setting ", str, " due to error ");
            k.append(e.toString());
            AbstractC1220Ua0.d("SystemSettings", k.toString(), new Object[0]);
        }
    }
}

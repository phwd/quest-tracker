package org.chromium.base;

import android.os.StrictMode;
import java.util.TimeZone;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TimezoneUtils {
    public static String getDefaultTimeZoneId() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        String id = TimeZone.getDefault().getID();
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return id;
    }
}

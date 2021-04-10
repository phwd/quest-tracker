package org.chromium.base;

import android.app.ActivityManager;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SysUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f10595a;
    public static Integer b;

    public static int a() {
        if (b == null) {
            b = Integer.valueOf(b());
        }
        return b.intValue();
    }

    /* JADX INFO: finally extract failed */
    public static int b() {
        Pattern compile = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            Log.w("SysUtils", "/proc/meminfo lacks a MemTotal entry?");
                            break;
                        }
                        Matcher matcher = compile.matcher(readLine);
                        if (matcher.find()) {
                            int parseInt = Integer.parseInt(matcher.group(1));
                            if (parseInt <= 1024) {
                                Log.w("SysUtils", "Invalid /proc/meminfo total size in kB: " + matcher.group(1));
                            } else {
                                bufferedReader.close();
                                fileReader.close();
                                StrictMode.setThreadPolicy(allowThreadDiskReads);
                                return parseInt;
                            }
                        }
                    } catch (Throwable th) {
                        bufferedReader.close();
                        throw th;
                    }
                }
                bufferedReader.close();
            } finally {
                fileReader.close();
            }
        } catch (Exception e) {
            Log.w("SysUtils", "Cannot get total physical size from /proc/meminfo", e);
        } catch (Throwable th2) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th2;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return 0;
    }

    public static boolean isCurrentlyLowMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    public static boolean isLowEndDevice() {
        if (f10595a == null) {
            boolean z = true;
            if (!AbstractC1575Zv.e().g("enable-low-end-device-mode")) {
                if (AbstractC1575Zv.e().g("disable-low-end-device-mode")) {
                    z = false;
                } else {
                    Integer valueOf = Integer.valueOf(b());
                    b = valueOf;
                    boolean z2 = valueOf.intValue() > 0 && (Build.VERSION.SDK_INT < 26 ? b.intValue() / 1024 <= 512 : b.intValue() / 1024 <= 1024);
                    if (z2 != (ContextUtils.getApplicationContext() != null ? ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).isLowRamDevice() : false)) {
                        z = false;
                    }
                    AbstractC3100ip1.f10165a.a("Android.SysUtilsLowEndMatches", z);
                    z = z2;
                }
            }
            f10595a = Boolean.valueOf(z);
        }
        return f10595a.booleanValue();
    }
}

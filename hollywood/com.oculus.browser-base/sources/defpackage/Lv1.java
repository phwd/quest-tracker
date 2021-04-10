package defpackage;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ThreadUtils;

/* renamed from: Lv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Lv1 {

    /* renamed from: a  reason: collision with root package name */
    public static Jv1 f8448a = null;
    public static long b = Long.MAX_VALUE;
    public static boolean c;

    public static boolean a() {
        Jv1 jv1 = f8448a;
        return jv1 != null && b != Long.MAX_VALUE && !jv1.a() && SystemClock.elapsedRealtime() - b < 300000;
    }

    public static void b(Context context) {
        TelephonyManager telephonyManager;
        Set set;
        List<ScanResult> list;
        TelephonyManager telephonyManager2;
        Object obj = ThreadUtils.f10596a;
        if (!a() && !c) {
            c = true;
            Kv1 kv1 = new Kv1();
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            TelephonyManager telephonyManager3 = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            Iv1 c2 = AbstractC4027oD0.c(context, wifiManager);
            Hv1 b2 = AbstractC4027oD0.b(context, telephonyManager3);
            if (!AbstractC4027oD0.e(context)) {
                set = Collections.emptySet();
                telephonyManager = telephonyManager3;
            } else {
                HashSet hashSet = new HashSet();
                List<ScanResult> scanResults = wifiManager.getScanResults();
                if (scanResults != null) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long currentTimeMillis = System.currentTimeMillis();
                    int i = 0;
                    while (i < scanResults.size()) {
                        ScanResult scanResult = scanResults.get(i);
                        String str = scanResult.BSSID;
                        if (str == null) {
                            list = scanResults;
                            telephonyManager2 = telephonyManager3;
                        } else {
                            list = scanResults;
                            telephonyManager2 = telephonyManager3;
                            hashSet.add(new Iv1(scanResult.SSID, str, Integer.valueOf(scanResult.level), Long.valueOf(currentTimeMillis - (elapsedRealtime - TimeUnit.MICROSECONDS.toMillis(scanResult.timestamp)))));
                        }
                        i++;
                        telephonyManager3 = telephonyManager2;
                        scanResults = list;
                    }
                }
                telephonyManager = telephonyManager3;
                set = hashSet;
            }
            C3685mD0 md0 = new C3685mD0(kv1, c2, b2, set);
            if (!AbstractC4027oD0.f(context)) {
                md0.onResult(Collections.emptySet());
                return;
            }
            C3514lD0 ld0 = new C3514lD0(md0);
            if (Build.VERSION.SDK_INT >= 29) {
                C4520r7.b(telephonyManager, ld0);
            } else {
                ld0.onResult(telephonyManager.getAllCellInfo());
            }
        }
    }
}

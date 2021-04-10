package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: oD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4027oD0 {
    public static Iv1 a(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return Iv1.f8258a;
        }
        String ssid = wifiInfo.getSSID();
        if (ssid == null || "<unknown ssid>".equals(ssid)) {
            ssid = null;
        } else if (ssid.startsWith("\"") && ssid.endsWith("\"") && ssid.length() > 2) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        return new Iv1(ssid, wifiInfo.getBSSID(), null, Long.valueOf(System.currentTimeMillis()));
    }

    public static Hv1 b(Context context, TelephonyManager telephonyManager) {
        Hv1 hv1;
        int i;
        CellInfo cellInfo;
        if (!f(context)) {
            hv1 = Hv1.b;
        } else {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                int i2 = 0;
                int i3 = 0;
                cellInfo = null;
                while (true) {
                    if (i2 >= allCellInfo.size()) {
                        break;
                    }
                    CellInfo cellInfo2 = allCellInfo.get(i2);
                    if (cellInfo2.isRegistered()) {
                        i3++;
                        if (i3 > 1) {
                            break;
                        }
                        cellInfo = cellInfo2;
                    }
                    i2++;
                }
                hv1 = d(cellInfo, SystemClock.elapsedRealtime(), System.currentTimeMillis());
            }
            cellInfo = null;
            hv1 = d(cellInfo, SystemClock.elapsedRealtime(), System.currentTimeMillis());
        }
        if (hv1 == null || ((i = hv1.c) != 0 && i != 1)) {
            return hv1;
        }
        return null;
    }

    public static Iv1 c(Context context, WifiManager wifiManager) {
        Iv1 iv1;
        if (e(context)) {
            iv1 = a(wifiManager.getConnectionInfo());
        } else if (f(context)) {
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (registerReceiver != null) {
                iv1 = a((WifiInfo) registerReceiver.getParcelableExtra("wifiInfo"));
            } else {
                iv1 = Iv1.f8258a;
            }
        } else {
            iv1 = Iv1.f8258a;
        }
        if (iv1.c == null) {
            return null;
        }
        return iv1;
    }

    public static Hv1 d(CellInfo cellInfo, long j, long j2) {
        if (cellInfo == null) {
            return Hv1.f8189a;
        }
        long millis = j2 - (j - TimeUnit.NANOSECONDS.toMillis(cellInfo.getTimeStamp()));
        if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity = ((CellInfoCdma) cellInfo).getCellIdentity();
            Gv1 a2 = Hv1.a(2);
            a2.b = Integer.valueOf(cellIdentity.getBasestationId());
            a2.c = Integer.valueOf(cellIdentity.getNetworkId());
            a2.e = Integer.valueOf(cellIdentity.getSystemId());
            a2.i = Long.valueOf(millis);
            return a2.a();
        } else if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity2 = ((CellInfoGsm) cellInfo).getCellIdentity();
            Gv1 a3 = Hv1.a(3);
            a3.b = Integer.valueOf(cellIdentity2.getCid());
            a3.c = Integer.valueOf(cellIdentity2.getLac());
            a3.d = Integer.valueOf(cellIdentity2.getMcc());
            a3.e = Integer.valueOf(cellIdentity2.getMnc());
            a3.i = Long.valueOf(millis);
            return a3.a();
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            Gv1 a4 = Hv1.a(4);
            a4.b = Integer.valueOf(cellIdentity3.getCi());
            a4.d = Integer.valueOf(cellIdentity3.getMcc());
            a4.e = Integer.valueOf(cellIdentity3.getMnc());
            a4.g = Integer.valueOf(cellIdentity3.getPci());
            a4.h = Integer.valueOf(cellIdentity3.getTac());
            a4.i = Long.valueOf(millis);
            return a4.a();
        } else if (!(cellInfo instanceof CellInfoWcdma)) {
            return Hv1.f8189a;
        } else {
            CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
            Gv1 a5 = Hv1.a(5);
            a5.b = Integer.valueOf(cellIdentity4.getCid());
            a5.c = Integer.valueOf(cellIdentity4.getLac());
            a5.d = Integer.valueOf(cellIdentity4.getMcc());
            a5.e = Integer.valueOf(cellIdentity4.getMnc());
            a5.f = Integer.valueOf(cellIdentity4.getPsc());
            a5.i = Long.valueOf(millis);
            return a5.a();
        }
    }

    public static boolean e(Context context) {
        return f(context) && g(context, "android.permission.ACCESS_WIFI_STATE");
    }

    public static boolean f(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return g(context, "android.permission.ACCESS_FINE_LOCATION");
        }
        return g(context, "android.permission.ACCESS_COARSE_LOCATION") || g(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    public static boolean g(Context context, String str) {
        return AbstractC3153j7.a(context, str, Process.myPid(), Process.myUid()) == 0;
    }
}

package com.facebook.wifiscan;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class WifiScanFilter {
    private static final Comparator<WifiScanResult> SIGNAL_STRENGTH_COMPARATOR = new Comparator<WifiScanResult>() {
        /* class com.facebook.wifiscan.WifiScanFilter.AnonymousClass1 */

        public int compare(WifiScanResult wifiScanResult, WifiScanResult wifiScanResult2) {
            return wifiScanResult2.level - wifiScanResult.level;
        }
    };

    public static List<WifiScanResult> filterScanResults(@Nullable List<WifiScanResult> list, int i, int i2, int i3) {
        if (list == null) {
            return new ArrayList();
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, SIGNAL_STRENGTH_COMPARATOR);
        ArrayList arrayList2 = new ArrayList(Math.min(i3, arrayList.size()));
        for (int i4 = 0; i4 < size && arrayList2.size() < i3; i4++) {
            WifiScanResult wifiScanResult = (WifiScanResult) arrayList.get(i4);
            if (wifiScanResult.level >= i || arrayList2.size() < i2) {
                arrayList2.add(wifiScanResult);
            }
        }
        return arrayList2;
    }

    public static List<WifiScanResult> filterForUniqueSsidScanResults(@Nullable List<WifiScanResult> list) {
        return filterForUniqueSsidScanResults(list, true);
    }

    public static List<WifiScanResult> filterForUniqueSsidScanResults(@Nullable List<WifiScanResult> list, boolean z) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList<WifiScanResult> arrayList = new ArrayList(list);
        Collections.sort(arrayList, SIGNAL_STRENGTH_COMPARATOR);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        HashSet hashSet = new HashSet(arrayList.size());
        for (WifiScanResult wifiScanResult : arrayList) {
            if ((z || !TextUtils.isEmpty(wifiScanResult.SSID)) && !hashSet.contains(wifiScanResult.SSID)) {
                hashSet.add(wifiScanResult.SSID);
                arrayList2.add(wifiScanResult);
            }
        }
        return arrayList2;
    }

    private WifiScanFilter() {
    }
}

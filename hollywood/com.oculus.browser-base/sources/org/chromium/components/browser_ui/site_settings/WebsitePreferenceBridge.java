package org.chromium.components.browser_ui.site_settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebsitePreferenceBridge {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface StorageInfoClearedCallback {
        void onStorageInfoCleared();
    }

    public static boolean a(int i) {
        return i == 16;
    }

    public static void addContentSettingExceptionToList(ArrayList arrayList, int i, String str, String str2, int i2, String str3) {
        arrayList.add(new C1032Qy(i, str, str2, Integer.valueOf(i2), str3));
    }

    public static Object createLocalStorageInfoMap() {
        return new HashMap();
    }

    public static Object createStorageInfoList() {
        return new ArrayList();
    }

    public static void insertChosenObjectInfoIntoList(ArrayList arrayList, int i, String str, String str2, String str3, String str4, boolean z) {
        arrayList.add(new C5316vp(i, str, str2, str3, str4, z));
    }

    public static void insertLocalStorageInfoIntoMap(HashMap hashMap, String str, long j, boolean z) {
        hashMap.put(str, new V90(str, j, z));
    }

    public static void insertPermissionInfoIntoList(int i, ArrayList arrayList, String str, String str2, boolean z) {
        if (i == 10 || i == 9) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                OB0 ob0 = (OB0) it.next();
                if (ob0.H.equals(str) && ob0.G.equals(str2)) {
                    return;
                }
            }
        }
        arrayList.add(new OB0(i, str, str2, z));
    }

    public static void insertStorageInfoIntoList(ArrayList arrayList, String str, int i, long j) {
        arrayList.add(new I21(str, i, j));
    }
}

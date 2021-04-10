package org.chromium.chrome.browser.sharing;

import J.N;
import java.util.ArrayList;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SharingServiceProxy {

    /* renamed from: a  reason: collision with root package name */
    public static SharingServiceProxy f10761a;
    public static long b;

    public static SharingServiceProxy a() {
        SharingServiceProxy sharingServiceProxy = f10761a;
        if (sharingServiceProxy != null) {
            return sharingServiceProxy;
        }
        if (b == 0) {
            N.MI$va2Pq(Profile.b());
        }
        SharingServiceProxy sharingServiceProxy2 = new SharingServiceProxy();
        f10761a = sharingServiceProxy2;
        return sharingServiceProxy2;
    }

    public static void createDeviceInfoAndAppendToList(ArrayList arrayList, String str, String str2, int i, long j) {
        arrayList.add(new SU0(null));
    }

    public static void onProxyCreated(long j) {
        b = j;
    }

    public static void onProxyDestroyed() {
        b = 0;
    }
}

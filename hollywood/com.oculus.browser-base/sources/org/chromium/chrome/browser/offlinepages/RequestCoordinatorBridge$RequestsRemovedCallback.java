package org.chromium.chrome.browser.offlinepages;

import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RequestCoordinatorBridge$RequestsRemovedCallback {
    public void onResult(long[] jArr, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jArr.length; i++) {
            arrayList.add(new C1831bM0(jArr[i], iArr[i]));
        }
        throw null;
    }
}

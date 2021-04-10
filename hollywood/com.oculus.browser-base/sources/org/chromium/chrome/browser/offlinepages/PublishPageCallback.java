package org.chromium.chrome.browser.offlinepages;

import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublishPageCallback extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public Callback f10720a;
    public OfflinePageItem b;
    public WindowAndroid c;

    public PublishPageCallback(WindowAndroid windowAndroid, OfflinePageItem offlinePageItem, Callback callback) {
        this.c = windowAndroid;
        this.b = offlinePageItem;
        this.f10720a = callback;
    }

    public void onResult(String str) {
        OfflinePageItem offlinePageItem;
        if (!str.isEmpty()) {
            OfflinePageItem offlinePageItem2 = this.b;
            String str2 = offlinePageItem2.f10719a;
            long j = offlinePageItem2.b;
            ClientId clientId = offlinePageItem2.c;
            offlinePageItem = new OfflinePageItem(str2, j, clientId.f10716a, clientId.b, offlinePageItem2.d, str, offlinePageItem2.f, offlinePageItem2.g, offlinePageItem2.h, offlinePageItem2.i, offlinePageItem2.j);
        } else {
            offlinePageItem = null;
        }
        AbstractC2254ds0.k(offlinePageItem, this.c, this.f10720a);
    }
}

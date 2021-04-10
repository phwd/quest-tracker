package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.offlinepages.PublishPageCallback;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Rr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1080Rr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final WindowAndroid f8858a;
    public final OfflinePageBridge b;
    public final OfflinePageItem c;
    public final Callback d;

    public C1080Rr0(WindowAndroid windowAndroid, OfflinePageBridge offlinePageBridge, OfflinePageItem offlinePageItem, Callback callback) {
        this.f8858a = windowAndroid;
        this.b = offlinePageBridge;
        this.c = offlinePageItem;
        this.d = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        WindowAndroid windowAndroid = this.f8858a;
        OfflinePageBridge offlinePageBridge = this.b;
        OfflinePageItem offlinePageItem = this.c;
        Callback callback = this.d;
        if (!((Boolean) obj).booleanValue()) {
            AbstractC3364kK0.g("OfflinePages.Sharing.PublishInternalPageResult", 14, 16);
            return;
        }
        PublishPageCallback publishPageCallback = new PublishPageCallback(windowAndroid, offlinePageItem, callback);
        N.MSHYzaXq(offlinePageBridge.f10718a, offlinePageBridge, offlinePageItem.b, publishPageCallback);
    }
}

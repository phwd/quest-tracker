package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: DO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DO0 implements OfflinePageBridge.SavePageCallback {

    /* renamed from: a  reason: collision with root package name */
    public WindowAndroid f7888a;
    public Callback b;
    public OfflinePageBridge c;

    public DO0(WindowAndroid windowAndroid, Callback callback, OfflinePageBridge offlinePageBridge) {
        this.f7888a = windowAndroid;
        this.b = callback;
        this.c = offlinePageBridge;
    }

    @Override // org.chromium.chrome.browser.offlinepages.OfflinePageBridge.SavePageCallback
    public void onSavePageDone(int i, String str, long j) {
        if (i == 0) {
            OfflinePageBridge offlinePageBridge = this.c;
            N.M8YdeM7z(offlinePageBridge.f10718a, offlinePageBridge, j, new CO0(this));
        }
    }
}

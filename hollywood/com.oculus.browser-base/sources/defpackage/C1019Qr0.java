package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Qr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1019Qr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final String f8789a;
    public final WebContents b;
    public final OfflinePageBridge c;
    public final OfflinePageItem d;
    public final boolean e;
    public final Callback f;

    public C1019Qr0(String str, WebContents webContents, OfflinePageBridge offlinePageBridge, OfflinePageItem offlinePageItem, boolean z, Callback callback) {
        this.f8789a = str;
        this.b = webContents;
        this.c = offlinePageBridge;
        this.d = offlinePageItem;
        this.e = z;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str = this.f8789a;
        WebContents webContents = this.b;
        OfflinePageBridge offlinePageBridge = this.c;
        OfflinePageItem offlinePageItem = this.d;
        boolean z = this.e;
        Callback callback = this.f;
        Uri uri = (Uri) obj;
        boolean z2 = false;
        if (offlinePageItem != null) {
            String str2 = offlinePageItem.e;
            if (!AbstractC2254ds0.g(uri)) {
                if (TextUtils.equals(uri.getScheme(), "http") || TextUtils.equals(uri.getScheme(), "https")) {
                    if (str2.isEmpty()) {
                        AbstractC1220Ua0.f("OfflinePageUtils", "Tried to share a page with no path.", new Object[0]);
                    }
                }
            }
            z2 = true;
        }
        if (!z2) {
            callback.onResult(null);
            return;
        }
        WindowAndroid I = webContents.I();
        String str3 = offlinePageItem.e;
        if (z || !N.MT9xecBl(offlinePageBridge.f10718a, offlinePageBridge, str3)) {
            AbstractC2254ds0.j(I, uri.toString(), str, str3, new File(str3), callback);
            return;
        }
        N.M5gQgQvs(offlinePageBridge.f10718a, offlinePageBridge, webContents, new C1080Rr0(I, offlinePageBridge, offlinePageItem, callback));
    }
}

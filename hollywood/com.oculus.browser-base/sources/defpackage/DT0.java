package defpackage;

import android.net.Uri;
import org.chromium.chrome.browser.util.ChromeFileProvider;

/* renamed from: DT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DT0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f7894a;

    public DT0(Uri uri) {
        this.f7894a = uri;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Uri uri = this.f7894a;
        Uri uri2 = (Uri) obj;
        Object obj2 = ChromeFileProvider.f10796J;
        synchronized (obj2) {
            ChromeFileProvider.M = uri2;
            ChromeFileProvider.K = ChromeFileProvider.d(uri);
            obj2.notify();
        }
    }
}

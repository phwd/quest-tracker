package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: d91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2137d91 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C2307e91 f9752a;
    public final Callback b;
    public final boolean c;

    public C2137d91(C2307e91 e91, Callback callback, boolean z) {
        this.f9752a = e91;
        this.b = callback;
        this.c = z;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        C2307e91 e91 = this.f9752a;
        Callback callback = this.b;
        boolean z = this.c;
        if (bitmap == null) {
            Drawable drawable = C2307e91.e;
            e91.d(drawable, z);
            callback.onResult(drawable);
            return;
        }
        callback.onResult(e91.f(bitmap, e91.j));
    }
}

package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: c91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1966c91 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C2307e91 f9587a;
    public final Callback b;
    public final boolean c;

    public C1966c91(C2307e91 e91, Callback callback, boolean z) {
        this.f9587a = e91;
        this.b = callback;
        this.c = z;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        Drawable drawable;
        C2307e91 e91 = this.f9587a;
        Callback callback = this.b;
        boolean z = this.c;
        if (bitmap == null) {
            if (e91.j) {
                drawable = C2307e91.b;
            } else {
                drawable = C2307e91.f9836a;
                e91.d(drawable, z);
            }
            callback.onResult(drawable);
            return;
        }
        callback.onResult(e91.f(bitmap, e91.j));
    }
}

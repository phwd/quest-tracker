package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: fn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2581fn0 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C2923hn0 f9952a;
    public final String b;

    public C2581fn0(C2923hn0 hn0, String str) {
        this.f9952a = hn0;
        this.b = str;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        Object obj;
        C2923hn0 hn0 = this.f9952a;
        String str2 = this.b;
        if (hn0.e.size() != 0) {
            for (int i = 0; i < hn0.j.b(); i++) {
                if (TextUtils.equals(str2, hn0.j.a(i).b.h())) {
                    if (bitmap != null) {
                        obj = new BitmapDrawable(bitmap);
                    } else if (AbstractC5154ur1.g(str2)) {
                        obj = hn0.g;
                    } else {
                        obj = new BitmapDrawable(hn0.c.c(str2, false));
                    }
                    ((C4765sb0) hn0.e.get(i)).b.m(AbstractC2752gn0.f10021a, obj);
                }
            }
        }
    }
}

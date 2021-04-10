package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;
import org.chromium.content_public.browser.NavigationEntry;

/* renamed from: Sm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1131Sm0 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C1375Wm0 f8916a;
    public final String b;

    public C1131Sm0(C1375Wm0 wm0, String str) {
        this.f8916a = wm0;
        this.b = str;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        C1375Wm0 wm0 = this.f8916a;
        String str2 = this.b;
        if (bitmap == null) {
            if (wm0.O == null) {
                wm0.O = new C3713mO();
            }
            bitmap = wm0.O.a(wm0.G.getResources(), str2, true);
        }
        for (int i = 0; i < wm0.f9172J.b(); i++) {
            NavigationEntry a2 = wm0.f9172J.a(i);
            if (TextUtils.equals(str2, a2.b.h())) {
                a2.g = bitmap;
            }
        }
        wm0.K.notifyDataSetChanged();
    }
}

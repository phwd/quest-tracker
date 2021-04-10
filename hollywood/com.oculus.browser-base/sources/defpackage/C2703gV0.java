package defpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.ShortcutHelper;

/* renamed from: gV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2703gV0 implements AbstractC2786gy1 {

    /* renamed from: a  reason: collision with root package name */
    public final Intent f10002a;
    public final String b;

    public C2703gV0(Intent intent, String str) {
        this.f10002a = intent;
        this.b = str;
    }

    @Override // defpackage.AbstractC2786gy1
    public void a(Xx1 xx1) {
        String str;
        Intent intent = this.f10002a;
        String str2 = this.b;
        String n = U20.n(intent, "org.chromium.chrome.browser.webapp_id");
        String n2 = U20.n(intent, "org.chromium.chrome.browser.webapp_url");
        C1932by1 by1 = null;
        Integer num = null;
        if (n == null || n2 == null) {
            AbstractC1220Ua0.a("WebappInfo", "Incomplete data provided: " + n + ", " + n2, new Object[0]);
        } else {
            long i = U20.i(intent, "org.chromium.chrome.browser.theme_color", 2147483648L);
            boolean b2 = AbstractC2103cy1.b(i);
            int i2 = b2 ? (int) i : -1;
            String n3 = U20.n(intent, "org.chromium.chrome.browser.webapp_icon");
            String n4 = U20.n(intent, "org.chromium.chrome.browser.webapp_scope");
            if (TextUtils.isEmpty(n4)) {
                n4 = ShortcutHelper.d(n2);
            }
            int h = U20.h(intent, "org.chromium.chrome.browser.webapp_display_mode", 3);
            int h2 = U20.h(intent, "org.chromium.content_public.common.orientation", 0);
            int h3 = U20.h(intent, "org.chromium.chrome.browser.webapp_source", 0);
            int i3 = h3 >= 16 ? 0 : h3;
            long i4 = U20.i(intent, "org.chromium.chrome.browser.background_color", 2147483648L);
            if (AbstractC2103cy1.b(i4)) {
                num = Integer.valueOf((int) i4);
            }
            boolean d = U20.d(intent, "org.chromium.chrome.browser.is_icon_generated", false);
            boolean d2 = U20.d(intent, "org.chromium.chrome.browser.webapp_icon_adaptive", false);
            boolean d3 = U20.d(intent, "org.chromium.chrome.browser.webapk_force_navigation", false);
            String n5 = U20.n(intent, "org.chromium.chrome.browser.webapp_name");
            String str3 = "";
            String str4 = (n5 == null && (n5 = U20.n(intent, "org.chromium.chrome.browser.webapp_title")) == null) ? str3 : n5;
            String n6 = U20.n(intent, "org.chromium.chrome.browser.webapp_short_name");
            if (n6 == null) {
                String n7 = U20.n(intent, "org.chromium.chrome.browser.webapp_title");
                if (n7 != null) {
                    str3 = n7;
                }
                str = str3;
            } else {
                str = n6;
            }
            by1 = new C1932by1(intent, i2, b2, null, new Yx1(n, n2, n4, new Zx1(n3, true), str4, str, h, h2, i3, num, RY0.a(ContextUtils.getApplicationContext()), d, d2, d3), null);
        }
        if (by1 != null) {
            xx1.g(by1);
        }
        Bitmap bitmap = (Bitmap) ShortcutHelper.f10603a.remove(str2);
        if (bitmap != null) {
            ShortcutHelper.storeWebappSplashImage(str2, bitmap);
        }
    }
}

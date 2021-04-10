package defpackage;

import android.text.TextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.url.GURL;

/* renamed from: z91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5889z91 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC1404Xa1 f11729a = new C5719y91();

    public static void a(Tab tab) {
        if (tab.l() == null) {
            tab.A(f11729a);
            return;
        }
        NavigationController f = tab.l().f();
        C0948Pm0 y = f.y();
        int i = y.b;
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            if (f.a(i - y.b)) {
                GURL gurl = y.a(i).c;
                if (!TextUtils.isEmpty(AbstractC0444Hf1.a().c(gurl))) {
                    tab.c(new LoadUrlParams(gurl.h(), 10));
                    return;
                }
            }
        }
    }
}

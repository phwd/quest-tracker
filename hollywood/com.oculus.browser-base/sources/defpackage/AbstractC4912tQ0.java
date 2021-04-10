package defpackage;

import J.N;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: tQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4912tQ0 {
    public static int a() {
        TemplateUrlService a2 = AbstractC0444Hf1.a();
        TemplateUrl a3 = a2.a();
        if (a3 == null) {
            return -1;
        }
        return N.MJpD6RKI(a2.c, a2, a3.b());
    }

    public static void b(int i) {
        AbstractC3364kK0.g("Android.SearchEngineChoice.Events", i, 3);
    }
}

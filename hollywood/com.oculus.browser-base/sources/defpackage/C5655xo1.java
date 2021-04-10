package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: xo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5655xo1 extends AbstractC2032cb {
    public final /* synthetic */ TtsPlatformImpl i;

    public C5655xo1(TtsPlatformImpl ttsPlatformImpl) {
        this.i = ttsPlatformImpl;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        TraceEvent j0 = TraceEvent.j0("TtsPlatformImpl:initialize.async_task");
        try {
            Locale[] availableLocales = Locale.getAvailableLocales();
            ArrayList arrayList = new ArrayList();
            for (Locale locale : availableLocales) {
                if (locale.getVariant().isEmpty()) {
                    try {
                        if (this.i.b.isLanguageAvailable(locale) > 0) {
                            String displayLanguage = locale.getDisplayLanguage();
                            if (!locale.getCountry().isEmpty()) {
                                displayLanguage = displayLanguage + " " + locale.getDisplayCountry();
                            }
                            arrayList.add(new C5995zo1(displayLanguage, locale.toString(), null));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            if (j0 != null) {
                j0.close();
            }
            return arrayList;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        TtsPlatformImpl ttsPlatformImpl = this.i;
        ttsPlatformImpl.d = (List) obj;
        ttsPlatformImpl.c = true;
        N.MpJkwIUo(ttsPlatformImpl.f10920a);
        C5825yo1 yo1 = this.i.f;
        if (yo1 != null) {
            yo1.f11700a.speak(yo1.b, yo1.c, yo1.d, yo1.e, yo1.f, yo1.g);
        }
        TraceEvent.g0("TtsPlatformImpl:initialize", (long) this.i.hashCode());
    }
}

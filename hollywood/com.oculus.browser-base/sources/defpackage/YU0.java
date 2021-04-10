package defpackage;

import J.N;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: YU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class YU0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9276a;
    public final Tab b;
    public final C2361eV0 c;

    public YU0(Callback callback, Tab tab, C2361eV0 ev0) {
        this.f9276a = callback;
        this.b = tab;
        this.c = ev0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9276a;
        Tab tab = this.b;
        C2361eV0 ev0 = this.c;
        if (!((Boolean) obj).booleanValue()) {
            callback.onResult(null);
            return;
        }
        Objects.requireNonNull(C2361eV0.T);
        Profile b2 = Profile.b();
        Map map = C6012zu0.f11778a;
        C5502wu0 wu0 = (C5502wu0) map.get(b2);
        if (wu0 == null) {
            wu0 = new C5502wu0(new C0111Bu0(b2));
            map.put(b2, wu0);
        }
        GURL url = tab.getUrl();
        ZU0 zu0 = new ZU0(callback, tab, ev0);
        if (url == null || url.j()) {
            zu0.onResult(new LinkedList());
            return;
        }
        C0111Bu0 bu0 = wu0.f11577a;
        C5332vu0 vu0 = new C5332vu0(zu0);
        Objects.requireNonNull(bu0);
        if (url.j()) {
            vu0.onResult(null);
            return;
        }
        C0050Au0 au0 = new C0050Au0(bu0, vu0);
        Profile profile = bu0.f7769a;
        N.MiPC31k4(profile, String.format(AbstractC5672xu0.f11647a.c() + "?url=%s", url.h()), "GET", "application/json; charset=UTF-8", "", 1000, new String[]{"Accept-Language", LocaleUtils.getDefaultLocaleListString()}, au0);
    }
}

package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: P50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P50 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f8665a;
    public final R50 b;
    public final HashMap c = new HashMap();
    public final L50 d = new L50(this);

    public P50() {
        OH0 oh0 = S50.f8876a;
        TH0 th0 = S50.b;
        Map c2 = UH0.c(new KH0[]{oh0, th0, S50.c});
        C1794b90 b90 = new C1794b90();
        LH0 lh0 = new LH0(null);
        lh0.f8415a = b90;
        c2.put(oh0, lh0);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = null;
        c2.put(th0, lh02);
        UH0 uh0 = new UH0(c2, null);
        this.f8665a = uh0;
        this.b = new R50(uh0);
    }
}

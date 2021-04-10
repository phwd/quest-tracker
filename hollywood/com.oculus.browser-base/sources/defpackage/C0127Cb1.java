package defpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: Cb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0127Cb1 {

    /* renamed from: a  reason: collision with root package name */
    public final C0371Gb1 f7822a;

    public C0127Cb1(C0371Gb1 gb1) {
        this.f7822a = gb1;
    }

    public void a(List list, int i) {
        C0371Gb1 gb1 = this.f7822a;
        gb1.e.m(list);
        if (list != null && i > 0 && i < list.size()) {
            C1795b91 b91 = gb1.e;
            NH0 nh0 = J91.f8274a;
            Map c = UH0.c(new KH0[]{nh0});
            JH0 jh0 = new JH0(null);
            jh0.f8282a = 3;
            ((HashMap) c).put(nh0, jh0);
            b91.f(i, 4, new UH0(c, null));
        }
    }
}

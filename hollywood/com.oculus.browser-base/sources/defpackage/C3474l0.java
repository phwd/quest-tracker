package defpackage;

import android.view.ViewStub;
import java.util.HashMap;
import java.util.Map;

/* renamed from: l0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3474l0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3816n0 f10320a;

    public C3474l0(ViewStub viewStub) {
        C2491fE fEVar = new C2491fE(viewStub);
        OH0 oh0 = AbstractC4158p0.f11043a;
        SH0 sh0 = AbstractC4158p0.b;
        QH0 qh0 = AbstractC4158p0.c;
        QH0 qh02 = AbstractC4158p0.e;
        Map c = UH0.c(new KH0[]{oh0, sh0, qh0, AbstractC4158p0.d, qh02, AbstractC4158p0.f});
        C1794b90 b90 = new C1794b90();
        LH0 lh0 = new LH0(null);
        lh0.f8415a = b90;
        HashMap hashMap = (HashMap) c;
        hashMap.put(oh0, lh0);
        JH0 jh0 = new JH0(null);
        jh0.f8282a = -1;
        hashMap.put(sh0, jh0);
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        hashMap.put(qh0, gh0);
        GH0 gh02 = new GH0(null);
        gh02.f8081a = false;
        hashMap.put(qh02, gh02);
        UH0 uh0 = new UH0(c, null);
        P70.a(uh0, qh0, fEVar, new C3303k0());
        uh0.f9530a.b(new C3987o0(uh0));
        this.f10320a = new C3816n0(uh0);
    }

    public int a() {
        return this.f10320a.F.f(AbstractC4158p0.d);
    }

    public void b(int i) {
        this.f10320a.F.l(AbstractC4158p0.d, i);
    }
}

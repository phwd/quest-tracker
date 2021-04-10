package defpackage;

import java.util.Objects;

/* renamed from: A90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class A90 implements YH0 {

    /* renamed from: a  reason: collision with root package name */
    public final I90 f7655a;

    public A90(I90 i90) {
        this.f7655a = i90;
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C4456ql1 ql1 = (C4456ql1) obj2;
        KH0 kh0 = (KH0) obj3;
        Objects.requireNonNull(this.f7655a);
        SH0 sh0 = F90.f7995a;
        if (kh0 == sh0) {
            int f = uh0.f(sh0);
            boolean z = false;
            if (f != 0) {
                if (f == 1) {
                    z = true;
                }
                ql1.e(z);
                return;
            }
            ql1.j();
            return;
        }
        RH0 rh0 = F90.b;
        if (kh0 == rh0) {
            ql1.a(uh0.e(rh0));
        }
    }
}

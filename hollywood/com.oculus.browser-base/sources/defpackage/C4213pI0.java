package defpackage;

import java.util.Set;

/* renamed from: pI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4213pI0 implements AbstractC3400kb1 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f11060a;

    public C4213pI0(Set set) {
        this.f11060a = set;
    }

    @Override // defpackage.AbstractC3400kb1
    public void a(int i, int i2, String str, Boolean bool, boolean z, boolean z2) {
        Set set = this.f11060a;
        if (!EM0.b(str) || z) {
            C4384qI0 b = C4384qI0.b(i2);
            if (z) {
                C4384qI0.e = b;
            }
            int f = b.f();
            if (!AbstractC4772sd1.g() || !set.contains(Integer.valueOf(f))) {
                C4384qI0.d.add(b);
                set.add(Integer.valueOf(f));
            }
        }
    }
}

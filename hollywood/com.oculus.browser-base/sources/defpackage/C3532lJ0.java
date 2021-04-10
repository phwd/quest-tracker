package defpackage;

import java.util.Objects;

/* renamed from: lJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3532lJ0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        SurfaceHolder$CallbackC2065cm cmVar;
        UH0 uh0 = (UH0) obj;
        C3361kJ0 kj0 = (C3361kJ0) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = AbstractC3703mJ0.f10414a;
        if (qh0 == kh0) {
            Boolean valueOf = Boolean.valueOf(uh0.h(qh0));
            if (!kj0.d || !valueOf.booleanValue()) {
                kj0.d = valueOf.booleanValue();
                kj0.b();
                return;
            }
            return;
        }
        QH0 qh02 = AbstractC3703mJ0.c;
        if (qh02 == kh0) {
            Boolean valueOf2 = Boolean.valueOf(uh0.h(qh02));
            Objects.requireNonNull(kj0);
            boolean booleanValue = valueOf2.booleanValue();
            kj0.f = booleanValue;
            if (booleanValue || (cmVar = kj0.g) == null) {
                kj0.b();
            } else {
                cmVar.b();
            }
        } else {
            QH0 qh03 = AbstractC3703mJ0.b;
            if (qh03 == kh0) {
                Boolean valueOf3 = Boolean.valueOf(uh0.h(qh03));
                Objects.requireNonNull(kj0);
                kj0.e = valueOf3.booleanValue();
                kj0.b();
            }
        }
    }
}

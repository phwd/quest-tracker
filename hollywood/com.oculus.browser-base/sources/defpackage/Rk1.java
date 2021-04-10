package defpackage;

import J.N;
import java.util.Objects;

/* renamed from: Rk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Rk1 extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Uk1 f8849a;

    public Rk1(Uk1 uk1) {
        this.f8849a = uk1;
    }

    @Override // defpackage.I70
    public void a(int i) {
        if (i == 1) {
            this.f8849a.K.f9104a.C();
            this.f8849a.r();
        }
    }

    @Override // defpackage.I70
    public void d(int i, boolean z, boolean z2) {
        if (i == 1) {
            this.f8849a.K.b(false, z, z2);
            this.f8849a.r();
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (i == 1) {
            this.f8849a.K.b(true, z, false);
            this.f8849a.r();
            Uk1 uk1 = this.f8849a;
            if (uk1.Z.k) {
                C3909na0 na0 = (C3909na0) uk1.d0;
                if (na0.R) {
                    Objects.requireNonNull(na0.f10497J);
                    N.MjJ0r9e$();
                }
            }
        }
    }
}

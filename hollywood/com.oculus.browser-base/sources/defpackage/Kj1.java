package defpackage;

import java.util.Objects;

/* renamed from: Kj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Kj1 implements Runnable {
    public final Oj1 F;
    public final VC G;

    public Kj1(Oj1 oj1, VC vc) {
        this.F = oj1;
        this.G = vc;
    }

    public void run() {
        Oj1 oj1 = this.F;
        VC vc = this.G;
        Objects.requireNonNull(oj1);
        PC.c(vc.b);
        AbstractC5717y9 y9Var = oj1.N;
        if (y9Var != null) {
            ((C5887z9) y9Var).i(null);
        }
    }
}

package defpackage;

import java.util.Objects;

/* renamed from: Gp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Gp1 implements Runnable {
    public final Hp1 F;
    public final Fp1 G;

    public Gp1(Hp1 hp1, Fp1 fp1) {
        this.F = hp1;
        this.G = fp1;
    }

    public void run() {
        Hp1 hp1 = this.F;
        Fp1 fp1 = this.G;
        Objects.requireNonNull(hp1);
        Objects.requireNonNull((Ep1) fp1);
    }
}

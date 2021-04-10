package defpackage;

import java.util.Objects;

/* renamed from: YS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YS0 implements Runnable {
    public final /* synthetic */ AbstractC2016cT0 F;
    public final /* synthetic */ C2892hd G;

    public YS0(C2187dT0 dt0, AbstractC2016cT0 ct0, C2892hd hdVar) {
        this.F = ct0;
        this.G = hdVar;
    }

    public void run() {
        AbstractC2016cT0 ct0 = this.F;
        C2892hd hdVar = this.G;
        AB0 ab0 = (AB0) ct0;
        Objects.requireNonNull(ab0);
        hdVar.m();
        ab0.s.d(hdVar);
        C5084uR0 ur0 = ab0.C;
        if (ur0 != null) {
            ur0.a(hdVar);
            ab0.w.o(1, ab0.C);
        }
        C3980ny nyVar = ab0.D;
        if (nyVar != null) {
            nyVar.i(hdVar);
            ab0.w.o(3, ab0.D);
        }
    }
}

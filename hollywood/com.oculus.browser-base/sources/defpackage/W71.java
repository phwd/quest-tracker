package defpackage;

import java.util.List;

/* renamed from: W71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W71 implements Runnable {
    public final C2475f81 F;
    public final List G;

    public W71(C2475f81 f81, List list) {
        this.F = f81;
        this.G = list;
    }

    public void run() {
        C2475f81 f81 = this.F;
        f81.b.m(AbstractC2646g81.g, Integer.valueOf(this.G.indexOf(((AbstractC0246Ea1) f81.e).j())));
    }
}

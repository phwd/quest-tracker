package defpackage;

import java.util.Collection;

/* renamed from: Ag0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0019Ag0 implements Runnable {
    public final /* synthetic */ C0994Qg0 F;
    public final /* synthetic */ C0869Of0 G;
    public final /* synthetic */ Collection H;
    public final /* synthetic */ AbstractC0202Dg0 I;

    public RunnableC0019Ag0(AbstractC0202Dg0 dg0, C0994Qg0 qg0, C0869Of0 of0, Collection collection) {
        this.I = dg0;
        this.F = qg0;
        this.G = of0;
        this.H = collection;
    }

    public void run() {
        this.F.a(this.I, this.G, this.H);
    }
}

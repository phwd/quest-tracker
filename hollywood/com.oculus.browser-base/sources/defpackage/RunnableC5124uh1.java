package defpackage;

/* renamed from: uh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5124uh1 implements Runnable {
    public final /* synthetic */ CharSequence F;
    public final /* synthetic */ int G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ C5464wh1 I;

    public RunnableC5124uh1(C5464wh1 wh1, CharSequence charSequence, int i, boolean z) {
        this.I = wh1;
        this.F = charSequence;
        this.G = i;
        this.H = z;
    }

    public void run() {
        C5464wh1 wh1 = this.I;
        CharSequence charSequence = this.F;
        int i = this.G;
        int i2 = this.H ? wh1.j | Integer.MIN_VALUE : 0;
        wh1.j = 0;
        wh1.f.A0(charSequence, i, false, i2);
    }
}

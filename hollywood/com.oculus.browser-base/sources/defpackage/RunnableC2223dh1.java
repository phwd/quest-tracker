package defpackage;

/* renamed from: dh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2223dh1 implements Runnable {
    public final /* synthetic */ CharSequence F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C5464wh1 H;

    public RunnableC2223dh1(C5464wh1 wh1, CharSequence charSequence, int i) {
        this.H = wh1;
        this.F = charSequence;
        this.G = i;
    }

    public void run() {
        C5464wh1 wh1 = this.H;
        CharSequence charSequence = this.F;
        int i = this.G;
        wh1.j = 0;
        wh1.f.A0(charSequence, i, true, 0);
    }
}

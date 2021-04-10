package defpackage;

/* renamed from: Nk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0822Nk0 implements Runnable {
    public final View$OnClickListenerC1249Uk0 F;
    public final AbstractC0579Jk0 G;
    public final CharSequence H;
    public final Integer I;

    public RunnableC0822Nk0(View$OnClickListenerC1249Uk0 uk0, AbstractC0579Jk0 jk0, CharSequence charSequence, Integer num) {
        this.F = uk0;
        this.G = jk0;
        this.H = charSequence;
        this.I = num;
    }

    public void run() {
        this.F.d(this.G, this.H, this.I);
    }
}

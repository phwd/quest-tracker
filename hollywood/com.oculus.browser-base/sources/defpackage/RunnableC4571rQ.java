package defpackage;

/* renamed from: rQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4571rQ implements Runnable {
    public final /* synthetic */ String F;
    public final /* synthetic */ BQ G;

    public RunnableC4571rQ(BQ bq, String str) {
        this.G = bq;
        this.F = str;
    }

    public void run() {
        this.G.G.announceForAccessibility(this.F);
    }
}

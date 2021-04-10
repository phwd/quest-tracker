package defpackage;

/* renamed from: id1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3065id1 implements Runnable {
    public final long F;
    public final int G;

    public RunnableC3065id1(long j, int i) {
        this.F = j;
        this.G = i;
    }

    public void run() {
        long j = this.F;
        int i = this.G;
        StringBuilder i2 = AbstractC2531fV.i("Startup.Android.TimeToGTSFirstMeaningfulPaint");
        i2.append(EM0.a(!EM0.b));
        i2.append(EM0.c(i));
        i2.append(": ");
        i2.append(i);
        i2.append(" thumbnails ");
        i2.append(j);
        i2.append("ms");
        AbstractC1220Ua0.d("TabSwitcherOnReturn", i2.toString(), new Object[0]);
        AbstractC3364kK0.k("Startup.Android.TimeToGTSFirstMeaningfulPaint" + EM0.a(!EM0.b) + EM0.c(i), j);
        StringBuilder sb = new StringBuilder();
        sb.append("Startup.Android.TimeToGTSFirstMeaningfulPaint");
        sb.append(EM0.a(EM0.b ^ true));
        AbstractC3364kK0.k(sb.toString(), j);
        AbstractC3364kK0.k("Startup.Android.TimeToGTSFirstMeaningfulPaint", j);
        AbstractC3364kK0.c("Startup.Android.ThumbnailFetchedForGTSFirstMeaningfulPaint", i);
        EM0.b = true;
    }
}

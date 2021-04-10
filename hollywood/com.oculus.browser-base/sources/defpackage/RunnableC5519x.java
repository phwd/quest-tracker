package defpackage;

import java.util.Calendar;

/* renamed from: x  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5519x implements Runnable {
    public final C5689y F;
    public final int G;
    public final int H;
    public final long I;

    public RunnableC5519x(C5689y yVar, int i, int i2, long j) {
        this.F = yVar;
        this.G = i;
        this.H = i2;
        this.I = j;
    }

    public void run() {
        C5689y yVar = this.F;
        int i = this.G;
        int i2 = this.H;
        long j = this.I;
        if (yVar.e.a(i, i2)) {
            yVar.c.put(Long.valueOf(j), Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        }
        yVar.e.b((Runnable) yVar.d.get(Long.valueOf(j)));
        yVar.d.remove(Long.valueOf(j));
    }
}

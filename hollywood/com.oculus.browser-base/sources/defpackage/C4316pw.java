package defpackage;

import java.util.ArrayList;

/* renamed from: pw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4316pw {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f11101a = new ArrayList();
    public final Runnable b;
    public final ArrayList c = new ArrayList();
    public boolean d;
    public long e;

    public C4316pw(Runnable runnable) {
        this.b = runnable;
    }

    public final boolean a(long j) {
        this.d = false;
        if (this.f11101a.isEmpty()) {
            return true;
        }
        this.c.addAll(this.f11101a);
        for (int i = 0; i < this.c.size(); i++) {
            C5677xw xwVar = (C5677xw) this.c.get(i);
            long j2 = xwVar.L + j;
            xwVar.L = j2;
            long min = Math.min((long) (((float) j2) - (((float) xwVar.Q) * C5677xw.F)), xwVar.b());
            if (min >= 0) {
                xwVar.M = 1.0f;
                if (xwVar.b() > 0) {
                    xwVar.M = xwVar.K.getInterpolation(((float) min) / ((float) xwVar.b()));
                }
                xwVar.f11648J.addAll(xwVar.I);
                for (int i2 = 0; i2 < xwVar.f11648J.size(); i2++) {
                    ((AbstractC5507ww) xwVar.f11648J.get(i2)).a(xwVar);
                }
                xwVar.f11648J.clear();
                if (min == xwVar.b()) {
                    xwVar.S = true;
                    xwVar.end();
                }
            }
            if (xwVar.R == 3) {
                this.f11101a.remove(xwVar);
            }
        }
        this.c.clear();
        this.b.run();
        return this.f11101a.isEmpty();
    }
}

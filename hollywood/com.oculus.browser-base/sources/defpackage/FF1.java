package defpackage;

import com.google.android.gms.internal.cast.zzcj;
import java.util.Objects;

/* renamed from: FF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FF1 implements Runnable {
    public final /* synthetic */ C3350kF1 F;
    public final /* synthetic */ zzcj G;

    public FF1(C3350kF1 kf1, zzcj zzcj) {
        this.F = kf1;
        this.G = zzcj;
    }

    public final void run() {
        boolean z;
        C3350kF1 kf1 = this.F;
        zzcj zzcj = this.G;
        NF1 nf1 = C3350kF1.D;
        Objects.requireNonNull(kf1);
        String str = zzcj.F;
        if (!GF1.a(str, kf1.N)) {
            kf1.N = str;
            z = true;
        } else {
            z = false;
        }
        NF1 nf12 = C3350kF1.D;
        Object[] objArr = {Boolean.valueOf(z), Boolean.valueOf(kf1.P)};
        if (nf12.d()) {
            nf12.c("hasChanged=%b, mFirstApplicationStatusUpdate=%b", objArr);
        }
        AbstractC1252Um um = kf1.I;
        if (um != null && (z || kf1.P)) {
            um.d();
        }
        kf1.P = false;
    }
}

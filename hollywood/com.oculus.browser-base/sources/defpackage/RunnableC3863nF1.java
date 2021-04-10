package defpackage;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.internal.cast.zzdb;
import java.util.Objects;

/* renamed from: nF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3863nF1 implements Runnable {
    public final /* synthetic */ C3350kF1 F;
    public final /* synthetic */ zzdb G;

    public RunnableC3863nF1(C3350kF1 kf1, zzdb zzdb) {
        this.F = kf1;
        this.G = zzdb;
    }

    public final void run() {
        boolean z;
        boolean z2;
        boolean z3;
        C3350kF1 kf1 = this.F;
        zzdb zzdb = this.G;
        NF1 nf1 = C3350kF1.D;
        Objects.requireNonNull(kf1);
        ApplicationMetadata applicationMetadata = zzdb.I;
        if (!GF1.a(applicationMetadata, kf1.G)) {
            kf1.G = applicationMetadata;
            kf1.I.c(applicationMetadata);
        }
        double d = zzdb.F;
        if (Double.isNaN(d) || Math.abs(d - kf1.S) <= 1.0E-7d) {
            z = false;
        } else {
            kf1.S = d;
            z = true;
        }
        boolean z4 = zzdb.G;
        if (z4 != kf1.O) {
            kf1.O = z4;
            z = true;
        }
        Double.isNaN(zzdb.L);
        NF1 nf12 = C3350kF1.D;
        Object[] objArr = {Boolean.valueOf(z), Boolean.valueOf(kf1.Q)};
        if (nf12.d()) {
            nf12.c("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", objArr);
        }
        AbstractC1252Um um = kf1.I;
        if (um != null && (z || kf1.Q)) {
            um.f();
        }
        int i = zzdb.H;
        if (i != kf1.U) {
            kf1.U = i;
            z2 = true;
        } else {
            z2 = false;
        }
        Object[] objArr2 = {Boolean.valueOf(z2), Boolean.valueOf(kf1.Q)};
        if (nf12.d()) {
            nf12.c("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", objArr2);
        }
        AbstractC1252Um um2 = kf1.I;
        if (um2 != null && (z2 || kf1.Q)) {
            um2.a(kf1.U);
        }
        int i2 = zzdb.f9670J;
        if (i2 != kf1.V) {
            kf1.V = i2;
            z3 = true;
        } else {
            z3 = false;
        }
        Object[] objArr3 = {Boolean.valueOf(z3), Boolean.valueOf(kf1.Q)};
        if (nf12.d()) {
            nf12.c("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", objArr3);
        }
        AbstractC1252Um um3 = kf1.I;
        if (um3 != null && (z3 || kf1.Q)) {
            um3.e(kf1.V);
        }
        if (!GF1.a(kf1.T, zzdb.K)) {
            kf1.T = zzdb.K;
        }
        kf1.Q = false;
    }
}

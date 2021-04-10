package defpackage;

import android.os.Handler;
import android.os.SystemClock;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;

/* renamed from: pr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4302pr0 implements AbstractC1457Xx, AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public final long f11092a;
    public C1518Yx b;
    public boolean c;
    public boolean d;
    public Handler e;
    public Runnable f;
    public final Callback g;
    public int h = ApplicationStatus.getStateForApplication();
    public long i;
    public long j;
    public boolean k;
    public long l;

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C4302pr0(org.chromium.base.Callback r3) {
        /*
            r2 = this;
            r2.<init>()
            int r0 = org.chromium.base.ApplicationStatus.getStateForApplication()
            r2.h = r0
            r2.g = r3
            android.os.Handler r3 = new android.os.Handler
            r3.<init>()
            r2.e = r3
            java.lang.String r3 = "STATUS_INDICATOR_WAIT_ON_SWITCH_ONLINE_TO_OFFLINE_DEFAULT_DURATION_MS"
            java.lang.String r0 = "OfflineIndicatorV2"
            java.lang.String r3 = J.N.MOVY9QtZ(r0, r3)     // Catch:{ UnsupportedOperationException -> 0x0026 }
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0026
            int r3 = java.lang.Integer.parseInt(r3)
            long r0 = (long) r3
            goto L_0x0028
        L_0x0026:
            r0 = 10000(0x2710, double:4.9407E-320)
        L_0x0028:
            r2.f11092a = r0
            or0 r3 = new or0
            r3.<init>(r2)
            r2.f = r3
            Vq0 r3 = org.chromium.base.ApplicationStatus.h
            r3.b(r2)
            int r3 = r2.h
            r0 = 1
            if (r3 != r0) goto L_0x0041
            long r0 = android.os.SystemClock.elapsedRealtime()
            r2.i = r0
        L_0x0041:
            Yx r3 = new Yx
            java.lang.String r0 = "OfflineDetector"
            r3.<init>(r2, r0)
            r2.b = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4302pr0.<init>(org.chromium.base.Callback):void");
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i2) {
        if (this.h != i2) {
            this.h = i2;
            if (i2 == 1) {
                this.i = SystemClock.elapsedRealtime();
            }
            d();
        }
    }

    @Override // defpackage.AbstractC1457Xx
    public void b(int i2) {
        boolean z = this.d;
        boolean z2 = i2 != 4;
        this.d = z2;
        if (z == z2) {
            this.k = true;
            return;
        }
        if (z2) {
            this.j = SystemClock.elapsedRealtime();
        }
        if ((this.k && !z) || !this.d) {
            this.l = SystemClock.elapsedRealtime();
        }
        this.k = true;
        d();
    }

    public final void c(String str) {
        AbstractC1220Ua0.d("OfflineDetector", AbstractC2531fV.f(str, " mConnectivityDetectorInitialized: %b, mTimeWhenLastForegrounded: %d, getElapsedTime: %d, mTimeWhenLastOfflineNotificationReceived: %d, mTimeWhenLastOnline: %d, mApplicationState: %d, mIsOfflineLastReportedByConnectivityDetector: %b, mIsEffectivelyOffline: %b"), Boolean.valueOf(this.k), Long.valueOf(this.i), Long.valueOf(SystemClock.elapsedRealtime()), Long.valueOf(this.j), Long.valueOf(this.l), Integer.valueOf(this.h), Boolean.valueOf(this.d), Boolean.valueOf(this.c));
    }

    public final void d() {
        this.e.removeCallbacks(this.f);
        if (this.h == 1) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.i;
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - this.j;
            long elapsedRealtime3 = SystemClock.elapsedRealtime();
            long j2 = this.l;
            long j3 = elapsedRealtime3 - j2;
            long j4 = 2000 - elapsedRealtime;
            long j5 = 2000 - elapsedRealtime2;
            long j6 = j2 > 0 ? this.f11092a - j3 : 0;
            c("Running updateState");
            AbstractC1220Ua0.d("OfflineDetector", "updateState(): timeSinceLastForeground: %d, timeSinceOfflineNotificationReceived: %d, timeSinceLastOnline: %d, timeNeededForForeground: %d, timeNeededForOffline: %d", Long.valueOf(elapsedRealtime), Long.valueOf(elapsedRealtime2), Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5));
            if (!this.d || (j4 <= 0 && j5 <= 0 && j6 <= 0)) {
                this.f.run();
            } else {
                this.e.postDelayed(this.f, Math.max(Math.max(j4, j5), j6));
            }
        }
    }
}

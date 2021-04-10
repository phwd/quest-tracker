package X;

import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.os.IBinder;
import androidx.annotation.RequiresApi;

@RequiresApi(26)
/* renamed from: X.0sk  reason: invalid class name */
public final class AnonymousClass0sk extends JobServiceEngine implements AnonymousClass03T {
    public JobParameters A00;
    public final AnonymousClass03W A01;
    public final Object A02 = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        r2.getIntent().setExtrasClassLoader(r4.A01.getClassLoader());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return new X.C07440sl(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        if (r2 == null) goto L_?;
     */
    @Override // X.AnonymousClass03T
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass03U A2D() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A02
            monitor-enter(r3)
            android.app.job.JobParameters r1 = r4.A00     // Catch:{ all -> 0x0024 }
            r0 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            return r0
        L_0x000a:
            android.app.job.JobWorkItem r2 = r1.dequeueWork()     // Catch:{ all -> 0x0024 }
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            if (r2 == 0) goto L_0x0023
            android.content.Intent r1 = r2.getIntent()
            X.03W r0 = r4.A01
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r1.setExtrasClassLoader(r0)
            X.0sl r0 = new X.0sl
            r0.<init>(r4, r2)
        L_0x0023:
            return r0
        L_0x0024:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0sk.A2D():X.03U");
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        this.A00 = jobParameters;
        this.A01.ensureProcessorRunningLocked(false);
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        boolean doStopCurrentWork = this.A01.doStopCurrentWork();
        synchronized (this.A02) {
            this.A00 = null;
        }
        return doStopCurrentWork;
    }

    public AnonymousClass0sk(AnonymousClass03W r2) {
        super(r2);
        this.A01 = r2;
    }

    @Override // X.AnonymousClass03T
    public final IBinder A1l() {
        return getBinder();
    }
}

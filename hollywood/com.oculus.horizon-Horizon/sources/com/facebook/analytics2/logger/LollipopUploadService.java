package com.facebook.analytics2.logger;

import X.AnonymousClass0GM;
import X.AnonymousClass0HX;
import X.AnonymousClass0HY;
import X.AnonymousClass0Ha;
import X.AnonymousClass0Hc;
import X.AnonymousClass0JF;
import X.AnonymousClass0NO;
import X.AnonymousClass0P5;
import X.C00860Gv;
import X.C00890Hd;
import X.C00980Hw;
import X.C00990Hx;
import X.C06850q2;
import X.C06870qB;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@TargetApi(21)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class LollipopUploadService extends JobService {
    @Nullable
    public C00990Hx A00;

    public final int onStartCommand(Intent intent, int i, int i2) {
        C00990Hx r1 = this.A00;
        AnonymousClass0P5.A00(r1);
        return r1.A02(intent, new C00980Hw(this, i2));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        AnonymousClass0Ha r1;
        C00990Hx r0 = this.A00;
        AnonymousClass0P5.A00(r0);
        int jobId = jobParameters.getJobId();
        C00890Hd r2 = r0.A01;
        AnonymousClass0P5.A00(r2);
        synchronized (r2) {
            AnonymousClass0Hc r02 = r2.A01.get(jobId);
            if (!(r02 == null || (r1 = r02.A00) == null)) {
                r1.sendMessageAtFrontOfQueue(r1.obtainMessage(3));
            }
        }
        return true;
    }

    public final void onCreate() {
        this.A00 = C00990Hx.A00(this);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        boolean z;
        PersistableBundle extras = jobParameters.getExtras();
        if (extras == null) {
            AnonymousClass0NO.A08("PostLolliopUploadService", "Job with no build ID, cancelling job");
        } else {
            try {
                if (AnonymousClass0JF.A01() == extras.getInt("__VERSION_CODE", 0)) {
                    try {
                        C00990Hx r6 = this.A00;
                        AnonymousClass0P5.A00(r6);
                        int jobId = jobParameters.getJobId();
                        String string = jobParameters.getExtras().getString("action");
                        AnonymousClass0HX r8 = new AnonymousClass0HX(new Bundle(jobParameters.getExtras()));
                        C06870qB r5 = new C06870qB(this, jobParameters);
                        String str = r8.A08;
                        if (str != null) {
                            AnonymousClass0GM A002 = AnonymousClass0GM.A00(r6.A00);
                            AnonymousClass0GM.A02(A002, A002.A04, str);
                        }
                        C00890Hd r7 = r6.A01;
                        AnonymousClass0P5.A00(r7);
                        AnonymousClass0HY r62 = new AnonymousClass0HY(jobId, r8, string);
                        C06850q2 r2 = new C06850q2(r5);
                        synchronized (r7) {
                            AnonymousClass0Hc r0 = r7.A01.get(r62.A00);
                            if (r0 != null) {
                                if (r0.A00 != null) {
                                    z = false;
                                }
                            }
                            C00890Hd.A00(r7, r62, r2);
                            z = true;
                        }
                        if (z) {
                            return true;
                        }
                        JobParameters jobParameters2 = r5.A00;
                        jobParameters2.getJobId();
                        r5.A01.jobFinished(jobParameters2, true);
                        return true;
                    } catch (C00860Gv e) {
                        AnonymousClass0NO.A0C("PostLolliopUploadService", "Misunderstood job service extras: %s", e);
                        return false;
                    }
                }
            } catch (Exception e2) {
                AnonymousClass0NO.A0H("PostLolliopUploadService", e2, "Corrupt bundle, cancelling job");
            }
        }
        jobParameters.getJobId();
        return false;
    }
}

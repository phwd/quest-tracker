package com.facebook.analytics2.logger;

import X.C0266Yq;
import X.G7;
import X.Gc;
import X.H8;
import X.H9;
import X.HB;
import X.HD;
import X.HE;
import X.HU;
import X.HV;
import X.IK;
import X.Mu;
import X.P6;
import X.YU;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import javax.annotation.Nullable;

@TargetApi(21)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class LollipopUploadService extends JobService {
    @Nullable
    public HV A00;

    public final int onStartCommand(Intent intent, int i, int i2) {
        HV hv = this.A00;
        P6.A00(hv);
        return hv.A02(intent, new HU(this, i2));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        HB hb;
        HV hv = this.A00;
        P6.A00(hv);
        int jobId = jobParameters.getJobId();
        HE he = hv.A01;
        P6.A00(he);
        synchronized (he) {
            HD hd = he.A01.get(jobId);
            if (!(hd == null || (hb = hd.A00) == null)) {
                hb.sendMessageAtFrontOfQueue(hb.obtainMessage(3));
            }
        }
        return true;
    }

    public final void onCreate() {
        this.A00 = HV.A00(this);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        boolean z;
        PersistableBundle extras = jobParameters.getExtras();
        if (extras == null) {
            Mu.A00("PostLolliopUploadService", "Job with no build ID, cancelling job");
        } else {
            try {
                if (IK.A01() == extras.getInt("__VERSION_CODE", 0)) {
                    try {
                        HV hv = this.A00;
                        P6.A00(hv);
                        int jobId = jobParameters.getJobId();
                        String string = jobParameters.getExtras().getString(OculusAuthorizationInterceptor.EXTRA_ACTION);
                        H8 h8 = new H8(new Bundle(jobParameters.getExtras()));
                        C0266Yq yq = new C0266Yq(this, jobParameters);
                        String str = h8.A08;
                        if (str != null) {
                            G7 A002 = G7.A00(hv.A00);
                            G7.A01(A002, A002.A02, str);
                        }
                        HE he = hv.A01;
                        P6.A00(he);
                        H9 h9 = new H9(jobId, h8, string);
                        YU yu = new YU(yq);
                        synchronized (he) {
                            HD hd = he.A01.get(h9.A00);
                            if (hd != null) {
                                if (hd.A00 != null) {
                                    z = false;
                                }
                            }
                            HE.A00(he, h9, yu);
                            z = true;
                        }
                        if (z) {
                            return true;
                        }
                        JobParameters jobParameters2 = yq.A00;
                        jobParameters2.getJobId();
                        yq.A01.jobFinished(jobParameters2, true);
                        return true;
                    } catch (Gc e) {
                        Mu.A03("PostLolliopUploadService", "Misunderstood job service extras: %s", e);
                        return false;
                    }
                }
            } catch (Exception e2) {
                Mu.A08("PostLolliopUploadService", e2, "Corrupt bundle, cancelling job");
            }
        }
        jobParameters.getJobId();
        return false;
    }
}

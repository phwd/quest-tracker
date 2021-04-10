package com.oculus.assistant.learning;

import X.AnonymousClass1O;
import X.C00638p;
import X.C00799i;
import X.C0139Dd;
import X.C1247w2;
import X.C1248w3;
import X.C1249w4;
import X.C1250w5;
import X.Gz;
import X.V3;
import X.WB;
import X.WE;
import X.WF;
import X.WG;
import android.app.job.JobParameters;
import android.preference.PreferenceManager;
import com.google.common.util.concurrent.ListenableFuture;

public class ExecutionJobService extends Gz {
    public WF A00;
    public WE A01;

    @Override // X.Gz
    public final synchronized boolean onStopJob(JobParameters jobParameters) {
        boolean onStopJob;
        onStopJob = super.onStopJob(jobParameters);
        if (onStopJob) {
            C00638p.A01(this, "assistant_keyboard_fl_papaya_last_submit", -1);
        }
        return onStopJob;
    }

    @Override // X.Gz
    public final ListenableFuture A02() {
        ListenableFuture A002 = this.A01.A00();
        if (WB.A00()) {
            return AnonymousClass1O.A00(A002, new C1249w4(this), V3.INSTANCE);
        }
        C0139Dd.A09("com.oculus.assistant.learning.ExecutionJobService", "Canceling execution");
        C1247w2 w2Var = new C1247w2(this);
        V3 v3 = V3.INSTANCE;
        return AnonymousClass1O.A00(AnonymousClass1O.A01(A002, w2Var, v3), new C1248w3(this), v3);
    }

    @Override // X.Gz
    public final void onCreate() {
        super.onCreate();
        this.A02 = new C1250w5(this);
        WE we = WE.A05;
        if (we == null) {
            we = new WE();
            WE.A05 = we;
        }
        this.A01 = we;
        this.A00 = new WF(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("assistant_keyboard_fl_device_name", "oculus"));
    }

    @Override // X.Gz
    public final void onDestroy() {
        if (WB.A00()) {
            C00799i.A00.logKeyboardFederatedLearningEvent(new WG(this.A00));
        }
        super.onDestroy();
    }
}

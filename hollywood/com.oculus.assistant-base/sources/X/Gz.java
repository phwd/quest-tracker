package X;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import com.facebook.papaya.client.platform.PlatformResourceChecker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executors;

public abstract class Gz extends JobService {
    public Handler A00;
    public C0904oW A01;
    public C1250w5 A02;
    public boolean A03;

    public abstract ListenableFuture A02();

    public final synchronized boolean onStartJob(JobParameters jobParameters) {
        A01("Started job service");
        if (jobParameters.getExtras().getInt("check_idle", 0) == 1) {
            if (!PlatformResourceChecker.isDeviceIdle(this)) {
                A01("Failed idleness check");
                Handler handler = this.A00;
                if (handler == null) {
                    handler = new Handler(Looper.getMainLooper());
                    this.A00 = handler;
                }
                handler.post(new Gx(this, jobParameters));
            } else {
                A01("Passed idleness check");
            }
        }
        Executors.newSingleThreadExecutor().execute(new Gw(this, jobParameters));
        return true;
    }

    public synchronized boolean onStopJob(JobParameters jobParameters) {
        A01("Execution job stopped");
        if (this.A02 != null) {
            PlatformResourceChecker.isDeviceIdle(this);
            PlatformResourceChecker.hasExternalPower(this);
            PlatformResourceChecker.hasUnmeteredConnectivity(this);
            PlatformResourceChecker.hasAnyConnectivity(this);
        }
        C0904oW oWVar = this.A01;
        if (oWVar != null && this.A03) {
            AnonymousClass1O.A00(oWVar.A01.A00(), new C0903oV(oWVar), V3.INSTANCE);
            this.A03 = false;
        }
        return false;
    }

    public static void A00(Gz gz, JobParameters jobParameters, Throwable th) {
        if (th != null) {
            C0139Dd.A07(Gz.class, th, "Failed to run papaya", new Object[0]);
        }
        gz.jobFinished(jobParameters, false);
    }

    private void A01(String str) {
        C0139Dd.A03(Gz.class, str);
        C1250w5 w5Var = this.A02;
        if (w5Var != null) {
            w5Var.A00(str);
        }
    }

    public void onCreate() {
        super.onCreate();
        A01("Execution job created");
    }

    public void onDestroy() {
        super.onDestroy();
        A01("Execution job destroyed");
        this.A03 = false;
    }
}

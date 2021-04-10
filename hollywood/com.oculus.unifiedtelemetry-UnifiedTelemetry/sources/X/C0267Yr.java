package X;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import com.facebook.analytics2.logger.LollipopUploadService;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import java.util.List;
import javax.annotation.Nullable;

@TargetApi(21)
/* renamed from: X.Yr  reason: case insensitive filesystem */
public final class C0267Yr extends HL {
    public final ComponentName A00;
    public final Context A01;
    public final JobScheduler A02;

    @Override // X.HL
    public final long A01(int i) {
        List<JobInfo> allPendingJobs;
        JobScheduler jobScheduler = this.A02;
        if (!(jobScheduler == null || (allPendingJobs = jobScheduler.getAllPendingJobs()) == null)) {
            for (JobInfo jobInfo : allPendingJobs) {
                if (jobInfo.getId() == i) {
                    return jobInfo.getMinLatencyMillis();
                }
            }
        }
        return Long.MAX_VALUE;
    }

    @Override // X.HL
    public final void A03(int i) {
        this.A02.cancel(i);
    }

    @Override // X.HL
    public final void A04(int i, @Nullable String str, H8 h8, long j, long j2) {
        ComponentName componentName;
        JobScheduler jobScheduler = this.A02;
        if (jobScheduler != null && (componentName = this.A00) != null) {
            C0268Ys ys = new C0268Ys(new PersistableBundle());
            ys.A4N(OculusAuthorizationInterceptor.EXTRA_ACTION, str);
            ys.A4M("__VERSION_CODE", IK.A01());
            try {
                jobScheduler.schedule(new JobInfo.Builder(i, componentName).setMinimumLatency(j).setOverrideDeadline(j2).setExtras((PersistableBundle) h8.A00(ys)).setRequiredNetworkType(1).setPersisted(false).build());
            } catch (IllegalArgumentException e) {
                PackageManager packageManager = this.A01.getPackageManager();
                int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
                if (componentEnabledSetting != 1) {
                    if (componentEnabledSetting != 2 && componentEnabledSetting != 3 && componentEnabledSetting != 4) {
                        if (packageManager.getServiceInfo(componentName, 512).isEnabled()) {
                            throw e;
                        }
                        return;
                    }
                    return;
                }
                throw e;
            } catch (NullPointerException e2) {
                Mu.A09("LollipopUploadScheduler", e2, "Nullpointer exception encountered while scheduling job");
            } catch (Throwable th) {
                Mu.A02("LollipopUploadScheduler", "Error getting serviceInfo from PackageManager", th);
                throw e;
            }
        }
    }

    public C0267Yr(Context context) {
        this.A01 = context;
        this.A02 = (JobScheduler) context.getSystemService("jobscheduler");
        this.A00 = new ComponentName(context, LollipopUploadService.class);
    }

    @Override // X.HL
    public final ComponentName A02() {
        return this.A00;
    }
}

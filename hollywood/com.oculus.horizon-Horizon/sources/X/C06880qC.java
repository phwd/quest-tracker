package X;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import com.facebook.analytics2.logger.LollipopUploadService;
import javax.annotation.Nullable;

@TargetApi(21)
/* renamed from: X.0qC  reason: invalid class name and case insensitive filesystem */
public final class C06880qC extends AnonymousClass0Hl {
    public final ComponentName A00;
    public final Context A01;
    public final JobScheduler A02;

    @Override // X.AnonymousClass0Hl
    public final void A00(int i, @Nullable String str, AnonymousClass0HX r10, long j, long j2) {
        ComponentName componentName;
        JobScheduler jobScheduler = this.A02;
        if (jobScheduler != null && (componentName = this.A00) != null) {
            C06890qD r5 = new C06890qD(new PersistableBundle());
            r5.A7k("action", str);
            r5.A7j("__VERSION_CODE", AnonymousClass0JF.A01());
            JobInfo.Builder overrideDeadline = new JobInfo.Builder(i, componentName).setMinimumLatency(j).setOverrideDeadline(j2);
            r5.A7k("uploader_class", r10.A09);
            r5.A7k("flexible_sampling_updater", r10.A06);
            r5.A7k("privacy_policy", r10.A05);
            r5.A7k("thread_handler_factory", r10.A07);
            r5.A7k("upload_job_instrumentation", r10.A08);
            r5.A7k("priority_dir", r10.A01.getAbsolutePath());
            r5.A7j("network_priority", r10.A02.intValue());
            r5.A7k("marauder_tier", r10.A04);
            r5.A7j("multi_batch_payload_size", r10.A00);
            r5.A7j("non_sticky_handling", r10.A0A ? 1 : 0);
            r5.A7k("batch_payload_iterator_factory", r10.A03);
            try {
                jobScheduler.schedule(overrideDeadline.setExtras((PersistableBundle) r5.A9V()).setRequiredNetworkType(1).setPersisted(false).build());
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
                AnonymousClass0NO.A0I("LollipopUploadScheduler", e2, "Nullpointer exception encountered while scheduling job");
            } catch (Throwable th) {
                AnonymousClass0NO.A0B("LollipopUploadScheduler", "Error getting serviceInfo from PackageManager", th);
                throw e;
            }
        }
    }

    public C06880qC(Context context) {
        this.A01 = context;
        this.A02 = (JobScheduler) context.getSystemService("jobscheduler");
        this.A00 = new ComponentName(context, LollipopUploadService.class);
    }
}

package com.oculus.job;

import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import com.oculus.durableiap.DurableIAPJobScheduler;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.headlesshorizon.social.SocialJobScheduler;
import java.util.Iterator;
import java.util.Objects;

public abstract class OculusJobHelper {
    public final Context mContext;
    public final int mJobID;
    public final Class<? extends JobService> mServiceClass;

    public enum Result {
        SKIPPED,
        SKIPPED_DUPLICATE,
        SCHEDULED,
        FAILED
    }

    public final void A00() {
        int i;
        AnonymousClass0QC r0;
        Result result;
        IErrorReporter iErrorReporter;
        String str;
        JobInfo.Builder requiredNetworkType;
        long j;
        boolean z = this instanceof SocialJobScheduler;
        if (!z) {
            i = 105;
            r0 = ((DurableIAPJobScheduler) this)._UL_mInjectionContext;
        } else {
            i = 105;
            r0 = ((SocialJobScheduler) this)._UL_mInjectionContext;
        }
        if (AnonymousClass0J2.A04(i, r0) != null) {
            JobInfo.Builder builder = new JobInfo.Builder(this.mJobID, new ComponentName(this.mContext, this.mServiceClass));
            if (!z) {
                A02(builder);
                requiredNetworkType = builder.setRequiredNetworkType(1);
                j = DurableIAPJobScheduler.PERIODIC_REFRESH_TIMER;
            } else {
                A02(builder);
                requiredNetworkType = builder.setRequiredNetworkType(1);
                j = SocialJobScheduler.PERIODIC_REFRESH_TIMER;
            }
            JobInfo build = requiredNetworkType.setPeriodic(j).build();
            Iterator<JobInfo> it = ((JobScheduler) this.mContext.getSystemService("jobscheduler")).getAllPendingJobs().iterator();
            while (true) {
                if (it.hasNext()) {
                    JobInfo next = it.next();
                    if (next.getId() == build.getId() && Objects.equals(next.getService(), build.getService()) && next.getBackoffPolicy() == build.getBackoffPolicy() && next.getInitialBackoffMillis() == build.getInitialBackoffMillis() && next.isPeriodic() == build.isPeriodic() && next.isPersisted() == build.isPersisted() && next.getIntervalMillis() == build.getIntervalMillis()) {
                        result = Result.SKIPPED_DUPLICATE;
                        break;
                    }
                } else if (((JobScheduler) this.mContext.getSystemService("jobscheduler")).schedule(build) != 1) {
                    result = Result.FAILED;
                } else {
                    result = Result.SCHEDULED;
                }
            }
        } else {
            result = Result.SKIPPED;
        }
        if (z) {
            SocialJobScheduler socialJobScheduler = (SocialJobScheduler) this;
            if (result == Result.FAILED) {
                iErrorReporter = (IErrorReporter) AnonymousClass0J2.A04(428, socialJobScheduler._UL_mInjectionContext);
                str = SocialJobScheduler.TAG;
            } else {
                return;
            }
        } else if (this instanceof DurableIAPJobScheduler) {
            DurableIAPJobScheduler durableIAPJobScheduler = (DurableIAPJobScheduler) this;
            if (result == Result.FAILED) {
                iErrorReporter = (IErrorReporter) AnonymousClass0J2.A04(428, durableIAPJobScheduler._UL_mInjectionContext);
                str = DurableIAPJobScheduler.TAG;
            } else {
                return;
            }
        } else {
            return;
        }
        iErrorReporter.A96(str, AnonymousClass006.A08(str, " failed (error code: ", result.name(), ")"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (((android.app.AppOpsManager) r8.getSystemService(android.app.AppOpsManager.class)).noteProxyOpNoThrow(r3, r6) == 0) goto L_0x004d;
     */
    @android.annotation.SuppressLint({"MissingPermission"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A02(android.app.job.JobInfo.Builder r10) {
        /*
            r9 = this;
            android.content.Context r8 = r9.mContext
            java.lang.String r2 = "android.permission.RECEIVE_BOOT_COMPLETED"
            int r0 = android.os.Process.myPid()
            int r7 = android.os.Process.myUid()
            java.lang.String r6 = r8.getPackageName()
            int r1 = r8.checkPermission(r2, r0, r7)
            r0 = -1
            if (r1 == r0) goto L_0x0045
            int r5 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r5 < r4) goto L_0x004d
            java.lang.String r3 = android.app.AppOpsManager.permissionToOp(r2)
            r2 = 0
            if (r3 == 0) goto L_0x004d
            if (r6 != 0) goto L_0x0035
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String[] r1 = r0.getPackagesForUid(r7)
            if (r1 == 0) goto L_0x0045
            int r0 = r1.length
            if (r0 <= 0) goto L_0x0045
            r6 = r1[r2]
        L_0x0035:
            if (r5 < r4) goto L_0x0045
            java.lang.Class<android.app.AppOpsManager> r0 = android.app.AppOpsManager.class
            java.lang.Object r0 = r8.getSystemService(r0)
            android.app.AppOpsManager r0 = (android.app.AppOpsManager) r0
            int r0 = r0.noteProxyOpNoThrow(r3, r6)
            if (r0 == 0) goto L_0x004d
        L_0x0045:
            java.lang.Class<? extends android.app.job.JobService> r1 = r9.mServiceClass
            java.lang.String r0 = "Did not enable job persistence due to missing permission."
            X.AnonymousClass0NO.A02(r1, r0)
            return
        L_0x004d:
            r0 = 1
            r10.setPersisted(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.job.OculusJobHelper.A02(android.app.job.JobInfo$Builder):void");
    }

    public final void A01() {
        ((JobScheduler) this.mContext.getSystemService("jobscheduler")).cancel(this.mJobID);
    }

    public OculusJobHelper(Context context, Class<? extends JobService> cls, int i) {
        this.mContext = context;
        this.mServiceClass = cls;
        this.mJobID = i;
    }
}

package com.oculus.auth.deviceownership;

import X.AbstractC06640p5;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.auth.components.AuthAction;
import com.oculus.auth.components.AuthLogger;
import com.oculus.auth.components.DeviceOwnershipHelper;
import com.oculus.time.Clock;

@TargetApi(21)
public class EnsureDeviceOwnershipJobService extends JobService {
    public static final String ACTION = "com.oculus.auth.deviceownership.ENSURE_DEVICE_OWNERSHIP";
    public static final String TAG = "EnsureDeviceOwnershipJobService";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, EnsureDeviceOwnershipJobService ensureDeviceOwnershipJobService) {
        ensureDeviceOwnershipJobService._UL_mInjectionContext = new AnonymousClass0QC(4, r2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ensureOwnershipAsync(final JobParameters jobParameters, final AuthAction authAction) {
        ((DeviceOwnershipHelper) AnonymousClass0J2.A03(2, 23, this._UL_mInjectionContext)).ensureOwnershipAsync(authAction).A09(new AnonymousClass0D4<Void, Void>() {
            /* class com.oculus.auth.deviceownership.EnsureDeviceOwnershipJobService.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.oculus.auth.components.AuthLogger */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // X.AnonymousClass0D4
            public Void then(AnonymousClass0DC<Void> r5) {
                ((AuthLogger) AnonymousClass0J2.A03(3, 456, EnsureDeviceOwnershipJobService.this._UL_mInjectionContext)).reportAction(authAction, r5.A0F(), RegularImmutableMap.A03);
                EnsureDeviceOwnershipJobService.this.jobFinished(jobParameters, r5.A0K());
                return null;
            }
        });
    }

    public static final void _UL_injectMe(Context context, EnsureDeviceOwnershipJobService ensureDeviceOwnershipJobService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), ensureDeviceOwnershipJobService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        jobParameters.getJobId();
        AnonymousClass0QC r4 = this._UL_mInjectionContext;
        final AuthAction newInstance = AuthAction.newInstance(ACTION, (Clock) AnonymousClass0J2.A03(0, 354, r4));
        ((Handler) AnonymousClass0J2.A03(1, 530, r4)).post(new Runnable() {
            /* class com.oculus.auth.deviceownership.EnsureDeviceOwnershipJobService.AnonymousClass1 */

            public void run() {
                EnsureDeviceOwnershipJobService.this.ensureOwnershipAsync(jobParameters, newInstance);
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        jobParameters.getJobId();
        return true;
    }
}

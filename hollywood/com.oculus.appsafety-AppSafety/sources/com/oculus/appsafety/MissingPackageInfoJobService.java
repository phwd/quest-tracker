package com.oculus.appsafety;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.oculus.appsafety.MissingPackageInfoJobService;
import com.oculus.appsafety.PackageVerifier;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import oculus.internal.Gatekeeper;
import oculus.internal.functional.Try;
import oculus.internal.pkgtelemetry.Package;

public class MissingPackageInfoJobService extends JobService {
    private static int JOB_ID = 125;
    public static final String LAST_JOB_RUN = "missing_package_info_last_job_run";
    private static final String TAG = MissingPackageInfoJobService.class.getSimpleName();
    private CompletableFuture<Void> cancellable;

    public static void scheduleJob(Context context) {
        if (new Gatekeeper(24).isEnabled()) {
            ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(new JobInfo.Builder(JOB_ID, new ComponentName(context, MissingPackageInfoJobService.class)).setRequiredNetworkType(1).setRequiresDeviceIdle(true).build());
        }
    }

    public boolean onStartJob(JobParameters params) {
        IBinder binder = ServiceManager.checkService("package");
        if (binder == null) {
            Log.wtf(TAG, "Package manager is not running");
            return false;
        }
        Calendar cutoff = Calendar.getInstance();
        cutoff.clear();
        cutoff.set(2020, 3, 1);
        this.cancellable = CompletableFuture.runAsync(new Runnable(IPackageManager.Stub.asInterface(binder), cutoff.getTimeInMillis()) {
            /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$SSPs85PRhcADpCLoTUvwQmFQ00 */
            private final /* synthetic */ IPackageManager f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MissingPackageInfoJobService.this.lambda$onStartJob$12$MissingPackageInfoJobService(this.f$1, this.f$2);
            }
        });
        this.cancellable.thenRun((Runnable) new Runnable(params) {
            /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$jtUKsQn_OHH9m7F9rGTZjM638K0 */
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                MissingPackageInfoJobService.this.lambda$onStartJob$13$MissingPackageInfoJobService(this.f$1);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$onStartJob$12$MissingPackageInfoJobService(IPackageManager packageManager, long CUTOFF) {
        try {
            ((Stream) packageManager.getInstalledPackages(0, Process.myUserHandle().getIdentifier()).getList().stream().filter($$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44.INSTANCE).filter($$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js.INSTANCE).filter(new Predicate(CUTOFF) {
                /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$4aGLHxQOX0kZnF_pDvDyhRyAPFQ */
                private final /* synthetic */ long f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MissingPackageInfoJobService.lambda$onStartJob$2(this.f$0, (PackageInfo) obj);
                }
            }).map(new Function() {
                /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$CnGIvj2FWaQ7O7v6FOEb__HSDw */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MissingPackageInfoJobService.this.lambda$onStartJob$3$MissingPackageInfoJobService((PackageInfo) obj);
                }
            }).map(new Function(packageManager) {
                /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$H0g0uxDv_LaMepPOXP6T63CktBY */
                private final /* synthetic */ IPackageManager f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Try.Try(new Try.F0(this.f$0) {
                        /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$AzAuS94yA0Ri8vmdWBjwU0aLgjU */
                        private final /* synthetic */ IPackageManager f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final Object get() {
                            MissingPackageInfoJobService.Builder builder;
                            return builder.setInstallerPackageName(this.f$1.getInstallerPackageName(MissingPackageInfoJobService.Builder.this.packageInfo.packageName));
                        }
                    });
                }
            }).sequential()).map(new Function() {
                /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$udxZqp1bPV4n0VTO9OaKP2vFHXk */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MissingPackageInfoJobService.this.lambda$onStartJob$7$MissingPackageInfoJobService((Try) obj);
                }
            }).map($$Lambda$MissingPackageInfoJobService$M_rYR4YcnBJYNTifabRV60aWPrY.INSTANCE).forEach($$Lambda$MissingPackageInfoJobService$SAClDMy74YkTeeMhsWPvT2_6rI.INSTANCE);
        } catch (RemoteException e) {
            Log.wtf(TAG, "Unable to get installed packages", e);
            throw new CompletionException(e);
        }
    }

    static /* synthetic */ boolean lambda$onStartJob$0(PackageInfo pi) {
        return pi.applicationInfo != null;
    }

    static /* synthetic */ boolean lambda$onStartJob$1(PackageInfo pi) {
        return (pi.applicationInfo.flags & 1) == 0;
    }

    static /* synthetic */ boolean lambda$onStartJob$2(long CUTOFF, PackageInfo pi) {
        return pi.lastUpdateTime < CUTOFF;
    }

    public /* synthetic */ Builder lambda$onStartJob$3$MissingPackageInfoJobService(PackageInfo pi) {
        return new Builder(pi);
    }

    public /* synthetic */ Try lambda$onStartJob$7$MissingPackageInfoJobService(Try t) {
        return t.tryMap(new Try.F1() {
            /* class com.oculus.appsafety.$$Lambda$MissingPackageInfoJobService$KMzbEVV3I_LfIFKhFsHFpbZSoo */

            public final Object get(Object obj) {
                return MissingPackageInfoJobService.this.lambda$onStartJob$6$MissingPackageInfoJobService((MissingPackageInfoJobService.Builder) obj);
            }
        });
    }

    public /* synthetic */ Builder lambda$onStartJob$6$MissingPackageInfoJobService(Builder builder) throws Exception {
        long start = System.currentTimeMillis();
        return builder.setPackageData(PackageVerifier.collectPackageTelemetry(this, new File(builder.packageInfo.applicationInfo.sourceDir), builder.installerPackageName, 0, new ArrayList<>())).setProcessingTime(System.currentTimeMillis() - start);
    }

    public /* synthetic */ void lambda$onStartJob$13$MissingPackageInfoJobService(JobParameters params) {
        AppSafetyApplication.getSharedPreferences().edit().putLong(LAST_JOB_RUN, System.currentTimeMillis()).commit();
        jobFinished(params, false);
    }

    public boolean onStopJob(JobParameters params) {
        if (this.cancellable.isDone()) {
            return false;
        }
        this.cancellable.cancel(true);
        return true;
    }

    /* access modifiers changed from: private */
    public class Builder {
        String installerPackageName;
        Package packageData;
        PackageInfo packageInfo;
        long processingTimeMs;

        Builder(PackageInfo packageInfo2) {
            this.packageInfo = packageInfo2;
        }

        /* access modifiers changed from: package-private */
        public Builder setInstallerPackageName(String installerPackageName2) {
            this.installerPackageName = installerPackageName2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setPackageData(Package packageData2) {
            this.packageData = packageData2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setProcessingTime(long processingTimeMs2) {
            this.processingTimeMs = processingTimeMs2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public PackageVerifier.PackageTelemetrySender build() {
            return new PackageVerifier.PackageTelemetrySender(MissingPackageInfoJobService.this, this.packageData, this.processingTimeMs);
        }
    }
}

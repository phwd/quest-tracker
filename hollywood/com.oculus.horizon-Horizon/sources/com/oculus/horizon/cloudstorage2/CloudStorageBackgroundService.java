package com.oculus.horizon.cloudstorage2;

import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.cloudstoragehelper.CloudStorageHelper;
import com.oculus.horizon.cloudstorage2.task.UploadSyncTask;
import com.oculus.horizon.cloudstorage2.task.UploadSyncTaskProvider;

public class CloudStorageBackgroundService extends JobService {
    public static final int JOB_ID = CloudStorageBackgroundService.class.hashCode();
    public static final Class<?> TAG = CloudStorageBackgroundService.class;
    @Inject
    @Eager
    public CloudStorageLogger mCloudStorageLogger;
    @Inject
    @Eager
    public UploadSyncTaskProvider mUploadSyncTaskProvider;

    /* renamed from: com.oculus.horizon.cloudstorage2.CloudStorageBackgroundService$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$JobType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.cloudstoragehelper.CloudStorageHelper$JobType[] r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.JobType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cloudstorage2.CloudStorageBackgroundService.AnonymousClass2.$SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$JobType = r2
                com.oculus.cloudstoragehelper.CloudStorageHelper$JobType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.JobType.UPLOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.cloudstoragehelper.CloudStorageHelper$JobType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.JobType.DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.CloudStorageBackgroundService.AnonymousClass2.<clinit>():void");
        }
    }

    public static void A00(Context context, String str) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(CloudStorageHelper.EXTRA_KEY_WORK_TYPE, CloudStorageHelper.JobType.UPLOAD.name());
        persistableBundle.putString("app_id", str);
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(JOB_ID, new ComponentName(context, CloudStorageBackgroundService.class)).setRequiredNetworkType(1).setExtras(persistableBundle).build());
    }

    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this);
        this.mUploadSyncTaskProvider = (UploadSyncTaskProvider) AnonymousClass117.A00(184, r1);
        this.mCloudStorageLogger = (CloudStorageLogger) AnonymousClass117.A00(527, r1);
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        String str;
        PersistableBundle extras = jobParameters.getExtras();
        if (!extras.containsKey("app_id")) {
            str = "Application ID was not provided!";
        } else if (extras.containsKey(CloudStorageHelper.EXTRA_KEY_WORK_TYPE)) {
            String string = extras.getString("app_id");
            switch (CloudStorageHelper.JobType.valueOf(extras.getString(CloudStorageHelper.EXTRA_KEY_WORK_TYPE)).ordinal()) {
                case 0:
                    this.mCloudStorageLogger.A00(string, CloudStorageLogger.SERVICE_OPERATION_BACKGROUND_UPLOAD);
                    final UploadSyncTask uploadSyncTask = new UploadSyncTask(this.mUploadSyncTaskProvider, string);
                    uploadSyncTask.A02().A09(new AnonymousClass0D4<Void, Void>() {
                        /* class com.oculus.horizon.cloudstorage2.CloudStorageBackgroundService.AnonymousClass1 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final Void then(AnonymousClass0DC<Void> r4) throws Exception {
                            boolean z;
                            if (r4.A0K()) {
                                AnonymousClass0NO.A03(CloudStorageBackgroundService.class, "Upload failed D:", r4.A0F());
                                z = uploadSyncTask.mShouldRetryOnFailure;
                            } else {
                                z = false;
                            }
                            CloudStorageBackgroundService.this.jobFinished(jobParameters, z);
                            return null;
                        }
                    });
                    return false;
                case 1:
                    throw new RuntimeException("Download sync for background service is not implemented!");
                default:
                    return false;
            }
        } else {
            str = "Work type was not provided!";
        }
        throw new IllegalArgumentException(str);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        CloudStorageHelper.JobType jobType = CloudStorageHelper.JobType.values()[extras.getInt(CloudStorageHelper.EXTRA_KEY_WORK_TYPE, -1)];
        String string = extras.getString("app_id");
        switch (jobType.ordinal()) {
            case 0:
                AnonymousClass0NO.A06(CloudStorageBackgroundService.class, "Upload sync for app %s was interrupted.", string);
                UploadSyncTask.A00(new UploadSyncTask(this.mUploadSyncTaskProvider, string), true);
                return true;
            case 1:
                throw new RuntimeException("Download sync for background service is not implemented!");
            default:
                return true;
        }
    }
}

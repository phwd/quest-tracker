package com.oculus.appsafety;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Protocol;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oculus.appsafety.PackagePartUploader;
import com.oculus.appsafety.PackagePartsUploader;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.DeviceAuth;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import oculus.internal.Gatekeeper;

public final class PackagePartsUploadJobService extends JobService {
    private static boolean DEBUG = false;
    private static final int INITIAL_BACKOFF_DURATION_MS = 3000;
    private static final int JOB_ID = 124;
    private static final int MAXIMUM_PERSISTED_PART_CONFIGS_SIZE = 10485760;
    private static final String PART_NAME = "part_name";
    private static final String TAG = PackagePartsUploadJobService.class.getSimpleName();
    private static PackagePartsQueueManager queueManager;
    private static MessageDigest sha256;
    private UnifiedTelemetryLogger analyticsLogger;
    private CompletableFuture<Void> finished;
    private BiConsumer<JobParameters, Boolean> jobFinishedFn;
    private final AtomicBoolean stopped;
    private PackagePartsUploader.PackagePartsUploadTask uploadTask;
    private PackagePartsUploader uploader;
    private Gatekeeper uploaderGk;

    public static void uploadTelemetryParts(Context context, PackageTelemetry pkg) {
        MemoryBufferPackagePart androidManifestPart = null;
        if (pkg.packageInfo.getAndroidManifest() != null) {
            byte[] androidManifestHash = pkg.androidManifestHash;
            if (androidManifestHash == null) {
                androidManifestHash = getDigester().digest(pkg.packageInfo.getAndroidManifest());
            }
            androidManifestPart = new MemoryBufferPackagePart(pkg.packageInfo.getAndroidManifest(), pkg.packageInfo.getPackageIdentifier(), "AndroidManifest.xml", androidManifestHash);
        }
        MemoryBufferPackagePart packageManifestPart = null;
        if (pkg.packageInfo.getPackageManifest() != null) {
            byte[] packageManifestHash = pkg.packageManifestHash;
            if (packageManifestHash == null) {
                packageManifestHash = getDigester().digest(pkg.packageInfo.getPackageManifest());
            }
            packageManifestPart = new MemoryBufferPackagePart(pkg.packageInfo.getPackageManifest(), pkg.packageInfo.getPackageIdentifier(), "META/MANIFEST.MF", packageManifestHash);
        }
        PackagePartsQueueManager queueManager2 = getQueueManager(context);
        if (androidManifestPart != null) {
            queueManager2.add(androidManifestPart);
        }
        if (packageManifestPart != null) {
            queueManager2.add(packageManifestPart);
        }
    }

    public PackagePartsUploadJobService() throws NoSuchAlgorithmException {
        this(null, null, null, null, null);
    }

    PackagePartsUploadJobService(PackagePartsQueueManager queueManager2, UnifiedTelemetryLogger analyticsLogger2, PackagePartsUploader uploader2, BiConsumer<JobParameters, Boolean> jobFinishedFn2, Gatekeeper uploaderGk2) {
        jobFinishedFn2 = jobFinishedFn2 == null ? new BiConsumer() {
            /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$dDxxD_0qHLTIYeMF0kEpb5mTio */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PackagePartsUploadJobService.this.lambda$new$0$PackagePartsUploadJobService((JobParameters) obj, (Boolean) obj2);
            }
        } : jobFinishedFn2;
        if (queueManager2 != null) {
            queueManager = queueManager2;
        }
        uploaderGk2 = uploaderGk2 == null ? new Gatekeeper(24) : uploaderGk2;
        this.analyticsLogger = analyticsLogger2;
        this.uploader = uploader2;
        this.jobFinishedFn = jobFinishedFn2;
        this.uploaderGk = uploaderGk2;
        this.stopped = new AtomicBoolean();
    }

    public /* synthetic */ void lambda$new$0$PackagePartsUploadJobService(JobParameters params, Boolean shouldReschedule) {
        jobFinished(params, shouldReschedule.booleanValue());
    }

    public boolean onStartJob(JobParameters params) {
        if (!this.uploaderGk.isEnabled()) {
            this.jobFinishedFn.accept(params, true);
            return true;
        }
        this.finished = CompletableFuture.runAsync(new Runnable() {
            /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$DYj7PeJ5cJnfOb7CZHKAZDlwZU */

            @Override // java.lang.Runnable
            public final void run() {
                PackagePartsUploadJobService.this.lambda$onStartJob$1$PackagePartsUploadJobService();
            }
        }).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function() {
            /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$bNxuR3bEmwTbbvgIlbWFwAUJaw */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PackagePartsUploadJobService.this.lambda$onStartJob$2$PackagePartsUploadJobService((Void) obj);
            }
        }).thenAccept((Consumer<? super U>) new Consumer(params) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$3yJaNqebdtXbPb37tRfrHaPDZrk */
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PackagePartsUploadJobService.this.lambda$onStartJob$3$PackagePartsUploadJobService(this.f$1, (Boolean) obj);
            }
        }).exceptionally((Function<Throwable, ? extends Void>) new Function(params) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$d8JOgbDkpgRLtA6fkQmCAOKy8uA */
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PackagePartsUploadJobService.this.lambda$onStartJob$4$PackagePartsUploadJobService(this.f$1, (Throwable) obj);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$onStartJob$1$PackagePartsUploadJobService() {
        try {
            init();
        } catch (Exception e) {
            Log.e(TAG, "Failed to fetch device auth token", e);
            throw new CompletionException(e);
        }
    }

    public /* synthetic */ CompletionStage lambda$onStartJob$2$PackagePartsUploadJobService(Void v) {
        synchronized (this) {
            if (this.stopped.get()) {
                return CompletableFuture.completedFuture(Boolean.valueOf(!queueManager.queue.isEmpty()));
            }
            return startUpload();
        }
    }

    public /* synthetic */ void lambda$onStartJob$3$PackagePartsUploadJobService(JobParameters params, Boolean shouldReschedule) {
        if (!this.stopped.get()) {
            if (DEBUG) {
                String str = TAG;
                Object[] objArr = new Object[1];
                objArr[0] = shouldReschedule.booleanValue() ? " Rescheduling." : "";
                Log.i(str, String.format("Job finished.%s", objArr));
            }
            this.jobFinishedFn.accept(params, shouldReschedule);
        }
    }

    public /* synthetic */ Void lambda$onStartJob$4$PackagePartsUploadJobService(JobParameters params, Throwable e) {
        if (DEBUG) {
            Log.e(TAG, "Job finished with error. Rescheduling.", e);
        }
        this.jobFinishedFn.accept(params, true);
        return null;
    }

    public boolean onStopJob(JobParameters params) {
        synchronized (this) {
            this.stopped.set(true);
            if (this.uploadTask != null) {
                this.uploadTask.stop();
            }
        }
        try {
            this.finished.get();
        } catch (Exception e) {
            Log.e(TAG, "Unexpected exception waiting for service to finish", e);
        }
        return true;
    }

    private void init() throws Exception {
        if (this.uploader == null) {
            getQueueManager(this);
            OkHttpClient client = new OkHttpClient();
            client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            MessageDigest sha2562 = getDigester();
            PackagePartUploader partUploader = new PackagePartUploader(client, new DeviceAuth(this).fetchToken(AppSafetyApplication.APPSAFETY_HW_CLIENT_TOKEN).value(), sha2562, new Gson(), new SettingsManager().getString("marauder_device_id", (String) null));
            this.analyticsLogger = UnifiedTelemetryLogger.getInstance();
            this.uploader = new PackagePartsUploader(partUploader);
        }
    }

    private static synchronized MessageDigest getDigester() {
        MessageDigest messageDigest;
        synchronized (PackagePartsUploadJobService.class) {
            try {
                if (sha256 == null) {
                    sha256 = MessageDigest.getInstance("SHA-256");
                }
                messageDigest = (MessageDigest) sha256.clone();
            } catch (CloneNotSupportedException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return messageDigest;
    }

    private static synchronized PackagePartsQueueManager getQueueManager(Context context) {
        PackagePartsQueueManager packagePartsQueueManager;
        synchronized (PackagePartsUploadJobService.class) {
            if (queueManager == null) {
                GsonBuilder builder = new GsonBuilder();
                PackagePartsQueueManager.configureGson(builder);
                queueManager = new PackagePartsQueueManager(context.getApplicationContext(), new File(context.getDataDir(), "parts_queue"), builder.create(), $$Lambda$PackagePartsUploadJobService$j7BIlqDnKVflYkKfHfMyT9ebM.INSTANCE, new Runnable(context) {
                    /* class com.oculus.appsafety.$$Lambda$PackagePartsUploadJobService$O_MCy18I0smUlt5xDkHN2MgGY2M */
                    private final /* synthetic */ Context f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        PackagePartsUploadJobService.scheduleJob(this.f$0);
                    }
                }, MAXIMUM_PERSISTED_PART_CONFIGS_SIZE);
            }
            packagePartsQueueManager = queueManager;
        }
        return packagePartsQueueManager;
    }

    /* access modifiers changed from: private */
    public static void scheduleJob(Context context) {
        ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(new JobInfo.Builder(124, new ComponentName(context, PackagePartsUploadJobService.class)).setPersisted(true).setRequiredNetworkType(1).setBackoffCriteria(3000, 1).build());
    }

    private synchronized CompletableFuture<Boolean> startUpload() {
        final CompletableFuture<Boolean> uploadDone;
        uploadDone = new CompletableFuture<>();
        if (DEBUG) {
            Log.i(TAG, String.format("Uploading %d parts", Integer.valueOf(queueManager.queue.size())));
        }
        this.uploadTask = this.uploader.upload(queueManager.queue, new PackagePartsUploader.Callbacks() {
            /* class com.oculus.appsafety.PackagePartsUploadJobService.AnonymousClass1 */

            @Override // com.oculus.appsafety.PackagePartsUploader.Callbacks
            public void onSuccess(PackagePart part, PackagePartUploader.UploadServiceResponse response) {
                if (PackagePartsUploadJobService.DEBUG) {
                    Log.i(PackagePartsUploadJobService.TAG, String.format("Uploaded %d bytes for part named %s/%s", Long.valueOf(part.getLength()), part.getPackageIdentifier(), part.getName()));
                }
                PackagePartsUploadJobService.queueManager.remove(part);
            }

            @Override // com.oculus.appsafety.PackagePartsUploader.Callbacks
            public void onFailure(PackagePart part, Throwable cause) {
                if (PackagePartsUploadJobService.DEBUG) {
                    Log.e(PackagePartsUploadJobService.TAG, String.format("Failed to upload part %s/%s", part.getPackageIdentifier(), part.getName()), cause);
                }
                PersistableBundle extras = new PersistableBundle();
                extras.putString(LoggingHelper.PACKAGE_NAME, part.getPackageIdentifier());
                extras.putString(PackagePartsUploadJobService.PART_NAME, part.getName());
                extras.putString(LoggingHelper.EVENT_SUBTYPE, "failure");
                LoggingHelper.formatException(extras, cause);
                PackagePartsUploadJobService.this.analyticsLogger.reportEvent(new AnalyticsEvent(PackagePartsUploadJobService.TAG, LoggingHelper.PACKAGE_PART_UPLOAD_EVENT, extras, (PersistableBundle) null), false);
            }

            @Override // com.oculus.appsafety.PackagePartsUploader.Callbacks
            public void onComplete(boolean stopped) {
                if (PackagePartsUploadJobService.DEBUG) {
                    if (stopped) {
                        Log.i(PackagePartsUploadJobService.TAG, "Finished uploading parts due to forced stop");
                    } else {
                        Log.i(PackagePartsUploadJobService.TAG, "Finished uploading parts.");
                    }
                }
                PackagePartsUploadJobService.queueManager.saveQueue();
                uploadDone.complete(Boolean.valueOf(!PackagePartsUploadJobService.queueManager.queue.isEmpty()));
            }
        });
        return uploadDone;
    }
}

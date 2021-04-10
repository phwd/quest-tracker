package com.oculus.appsafety;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.util.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oculus.appsafety.signals.ISafetySignal;
import com.oculus.appsafety.signals.SafetySignalCollectorConfiguration;
import com.oculus.appsafety.signals.SafetySignalCollectorConfigurationFetcher;
import com.oculus.os.DeviceAuth;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import oculus.internal.Gatekeeper;
import oculus.internal.functional.Try;

public class SafetySignalCollectorJobService extends JobService {
    private static final boolean DEBUG = false;
    private static final int JOB_ID = 126;
    private static final String JSON_EXTRAS_FIELD = "extras";
    private static final String LAST_COLLECTION_RUN = "safety_signal_last_collection_run";
    private static final long PERIOD_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    private static final String TAG = SafetySignalCollectorJobService.class.getSimpleName();
    private static final String VERSION = "2";
    private static final String VERSION_FIELD = "version";
    private Function<String, JsonObject> mCollectSafetySignalsFunc;
    private CompletableFuture<Void> mCompletableTask;
    private Supplier<String> mFetchAccessTokenFunc;
    private SafetySignalCollectorConfigurationFetcher mFetcher;
    private Gatekeeper mGatekeeper;
    private BiConsumer<JobParameters, Boolean> mJobFinishedFunc;
    private Function<JsonObject, Boolean> mSendUnifiedTelemetryFunc;
    private AtomicBoolean mStopped;
    private UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public SafetySignalCollectorJobService() {
        this(new Gatekeeper(26), null, null, null, null, null, new AtomicBoolean(), null);
    }

    SafetySignalCollectorJobService(Gatekeeper gatekeeper, BiConsumer<JobParameters, Boolean> jobFinishedFunc, Supplier<String> fetchAccessTokenFunc, Function<String, JsonObject> collectSafetySignalsFunc, Function<JsonObject, Boolean> sendUnifiedTelemetryFunc, SafetySignalCollectorConfigurationFetcher fetcher, AtomicBoolean stopped, UnifiedTelemetryLogger unifiedTelemetryLogger) {
        BiConsumer<JobParameters, Boolean> biConsumer;
        Function<String, JsonObject> function;
        Function<JsonObject, Boolean> function2;
        this.mGatekeeper = gatekeeper;
        if (jobFinishedFunc == null) {
            biConsumer = new BiConsumer() {
                /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$nzka8vxOCBPU0GcJfL5on2QgY */

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SafetySignalCollectorJobService.this.lambda$new$0$SafetySignalCollectorJobService((JobParameters) obj, (Boolean) obj2);
                }
            };
        } else {
            biConsumer = jobFinishedFunc;
        }
        this.mJobFinishedFunc = biConsumer;
        this.mFetchAccessTokenFunc = fetchAccessTokenFunc == null ? new Supplier() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$ZrUr8YXpD1WIDxVUcInQaA5gNww */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SafetySignalCollectorJobService.this.lambda$new$1$SafetySignalCollectorJobService();
            }
        } : fetchAccessTokenFunc;
        if (collectSafetySignalsFunc == null) {
            function = new Function() {
                /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$OXChEPRCgm9IroH11Q_QUYr0Q3U */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SafetySignalCollectorJobService.this.lambda$new$2$SafetySignalCollectorJobService((String) obj);
                }
            };
        } else {
            function = collectSafetySignalsFunc;
        }
        this.mCollectSafetySignalsFunc = function;
        if (sendUnifiedTelemetryFunc == null) {
            function2 = new Function() {
                /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$7HNfN1FIbynLJl3i_36GkX8Yk1U */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SafetySignalCollectorJobService.this.lambda$new$3$SafetySignalCollectorJobService((JsonObject) obj);
                }
            };
        } else {
            function2 = sendUnifiedTelemetryFunc;
        }
        this.mSendUnifiedTelemetryFunc = function2;
        this.mFetcher = fetcher;
        this.mStopped = stopped;
        this.mUnifiedTelemetryLogger = unifiedTelemetryLogger;
    }

    public /* synthetic */ void lambda$new$0$SafetySignalCollectorJobService(JobParameters params, Boolean wantsReschedule) {
        jobFinished(params, wantsReschedule.booleanValue());
    }

    public static void scheduleJob(Context context) {
        ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(new JobInfo.Builder(126, new ComponentName(context, SafetySignalCollectorJobService.class)).setRequiredNetworkType(1).setPeriodic(PERIOD_IN_MILLIS).setRequiresDeviceIdle(true).build());
    }

    public boolean onStartJob(JobParameters params) {
        initializeLogger();
        if (!this.mGatekeeper.isEnabled()) {
            this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("gatekeeper_disabled"), false);
            return false;
        }
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("gatekeeper_enabled"), false);
        this.mCompletableTask = CompletableFuture.supplyAsync(new Supplier() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$77Ppmo2K_LLja5iywbna1NiXU */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SafetySignalCollectorJobService.this.lambda$onStartJob$4$SafetySignalCollectorJobService();
            }
        }).thenApplyAsync((Function) new Function() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$zD0kAesj7GwcmdB1eSgwVAGaiTg */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SafetySignalCollectorJobService.this.lambda$onStartJob$5$SafetySignalCollectorJobService((String) obj);
            }
        }).thenApplyAsync((Function) new Function() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$8G0bNU_RyohIMbZrpO_xcgfYuVs */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SafetySignalCollectorJobService.this.lambda$onStartJob$6$SafetySignalCollectorJobService((JsonObject) obj);
            }
        }).thenAcceptAsync((Consumer) new Consumer(params) {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$_39d3t6kkkBbsIxU2HCstBuQaBQ */
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SafetySignalCollectorJobService.this.lambda$onStartJob$7$SafetySignalCollectorJobService(this.f$1, (Boolean) obj);
            }
        }).exceptionally((Function<Throwable, ? extends Void>) new Function(params) {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$hbfNM6GeCUj2Sy2Y8yHx753GW2w */
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SafetySignalCollectorJobService.this.lambda$onStartJob$8$SafetySignalCollectorJobService(this.f$1, (Throwable) obj);
            }
        });
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_started"), false);
        return true;
    }

    public /* synthetic */ String lambda$onStartJob$4$SafetySignalCollectorJobService() {
        if (this.mStopped.get()) {
            return null;
        }
        return this.mFetchAccessTokenFunc.get();
    }

    public /* synthetic */ JsonObject lambda$onStartJob$5$SafetySignalCollectorJobService(String accessToken) {
        if (this.mStopped.get()) {
            return null;
        }
        return this.mCollectSafetySignalsFunc.apply(accessToken);
    }

    public /* synthetic */ Boolean lambda$onStartJob$6$SafetySignalCollectorJobService(JsonObject extras) {
        if (this.mStopped.get()) {
            return false;
        }
        return this.mSendUnifiedTelemetryFunc.apply(extras);
    }

    public /* synthetic */ void lambda$onStartJob$7$SafetySignalCollectorJobService(JobParameters params, Boolean success) {
        this.mJobFinishedFunc.accept(params, Boolean.valueOf(!success.booleanValue()));
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent(success.booleanValue() ? "job_completed" : "job_reschedule"), false);
    }

    public /* synthetic */ Void lambda$onStartJob$8$SafetySignalCollectorJobService(JobParameters params, Throwable e) {
        this.mJobFinishedFunc.accept(params, true);
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_failed", e), false);
        return null;
    }

    public boolean onStopJob(JobParameters params) {
        initializeLogger();
        if (this.mCompletableTask.isDone()) {
            return false;
        }
        this.mCompletableTask.cancel(true);
        this.mStopped.set(true);
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_stopped"), false);
        return true;
    }

    private void initializeLogger() {
        if (this.mUnifiedTelemetryLogger == null) {
            this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(this);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: collectSafetySignals */
    public JsonObject lambda$new$2$SafetySignalCollectorJobService(String accessToken) throws CompletionException {
        try {
            if (this.mFetcher == null) {
                this.mFetcher = new SafetySignalCollectorConfigurationFetcher(accessToken, VERSION);
            }
            List<ISafetySignal> signals = SafetySignalCollectorConfiguration.getSignals(this, this.mUnifiedTelemetryLogger, this.mFetcher);
            JsonObject extras = new JsonObject();
            ((Stream) signals.stream().sequential()).map($$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.INSTANCE).forEach(new Consumer(extras) {
                /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$yNDePuOm1KpXNbG683ncfN7IgE */
                private final /* synthetic */ JsonObject f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SafetySignalCollectorJobService.this.lambda$collectSafetySignals$13$SafetySignalCollectorJobService(this.f$1, (Try) obj);
                }
            });
            return extras;
        } catch (IOException e) {
            throw new CompletionException(String.format("Failed to complete safety signals collection: %s", e), e);
        }
    }

    public /* synthetic */ void lambda$collectSafetySignals$13$SafetySignalCollectorJobService(JsonObject extras, Try t) {
        t.onFailure(new Consumer() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$c_0c1vZLoUuoiNJXgMEQNiewLTc */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SafetySignalCollectorJobService.this.lambda$collectSafetySignals$11$SafetySignalCollectorJobService((Exception) obj);
            }
        }).onSuccess(new Consumer() {
            /* class com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$gF5R23OVJEx80J6e6A8ketjAcsk */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Pair pair;
                JsonObject.this.add((String) pair.first, (JsonElement) ((Pair) obj).second);
            }
        });
    }

    public /* synthetic */ void lambda$collectSafetySignals$11$SafetySignalCollectorJobService(Exception e) {
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_failed_to_collect_signal", e), false);
    }

    /* access modifiers changed from: private */
    /* renamed from: sendUnifiedTelemetry */
    public Boolean lambda$new$3$SafetySignalCollectorJobService(JsonObject extras) {
        if (extras == null) {
            return false;
        }
        String extrasString = new Gson().toJson((JsonElement) extras);
        SharedPreferences sp = AppSafetyApplication.getSharedPreferences();
        long lastCollection = sp.getLong(LAST_COLLECTION_RUN, -1);
        long currentCollection = System.currentTimeMillis();
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("version", VERSION);
        bundle.putString(JSON_EXTRAS_FIELD, extrasString);
        bundle.putLong(LAST_COLLECTION_RUN, lastCollection);
        this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalStatus(bundle), false);
        sp.edit().putLong(LAST_COLLECTION_RUN, currentCollection).commit();
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: fetchAccessToken */
    public String lambda$new$1$SafetySignalCollectorJobService() throws CompletionException {
        try {
            return new DeviceAuth(this).fetchToken(AppSafetyApplication.APPSAFETY_HW_CLIENT_TOKEN).value();
        } catch (Exception e) {
            this.mUnifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_failed_to_get_access_token", e), false);
            throw new CompletionException(e);
        }
    }
}

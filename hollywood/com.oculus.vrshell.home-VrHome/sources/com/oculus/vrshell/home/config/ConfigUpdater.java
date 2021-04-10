package com.oculus.vrshell.home.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.debug.log.BLog;
import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.codegen.QuickPromotionModule;
import com.oculus.util.constants.OculusConstants;
import com.oculus.vrshell.home.HomeApplication;
import com.oculus.vrshell.home.HomeEventLogger;
import com.oculus.vrshell.home.module.GateKeepers;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import okhttp3.OkHttpClient;

public class ConfigUpdater {
    public static final String CONFIG_ERROR = "error";
    public static final String CONFIG_ERROR_DETAILS = "error_details";
    public static final String CONFIG_EVENT_NAME = "oculus_config";
    public static final String CONFIG_STATUS = "status";
    private static final String TAG = ConfigUpdater.class.getSimpleName();

    /* access modifiers changed from: protected */
    public final native String[] nativeInitializeOVRPlatform(Context context, String str);

    /* access modifiers changed from: private */
    public class ConfigContext {
        public final String mAccessToken;
        public final OkHttpClient mHttpClient;
        public final String mId;

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
            r2 = r1;
            r1 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
            r1 = th;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ConfigContext(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
            /*
                r5 = this;
                com.oculus.vrshell.home.config.ConfigUpdater.this = r6
                r5.<init>()
                r5.mAccessToken = r8
                r5.mId = r9
                com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
                java.lang.String r1 = com.oculus.vrshell.home.config.ConfigUpdater.access$000()
                java.lang.String r2 = "createHttpClient"
                r0.<init>(r1, r2)
                r2 = 0
                okhttp3.OkHttpClient$Builder r1 = new okhttp3.OkHttpClient$Builder     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                r1.<init>()     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                com.oculus.device.APIInterceptor r3 = new com.oculus.device.APIInterceptor     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                r3.<init>(r7)     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                okhttp3.OkHttpClient$Builder r1 = r1.addNetworkInterceptor(r3)     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                okhttp3.OkHttpClient r1 = r1.build()     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                r5.mHttpClient = r1     // Catch:{ Throwable -> 0x003a, all -> 0x0051 }
                if (r0 == 0) goto L_0x0030
                if (r2 == 0) goto L_0x0036
                r0.close()     // Catch:{ Throwable -> 0x0031 }
            L_0x0030:
                return
            L_0x0031:
                r1 = move-exception
                r2.addSuppressed(r1)
                goto L_0x0030
            L_0x0036:
                r0.close()
                goto L_0x0030
            L_0x003a:
                r1 = move-exception
                throw r1     // Catch:{ all -> 0x003c }
            L_0x003c:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
            L_0x0040:
                if (r0 == 0) goto L_0x0047
                if (r2 == 0) goto L_0x004d
                r0.close()     // Catch:{ Throwable -> 0x0048 }
            L_0x0047:
                throw r1
            L_0x0048:
                r3 = move-exception
                r2.addSuppressed(r3)
                goto L_0x0047
            L_0x004d:
                r0.close()
                goto L_0x0047
            L_0x0051:
                r1 = move-exception
                goto L_0x0040
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.config.ConfigUpdater.ConfigContext.<init>(com.oculus.vrshell.home.config.ConfigUpdater, android.content.Context, java.lang.String, java.lang.String):void");
        }
    }

    /* access modifiers changed from: private */
    public abstract class ConfigTask implements Continuation<ConfigContext, Void> {
        private final String mLoggingKey;
        private final TaskCompletionSource mTcs = new TaskCompletionSource();

        public abstract void run(ConfigContext configContext, TaskCompletionSource taskCompletionSource) throws Exception;

        public ConfigTask(String loggingKey) {
            this.mLoggingKey = loggingKey;
        }

        public Task getTask() {
            return this.mTcs.getTask();
        }

        @Override // bolts.Continuation
        public Void then(Task<ConfigContext> task) {
            ConfigContext configContext = task.getResult();
            if (configContext == null) {
                HomeEventLogger.reportEvent("oculus_config", "error", "no_config_context_" + this.mLoggingKey);
                this.mTcs.setError(new Exception("No config context"));
            } else {
                try {
                    run(configContext, this.mTcs);
                } catch (Exception e) {
                    HomeEventLogger.reportEvent("oculus_config", "error", "task_run_failed_" + this.mLoggingKey, "error_details", e.getMessage());
                    this.mTcs.setError(e);
                }
            }
            return null;
        }
    }

    public Task<Void> clearAsync(final Context context) {
        BLog.i(TAG, "clearAsync(): Starting");
        return Task.callInBackground(new Callable<Void>() {
            /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                GateKeepers.onLogout(context);
                QuickExperimentsModuleImpl.onLogout(context);
                QuickPromotionModuleImpl.onLogout(context);
                HomeApplication.clearApplicationData(context);
                BLog.i(ConfigUpdater.TAG, "clearAsync(): Complete");
                return null;
            }
        });
    }

    public Task<Void> fetchAsync(Context context) {
        BLog.i(TAG, "fetchAsync(): Starting");
        HomeEventLogger.reportEvent("oculus_config", "status", "config_update_started");
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            HomeEventLogger.reportEvent("oculus_config", "status", "config_update_no_internet", "error", "no_internet");
            return Task.forError(new UnknownHostException("Not connected to internet"));
        }
        try {
            Task<ConfigContext> configContextTask = getConfigContext(context);
            List<ConfigTask> configTasks = new ArrayList<>();
            List<Task<Void>> fetchTasks = new ArrayList<>();
            configTasks.add(createGKsFetch(context));
            configTasks.add(createQEsFetch(context));
            configTasks.add(createQPsFetch(context));
            for (ConfigTask configTask : configTasks) {
                configContextTask.onSuccess(configTask);
                fetchTasks.add(configTask.getTask());
            }
            return Task.whenAll(fetchTasks).continueWith(new Continuation<Void, Void>() {
                /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass2 */

                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    if (task.isFaulted()) {
                        HomeEventLogger.reportEvent("oculus_config", "status", "config_update_failed", "error", "task_faulted", "error_details", task.getError().getMessage());
                        BLog.i(ConfigUpdater.TAG, "fetchAsync(): Failed");
                        throw task.getError();
                    } else if (task.isCancelled()) {
                        HomeEventLogger.reportEvent("oculus_config", "status", "config_update_cancelled");
                        BLog.i(ConfigUpdater.TAG, "fetchAsync(): Cancelled");
                        throw new Exception("Config update task was cancelled.");
                    } else {
                        HomeEventLogger.reportEvent("oculus_config", "status", "config_update_completed");
                        BLog.i(ConfigUpdater.TAG, "fetchAsync(): Successful");
                        return null;
                    }
                }
            });
        } catch (Exception e) {
            HomeEventLogger.reportEvent("oculus_config", "status", "config_update_context_failed", "error", "exception", "error_details", e.getMessage());
            return Task.forError(new Exception("Getter for Config Context failed"));
        }
    }

    private Task<ConfigContext> getConfigContext(final Context context) {
        return Task.callInBackground(new Callable<ConfigContext>() {
            /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public ConfigContext call() throws Exception {
                String error;
                String errorDetails;
                String[] accessTokenAndUserId = ConfigUpdater.this.nativeInitializeOVRPlatform(context, "1481000308606657");
                if (accessTokenAndUserId.length != 2) {
                    switch (accessTokenAndUserId.length) {
                        case 0:
                        case 1:
                            errorDetails = "Failed to initialize Platform. " + accessTokenAndUserId.length + " param(s)";
                            break;
                        default:
                            errorDetails = null;
                            break;
                    }
                    if (errorDetails != null) {
                        HomeEventLogger.reportEvent("oculus_config", "status", "config_update_error", "error", "platform_init_failed", "error_details", errorDetails);
                        throw new Exception(errorDetails);
                    }
                }
                String accessToken = accessTokenAndUserId[0];
                String userId = accessTokenAndUserId[1];
                if (TextUtils.isEmpty(accessToken)) {
                    error = "no_access_token";
                } else if (TextUtils.isEmpty(userId)) {
                    error = "no_user_id";
                } else if (userId.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
                    error = "invalid_user_id";
                } else {
                    error = null;
                }
                if (error != null) {
                    HomeEventLogger.reportEvent("oculus_config", "status", "config_update_error", "error", error);
                    throw new Exception(error);
                }
                HomeApplication.instance.onUserId(userId);
                HomeApplication.instance.onAccessToken(accessToken);
                return new ConfigContext(context, accessToken, userId);
            }
        });
    }

    private ConfigTask createGKsFetch(final Context context) {
        return new ConfigTask("fetch_gks") {
            /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass4 */

            @Override // com.oculus.vrshell.home.config.ConfigUpdater.ConfigTask
            public void run(ConfigContext configContext, final TaskCompletionSource tcs) throws Exception {
                GateKeepers.fetchAndCacheGKs(context, configContext.mHttpClient, configContext.mAccessToken, configContext.mId, new Runnable() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass4.AnonymousClass1 */

                    public void run() {
                        tcs.setResult(null);
                    }
                }, new Runnable() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass4.AnonymousClass2 */

                    public void run() {
                        tcs.setError(new Exception("Failed to fetch gks"));
                    }
                });
            }
        };
    }

    private ConfigTask createQEsFetch(final Context context) {
        return new ConfigTask("fetch_qes") {
            /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass5 */

            @Override // com.oculus.vrshell.home.config.ConfigUpdater.ConfigTask
            public void run(ConfigContext configContext, final TaskCompletionSource tcs) throws Exception {
                QuickExperimentsModuleImpl.fetchAndCacheQEs(context, configContext.mHttpClient, configContext.mId, new Runnable() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        tcs.setResult(null);
                    }
                }, new Runnable() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass5.AnonymousClass2 */

                    public void run() {
                        tcs.setError(new Exception("Failed to fetch qes"));
                    }
                });
            }
        };
    }

    private ConfigTask createQPsFetch(final Context context) {
        return new ConfigTask("fetch_qp") {
            /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass6 */

            @Override // com.oculus.vrshell.home.config.ConfigUpdater.ConfigTask
            public void run(ConfigContext configContext, final TaskCompletionSource tcs) throws Exception {
                QuickPromotionModuleImpl.fetchForAllSurfaces(context, configContext.mHttpClient, configContext.mAccessToken, QuickPromotionModule.QPApplication.HOME, new QuickPromotionModuleImpl.SurfaceFetchCallback() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdater.AnonymousClass6.AnonymousClass1 */

                    @Override // com.oculus.modules.QuickPromotionModuleImpl.SurfaceFetchCallback
                    public void onFetchComplete() {
                        tcs.setResult(null);
                    }
                });
            }
        };
    }
}

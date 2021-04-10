package com.facebook;

import X.AnonymousClass006;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AppEventsLoggerUtility;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.WebDialog;
import java.io.File;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookSdk {
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    public static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    public static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized.";
    public static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    public static final int DEFAULT_CORE_POOL_SIZE = 5;
    public static final int DEFAULT_KEEP_ALIVE = 1;
    public static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    public static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        /* class com.facebook.FacebookSdk.AnonymousClass1 */
        public final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, AnonymousClass006.A01("FacebookSdk #", this.counter.incrementAndGet()));
        }
    };
    public static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    public static final String FACEBOOK_COM = "facebook.com";
    public static final Object LOCK = new Object();
    public static final int MAX_REQUEST_CODE_RANGE = 100;
    public static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    public static final String TAG = "com.facebook.FacebookSdk";
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    public static volatile String appClientToken = null;
    public static Context applicationContext = null;
    public static volatile String applicationId = null;
    public static volatile String applicationName = null;
    public static LockOnGetVariable<File> cacheDir = null;
    public static int callbackRequestCodeOffset = 64206;
    public static volatile Executor executor = null;
    public static volatile String facebookDomain = "facebook.com";
    public static volatile boolean isDebugEnabled;
    public static boolean isLegacyTokenUpgradeSupported;
    public static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet<>(Arrays.asList(LoggingBehavior.DEVELOPER_ERRORS));
    public static AtomicLong onProgressThreshold = new AtomicLong(65536);
    public static Boolean sdkInitialized = false;
    public static volatile int webDialogTheme;

    public interface InitializeCallback {
        void onInitialized();
    }

    public static String getSdkVersion() {
        return FacebookSdkVersion.BUILD;
    }

    public static GraphResponse publishInstallAndWaitForResponse(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        try {
            AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences(ATTRIBUTION_PREFERENCES, 0);
            String A05 = AnonymousClass006.A05(str, "ping");
            String A052 = AnonymousClass006.A05(str, "json");
            long j = sharedPreferences.getLong(A05, 0);
            String string = sharedPreferences.getString(A052, null);
            try {
                GraphRequest newPostRequest = GraphRequest.newPostRequest(null, String.format(PUBLISH_ACTIVITY_PATH, str), AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT, attributionIdentifiers, AppEventsLogger.A01(context), getLimitEventAndDataUsage(context), context), null);
                if (j != 0) {
                    if (string != null) {
                        try {
                            return new GraphResponse((GraphRequest) null, (HttpURLConnection) null, (String) null, new JSONObject(string));
                        } catch (JSONException unused) {
                        }
                    }
                    return GraphResponse.createResponsesFromString("true", null, new GraphRequestBatch(newPostRequest)).get(0);
                }
                GraphResponse executeAndWait = GraphRequest.executeAndWait(newPostRequest);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong(A05, System.currentTimeMillis());
                JSONObject jSONObject = executeAndWait.graphObject;
                if (jSONObject != null) {
                    edit.putString(A052, jSONObject.toString());
                }
                edit.apply();
                return executeAndWait;
            } catch (JSONException e) {
                throw new FacebookException("An error occurred while publishing install.", e);
            }
        } catch (Exception e2) {
            Utility.logd("Facebook-publish", e2);
            return new GraphResponse(null, null, new FacebookRequestError(null, e2));
        }
    }

    public static void addLoggingBehavior(LoggingBehavior loggingBehavior) {
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.add(loggingBehavior);
            updateGraphDebugBehavior();
        }
    }

    public static void clearLoggingBehaviors() {
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.clear();
        }
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static Set<LoggingBehavior> getLoggingBehaviors() {
        Set<LoggingBehavior> unmodifiableSet;
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(hashSet));
        }
        return unmodifiableSet;
    }

    public static boolean isFacebookRequestCode(int i) {
        int i2 = callbackRequestCodeOffset;
        if (i < i2 || i >= i2 + 100) {
            return false;
        }
        return true;
    }

    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = sdkInitialized.booleanValue();
        }
        return booleanValue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r2.contains(r3) == false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isLoggingBehaviorEnabled(com.facebook.LoggingBehavior r3) {
        /*
            java.util.HashSet<com.facebook.LoggingBehavior> r2 = com.facebook.FacebookSdk.loggingBehaviors
            monitor-enter(r2)
            boolean r0 = com.facebook.FacebookSdk.isDebugEnabled     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            boolean r1 = r2.contains(r3)     // Catch:{ all -> 0x0011 }
            r0 = 1
            if (r1 != 0) goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            monitor-exit(r2)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.isLoggingBehaviorEnabled(com.facebook.LoggingBehavior):boolean");
    }

    public static void loadDefaultsFromMetadata(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), DEFAULT_MAXIMUM_POOL_SIZE);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (applicationId == null) {
                        Object obj = applicationInfo.metaData.get(APPLICATION_ID_PROPERTY);
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                str = str.substring(2);
                            }
                            applicationId = str;
                        } else if (obj instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (applicationName == null) {
                        applicationName = applicationInfo.metaData.getString(APPLICATION_NAME_PROPERTY);
                    }
                    if (appClientToken == null) {
                        appClientToken = applicationInfo.metaData.getString(CLIENT_TOKEN_PROPERTY);
                    }
                    if (webDialogTheme == 0) {
                        setWebDialogTheme(applicationInfo.metaData.getInt(WEB_DIALOG_THEME));
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
    }

    public static void removeLoggingBehavior(LoggingBehavior loggingBehavior) {
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.remove(loggingBehavior);
        }
    }

    public static void setCacheDir(File file) {
        cacheDir = new LockOnGetVariable<>(file);
    }

    public static void setExecutor(Executor executor2) {
        Validate.notNull(executor2, "executor");
        synchronized (LOCK) {
            executor = executor2;
        }
    }

    public static void setFacebookDomain(String str) {
        Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = str;
    }

    public static void setLimitEventAndDataUsage(Context context, boolean z) {
        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", z).apply();
    }

    public static void setOnProgressThreshold(long j) {
        onProgressThreshold.set(j);
    }

    public static void setWebDialogTheme(int i) {
        if (i == 0) {
            i = WebDialog.DEFAULT_THEME;
        }
        webDialogTheme = i;
    }

    public static void updateGraphDebugBehavior() {
        if (loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            HashSet<LoggingBehavior> hashSet = loggingBehaviors;
            LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
            if (!hashSet.contains(loggingBehavior)) {
                loggingBehaviors.add(loggingBehavior);
            }
        }
    }

    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return applicationContext;
    }

    public static String getApplicationId() {
        Validate.sdkInitialized();
        return applicationId;
    }

    public static String getApplicationName() {
        Validate.sdkInitialized();
        return applicationName;
    }

    public static String getApplicationSignature(Context context) {
        PackageManager packageManager;
        Validate.sdkInitialized();
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (!(signatureArr == null || signatureArr.length == 0)) {
                MessageDigest instance = MessageDigest.getInstance(Utility.HASH_ALGORITHM_SHA1);
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    public static File getCacheDir() {
        Validate.sdkInitialized();
        return cacheDir.getValue();
    }

    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }

    public static String getClientToken() {
        Validate.sdkInitialized();
        return appClientToken;
    }

    public static String getFacebookDomain() {
        return facebookDomain;
    }

    public static boolean getLimitEventAndDataUsage(Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return webDialogTheme;
    }

    public static boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public static boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    public static void publishInstallAsync(Context context, final String str) {
        final Context applicationContext2 = context.getApplicationContext();
        getExecutor().execute(new Runnable() {
            /* class com.facebook.FacebookSdk.AnonymousClass4 */

            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(applicationContext2, str);
            }
        });
    }

    public static void setApplicationId(String str) {
        applicationId = str;
    }

    public static void setApplicationName(String str) {
        applicationName = str;
    }

    public static void setClientToken(String str) {
        appClientToken = str;
    }

    public static void setIsDebugEnabled(boolean z) {
        isDebugEnabled = z;
    }

    public static void setLegacyTokenUpgradeSupported(boolean z) {
        isLegacyTokenUpgradeSupported = z;
    }

    public static synchronized void sdkInitialize(Context context) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, (InitializeCallback) null);
        }
    }

    public static synchronized void sdkInitialize(Context context, int i) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, i, null);
        }
    }

    public static synchronized void sdkInitialize(Context context, int i, InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (sdkInitialized.booleanValue() && i != callbackRequestCodeOffset) {
                throw new FacebookException(CALLBACK_OFFSET_CHANGED_AFTER_INIT);
            } else if (i >= 0) {
                callbackRequestCodeOffset = i;
                sdkInitialize(context);
            } else {
                throw new FacebookException(CALLBACK_OFFSET_NEGATIVE);
            }
        }
    }

    public static synchronized void sdkInitialize(Context context, final InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (!sdkInitialized.booleanValue()) {
                Validate.notNull(context, "applicationContext");
                Validate.hasFacebookActivity(context, false);
                Validate.hasInternetPermissions(context, false);
                Context applicationContext2 = context.getApplicationContext();
                applicationContext = applicationContext2;
                loadDefaultsFromMetadata(applicationContext2);
                Utility.loadAppSettingsAsync(applicationContext, applicationId);
                NativeProtocol.updateAllAvailableProtocolVersionsAsync();
                BoltsMeasurementEventListener.getInstance(applicationContext);
                cacheDir = new LockOnGetVariable<>(new Callable<File>() {
                    /* class com.facebook.FacebookSdk.AnonymousClass2 */

                    @Override // java.util.concurrent.Callable
                    public File call() throws Exception {
                        return FacebookSdk.applicationContext.getCacheDir();
                    }
                });
                getExecutor().execute(new FutureTask(new Callable<Void>() {
                    /* class com.facebook.FacebookSdk.AnonymousClass3 */

                    @Override // java.util.concurrent.Callable
                    public Void call() throws Exception {
                        AccessTokenManager.getInstance().loadCurrentAccessToken();
                        ProfileManager.getInstance().loadCurrentProfile();
                        if (AccessTokenManager.getInstance().currentAccessToken != null && ProfileManager.getInstance().currentProfile == null) {
                            Profile.fetchProfileForCurrentAccessToken();
                        }
                        InitializeCallback initializeCallback = initializeCallback;
                        if (initializeCallback == null) {
                            return null;
                        }
                        initializeCallback.onInitialized();
                        return null;
                    }
                }));
                sdkInitialized = true;
            } else if (initializeCallback != null) {
                initializeCallback.onInitialized();
            }
        }
    }
}

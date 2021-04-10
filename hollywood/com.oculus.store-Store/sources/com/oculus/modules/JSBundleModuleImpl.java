package com.oculus.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import bolts.Continuation;
import bolts.Task;
import com.oculus.device.AccessTokenForwarder;
import com.oculus.modules.codegen.JSBundleModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.util.FileUtils;
import com.oculus.vrshell.home.AppVersion;
import com.oculus.vrshell.home.CryptoMethods;
import com.oculus.vrshell.home.HomeEventLogger;
import com.oculus.vrshell.home.JSBundle;
import com.oculus.vrshell.home.UsedByNative;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSBundleModuleImpl extends JSBundleModule {
    private static final String BUNDLE_CONFIGURATION_API_PATH = "bundle_configuration_v2";
    private static final String BUNDLE_CONFIGURATION_KEY = "bundle_configuration_v2";
    private static final String FALLBACK_ACCESS_TOKEN = "OC|826037204154824|";
    private static final String JS_BUNDLES_SUB_PATH = "js_bundles";
    private static final String JS_BUNDLE_EVENT_ERROR = "error";
    private static final String JS_BUNDLE_EVENT_ERROR_DETAILS = "error_details";
    private static final String JS_BUNDLE_EVENT_NAME = "oculus_js_bundle";
    private static final String JS_BUNDLE_EVENT_STATUS = "status";
    private static final String JS_BUNDLE_PREFIX = "reactnative.";
    private static final String MANUAL_OVERRIDE_PLACEHOLDER = "<none>";
    private static final String OCULUS_GRAPH_API_URL = "https://graph.oculus.com";
    private static final String SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY = "JSBundleManualOverride";
    private static final String TAG = JSBundleModuleImpl.class.getSimpleName();
    private static String sCachedBundlesPath = null;
    private final long mAppBuildTimeMillis;
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    /* access modifiers changed from: protected */
    public final native JSBundle nativeGetOverrideBundle(String str, String str2, String str3);

    public JSBundleModuleImpl(Context context, long appBuildTimeMillis) {
        this.mContext = context;
        this.mAppBuildTimeMillis = appBuildTimeMillis;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0058, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.OkHttpClient getHttpClient() {
        /*
            r7 = this;
            okhttp3.OkHttpClient r1 = r7.mOkHttpClient
            if (r1 != 0) goto L_0x0035
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.modules.JSBundleModuleImpl.TAG
            java.lang.String r2 = "createHttpClient"
            r0.<init>(r1, r2)
            r2 = 0
            okhttp3.OkHttpClient$Builder r1 = new okhttp3.OkHttpClient$Builder     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            r1.<init>()     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            com.oculus.device.APIInterceptor r3 = new com.oculus.device.APIInterceptor     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            android.content.Context r4 = r7.mContext     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            okhttp3.OkHttpClient$Builder r1 = r1.addNetworkInterceptor(r3)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            long r4 = r7.mAppBuildTimeMillis     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            okhttp3.CertificatePinner r3 = com.facebook.netlite.certificatepinning.okhttp.FbCertificatePinnerFactory.create(r4)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            okhttp3.OkHttpClient$Builder r1 = r1.certificatePinner(r3)     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            okhttp3.OkHttpClient r1 = r1.build()     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            r7.mOkHttpClient = r1     // Catch:{ Throwable -> 0x0041, all -> 0x0058 }
            if (r0 == 0) goto L_0x0035
            if (r2 == 0) goto L_0x003d
            r0.close()     // Catch:{ Throwable -> 0x0038 }
        L_0x0035:
            okhttp3.OkHttpClient r1 = r7.mOkHttpClient
            return r1
        L_0x0038:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0035
        L_0x003d:
            r0.close()
            goto L_0x0035
        L_0x0041:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x0047:
            if (r0 == 0) goto L_0x004e
            if (r2 == 0) goto L_0x0054
            r0.close()     // Catch:{ Throwable -> 0x004f }
        L_0x004e:
            throw r1
        L_0x004f:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x004e
        L_0x0054:
            r0.close()
            goto L_0x004e
        L_0x0058:
            r1 = move-exception
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.JSBundleModuleImpl.getHttpClient():okhttp3.OkHttpClient");
    }

    @UsedByNative
    public static String getBundlesPath(Context context) {
        if (sCachedBundlesPath == null) {
            try {
                File directory = new File(FileUtils.getPrivateFilePath(context, JS_BUNDLES_SUB_PATH));
                if (!directory.exists()) {
                    directory.mkdir();
                }
                sCachedBundlesPath = directory.toString();
            } catch (Exception e) {
                Log.e(TAG, "Failed creating JS Bundle path " + e.toString());
            }
        }
        return sCachedBundlesPath;
    }

    /* access modifiers changed from: private */
    public static String getBundleSubPath(String fileName) {
        return JS_BUNDLES_SUB_PATH + File.separator + fileName;
    }

    /* access modifiers changed from: private */
    public static File getBundleFile(Context context, String fileName) {
        return new File(getBundlesPath(context) + File.separator + fileName);
    }

    private String getAccessToken() {
        AccessTokenForwarder accessTokenForwarder = AccessTokenForwarder.getInstance();
        String accessToken = accessTokenForwarder != null ? accessTokenForwarder.getAccessToken() : null;
        if (accessToken == null) {
            return FALLBACK_ACCESS_TOKEN;
        }
        return accessToken;
    }

    @UsedByNative
    public static String getDebugOverrideBundle(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY, 0);
        String result = sharedPrefs.getString(SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY, "");
        sharedPrefs.edit().putString(SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY, "").commit();
        return result;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void forceApplyBundleImpl(JSBundleModule.AvailableBundle bundle, Resolver<Boolean> resolver) {
        String filename = bundle.filename;
        Log.d(TAG, "Applying manual override bundle " + filename);
        resolver.resolve(Boolean.valueOf(this.mContext.getSharedPreferences(SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY, 0).edit().putString(SHARED_PREFERENCES_OVERRIDE_BUNDLE_KEY, filename).commit()));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void getInstalledBundlesListImpl(Resolver<List<JSBundleModule.AvailableBundle>> resolver) {
        Log.d(TAG, "Fetching list of installed JS bundles");
        try {
            List<JSBundleModule.AvailableBundle> installedBundles = new ArrayList<>();
            File[] listFiles = new File(getBundlesPath(this.mContext)).listFiles();
            for (File file : listFiles) {
                String filename = file.getName();
                if (file.isFile() && filename.startsWith(JS_BUNDLE_PREFIX)) {
                    JSONObject installedBundleJSONObject = new JSONObject();
                    installedBundleJSONObject.put("checksum", MANUAL_OVERRIDE_PLACEHOLDER);
                    installedBundleJSONObject.put(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME, filename);
                    installedBundleJSONObject.put("url", MANUAL_OVERRIDE_PLACEHOLDER);
                    installedBundleJSONObject.put("installed", true);
                    installedBundles.add(JSBundleModule.AvailableBundle.makeFromJSONObject(installedBundleJSONObject));
                }
            }
            resolver.resolve(installedBundles);
        } catch (JSONException e) {
            Log.e(TAG, String.format("Error while building installed bundle list: %s", e.getMessage()));
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void fetchAvailableBundlesListImpl(final Resolver<List<JSBundleModule.AvailableBundle>> resolver) {
        Log.d(TAG, "Fetching list of available JS bundles");
        requestJSBundleConfiguration(getAccessToken(), true).continueWith(new Continuation<String, Void>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass1 */

            public Void then(Task<String> task) {
                if (task.isFaulted() || task.isCancelled()) {
                    resolver.reject(task.getError());
                    return null;
                }
                try {
                    JSONObject parsedBundleConfiguration = new JSONObject(new JSONObject((String) task.getResult()).getString("bundle_configuration_v2"));
                    ArrayList arrayList = new ArrayList();
                    Iterator<String> serviceKeys = parsedBundleConfiguration.keys();
                    while (serviceKeys.hasNext()) {
                        JSONObject service = parsedBundleConfiguration.getJSONObject(serviceKeys.next());
                        Iterator<String> bundleKeys = service.keys();
                        while (bundleKeys.hasNext()) {
                            JSONObject bundle = service.getJSONObject(bundleKeys.next());
                            String filename = bundle.getString(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME);
                            boolean installed = JSBundleModuleImpl.getBundleFile(JSBundleModuleImpl.this.mContext, filename).exists();
                            JSONObject availableBundleJSONObject = new JSONObject();
                            availableBundleJSONObject.put("checksum", bundle.getString("checksum"));
                            availableBundleJSONObject.put(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME, filename);
                            availableBundleJSONObject.put("url", bundle.getString("url"));
                            availableBundleJSONObject.put("installed", installed);
                            arrayList.add(JSBundleModule.AvailableBundle.makeFromJSONObject(availableBundleJSONObject));
                        }
                    }
                    resolver.resolve(arrayList);
                    return null;
                } catch (JSONException e) {
                    Log.e(JSBundleModuleImpl.TAG, String.format("Error while getting override bundle: %s", e.getMessage()));
                    resolver.reject(e);
                    return null;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void downloadBundleImpl(final JSBundleModule.AvailableBundle bundle, final Resolver<Boolean> resolver) {
        Log.d(TAG, String.format("Downloading bundle %s", bundle.filename));
        Task.callInBackground(new Callable<JSBundle>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public JSBundle call() throws Exception {
                return new JSBundle(bundle.filename, bundle.url, bundle.checksum);
            }
        }).onSuccessTask(downloadJSBundle()).continueWith(new Continuation<Boolean, Void>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass2 */

            public Void then(Task<Boolean> task) {
                if (task.isFaulted() || task.isCancelled()) {
                    resolver.reject(task.getError());
                    return null;
                }
                resolver.resolve(task.getResult());
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void removeBundleImpl(JSBundleModule.AvailableBundle bundle, Resolver<Boolean> resolver) {
        File bundleFile = getBundleFile(this.mContext, bundle.filename);
        if (bundleFile.exists()) {
            resolver.resolve(Boolean.valueOf(bundleFile.delete()));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.JSBundleModule
    public void fetchAndApplyBundleOverrideImpl(String serviceName, boolean qaOnly, Resolver<Boolean> resolver) {
        Log.d(TAG, String.format("Called fetchAndApplyBundleOverride for service: %s", serviceName));
        performJSBundleDownload(serviceName, getAccessToken(), qaOnly, resolver);
    }

    private void performJSBundleDownload(String serviceName, String accessToken, boolean qaOnly, final Resolver<Boolean> resolver) {
        requestJSBundleConfiguration(accessToken, qaOnly).onSuccessTask(getOverrideBundle(serviceName)).onSuccessTask(downloadJSBundle()).continueWith(new Continuation<Boolean, Void>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass4 */

            public Void then(Task<Boolean> task) {
                if (task.isFaulted() || task.isCancelled()) {
                    resolver.reject(task.getError());
                    return null;
                }
                resolver.resolve(task.getResult());
                return null;
            }
        });
    }

    private Task<String> requestJSBundleConfiguration(final String accessToken, final boolean qaOnly) {
        return Task.callInBackground(new Callable<String>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass5 */

            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                try {
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_config_fetch_started");
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(JSBundleModuleImpl.OCULUS_GRAPH_API_URL).newBuilder();
                    urlBuilder.addPathSegment("bundle_configuration_v2");
                    urlBuilder.addQueryParameter("access_token", accessToken);
                    urlBuilder.addQueryParameter("qa_only", Boolean.toString(qaOnly));
                    Request request = new Request.Builder().url(urlBuilder.build().toString()).build();
                    Log.d(JSBundleModuleImpl.TAG, String.format("Built request: %s", request.toString()));
                    Response response = JSBundleModuleImpl.this.getHttpClient().newCall(request).execute();
                    if (!response.isSuccessful()) {
                        String errorString = String.format("Failed to fetch JS bundle configuration. Response failed: %s", response.toString());
                        Log.e(JSBundleModuleImpl.TAG, errorString);
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_config_fetch_failed", "error", "response_unsuccessful", "error_details", errorString);
                        throw new Exception(errorString);
                    }
                    String bundleConfiguration = response.body().string();
                    Log.d(JSBundleModuleImpl.TAG, String.format("Received JS bundle configuration: %s", bundleConfiguration));
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_config_fetch_success");
                    return bundleConfiguration;
                } catch (Exception e) {
                    String errorString2 = String.format("Failed to fetch JS bundles: %s", e.toString());
                    Log.e(JSBundleModuleImpl.TAG, errorString2);
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_config_fetch_failed", "error", "exception", "error_details", errorString2);
                    throw new Exception(errorString2);
                }
            }
        });
    }

    private Continuation<String, Task<JSBundle>> getOverrideBundle(final String serviceName) {
        return new Continuation<String, Task<JSBundle>>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass6 */

            public Task<JSBundle> then(Task<String> task) throws Exception {
                try {
                    String bundleConfigurationString = new JSONObject((String) task.getResult()).getString("bundle_configuration_v2");
                    JSBundle bundle = JSBundleModuleImpl.this.nativeGetOverrideBundle(AppVersion.getAppVersionName(JSBundleModuleImpl.this.mContext), serviceName, bundleConfigurationString);
                    if (bundle == null) {
                        Log.d(JSBundleModuleImpl.TAG, String.format("No override bundle available for service: %s", serviceName));
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_no_override_available");
                    } else {
                        Log.d(JSBundleModuleImpl.TAG, String.format("Got override bundle from C++: %s", bundle.toString()));
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_override_determined");
                    }
                    return Task.forResult(bundle);
                } catch (JSONException e) {
                    Log.e(JSBundleModuleImpl.TAG, String.format("Error while getting override bundle: %s", e.getMessage()));
                    throw e;
                }
            }
        };
    }

    private Continuation<JSBundle, Task<Boolean>> downloadJSBundle() {
        return new Continuation<JSBundle, Task<Boolean>>() {
            /* class com.oculus.modules.JSBundleModuleImpl.AnonymousClass7 */

            public Task<Boolean> then(Task<JSBundle> task) throws Exception {
                JSBundle bundle = (JSBundle) task.getResult();
                if (bundle == null) {
                    return Task.forResult(false);
                }
                try {
                    if (JSBundleModuleImpl.getBundlesPath(JSBundleModuleImpl.this.mContext) == null) {
                        Log.e(JSBundleModuleImpl.TAG, "Failed to create the JS bundles directory.");
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_output_directory_failed");
                        throw new Exception("Failed to create the JS bundles directory.");
                    }
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_started");
                    Request request = new Request.Builder().url(HttpUrl.parse(bundle.getUrl()).newBuilder().build().toString()).build();
                    Log.d(JSBundleModuleImpl.TAG, String.format("Built request: %s", request.toString()));
                    Response response = JSBundleModuleImpl.this.getHttpClient().newCall(request).execute();
                    if (!response.isSuccessful()) {
                        String errorString = String.format("Failed to download JS bundles. Response failed: %s", response.toString());
                        Log.e(JSBundleModuleImpl.TAG, errorString);
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_failed", "error", "response_unsuccessful", "error_details", errorString);
                        throw new Exception(errorString);
                    }
                    String unverifiedFileName = "unverified_" + bundle.getFileName();
                    File unverifiedBundleFile = JSBundleModuleImpl.getBundleFile(JSBundleModuleImpl.this.mContext, unverifiedFileName);
                    FileUtils.writeBytesToInternalStorage(JSBundleModuleImpl.this.mContext, JSBundleModuleImpl.getBundleSubPath(unverifiedFileName), response.body().bytes());
                    Log.d(JSBundleModuleImpl.TAG, String.format("Downloaded %s to %s", unverifiedFileName, unverifiedBundleFile.toString()));
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_complete");
                    boolean checksumIsValid = JSBundleModuleImpl.this.verifyBundleChecksum(unverifiedBundleFile, bundle.getChecksum());
                    String str = JSBundleModuleImpl.TAG;
                    Object[] objArr = new Object[2];
                    objArr[0] = bundle.getChecksum();
                    objArr[1] = checksumIsValid ? "valid" : "invalid";
                    Log.d(str, String.format("JS bundle override checksum %s is %s", objArr));
                    if (checksumIsValid) {
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_checksum_verified");
                        if (unverifiedBundleFile.renameTo(JSBundleModuleImpl.getBundleFile(JSBundleModuleImpl.this.mContext, bundle.getFileName()))) {
                            Log.d(JSBundleModuleImpl.TAG, String.format("%s renamed to %s. Ready for execution.", unverifiedFileName, bundle.getFileName()));
                            HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_ready_for_execution");
                            return Task.forResult(true);
                        }
                        String errorString2 = String.format("Failed to rename verified JS bundle", new Object[0]);
                        HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_failed", "error", "rename_failed", "error_details", errorString2);
                        throw new Exception(errorString2);
                    }
                    String errorString3 = String.format("JS bundle override checksum was invalid", new Object[0]);
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_failed", "error", "checksum_not_match", "error_details", errorString3);
                    throw new Exception(errorString3);
                } catch (Exception e) {
                    String errorString4 = String.format("Failed to download JS bundle: %s", e.toString());
                    HomeEventLogger.reportEvent(JSBundleModuleImpl.JS_BUNDLE_EVENT_NAME, "status", "js_bundle_download_failed", "error", "exception", "error_details", errorString4);
                    throw new Exception(errorString4);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean verifyBundleChecksum(File file, String checksum) {
        String fileHash = CryptoMethods.getFileMD5(file);
        Log.d(TAG, String.format("Comparing bundle checksum %s with computed hash %s", checksum, fileHash));
        return checksum.equals(fileHash);
    }
}

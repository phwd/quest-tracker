package com.oculus.platform;

import X.AnonymousClass006;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.horizon.util.permissions.PermissionRequest;
import com.oculus.platform.callback.OVRErrorOrReleaseCallback;
import com.oculus.platform.callback.OVRStringCallback;
import com.oculus.platform.receiver.IAPBroadcastReceiver;
import com.oculus.platform.receiver.OVRPlatformBroadcastReceiver;
import com.oculus.platform.util.OVRError;
import com.oculus.platform.util.Util;
import com.oculus.provider.OculusContent;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OVR {
    public static final String BEGIN_VIDEO_CAPTURE_WITH_VRCAMERA_SURFACE = "BEGIN_VIDEO_CAPTURE_WITH_VRCAMERA_SURFACE";
    public static final String EXTRA_HOME_ENTITLEMENT_FAIL_DETAIL = "extra_home_entitlement_fail_detail";
    public static final String EXTRA_HOME_ENTITLEMENT_FAIL_PACKAGE = "extra_home_entitlement_fail_package";
    public static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    public static final String GENERIC_RESULT_KEY = "result_json";
    public static final String GENERIC_SUCCESS_KEY = "is_successful";
    public static final String HORIZON_PERM_STATUS = "permission_status";
    public static final String IAP_SHELL_RESULT = "iap_shell_json";
    public static final String IAP_SUCCESS_KEY = "is_successful";
    public static final String INTENT_KEY_CMD = "intent_cmd";
    public static final String MESSAGE_TYPE_KEY = "platform_message_type";
    public static final String OVR_PLATFORM_BROADCAST_PERMISSION = "com.oculus.horizon.permission.OVR_PLATFORM_BROADCAST";
    public static final String REQUEST_ID_KEY = "platform_request_id";
    public static final String ROOM_ID_KEY = "ovr_room_id";
    public static final String SERVICE_COMPONENT = "com.oculus.horizon.service.OVRService";
    public static final String SERVICE_PACKAGE = "com.oculus.horizon";
    public static final String SHARED_PREFS_FCM_TOKEN_KEY = "fcm_token";
    public static final String SHARED_PREFS_PREFIX = "OVR_platform_shared_prefs_";
    public static final String SHARE_RESULT_KEY = "share_result_json";
    public static final String SHARE_SUCCESS_KEY = "share_was_successful";
    public static final String SOCIAL_LAUNCH_KEY = "ovr_social_launch";
    public static final String SYSTEM_VOIP_STATE_KEY = "system_voip_state";
    public static final String TAG = "OVR";
    public static Intent mLatestIntent;
    public static Surface mLatestVrcameraSurface;
    public Context mApplicationContext;
    public boolean mHasRegisteredReceiver = false;
    public IAPBroadcastReceiver mIAPReceiver;
    public boolean mIsStandalone = false;
    public Activity mNullableActivity;
    public OVRPlatformBroadcastReceiver mPlatformReceiver;
    public OVRProfile mProfile;
    public ComponentName mReturnComponent;
    public OVRServiceManager mServiceManager;

    public class GetLoggedInUserCallable implements Callable<String> {
        public String mAppId;

        public GetLoggedInUserCallable(String str) {
            this.mAppId = str;
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            OVR ovr = OVR.this;
            OVRProfile oVRProfile = new OVRProfile();
            ovr.mProfile = oVRProfile;
            oVRProfile.set(ovr.mApplicationContext, this.mAppId);
            return OVR.this.mProfile.id;
        }
    }

    public class SharedPrefsFBNSKeys {
        public static final String CONNECTION_KEY = "connection_key";
        public static final String CONNECTION_SECRET = "connection_secret";
        public static final String DEVICE_ID = "device_id";

        public SharedPrefsFBNSKeys() {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        if (r3 == null) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject getPermissionInfoFromCursor(java.lang.String r8) {
        /*
            r7 = this;
            r3 = 0
            android.content.Context r0 = r7.mApplicationContext     // Catch:{ Exception -> 0x0035 }
            android.content.ContentResolver r1 = r0.getContentResolver()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r0 = "content://com.oculus.horizon.platformsdk/query?request="
            java.lang.String r0 = X.AnonymousClass006.A05(r0, r8)     // Catch:{ Exception -> 0x0035 }
            android.net.Uri r2 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x0035 }
            r4 = r3
            r5 = r3
            r6 = r3
            android.database.Cursor r3 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0035 }
            if (r3 == 0) goto L_0x002f
            boolean r0 = r3.moveToNext()     // Catch:{ Exception -> 0x0035 }
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "permission_status"
            int r0 = r3.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r0 = r3.getString(r0)     // Catch:{ Exception -> 0x0035 }
            org.json.JSONObject r0 = getPermissionHelper(r0)     // Catch:{ Exception -> 0x0035 }
            goto L_0x0044
        L_0x002f:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0035 }
            r0.<init>()     // Catch:{ Exception -> 0x0035 }
            goto L_0x0042
        L_0x0035:
            r2 = move-exception
            java.lang.String r1 = "OVR"
            java.lang.String r0 = ""
            android.util.Log.e(r1, r0, r2)     // Catch:{ all -> 0x0048 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ all -> 0x0048 }
            r0.<init>()     // Catch:{ all -> 0x0048 }
        L_0x0042:
            if (r3 == 0) goto L_0x0047
        L_0x0044:
            r3.close()
        L_0x0047:
            return r0
        L_0x0048:
            r0 = move-exception
            if (r3 == 0) goto L_0x004e
            r3.close()
        L_0x004e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVR.getPermissionInfoFromCursor(java.lang.String):org.json.JSONObject");
    }

    public static native void nativeAssetFileDownloadUpdate(String str);

    public static native void nativeGenericDeeplinkErrorResult(long j, long j2, int i, String str);

    public static native void nativeGenericDeeplinkResult(long j, long j2, String str);

    public static native void nativeHorizonPermissionStatusUpdate(long j, String str);

    public static native void nativeHttpClientRelease(long j);

    public static native void nativeHttpError(int i, String str, long j);

    public static native void nativeHttpSuccess(String str, long j);

    public static native void nativeIAPResultFromShell(long j, String str, boolean z);

    public static native void nativeJoinRoomNotification(String str);

    public static native void nativeLivestreamingStatusUpdate(String str);

    public static native void nativeOnLaunchIntentChanged();

    public static native void nativeSetAndroidObjects(Context context);

    public static native void nativeShareToFacebookResultFromShell(long j, boolean z, String str);

    public static native void nativeSystemVoipStateNotification(String str);

    public static native void nativeVrcameraSurfaceUpdate(String str);

    public static native void nativeWriteFileToSharedMemory(String str, Object obj);

    public static class InitWebRTCCallable implements Callable<Void> {
        public Context mContext;
        public boolean mIsWebRtcEnabled;

        public InitWebRTCCallable(Context context, boolean z) {
            this.mContext = context;
            this.mIsWebRtcEnabled = z;
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            if (!this.mIsWebRtcEnabled) {
                return null;
            }
            OVR.nativeSetAndroidObjects(this.mContext);
            return null;
        }
    }

    private void assertIsInitialized() {
        if (this.mServiceManager == null) {
            throw new IllegalStateException("You must call initialize() before you can request data.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkForRoomInvites(Activity activity) {
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent.hasExtra("ovr_room_id")) {
                nativeJoinRoomNotification(intent.getStringExtra("ovr_room_id"));
                intent.removeExtra("ovr_room_id");
                activity.setIntent(intent);
            }
        }
    }

    private SharedPreferences getFBNSSharedPreferences(String str, Context context) {
        return context.getSharedPreferences(AnonymousClass006.A05(SHARED_PREFS_PREFIX, str), 0);
    }

    public static JSONObject getPermissionHelper(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("permission_grant_status", str.toUpperCase());
            jSONObject.put("has_permission", str.equals("granted"));
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "", e);
            return jSONObject;
        }
    }

    private void launchHorizonPermissionHelper(String str, long j) {
        Intent intent = new Intent(PermissionRequest.PERMISSION_INTENT_ACTION_NAME);
        intent.addFlags(268435456);
        intent.putExtra(PermissionRequest.PERMISSION_INTENT_KEY, str);
        intent.putExtra("platform_request_id", j);
        this.mApplicationContext.startActivity(intent);
    }

    public static void setLatestIntentIfNotNull(Intent intent) {
        if (intent != null) {
            mLatestIntent = intent;
        }
    }

    public boolean getHorizonMicrophonePermission() {
        return getPermissionInfoFromCursor(OculusContent.Platform.MIC_PERMISSION_REQUEST_NAME).optBoolean("has_permission");
    }

    public String getHorizonMicrophonePermissionJSON() {
        return getPermissionInfoFromCursor(OculusContent.Platform.MIC_PERMISSION_REQUEST_NAME).toString();
    }

    public boolean getHorizonWriteStoragePermission() {
        return getPermissionInfoFromCursor(OculusContent.Platform.WRITE_STORAGE_PERMISSION_REQUEST_NAME).optBoolean("has_permission");
    }

    public String getHorizonWriteStoragePermissionJSON() {
        return getPermissionInfoFromCursor(OculusContent.Platform.WRITE_STORAGE_PERMISSION_REQUEST_NAME).toString();
    }

    public void launchHorizonMicrophonePermissionDeeplink(long j) {
        launchHorizonPermissionHelper("android.permission.RECORD_AUDIO", j);
    }

    public void launchHorizonWriteStoragePermissionDeeplink(long j) {
        launchHorizonPermissionHelper(OVRMediaServiceManager.EXTERNAL_STORAGE_PERMISSION, j);
    }

    public void nativeCheckForRoomInvites() {
        checkForRoomInvites(this.mNullableActivity);
        this.mNullableActivity = null;
    }

    public String nativeGetFCMCredentialsSync(String str, Context context) {
        if (str != null) {
            String string = getFBNSSharedPreferences(str, context).getString(SHARED_PREFS_FCM_TOKEN_KEY, "");
            if (string == null || string.equals("")) {
                return "";
            }
            return string;
        }
        throw new IllegalStateException("No userid provided for fcm credentials");
    }

    public String nativeGetLaunchDetails(Context context) {
        if (!(context instanceof Activity)) {
            return "{\"type\":\"NORMAL\"}";
        }
        Activity activity = (Activity) context;
        Intent intent = mLatestIntent;
        if (intent == null) {
            intent = activity.getIntent();
        }
        if (intent.hasExtra("intent_cmd")) {
            try {
                JSONObject jSONObject = new JSONObject(intent.getStringExtra("intent_cmd"));
                if (jSONObject.has("ovr_social_launch")) {
                    return jSONObject.get("ovr_social_launch").toString();
                }
            } catch (JSONException unused) {
            }
        }
        if (intent.hasExtra("ovr_room_id")) {
            return AnonymousClass006.A07("{\"type\":\"INVITE\",\"room_id\":\"", intent.getStringExtra("ovr_room_id"), "\"}");
        }
        return "{\"type\":\"NORMAL\"}";
    }

    public String nativeInitialize(Context context, String str, String str2, int i, int i2, boolean z) {
        if (this.mServiceManager != null) {
            return this.mProfile.id;
        }
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        OVRServiceManager oVRServiceManager = new OVRServiceManager(applicationContext, str, str2, i, i2);
        this.mServiceManager = oVRServiceManager;
        oVRServiceManager.connectOVRServiceInBackground();
        ExecutorService executorService = this.mServiceManager.mThreadPool;
        Future submit = executorService.submit(new GetLoggedInUserCallable(str));
        Future submit2 = executorService.submit(new InitWebRTCCallable(context, z));
        this.mIsStandalone = context.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
        registerBroadcastReceiver(context);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.mReturnComponent = activity.getComponentName();
            registerLifeCycleCallback(activity);
            this.mNullableActivity = activity;
        } else if (context instanceof Service) {
            this.mReturnComponent = new ComponentName(context, context.getClass());
        } else {
            Log.w(TAG, "OVRPlatform is in limited mode. Some features won't work. You likely want to init with an Activity.");
        }
        ComponentName componentName = this.mReturnComponent;
        if (componentName != null) {
            componentName.flattenToString();
        }
        try {
            submit2.get();
        } catch (Exception e) {
            Log.e(TAG, "webRTC failed to init", e);
        }
        try {
            return (String) submit.get();
        } catch (Exception e2) {
            Log.e(TAG, "Failed to get the user", e2);
            return "exception";
        }
    }

    public void nativeSetFCMCredentialsSync(String str, Context context, String str2) {
        if (str != null) {
            SharedPreferences.Editor edit = getFBNSSharedPreferences(str, context).edit();
            edit.putString(SHARED_PREFS_FCM_TOKEN_KEY, str2);
            edit.apply();
            return;
        }
        throw new IllegalStateException("No userid provided for fcm credentials");
    }

    public void registerBroadcastReceiver(Context context) {
        this.mPlatformReceiver = new OVRPlatformBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(OVRPlatformBroadcastReceiver.INVITE_ACTION);
        intentFilter.addAction("com.oculus.system_voip_state");
        intentFilter.addAction("com.oculus.livestreaming_status_change");
        intentFilter.addAction(OVRPlatformBroadcastReceiver.HORIZON_PERMISSION_STATUS_CHANGED);
        intentFilter.addAction(OVRPlatformBroadcastReceiver.GENERIC_DEEPLINK_RESULT);
        intentFilter.addAction(OVRPlatformBroadcastReceiver.SHARE_TO_FACEBOOK_RESULT);
        intentFilter.addAction(OVRPlatformBroadcastReceiver.DUMP_THREADS);
        intentFilter.addAction("com.oculus.asset_file_download_update");
        intentFilter.addAction(OVRPlatformBroadcastReceiver.LAUNCH_INTENT_CHANGED);
        intentFilter.addAction("com.oculus.vrcamera_surface_change");
        context.getApplicationContext().registerReceiver(this.mPlatformReceiver, intentFilter, OVR_PLATFORM_BROADCAST_PERMISSION, null);
        this.mIAPReceiver = new IAPBroadcastReceiver();
        context.getApplicationContext().registerReceiver(this.mIAPReceiver, new IntentFilter("com.oculus.iap_shell_result"), IAPBroadcastReceiver.IAP_BROADCAST_PERMISSION, null);
        this.mHasRegisteredReceiver = true;
    }

    public static /* synthetic */ String access$400() {
        return TAG;
    }

    private void assertIsInitializedAndHasProfile() {
        assertIsInitialized();
        if (this.mProfile == null) {
            throw new IllegalStateException("Nobody is logged in!");
        }
    }

    public static Intent getLatestIntent() {
        return mLatestIntent;
    }

    private void registerLifeCycleCallback(Activity activity) {
        activity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            /* class com.oculus.platform.OVR.AnonymousClass1 */

            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityDestroyed(Activity activity) {
                if (OVR.this.mHasRegisteredReceiver) {
                    activity.getApplicationContext().unregisterReceiver(OVR.this.mPlatformReceiver);
                    activity.getApplicationContext().unregisterReceiver(OVR.this.mIAPReceiver);
                    OVR.this.mHasRegisteredReceiver = false;
                }
            }

            public void onActivityResumed(Activity activity) {
                OVR.this.checkForRoomInvites(activity);
            }
        });
    }

    public void broadcastJsonStringFromDataChannel(String str) {
        assertIsInitialized();
        this.mServiceManager.broadcastJsonStringFromDataChannel(str);
    }

    public void broadcastLivestreamingSurface(String str, Surface surface, int i) {
        assertIsInitialized();
        this.mServiceManager.broadcastLivestreamingSurface(str, surface, i);
    }

    public void broadcastLivestreamingVrcameraSurface(String str, Surface surface, int i) {
        assertIsInitialized();
        this.mServiceManager.broadcastLivestreamingVrcameraSurface(str, surface, i);
    }

    public void broadcastPartyChatLocalState(int i, int i2, int i3) {
        assertIsInitialized();
        this.mServiceManager.broadcastPartyChatLocalState(i, i2, i3);
    }

    public void broadcastSetVrcameraAvailable(boolean z) {
        assertIsInitialized();
        this.mServiceManager.broadcastSetVrcameraAvailable(z);
    }

    public Surface getLatestSurface() {
        assertIsInitialized();
        return mLatestVrcameraSurface;
    }

    public boolean getLocalMicPermissionGranted() {
        return OVRMicrophone.checkMicPermission();
    }

    public boolean isStandalone() {
        return this.mIsStandalone;
    }

    public void nativeAssetFileDeleteById(long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.assetFileDeleteById(j, new OVRStringCallback(j2, j3));
    }

    public void nativeAssetFileDeleteByName(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.assetFileDeleteByName(str, new OVRStringCallback(j, j2));
    }

    public void nativeAssetFileDownloadById(long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.assetFileDownloadById(j, new OVRStringCallback(j2, j3));
    }

    public void nativeAssetFileDownloadByName(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.assetFileDownloadByName(str, new OVRStringCallback(j, j2));
    }

    public void nativeAssetFileDownloadCancelById(long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.assetFileDownloadCancelById(j, new OVRStringCallback(j2, j3));
    }

    public void nativeAssetFileDownloadCancelByName(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.assetFileDownloadCancelByName(str, new OVRStringCallback(j, j2));
    }

    public void nativeAssetFileList(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.assetFileList(new OVRStringCallback(j, j2));
    }

    public void nativeAssetFileStatusById(long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.assetFileStatusById(j, new OVRStringCallback(j2, j3));
    }

    public void nativeAssetFileStatusByName(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.assetFileStatusByName(str, new OVRStringCallback(j, j2));
    }

    public void nativeCancelRecordingForUserReportFlow(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.cancelRecording(str, new OVRStringCallback(j, j2));
    }

    public void nativeCloudStorage2GetUserDirectoryPathMethodID(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.getCloudStorage2UserDirectoryPath(new OVRStringCallback(j, j2));
    }

    public Bundle nativeGetAccessTokenSync() {
        assertIsInitialized();
        return this.mServiceManager.getAccessTokenSync();
    }

    public Bundle nativeGetBaselineGraphURLSync() {
        assertIsInitialized();
        return this.mServiceManager.getBaselineGraphURLSync();
    }

    public int nativeGetCurrentAppVersion() {
        assertIsInitialized();
        return this.mServiceManager.getCurrentAppVersion();
    }

    public void nativeGetCurrentMapUuidMethodID(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.getCurrentMapUuid(new OVRStringCallback(j, j2));
    }

    public Bundle nativeGetDeviceScopedAccessTokenSync() {
        assertIsInitialized();
        return this.mServiceManager.getDeviceScopedAccessTokenSync();
    }

    public Bundle nativeGetEntitlementCheckBundleSync() {
        assertIsInitialized();
        return this.mServiceManager.getEntitlementCheckBundleSync();
    }

    public Bundle nativeGetInstalledApplicationsJSONSync() {
        assertIsInitialized();
        return this.mServiceManager.getInstalledApplicationsJSONSync();
    }

    public Bundle nativeGetLatestAppVersionInformation() {
        assertIsInitialized();
        return this.mServiceManager.getLatestAvailableAppInformation();
    }

    public Bundle nativeGetLinkedAccounts(String str) {
        assertIsInitialized();
        try {
            JSONArray queryLinkedAccounts = LinkedAccountsHelper.queryLinkedAccounts(this.mApplicationContext, new JSONArray(str));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", queryLinkedAccounts);
            Bundle bundle = new Bundle();
            bundle.putString("linked_accounts", jSONObject.toString());
            return bundle;
        } catch (JSONException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public void nativeGetLivestreamingStatus(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.getLivestreamingStatus(new OVRStringCallback(j, j2));
    }

    public Bundle nativeGetLoggedInUser() {
        assertIsInitializedAndHasProfile();
        OVRProfile oVRProfile = this.mProfile;
        if (oVRProfile.name == null && oVRProfile.id == null) {
            return Util.makeErrorResult(OVRError.AUTHENTICATION_ERROR);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(OculusContent.FriendList.ALIAS_COLUMN, this.mProfile.name);
            jSONObject.put("id", this.mProfile.id);
            jSONObject.put("profile_url", this.mProfile.imageUrl);
            Bundle bundle = new Bundle();
            bundle.putString("profile_json", jSONObject.toString());
            return bundle;
        } catch (JSONException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public Bundle nativeGetPackageName() {
        assertIsInitialized();
        return this.mServiceManager.getPackageName();
    }

    public Bundle nativeGetSharedMicrophoneData(long j, int i) {
        assertIsInitialized();
        return this.mServiceManager.getSharedMicrophoneData(j, i);
    }

    public Bundle nativeGetSystemVoipDataSync(boolean z) {
        assertIsInitialized();
        return this.mServiceManager.getSystemVoipDataSync(z);
    }

    public String nativeGetSystemVoipMicrophoneMuted() {
        assertIsInitialized();
        return this.mServiceManager.getSystemVoipMicrophoneMutedSync();
    }

    public boolean nativeGetSystemVoipPassthrough() {
        assertIsInitialized();
        return this.mServiceManager.getSystemVoipPassthroughSync();
    }

    public String nativeGetSystemVoipStatus() {
        assertIsInitialized();
        return this.mServiceManager.getSystemVoipStatusSync();
    }

    public Bundle nativeGetUserAgentSync() {
        assertIsInitialized();
        return this.mServiceManager.getUserAgentSync();
    }

    public String nativeGetUserLocale() {
        return Locale.getDefault().toString();
    }

    public Bundle nativeGetViewerPurchasesDurableCacheJSON() {
        assertIsInitialized();
        return this.mServiceManager.getViewerPurchasesDurableCacheJSONSync();
    }

    public void nativeLanguagePackGetCurrent(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.languagePackGetCurrent(new OVRStringCallback(j, j2));
    }

    public void nativeLanguagePackSetCurrent(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.languagePackSetCurrent(str, new OVRStringCallback(j, j2));
    }

    public void nativeLaunchAbuseReportFlow(long j, String str, String str2, String str3, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchAbuseReportFlow(j, str, str2, str3, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLaunchCheckoutFlow(String str, long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.launchIAPCheckoutFlow(str, j, new OVRStringCallback(j2, j3), this.mReturnComponent);
    }

    public void nativeLaunchInvitableUsersFlow(long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.launchInvitableUsersFlow(j, new OVRStringCallback(j2, j3));
    }

    public void nativeLaunchLivestreamingFlow(int i, long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.launchLivestreamingFlow(i, j, new OVRErrorOrReleaseCallback(j2, j3), this.mReturnComponent);
    }

    public void nativeLaunchOtherApp(long j, String str, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.launchOtherApp(j, str, new OVRStringCallback(j2, j3));
    }

    public void nativeLaunchUserBlockFlow(long j, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchUserBlockFlow(j, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLaunchUserFriendRequestFlow(long j, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchUserFriendRequestFlow(j, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLaunchUserProfile(long j, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchUserProfile(j, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLaunchUserReportFlow(long j, String str, boolean z, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchUserReportFlow(j, i, j2, null, str, z, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLaunchUserUnblockFlow(long j, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.launchUserUnblockFlow(j, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeLogToMarauder(boolean z, String str) {
        assertIsInitialized();
        this.mServiceManager.logToMarauder(z, str);
    }

    public Bundle nativePartyChatGetVolume() {
        assertIsInitialized();
        return this.mServiceManager.partyChatGetVolume();
    }

    public Bundle nativePartyChatSetVolume(float f) {
        assertIsInitialized();
        return this.mServiceManager.partyChatSetVolume(f);
    }

    public void nativePauseLivestreaming(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.pauseLivestream(new OVRStringCallback(j, j2));
    }

    public void nativeRequestMapMethodID(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.requestMap(str, new OVRStringCallback(j, j2));
    }

    public void nativeResumeLivestreaming(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.resumeLivestream(new OVRStringCallback(j, j2));
    }

    public void nativeSetRichPresence(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.setRichPresence(str, new OVRStringCallback(j, j2));
    }

    public Bundle nativeSetSystemVoipMicrophoneMutedSync(int i) {
        assertIsInitialized();
        return this.mServiceManager.setSystemVoipMicrophoneMutedSync(i);
    }

    public int nativeSetSystemVoipPassthroughSync(boolean z) {
        assertIsInitialized();
        ParcelFileDescriptor systemVoipPassthroughSync = this.mServiceManager.setSystemVoipPassthroughSync(z);
        if (systemVoipPassthroughSync != null) {
            return systemVoipPassthroughSync.detachFd();
        }
        return -1;
    }

    public Bundle nativeSetSystemVoipSuppressedSync(boolean z) {
        assertIsInitialized();
        return this.mServiceManager.setSystemVoipSuppressedSync(z);
    }

    public void nativeShareMapMethodID(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.shareMap(str, new OVRStringCallback(j, j2));
    }

    public void nativeShareMediaToFacebook(String str, String str2, String str3, int i, long j, long j2, long j3) {
        assertIsInitialized();
        this.mServiceManager.shareMediaToFacebook(str, str2, str3, i, j, new OVRErrorOrReleaseCallback(j2, j3), this.mReturnComponent);
    }

    public void nativeSharedMicrophoneDisableNoiseSuppressor() {
        assertIsInitialized();
        this.mServiceManager.sharedMicrophoneDisableNoiseSuppressor();
    }

    public void nativeSharedMicrophoneEnableNoiseSuppressor() {
        assertIsInitialized();
        this.mServiceManager.sharedMicrophoneEnableNoiseSuppressor();
    }

    public int nativeSharedMicrophoneGetSocketFD(long j) {
        assertIsInitialized();
        ParcelFileDescriptor sharedMicrophoneGetSocketFD = this.mServiceManager.sharedMicrophoneGetSocketFD(j);
        if (sharedMicrophoneGetSocketFD != null) {
            return sharedMicrophoneGetSocketFD.detachFd();
        }
        return -1;
    }

    public void nativeSharedMicrophoneStop(long j) {
        assertIsInitialized();
        this.mServiceManager.sharedMicrophoneStop(j);
    }

    public void nativeStartLivestreaming(int i, int i2, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.startLivestream(i, i2, new OVRStringCallback(j, j2));
    }

    public Bundle nativeStartPartyChat(long j) {
        assertIsInitialized();
        return this.mServiceManager.startPartyChat(j);
    }

    public void nativeStartPartyLivestreaming(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.startPartyLivestream(new OVRStringCallback(j, j2));
    }

    public void nativeStartRecordingForUserReportFlow(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.startRecording(new OVRStringCallback(j, j2));
    }

    public void nativeStopLivestreaming(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.stopLivestream(new OVRStringCallback(j, j2));
    }

    public Bundle nativeStopPartyChat() {
        assertIsInitialized();
        return this.mServiceManager.stopPartyChat();
    }

    public void nativeStopPartyLivestreaming(long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.stopPartyLivestream(new OVRStringCallback(j, j2));
    }

    public void nativeStopRecordingAndLaunchReportFlow(long j, String str, boolean z, int i, long j2, long j3, long j4) {
        assertIsInitialized();
        this.mServiceManager.stopRecordingAndLaunchReportFlow(j, str, z, i, j2, new OVRErrorOrReleaseCallback(j3, j4), this.mReturnComponent);
    }

    public void nativeUpdateLivestreamingCommentsVisibility(boolean z, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.updateLivestreamCommentsVisibility(z, new OVRStringCallback(j, j2));
    }

    public void nativeUpdateLivestreamingMicStatus(int i, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.updateLivestreamMicStatus(i, new OVRStringCallback(j, j2));
    }

    public void nativeUpdatePlatformContext(String str) {
        assertIsInitialized();
        this.mServiceManager.updatePlatformContext(str);
    }

    public void nativeVerifyApplicationLivestreamingAllowed(String str, long j, long j2) {
        assertIsInitialized();
        this.mServiceManager.verifyApplicationLivestreamingAllowed(str, new OVRStringCallback(j, j2));
    }

    public static void setLatestVrcameraSurface(Surface surface) {
        mLatestVrcameraSurface = surface;
    }

    public Bundle nativeGetFBNSCredentialsSync() {
        assertIsInitializedAndHasProfile();
        return nativeGetFBNSCredentialsSync(this.mProfile.id, this.mApplicationContext);
    }

    public Bundle nativeGetFBNSCredentialsSync(String str, Context context) {
        Bundle bundle = new Bundle();
        if (str != null) {
            SharedPreferences fBNSSharedPreferences = getFBNSSharedPreferences(str, context);
            long j = fBNSSharedPreferences.getLong(SharedPrefsFBNSKeys.CONNECTION_KEY, -1);
            String string = fBNSSharedPreferences.getString(SharedPrefsFBNSKeys.CONNECTION_SECRET, null);
            String string2 = fBNSSharedPreferences.getString("device_id", null);
            if (!(j == -1 || string == null || string2 == null)) {
                bundle.putLong(SharedPrefsFBNSKeys.CONNECTION_KEY, j);
                bundle.putString(SharedPrefsFBNSKeys.CONNECTION_SECRET, string);
                bundle.putString("device_id", string2);
            }
        }
        return bundle;
    }

    public void nativeSetFBNSCredentialsSync(long j, String str, String str2) {
        assertIsInitializedAndHasProfile();
        nativeSetFBNSCredentialsSync(this.mProfile.id, this.mApplicationContext, j, str, str2);
    }

    public void nativeSetFBNSCredentialsSync(String str, Context context, long j, String str2, String str3) {
        if (str != null) {
            SharedPreferences.Editor edit = getFBNSSharedPreferences(str, context).edit();
            edit.putLong(SharedPrefsFBNSKeys.CONNECTION_KEY, j);
            edit.putString(SharedPrefsFBNSKeys.CONNECTION_SECRET, str2);
            edit.putString("device_id", str3);
            edit.apply();
        }
    }
}

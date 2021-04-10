package com.oculus.platform;

import X.AnonymousClass006;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Surface;
import com.facebook.internal.AnalyticsEvents;
import com.oculus.aidl.OVRMediaServiceInterface;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.aidl.OVRShareServiceInterface;
import com.oculus.common.vrshell.Constants;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.service.AssetBundleUtil;
import com.oculus.horizon.service.result.SetRichPresenceResultBuilder;
import com.oculus.horizon.service_media.LiveStreamingAudience;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import com.oculus.horizon.service_media.dumper.OVRMediaServiceDumperPlugin;
import com.oculus.platform.OVRServiceSynchronous;
import com.oculus.platform.callback.OVRErrorOrReleaseCallback;
import com.oculus.platform.callback.OVRStringCallback;
import com.oculus.platform.receiver.OVRPlatformResultListener;
import com.oculus.platform.receiver.OVRPlatformResultReceiver;
import com.oculus.platform.util.OVRError;
import com.oculus.platform.util.Util;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class OVRServiceManager {
    public static final String ASSET_FILE_ID_KEY = "asset_id";
    public static final String ASSET_FILE_NAME_KEY = "asset_filename";
    public static final String COLOCATION_RECEIVER_KEY = "receiver_tag";
    public static final String COLOCATION_RESPONSE_KEY = "result_key";
    public static final String FROM_PACKAGE_KEY = "from_pkg";
    public static final String IAP_COMPONENT_NAME_FLATTEN_KEY = "component_name_flatten";
    public static final String IAP_COMPONENT_NAME_KEY = "component_name";
    public static final String IAP_RESPONSE_KEY = "response";
    public static final String IAP_RESULT_RECEIVER_KEY = "result_receiver";
    public static final String IAP_SKU_KEY = "sku";
    public static final String INVITABLE_USERS_RECEIVER_KEY = "result_receiver";
    public static final String INVITABLE_USERS_ROOM_KEY = "ovr_room_id";
    public static final String LANGUAGE_PACK_TAG_KEY = "tag";
    public static final int MAX_FILE_SIZE = 104857600;
    public static final int NUMBER_OF_THREADS_IN_POOL = 10;
    public static final String RICH_PRESENCE_RECEIVER_KEY = "receiver_tag";
    public static final int SHARED_MEMORY_INFO_ID = 1;
    public static final String SHARE_SERVICE_SOCIAL_PLATFORM_COMPONENT = "com.oculus.socialplatform.SharedMemoryService";
    public static final String SOCIAL_PLATFORM_PACKAGE = "com.oculus.socialplatform";
    public static final String SOURCE_KEY = "source";
    public static final String SOURCE_SDK_DEEPLINK = "sdk_deeplink";
    public static final String TAG = "OVRServiceManager";
    public final String mAppId;
    public final Context mContext;
    public int mCurrentSDKMajorVersion = 0;
    public int mCurrentSDKMinorVersion = 0;
    public final String mGameEngine;
    public final Object mLock = new Object();
    public final Handler mMainThreadHandler;
    public OVRServiceSynchronous<OVRMediaServiceInterface> mOVRMediaServiceSync;
    public OVRServiceSynchronous<OVRServiceInterface> mOVRServiceSync;
    public OVRServiceSynchronous<OVRShareServiceInterface> mOVRShareServiceSocialPlatformSync;
    public ExecutorService mThreadPool;

    public enum ConnectionStatus {
        CONNECTED,
        CONNECTING,
        NOT_CONNECTED
    }

    public void launchIAPCheckoutFlow(final String str, final long j, final OVRStringCallback oVRStringCallback, final ComponentName componentName) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass21 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                generateArgsBundle.putString(OVRServiceManager.IAP_SKU_KEY, str);
                try {
                    final Intent iAPCheckoutFlowIntent = waitForConnect.mServiceInterface.getIAPCheckoutFlowIntent(null, generateArgsBundle);
                    if (iAPCheckoutFlowIntent == null) {
                        Log.e(OVRServiceManager.TAG, String.format("getIAPCheckoutFlowIntent returned null, sku[%s]", str));
                        OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                        return;
                    }
                    iAPCheckoutFlowIntent.putExtra(OVRServiceManager.IAP_COMPONENT_NAME_KEY, componentName);
                    String stringExtra = iAPCheckoutFlowIntent.getStringExtra("uri");
                    if (stringExtra != null) {
                        iAPCheckoutFlowIntent.putExtra("uri", new Uri.Builder().path(stringExtra).appendQueryParameter("platform_request_id", Long.toString(j)).appendQueryParameter(OVRServiceManager.IAP_COMPONENT_NAME_FLATTEN_KEY, componentName.flattenToString()).build().toString());
                    }
                    iAPCheckoutFlowIntent.setAction(Constants.ACTION_LAUNCH);
                    iAPCheckoutFlowIntent.putExtra("intent_data", "systemux://launch_iap");
                    iAPCheckoutFlowIntent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver"));
                    OVRServiceManager.this.mMainThreadHandler.post(new Runnable() {
                        /* class com.oculus.platform.OVRServiceManager.AnonymousClass21.AnonymousClass1 */

                        public void run() {
                            try {
                                OVRServiceManager.this.mContext.sendBroadcast(iAPCheckoutFlowIntent);
                            } catch (ActivityNotFoundException e) {
                                Log.e(OVRServiceManager.TAG, "Error launching IAP intent", e);
                                AnonymousClass21 r0 = AnonymousClass21.this;
                                OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.STORE_INSTALLATION_ERROR);
                            }
                        }
                    });
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void launchOtherApp(final long j, final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass31 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                if (waitForConnect.mBindError != null) {
                    Log.e(OVRServiceManager.TAG, "Unable to bind to the Horizon Service.");
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, waitForConnect.mBindError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString(DeeplinkIntentUtils.PARAM_APP_ID, Long.toString(j));
                    generateArgsBundle.putString(DeeplinkIntentUtils.PARAM_DEEPLINK_OPTIONS, str);
                    Bundle launchOtherApp = waitForConnect.mServiceInterface.launchOtherApp(generateArgsBundle);
                    if (Util.isServiceError(launchOtherApp)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(launchOtherApp));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(launchOtherApp));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling launchOtherApp", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void shareMediaToFacebook(final String str, final String str2, final String str3, final int i, final long j, final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, final ComponentName componentName) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass19 */

            public void run() {
                String str;
                String str2;
                File file = new File(str2);
                if (!file.exists()) {
                    str = OVRServiceManager.TAG;
                    str2 = "File does not exist: ";
                } else if (file.length() > 104857600) {
                    str = OVRServiceManager.TAG;
                    str2 = "File is too large to share: ";
                } else {
                    SharedMemoryJNIReturnValues sharedMemoryJNIReturnValues = new SharedMemoryJNIReturnValues();
                    OVR.nativeWriteFileToSharedMemory(str2, sharedMemoryJNIReturnValues);
                    int i = sharedMemoryJNIReturnValues.fd;
                    int i2 = sharedMemoryJNIReturnValues.size;
                    if (!(i == -1 || i2 == 0)) {
                        ParcelFileDescriptor adoptFd = ParcelFileDescriptor.adoptFd(i);
                        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRShareServiceSocialPlatformSync.waitForConnect();
                        OVRError oVRError = waitForConnect.mBindError;
                        if (oVRError != null) {
                            Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRErrorOrReleaseCallback, oVRError);
                            return;
                        }
                        Message obtain = Message.obtain(null, 1, i2, 0, adoptFd);
                        Bundle bundle = new Bundle();
                        String str3 = str2;
                        bundle.putCharSequence("fileName", str3.substring(str3.lastIndexOf("/") + 1));
                        obtain.setData(bundle);
                        try {
                            waitForConnect.mMessenger.send(obtain);
                            OVRServiceManager.this.deeplinkToSystemUI("systemux://share_media", new Uri.Builder().encodedPath("/system_utilities/share_modal/").appendPath(componentName.getPackageName()).appendPath(OVRServiceManager.SOURCE_SDK_DEEPLINK).appendQueryParameter("defaultComment", str).appendQueryParameter("contentType", str3).appendQueryParameter("returnUrl", AnonymousClass006.A05("apk://", componentName.flattenToString())), i, j, componentName, oVRErrorOrReleaseCallback);
                            return;
                        } catch (RemoteException unused) {
                            OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRErrorOrReleaseCallback, (OVRErrorOrReleaseCallback) OVRError.UNKNOWN_ERROR);
                            return;
                        }
                    }
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRErrorOrReleaseCallback, (OVRErrorOrReleaseCallback) OVRError.UNKNOWN_ERROR);
                }
                Log.e(str, AnonymousClass006.A05(str2, str2));
                OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRErrorOrReleaseCallback, (OVRErrorOrReleaseCallback) OVRError.UNKNOWN_ERROR);
            }
        });
    }

    private Bundle createMediaServiceCommandBundle(String str) {
        Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
        generateArgsBundle.putString("message_type", str);
        return generateArgsBundle;
    }

    public static JSONObject flatBundleToJson(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, JSONObject.wrap(bundle.get(str)));
        }
        return jSONObject;
    }

    public static String getAssetStringFromBundle(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "Horizon is outdated.");
            return "";
        }
        Bundle bundle2 = bundle.getBundle(AssetBundleUtil.EXTRA_LANGUAGE_PACK_INFO);
        bundle.remove(AssetBundleUtil.EXTRA_LANGUAGE_PACK_INFO);
        try {
            JSONObject flatBundleToJson = flatBundleToJson(bundle);
            if (bundle2 != null) {
                flatBundleToJson.put(AssetBundleUtil.EXTRA_LANGUAGE_PACK_INFO, flatBundleToJson(bundle2));
            }
            return flatBundleToJson.toString();
        } catch (JSONException e) {
            Log.e(TAG, "bad bundle", e);
            return "";
        }
    }

    public static String getJsonStringFromBundle(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "Horizon is outdated.");
            return "";
        }
        try {
            return flatBundleToJson(bundle).toString();
        } catch (JSONException e) {
            Log.e(TAG, "bad bundle", e);
            return "";
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ResultReceiver getReceiverForStringCallback(OVRStringCallback oVRStringCallback, String str) {
        return getReceiverForIntent(new OVRPlatformResultReceiver(new OVRPlatformResultListener(oVRStringCallback, this.mMainThreadHandler, str)));
    }

    private void handleLivestreamingAPI(final OVRStringCallback oVRStringCallback, final Bundle bundle, final String str) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass27 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRMediaServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString("message_type", str);
                    generateArgsBundle.putAll(bundle);
                    oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(waitForConnect.mServiceInterface.sendCommandToMediaService(generateArgsBundle)));
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void postNewClientReleaseRunnable(final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback) {
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass35 */

            public void run() {
                oVRErrorOrReleaseCallback.onClientRelease();
            }
        });
    }

    private void sendCommandToMediaService(final Bundle bundle) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass26 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRMediaServiceSync.waitForConnect();
                if (waitForConnect.mBindError == null) {
                    try {
                        waitForConnect.mServiceInterface.sendCommandToMediaService(bundle);
                    } catch (RemoteException e) {
                        Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bundle stopOrCancelRecording(String str, String str2) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRMediaServiceSync.waitForConnect();
        if (waitForConnect.mBindError == null) {
            try {
                Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
                generateArgsBundle.putString("recording_uuid", str);
                generateArgsBundle.putString("message_type", str2);
                return waitForConnect.mServiceInterface.sendCommandToMediaService(generateArgsBundle);
            } catch (RemoteException e) {
                Log.e(TAG, "Error while sending stop abuse recording command to OVRMediaService", e);
            }
        }
        return null;
    }

    public void assetFileDeleteById(final long j, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass10 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDeleteById = waitForConnect.mServiceInterface.assetFileDeleteById(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) j));
                    if (Util.isServiceError(assetFileDeleteById)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDeleteById));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDeleteById));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error deleting asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileDeleteByName(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass11 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDeleteByName = waitForConnect.mServiceInterface.assetFileDeleteByName(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) str));
                    if (Util.isServiceError(assetFileDeleteByName)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDeleteByName));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDeleteByName));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error deleting asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileDownloadById(final long j, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass6 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDownloadById = waitForConnect.mServiceInterface.assetFileDownloadById(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) j));
                    if (Util.isServiceError(assetFileDownloadById)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDownloadById));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDownloadById));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error downloading an asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileDownloadByName(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass7 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDownloadByName = waitForConnect.mServiceInterface.assetFileDownloadByName(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) str));
                    if (Util.isServiceError(assetFileDownloadByName)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDownloadByName));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDownloadByName));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error downloading an asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileDownloadCancelById(final long j, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass8 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDownloadCancelById = waitForConnect.mServiceInterface.assetFileDownloadCancelById(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) j));
                    if (Util.isServiceError(assetFileDownloadCancelById)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDownloadCancelById));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDownloadCancelById));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error canceling download request of an asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileDownloadCancelByName(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass9 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileDownloadCancelByName = waitForConnect.mServiceInterface.assetFileDownloadCancelByName(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) str));
                    if (Util.isServiceError(assetFileDownloadCancelByName)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileDownloadCancelByName));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(assetFileDownloadCancelByName));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error canceling download request of an asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileList(final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass14 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileList = waitForConnect.mServiceInterface.assetFileList();
                    if (Util.isServiceError(assetFileList)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileList));
                    } else {
                        oVRStringCallback.onSuccess(assetFileList.getString("result"));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error getting asset list", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileStatusById(final long j, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass12 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileStatusById = waitForConnect.mServiceInterface.assetFileStatusById(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) j));
                    if (Util.isServiceError(assetFileStatusById)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileStatusById));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getAssetStringFromBundle(assetFileStatusById));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error getting asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void assetFileStatusByName(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass13 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    Bundle assetFileStatusByName = waitForConnect.mServiceInterface.assetFileStatusByName(OVRServiceManager.this.getDefaultAssetFileArgsBundle((OVRServiceManager) str));
                    if (Util.isServiceError(assetFileStatusByName)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(assetFileStatusByName));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getAssetStringFromBundle(assetFileStatusByName));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error getting asset file", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void broadcastJsonStringFromDataChannel(String str) {
        Bundle createMediaServiceCommandBundle = createMediaServiceCommandBundle(OVRMediaServiceContract.BROADCAST_JSON_STRING_FROM_DATA_CHANNEL);
        createMediaServiceCommandBundle.putString("data", str);
        sendCommandToMediaService(createMediaServiceCommandBundle);
    }

    public void broadcastLivestreamingSurface(final String str, final Surface surface, final int i) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass24 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRMediaServiceSync.waitForConnect();
                if (waitForConnect.mBindError == null) {
                    try {
                        OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                        Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                        generateArgsBundle.putString("message_type", OVRMediaServiceContract.BROADCAST_CAPTURE_SURFACE);
                        generateArgsBundle.putParcelable("surface", surface);
                        generateArgsBundle.putString("package_name", str);
                        generateArgsBundle.putInt("error_code", i);
                        waitForConnect.mServiceInterface.sendCommandToMediaService(generateArgsBundle);
                    } catch (RemoteException e) {
                        Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    }
                }
            }
        });
    }

    public void broadcastLivestreamingVrcameraSurface(final String str, final Surface surface, final int i) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass25 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRMediaServiceSync.waitForConnect();
                if (waitForConnect.mBindError == null) {
                    try {
                        OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                        Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                        generateArgsBundle.putString("message_type", OVRMediaServiceContract.BROADCAST_CAPTURE_VRCAMERA_SURFACE);
                        generateArgsBundle.putParcelable("surface", surface);
                        generateArgsBundle.putString("package_name", str);
                        generateArgsBundle.putInt("error_code", i);
                        waitForConnect.mServiceInterface.sendCommandToMediaService(generateArgsBundle);
                    } catch (RemoteException e) {
                        Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    }
                }
            }
        });
    }

    public void broadcastPartyChatLocalState(int i, int i2, int i3) {
        Bundle createMediaServiceCommandBundle = createMediaServiceCommandBundle(OVRMediaServiceContract.BROADCAST_PARTY_CHAT_LOCAL_STATE);
        createMediaServiceCommandBundle.putInt("local_connection_state", i);
        createMediaServiceCommandBundle.putInt("connection_reason", i2);
        createMediaServiceCommandBundle.putInt("connection_retries", i3);
        sendCommandToMediaService(createMediaServiceCommandBundle);
    }

    public void broadcastSetVrcameraAvailable(boolean z) {
        Bundle createMediaServiceCommandBundle = createMediaServiceCommandBundle(OVRMediaServiceContract.BROADCAST_SET_VRCAMERA_AVAILABLE);
        createMediaServiceCommandBundle.putBoolean("is_vrcamera_available", z);
        sendCommandToMediaService(createMediaServiceCommandBundle);
    }

    public void cancelRecording(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass29 */

            public void run() {
                OVRServiceManager.this.stopOrCancelRecording(str, OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_CANCEL);
                oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(new Bundle()));
            }
        });
    }

    public void connectOVRServiceInBackground() {
        this.mOVRServiceSync.connectInBackground();
    }

    public Bundle getAccessTokenSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        OVRError oVRError = waitForConnect.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            return waitForConnect.mServiceInterface.getAppScopedAccessToken(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public Bundle getBaselineGraphURLSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        OVRError oVRError = waitForConnect.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            Bundle baseURLEndpoint = waitForConnect.mServiceInterface.getBaseURLEndpoint(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
            if (baseURLEndpoint.containsKey("base_url_endpoint")) {
                baseURLEndpoint.putString("base_url_endpoint", baseURLEndpoint.getParcelable("base_url_endpoint").toString());
            }
            return baseURLEndpoint;
        } catch (RemoteException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public void getCloudStorage2UserDirectoryPath(final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass17 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle cloudStorage2UserDirectoryPath = waitForConnect.mServiceInterface.getCloudStorage2UserDirectoryPath(Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion));
                    if (Util.isServiceError(cloudStorage2UserDirectoryPath)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(cloudStorage2UserDirectoryPath));
                    } else {
                        oVRStringCallback.onSuccess(cloudStorage2UserDirectoryPath.getString("result"));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling getCloudStorage2UserDirectoryPath", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public int getCurrentAppVersion() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCurrentMapUuid(final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass36 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                try {
                    generateArgsBundle.putParcelable("receiver_tag", OVRServiceManager.this.getReceiverForStringCallback(oVRStringCallback, OVRServiceManager.COLOCATION_RESPONSE_KEY));
                    waitForConnect.mServiceInterface.getCurrentMapUuid(generateArgsBundle);
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling getCurrentMapUuid", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public Bundle getDeviceScopedAccessTokenSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        OVRError oVRError = waitForConnect.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            return waitForConnect.mServiceInterface.getDeviceScopedAccessToken(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public Bundle getEntitlementCheckBundleSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED = this.mOVRServiceSync.waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED();
        OVRError oVRError = waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            Bundle entitlementCheckBundle = waitForConnectPossiblyMainthread_DO_NOT_USE_OR_YOU_WILL_BE_FIRED.mServiceInterface.getEntitlementCheckBundle(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
            if (entitlementCheckBundle == null) {
                return Util.makeErrorResult(OVRError.STORE_INSTALLATION_ERROR);
            }
            return entitlementCheckBundle;
        } catch (RemoteException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public Bundle getInstalledApplicationsJSONSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            return waitForConnect.mServiceInterface.getInstalledVRApplications(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Horizon isn't new enough to getInstalledApplications.", e);
            return new Bundle();
        }
    }

    public Bundle getLatestAvailableAppInformation() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        OVRError oVRError = waitForConnect.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            Bundle latestAvailableAppInformation = waitForConnect.mServiceInterface.getLatestAvailableAppInformation(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
            if (latestAvailableAppInformation == null) {
                return Util.makeErrorResult(OVRError.STORE_INSTALLATION_ERROR);
            }
            if (Util.isServiceError(latestAvailableAppInformation)) {
                return latestAvailableAppInformation;
            }
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("currentVersion", packageInfo.versionName);
                jSONObject.put("currentVersionCode", packageInfo.versionCode);
                jSONObject.put("latestVersion", latestAvailableAppInformation.getString("latest_version_name"));
                jSONObject.put("latestVersionCode", latestAvailableAppInformation.getInt("latest_version_code"));
                Bundle bundle = new Bundle();
                bundle.putString("app_version_information", jSONObject.toString());
                return bundle;
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            } catch (JSONException e2) {
                Log.e(TAG, "An error occurred getting app version", e2);
                return Util.makeErrorResult(e2, e2.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
            }
        } catch (RemoteException e3) {
            return Util.makeErrorResult(e3, e3.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public void getLivestreamingStatus(OVRStringCallback oVRStringCallback) {
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.GET_LIVESTREAMING_STATUS);
    }

    public Bundle getPackageName() {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", this.mContext.getPackageName());
        return bundle;
    }

    public Bundle getSharedMicrophoneData(long j, int i) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putLong("microphone_uid", j);
            generateArgsBundle.putInt("max_size", i);
            return waitForConnect.mServiceInterface.getSharedMicrophoneData(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSharedMicrophoneData()", e);
            return new Bundle();
        }
    }

    public Bundle getSystemVoipDataSync(boolean z) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putBoolean("include_pcm", z);
            return waitForConnect.mServiceInterface.getSystemVoipData(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSystemVoipData()", e);
            return new Bundle();
        }
    }

    public String getSystemVoipMicrophoneMutedSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return null;
        }
        try {
            return waitForConnect.mServiceInterface.getSystemVoipMicrophoneMuted(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSystemVoipMicrophoneMutedSync()", e);
            return null;
        }
    }

    public boolean getSystemVoipPassthroughSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return false;
        }
        try {
            return waitForConnect.mServiceInterface.getSystemVoipPassthrough(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSystemVoipPassthroughSync()", e);
            return false;
        }
    }

    public String getSystemVoipStatusSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return null;
        }
        try {
            return waitForConnect.mServiceInterface.getSystemVoipStatus(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSystemVoipStatusSync()", e);
            return null;
        }
    }

    public Bundle getUserAgentSync() {
        Bundle bundle = new Bundle();
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
            StringBuilder sb = new StringBuilder();
            sb.append(this.mCurrentSDKMajorVersion);
            sb.append(".");
            sb.append(this.mCurrentSDKMinorVersion);
            String obj = sb.toString();
            UserAgentBuilder userAgentBuilder = new UserAgentBuilder(this.mContext);
            userAgentBuilder.mHttpAgent = AnonymousClass006.A05("OVRPlatform/", obj);
            userAgentBuilder.setAppName(this.mAppId);
            userAgentBuilder.setAppVersion(packageInfo.versionName);
            userAgentBuilder.setBuildVersion(String.valueOf(packageInfo.versionCode));
            userAgentBuilder.setLocale(Locale.getDefault().getLanguage());
            userAgentBuilder.setEngine(this.mGameEngine);
            bundle.putString("user_agent", userAgentBuilder.build());
            return bundle;
        } catch (PackageManager.NameNotFoundException unused) {
            throw new RuntimeException();
        }
    }

    public Bundle getViewerPurchasesDurableCacheJSONSync() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        OVRError oVRError = waitForConnect.mBindError;
        if (oVRError != null) {
            return Util.makeErrorResult(oVRError);
        }
        try {
            return waitForConnect.mServiceInterface.getViewerPurchasesDurableCacheJSON(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    public void languagePackGetCurrent(final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass15 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle languagePackGetCurrent = waitForConnect.mServiceInterface.languagePackGetCurrent(Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion));
                    if (Util.isServiceError(languagePackGetCurrent)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(languagePackGetCurrent));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(languagePackGetCurrent));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error getting current langauge pack", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void languagePackSetCurrent(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass16 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString("tag", str);
                    Bundle languagePackSetCurrent = waitForConnect.mServiceInterface.languagePackSetCurrent(generateArgsBundle);
                    if (Util.isServiceError(languagePackSetCurrent)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(languagePackSetCurrent));
                    } else {
                        oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(languagePackSetCurrent));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error setting current langauge pack", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void launchAbuseReportFlow(long j, String str, String str2, String str3, int i, long j2, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        Uri.Builder appendPath = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(componentName.getPackageName());
        String l = Long.toString(j);
        deeplinkToSystemUI("systemux://user_report", appendPath.appendPath(l).appendPath(SOURCE_SDK_DEEPLINK).appendQueryParameter("content_id", l).appendQueryParameter("report_type", str).appendQueryParameter(AnalyticsEvents.PARAMETER_LIKE_VIEW_OBJECT_TYPE, str2).appendQueryParameter("video_mode", str3).appendQueryParameter("report_sdk_version", "3"), i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchInvitableUsersFlow(final long j, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass22 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                generateArgsBundle.putLong("ovr_room_id", j);
                try {
                    final Intent invitableUsersFlowIntent = waitForConnect.mServiceInterface.getInvitableUsersFlowIntent(generateArgsBundle);
                    if (invitableUsersFlowIntent == null) {
                        Log.e(OVRServiceManager.TAG, String.format("getInvitableUsersFlowIntent returned null, room[%s]", Long.toString(j)));
                        OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                        return;
                    }
                    invitableUsersFlowIntent.putExtra("result_receiver", OVRServiceManager.this.getReceiverForStringCallback(oVRStringCallback, ""));
                    OVRServiceManager.this.mMainThreadHandler.post(new Runnable() {
                        /* class com.oculus.platform.OVRServiceManager.AnonymousClass22.AnonymousClass1 */

                        public void run() {
                            try {
                                invitableUsersFlowIntent.setAction(Constants.ACTION_LAUNCH);
                                invitableUsersFlowIntent.putExtra("intent_data", "systemux://invite_friends");
                                invitableUsersFlowIntent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver"));
                                OVRServiceManager.this.mContext.sendBroadcast(invitableUsersFlowIntent);
                            } catch (ActivityNotFoundException e) {
                                Log.e(OVRServiceManager.TAG, "Error launching InvitableUsersFlow intent", e);
                                AnonymousClass22 r0 = AnonymousClass22.this;
                                OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.STORE_INSTALLATION_ERROR);
                            }
                        }
                    });
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling doInBackground()", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void launchLivestreamingFlow(int i, long j, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        deeplinkToSystemUI("systemux://livestreaming", new Uri.Builder().encodedPath("/system_utilities/livestreaming/").appendPath(componentName.getPackageName()).appendPath(SOURCE_SDK_DEEPLINK), i, j, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchUserBlockFlow(long j, int i, long j2, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        deeplinkToSystemUI("systemux://user_block", new Uri.Builder().encodedPath("/system_utilities/user_block/").appendPath(componentName.getPackageName()).appendPath(Long.toString(j)).appendPath(SOURCE_SDK_DEEPLINK), i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchUserFriendRequestFlow(long j, int i, long j2, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        deeplinkToSystemUI("systemux://user_friend_request", new Uri.Builder().encodedPath("/system_utilities/user_friend_request/").appendPath(componentName.getPackageName()).appendPath(Long.toString(j)).appendPath(SOURCE_SDK_DEEPLINK), i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchUserProfile(long j, int i, long j2, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        deeplinkToSystemUI("systemux://user_profile", new Uri.Builder().encodedPath("/system_utilities/profile/").appendPath(componentName.getPackageName()).appendPath(Long.toString(j)).appendPath(SOURCE_SDK_DEEPLINK), i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchUserReportFlow(long j, int i, long j2, String str, String str2, boolean z, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        Uri.Builder appendQueryParameter = new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(componentName.getPackageName()).appendPath(Long.toString(j)).appendPath(SOURCE_SDK_DEEPLINK).appendQueryParameter("abuse_report_type", str2).appendQueryParameter("prevent_people_chooser", String.valueOf(z)).appendQueryParameter("report_sdk_version", "2");
        if (str != null) {
            appendQueryParameter.appendQueryParameter("recording_uuid", str);
        }
        deeplinkToSystemUI("systemux://user_report", appendQueryParameter, i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void launchUserUnblockFlow(long j, int i, long j2, OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, ComponentName componentName) {
        deeplinkToSystemUI("systemux://user_unblock", new Uri.Builder().encodedPath("/system_utilities/user_unblock/").appendPath(componentName.getPackageName()).appendPath(Long.toString(j)).appendPath(SOURCE_SDK_DEEPLINK), i, j2, componentName, oVRErrorOrReleaseCallback);
    }

    public void logToMarauder(final boolean z, final String str) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass18 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                if (waitForConnect.mBindError == null) {
                    try {
                        OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                        Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                        generateArgsBundle.putBoolean("first_party_app_log", z);
                        Bundle logToMarauder = waitForConnect.mServiceInterface.logToMarauder(str, generateArgsBundle);
                        if (logToMarauder == null || Util.isServiceError(logToMarauder)) {
                            Log.e(OVRServiceManager.TAG, "An error occurred; Horizon didn't log to Maruader;");
                        }
                    } catch (RemoteException e) {
                        Log.e(OVRServiceManager.TAG, "Horizon isn't new enough to log Marauder calls.", e);
                    }
                }
            }
        });
    }

    public Bundle partyChatGetVolume() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            return waitForConnect.mServiceInterface.partyChatGetVolume(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling partyChatGetVolume()", e);
            return new Bundle();
        }
    }

    public Bundle partyChatSetVolume(float f) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putFloat("volume", f);
            return waitForConnect.mServiceInterface.partyChatSetVolume(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling partyChatSetVolume()", e);
            return new Bundle();
        }
    }

    public void pauseLivestream(OVRStringCallback oVRStringCallback) {
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.PAUSE_STREAMING);
    }

    public void requestMap(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass38 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("uuid", str);
                try {
                    bundle.putParcelable("receiver_tag", OVRServiceManager.this.getReceiverForStringCallback(oVRStringCallback, OVRServiceManager.COLOCATION_RESPONSE_KEY));
                    waitForConnect.mServiceInterface.requestMap(bundle);
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling requestMap", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void resumeLivestream(OVRStringCallback oVRStringCallback) {
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.RESUME_STREAMING);
    }

    public void sendProcessToken() {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass5 */

            public void run() {
                Bundle makeErrorResult;
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    makeErrorResult = Util.makeErrorResult(oVRError);
                } else {
                    try {
                        OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                        Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                        generateArgsBundle.putBinder("token", new Binder());
                        makeErrorResult = waitForConnect.mServiceInterface.registerProcessToken(generateArgsBundle);
                    } catch (RemoteException e) {
                        makeErrorResult = Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                    }
                }
                if (Util.isServiceError(makeErrorResult)) {
                    Log.w(OVRServiceManager.TAG, String.format("Failed to register process token ([%s] %s), service will not track process life.", Util.getServiceError(makeErrorResult).name(), Util.getServiceErrorMessage(makeErrorResult)));
                }
            }
        });
    }

    public void setRichPresence(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass32 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                if (waitForConnect.mBindError != null) {
                    Log.e(OVRServiceManager.TAG, "Unable to bind to the Horizon Service.");
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString(SetRichPresenceResultBuilder.RICH_PRESENCE_JSON_ARG_KEY, str);
                    generateArgsBundle.putParcelable("receiver_tag", OVRServiceManager.this.getReceiverForStringCallback(oVRStringCallback, ""));
                    Bundle richPresence = waitForConnect.mServiceInterface.setRichPresence(generateArgsBundle);
                    if (Util.isServiceError(richPresence)) {
                        Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, Util.getServiceError(richPresence));
                    }
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling setRichPresence", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public Bundle setSystemVoipMicrophoneMutedSync(int i) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putInt("microphone_muted", i);
            return waitForConnect.mServiceInterface.setSystemVoipMicrophoneMuted(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling setSystemVoipMicrophoneMuted()", e);
            return new Bundle();
        }
    }

    public ParcelFileDescriptor setSystemVoipPassthroughSync(boolean z) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return null;
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putBoolean("passthrough", z);
            return waitForConnect.mServiceInterface.setSystemVoipPassthrough(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling setSystemVoipPassthrough()", e);
            return null;
        }
    }

    public Bundle setSystemVoipSuppressedSync(boolean z) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putBoolean("suppressed", z);
            return waitForConnect.mServiceInterface.setSystemVoipSuppressed(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling setSystemVoipSuppressed()", e);
            return new Bundle();
        }
    }

    public void shareMap(final String str, final OVRStringCallback oVRStringCallback) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass37 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                generateArgsBundle.putString("uuid", str);
                try {
                    generateArgsBundle.putParcelable("receiver_tag", OVRServiceManager.this.getReceiverForStringCallback(oVRStringCallback, OVRServiceManager.COLOCATION_RESPONSE_KEY));
                    waitForConnect.mServiceInterface.shareMap(generateArgsBundle);
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling shareMap", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void sharedMicrophoneDisableNoiseSuppressor() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return;
        }
        try {
            waitForConnect.mServiceInterface.sharedMicrophoneDisableNoiseSuppressor(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling disableNoiseSuppressor()", e);
        }
    }

    public void sharedMicrophoneEnableNoiseSuppressor() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return;
        }
        try {
            waitForConnect.mServiceInterface.sharedMicrophoneEnableNoiseSuppressor(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling enableNoiseSuppressor()", e);
        }
    }

    public ParcelFileDescriptor sharedMicrophoneGetSocketFD(long j) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return null;
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putLong("microphone_uid", j);
            return waitForConnect.mServiceInterface.getSharedMicrophoneSocketFD(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSharedMicrophoneSocketFD()", e);
            return null;
        }
    }

    public void sharedMicrophoneStop(long j) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return;
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putLong("microphone_uid", j);
            waitForConnect.mServiceInterface.sharedMicrophoneStop(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling getSharedMicrophoneData()", e);
        }
    }

    public void startLivestream(int i, int i2, OVRStringCallback oVRStringCallback) {
        Bundle bundle = new Bundle();
        bundle.putInt(LiveStreamingAudience.INTENT_KEY, i);
        bundle.putInt("microphone_status", i2);
        handleLivestreamingAPI(oVRStringCallback, bundle, OVRMediaServiceContract.START_FACEBOOK_STREAMING);
    }

    public Bundle startPartyChat(long j) {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
            generateArgsBundle.putLong("party_id", j);
            return waitForConnect.mServiceInterface.startPartyChat(generateArgsBundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling startPartyChat()", e);
            return new Bundle();
        }
    }

    public void startPartyLivestream(OVRStringCallback oVRStringCallback) {
        new Bundle();
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.START_PARTY_VIDEO_STREAMING);
    }

    public void startRecording(final OVRStringCallback oVRStringCallback) {
        final Bundle bundle = new Bundle();
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass28 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRMediaServiceSync.waitForConnect();
                OVRError oVRError = waitForConnect.mBindError;
                if (oVRError != null) {
                    Util.handleClientError(OVRServiceManager.this.mMainThreadHandler, oVRStringCallback, oVRError);
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString("message_type", OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_START);
                    generateArgsBundle.putAll(bundle);
                    Bundle sendCommandToMediaService = waitForConnect.mServiceInterface.sendCommandToMediaService(generateArgsBundle);
                    if (sendCommandToMediaService == null) {
                        oVRStringCallback.onClientError(OVRError.UNKNOWN_ERROR.mCode, "Failed to start recording");
                        return;
                    }
                    boolean z = sendCommandToMediaService.getBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, false);
                    String string = sendCommandToMediaService.getString("recording_uuid", null);
                    if (!z || string == null || string.isEmpty()) {
                        oVRStringCallback.onClientError(OVRError.UNKNOWN_ERROR.mCode, "Failed to start recording");
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("recording_uuid", string);
                    oVRStringCallback.onSuccess(OVRServiceManager.getJsonStringFromBundle(bundle));
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error while sending start abuse recording command to OVRMediaService", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRStringCallback, (OVRStringCallback) OVRError.UNKNOWN_ERROR);
                }
            }
        });
    }

    public void stopLivestream(OVRStringCallback oVRStringCallback) {
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.STOP_FACEBOOK_STREAMING);
    }

    public Bundle stopPartyChat() {
        OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = this.mOVRServiceSync.waitForConnect();
        if (waitForConnect.mBindError != null) {
            Log.e(TAG, "Unable to bind to the Horizon Service.");
            return new Bundle();
        }
        try {
            return waitForConnect.mServiceInterface.stopPartyChat(Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling stopPartyChat()", e);
            return new Bundle();
        }
    }

    public void stopPartyLivestream(OVRStringCallback oVRStringCallback) {
        new Bundle();
        handleLivestreamingAPI(oVRStringCallback, new Bundle(), OVRMediaServiceContract.STOP_PARTY_VIDEO_STREAMING);
    }

    public void stopRecordingAndLaunchReportFlow(final long j, final String str, final boolean z, final int i, final long j2, final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, final ComponentName componentName) {
        if (str == null || str.isEmpty()) {
            launchUserReportFlow(j, i, j2, null, "USER", z, oVRErrorOrReleaseCallback, componentName);
        } else {
            this.mThreadPool.execute(new Runnable() {
                /* class com.oculus.platform.OVRServiceManager.AnonymousClass30 */

                public void run() {
                    OVRServiceManager.this.stopOrCancelRecording(str, OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STOP);
                    OVRServiceManager.this.launchUserReportFlow(j, i, j2, str, "USER", z, oVRErrorOrReleaseCallback, componentName);
                }
            });
        }
    }

    public void updateLivestreamCommentsVisibility(boolean z, OVRStringCallback oVRStringCallback) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(OVRMediaServiceContract.INTENT_KEY_COMMENTS_VISIBLE, z);
        handleLivestreamingAPI(oVRStringCallback, bundle, OVRMediaServiceContract.UPDATE_LIVESTREAMING_COMMENTS_VISIBILITY);
    }

    public void updateLivestreamMicStatus(int i, OVRStringCallback oVRStringCallback) {
        Bundle bundle = new Bundle();
        bundle.putInt("microphone_status", i);
        handleLivestreamingAPI(oVRStringCallback, bundle, OVRMediaServiceContract.UPDATE_LIVESTREAMING_MICROPHONE);
    }

    public void updatePlatformContext(final String str) {
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass20 */

            public void run() {
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = OVRServiceManager.this.mOVRServiceSync.waitForConnect();
                if (waitForConnect.mBindError != null) {
                    Log.e(OVRServiceManager.TAG, "Unable to bind to the Horizon Service.");
                    return;
                }
                try {
                    OVRServiceManager oVRServiceManager = OVRServiceManager.this;
                    Bundle generateArgsBundle = Util.generateArgsBundle(oVRServiceManager.mAppId, oVRServiceManager.mCurrentSDKMajorVersion, oVRServiceManager.mCurrentSDKMinorVersion);
                    generateArgsBundle.putString(GraphQLParamsHelper.KEY_ARGS, str);
                    waitForConnect.mServiceInterface.updatePlatformContext(generateArgsBundle);
                } catch (RemoteException e) {
                    Log.e(OVRServiceManager.TAG, "Error calling updatePlatformContext()", e);
                }
            }
        });
    }

    public void verifyApplicationLivestreamingAllowed(String str, OVRStringCallback oVRStringCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        handleLivestreamingAPI(oVRStringCallback, bundle, OVRMediaServiceContract.GET_IS_LIVESTREAMING_ENABLED);
    }

    public OVRServiceManager(Context context, String str, String str2, int i, int i2) {
        this.mContext = context;
        this.mGameEngine = str2;
        this.mMainThreadHandler = new Handler(context.getMainLooper());
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        this.mThreadPool = newFixedThreadPool;
        this.mAppId = str;
        this.mCurrentSDKMajorVersion = i;
        this.mCurrentSDKMinorVersion = i2;
        Context context2 = this.mContext;
        OVRServiceSynchronous<OVRServiceInterface> oVRServiceSynchronous = new OVRServiceSynchronous<>(context2, "com.oculus.horizon", OVR.SERVICE_COMPONENT, newFixedThreadPool, new OVRServiceSynchronous.AidlService<OVRServiceInterface>() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass1 */

            @Override // com.oculus.platform.OVRServiceSynchronous.AidlService
            public OVRServiceInterface serviceFromBinder(IBinder iBinder) {
                return OVRServiceInterface.Stub.asInterface(iBinder);
            }
        });
        this.mOVRServiceSync = oVRServiceSynchronous;
        oVRServiceSynchronous.mConnectionStatusListener = new OVRServiceSynchronous.ConnectionStatusListener() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass2 */

            @Override // com.oculus.platform.OVRServiceSynchronous.ConnectionStatusListener
            public void onConnectionStatusChanged(OVRServiceSynchronous.ConnectionStatus connectionStatus) {
                if (connectionStatus == OVRServiceSynchronous.ConnectionStatus.CONNECTED) {
                    OVRServiceManager.this.sendProcessToken();
                }
            }
        };
        this.mOVRMediaServiceSync = new OVRServiceSynchronous<>(context2, "com.oculus.horizon", OVRMediaServiceDumperPlugin.HORIZON_COMPONENT_NAME, newFixedThreadPool, new OVRServiceSynchronous.AidlService<OVRMediaServiceInterface>() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass3 */

            @Override // com.oculus.platform.OVRServiceSynchronous.AidlService
            public OVRMediaServiceInterface serviceFromBinder(IBinder iBinder) {
                return OVRMediaServiceInterface.Stub.asInterface(iBinder);
            }
        });
        this.mOVRShareServiceSocialPlatformSync = new OVRServiceSynchronous<>(context2, "com.oculus.socialplatform", SHARE_SERVICE_SOCIAL_PLATFORM_COMPONENT, newFixedThreadPool, new OVRServiceSynchronous.AidlService<OVRShareServiceInterface>() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass4 */

            @Override // com.oculus.platform.OVRServiceSynchronous.AidlService
            public OVRShareServiceInterface serviceFromBinder(IBinder iBinder) {
                return OVRShareServiceInterface.Stub.asInterface(iBinder);
            }
        });
    }

    public static /* synthetic */ String access$300() {
        return TAG;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deeplinkToSystemUI(final String str, Uri.Builder builder, int i, long j, final ComponentName componentName, final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback) {
        final Uri build = builder.appendQueryParameter("platform_message_type", Integer.toString(i)).appendQueryParameter("platform_request_id", Long.toString(j)).build();
        this.mThreadPool.execute(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass23 */

            public void run() {
                Intent intent = new Intent(Constants.ACTION_LAUNCH);
                intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver"));
                intent.putExtra("intent_data", str);
                intent.putExtra("uri", build.toString());
                intent.putExtra("intent_pkg", componentName.getPackageName());
                intent.toUri(0);
                try {
                    OVRServiceManager.this.mContext.sendBroadcast(intent);
                    OVRServiceManager.this.postNewClientReleaseRunnable(oVRErrorOrReleaseCallback);
                } catch (ActivityNotFoundException e) {
                    Log.e(OVRServiceManager.TAG, "Error launching deeplink intent", e);
                    OVRServiceManager.this.postNewClientErrorRunnable((OVRServiceManager) oVRErrorOrReleaseCallback, (OVRErrorOrReleaseCallback) OVRError.STORE_INSTALLATION_ERROR);
                }
            }
        });
    }

    public static ResultReceiver getReceiverForIntent(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public ExecutorService getExecutorService() {
        return this.mThreadPool;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bundle getDefaultAssetFileArgsBundle(long j) {
        Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
        generateArgsBundle.putLong("asset_id", j);
        return generateArgsBundle;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bundle getDefaultAssetFileArgsBundle(String str) {
        Bundle generateArgsBundle = Util.generateArgsBundle(this.mAppId, this.mCurrentSDKMajorVersion, this.mCurrentSDKMinorVersion);
        generateArgsBundle.putString("asset_filename", str);
        return generateArgsBundle;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void postNewClientErrorRunnable(final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, final OVRError oVRError) {
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass34 */

            public void run() {
                OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback = oVRErrorOrReleaseCallback;
                OVRError oVRError = oVRError;
                oVRErrorOrReleaseCallback.onClientError(oVRError.mCode, oVRError.mErrorMessage);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void postNewClientErrorRunnable(final OVRStringCallback oVRStringCallback, final OVRError oVRError) {
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.oculus.platform.OVRServiceManager.AnonymousClass33 */

            public void run() {
                OVRStringCallback oVRStringCallback = oVRStringCallback;
                OVRError oVRError = oVRError;
                oVRStringCallback.onClientError(oVRError.mCode, oVRError.mErrorMessage);
            }
        });
    }
}

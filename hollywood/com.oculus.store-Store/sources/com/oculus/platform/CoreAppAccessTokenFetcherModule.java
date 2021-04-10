package com.oculus.platform;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.platform.OVRServiceSynchronous;
import com.oculus.platform.aidl.OVRServiceInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreAppAccessTokenFetcherModule extends RCTBaseJavaModule {
    private static final String CORE_APP_ID = "1880592348624904";
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 17;
    private static final String MODULE_NAME = "CoreAppAccessTokenFetcher";
    private static final String SERVICE_COMPONENT = "com.oculus.horizon.service.OVRService";
    private static final String SERVICE_PACKAGE = "com.oculus.horizon";
    private static final String TAG = CoreAppAccessTokenFetcherModule.class.getSimpleName();
    private final Context mContext;

    public CoreAppAccessTokenFetcherModule(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getToken", "ii"));
        return list;
    }

    public void shutdownModule() {
    }

    /* access modifiers changed from: private */
    public static Bundle generateArgsBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("sdk_major_version", 1);
        bundle.putInt("sdk_minor_version", 17);
        bundle.putString("app_id", CORE_APP_ID);
        bundle.putBoolean("request_core_app_token", true);
        return bundle;
    }

    public void getToken(final int resolveID, final int rejectID) {
        new Thread(new Runnable() {
            /* class com.oculus.platform.CoreAppAccessTokenFetcherModule.AnonymousClass1 */

            public void run() {
                String accessToken;
                OVRServiceSynchronous<OVRServiceInterface>.ServiceBindResult result = new OVRServiceSynchronous<>(CoreAppAccessTokenFetcherModule.this.mContext, "com.oculus.horizon", CoreAppAccessTokenFetcherModule.SERVICE_COMPONENT, Executors.newSingleThreadExecutor(), new OVRServiceSynchronous.AidlService<OVRServiceInterface>() {
                    /* class com.oculus.platform.CoreAppAccessTokenFetcherModule.AnonymousClass1.AnonymousClass1 */

                    @Override // com.oculus.platform.OVRServiceSynchronous.AidlService
                    public OVRServiceInterface serviceFromBinder(IBinder binder) {
                        return OVRServiceInterface.Stub.asInterface(binder);
                    }
                }).waitForConnect();
                if (result.mBindError) {
                    Log.e(CoreAppAccessTokenFetcherModule.TAG, "Error binding to OVRService: " + result.mBindError);
                    CoreAppAccessTokenFetcherModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, rejectID, "[]");
                    return;
                }
                try {
                    try {
                        accessToken = new JSONObject().put("accessToken", result.mServiceInterface.getAppScopedAccessToken(CoreAppAccessTokenFetcherModule.generateArgsBundle()).getString("access_token")).toString();
                    } catch (JSONException e) {
                        Log.e(CoreAppAccessTokenFetcherModule.TAG, "Unable to populate accessToken for callback.", e);
                    }
                    CoreAppAccessTokenFetcherModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, resolveID, "[" + accessToken + "]");
                } catch (RemoteException e2) {
                    Log.e(CoreAppAccessTokenFetcherModule.TAG, "Error getting AccessToken from OVRService", e2);
                    CoreAppAccessTokenFetcherModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, rejectID, "[]");
                }
            }
        }).start();
    }
}

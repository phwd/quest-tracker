package com.oculus.platform;

import X.AnonymousClass006;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.oculus.messenger.service.MessengerService;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.platform.OVRServiceSynchronous;
import com.oculus.platform.aidl.OVRServiceInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreAppAccessTokenFetcherModule extends RCTBaseJavaModule {
    public static final String CORE_APP_ID = "1880592348624904";
    public static final int MAJOR_VERSION = 1;
    public static final int MINOR_VERSION = 17;
    public static final String MODULE_NAME = "CoreAppAccessTokenFetcher";
    public static final String SERVICE_COMPONENT = "com.oculus.horizon.service.OVRService";
    public static final String SERVICE_PACKAGE = "com.oculus.horizon";
    public static final String TAG = "CoreAppAccessTokenFetcherModule";
    public final Context mContext;

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void shutdownModule() {
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getToken", "ii"));
        return arrayList;
    }

    public static Bundle generateArgsBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("sdk_major_version", 1);
        bundle.putInt("sdk_minor_version", 17);
        bundle.putString(MessengerService.InitParamKeys.APP_ID, "1880592348624904");
        bundle.putBoolean("request_core_app_token", true);
        return bundle;
    }

    public void getToken(final int i, final int i2) {
        new Thread(new Runnable() {
            /* class com.oculus.platform.CoreAppAccessTokenFetcherModule.AnonymousClass1 */

            public void run() {
                String obj;
                OVRServiceSynchronous<T>.ServiceBindResult waitForConnect = new OVRServiceSynchronous(CoreAppAccessTokenFetcherModule.this.mContext, "com.oculus.horizon", CoreAppAccessTokenFetcherModule.SERVICE_COMPONENT, Executors.newSingleThreadExecutor(), new OVRServiceSynchronous.AidlService<OVRServiceInterface>() {
                    /* class com.oculus.platform.CoreAppAccessTokenFetcherModule.AnonymousClass1.AnonymousClass1 */

                    @Override // com.oculus.platform.OVRServiceSynchronous.AidlService
                    public OVRServiceInterface serviceFromBinder(IBinder iBinder) {
                        return OVRServiceInterface.Stub.asInterface(iBinder);
                    }
                }).waitForConnect();
                boolean z = waitForConnect.mBindError;
                if (z) {
                    Log.e(CoreAppAccessTokenFetcherModule.TAG, AnonymousClass006.A0E("Error binding to OVRService: ", z));
                    RCTBaseJavaModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, i2, "[]");
                    return;
                }
                try {
                    try {
                        obj = new JSONObject().put("accessToken", waitForConnect.mServiceInterface.getAppScopedAccessToken(CoreAppAccessTokenFetcherModule.generateArgsBundle()).getString("access_token")).toString();
                    } catch (JSONException e) {
                        Log.e(CoreAppAccessTokenFetcherModule.TAG, "Unable to populate accessToken for callback.", e);
                    }
                    RCTBaseJavaModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, i, AnonymousClass006.A09("[", obj, "]"));
                } catch (RemoteException e2) {
                    Log.e(CoreAppAccessTokenFetcherModule.TAG, "Error getting AccessToken from OVRService", e2);
                    RCTBaseJavaModule.nativeInvokeCallback(CoreAppAccessTokenFetcherModule.this.RVRCtxTag, i2, "[]");
                }
            }
        }).start();
    }

    public CoreAppAccessTokenFetcherModule(Context context) {
        this.mContext = context;
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }
}

package com.oculus.assistant.api;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import com.oculus.assistant.api.OculusAssistantMessageTypes;
import com.oculus.assistant.api.OculusAssistantSDK;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OculusAssistantSDK {
    private static final String TAG = "OculusAssistantSDK";
    private final String mAppId;
    private OculusAssistantConnection mAssistantConnection = new OculusAssistantConnection() {
        /* class com.oculus.assistant.api.OculusAssistantSDK.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantConnected() {
            OculusAssistantSDK.this.registerCallback();
            synchronized (OculusAssistantSDK.this.mQueueLock) {
                Log.d(OculusAssistantSDK.TAG, "Connected.  Running Queued Commands: " + OculusAssistantSDK.this.mQueue.size());
                while (!OculusAssistantSDK.this.mQueue.isEmpty()) {
                    ((Runnable) OculusAssistantSDK.this.mQueue.remove(0)).run();
                }
            }
        }
    };
    private OculusAssistantSubscription mCallbackSubscription = new OculusAssistantSubscription(OculusAssistantMessageTypes.VOICE_SDK_ASSISTANT_COMMAND_CALLBACK, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantSDK.AnonymousClass2 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (OculusAssistantMessageTypes.VOICE_SDK_ASSISTANT_COMMAND_CALLBACK.equals(str)) {
                Log.d(OculusAssistantSDK.TAG, "Callback: " + OculusAssistantSDK.this.mAppId);
                String string = bundle.getString(OculusAssistantMessageTypes.VoiceSdk.APP_ID);
                if (!OculusAssistantSDK.this.mAppId.equals(string)) {
                    Log.d(OculusAssistantSDK.TAG, "Ignoring, different appId: " + string);
                    return;
                }
                synchronized (OculusAssistantSDK.this.mSubscriptionLock) {
                    String string2 = bundle.getString(OculusAssistantMessageTypes.VoiceSdk.ACTION_ID);
                    if (string2 != null && string2.isEmpty()) {
                        Log.e(OculusAssistantSDK.TAG, "Empty actionId");
                    } else if (!OculusAssistantSDK.this.mSubscriptions.containsKey(string2)) {
                        Log.e(OculusAssistantSDK.TAG, "Not listening for: " + string2);
                    } else {
                        ((OculusAssistantSDKCallback) OculusAssistantSDK.this.mSubscriptions.get(string2)).onCallback(string2, bundle.getByteArray(OculusAssistantMessageTypes.VoiceSdk.COMMAND_OUTPUT), bundle.getBundle(OculusAssistantMessageTypes.VoiceSdk.SLOTS), bundle.getString(OculusAssistantMessageTypes.VoiceSdk.JSON_RESPONSE));
                    }
                }
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantSDK.TAG, "Deactivated " + str);
        }
    }, null);
    private final Context mContext;
    private final List<Runnable> mQueue = Collections.synchronizedList(new LinkedList());
    private final Object mQueueLock = new Object();
    private final Object mSubscriptionLock = new Object();
    private final Map<String, OculusAssistantSDKCallback> mSubscriptions = Collections.synchronizedMap(new HashMap());

    public interface OculusAssistantSDKCallback {
        void onCallback(String str, byte[] bArr, Bundle bundle, String str2);
    }

    public OculusAssistantSDK(Context context, String str) {
        this.mAppId = str;
        this.mContext = context;
    }

    private void connect() {
        Log.d(TAG, "Connecting: " + this.mAppId);
        OculusAssistant.bindService(this.mContext, this.mAssistantConnection);
    }

    public void activateApplication() {
        Log.d(TAG, "Activate Application: " + this.mAppId);
        if (!this.mAssistantConnection.isConnectedToAssistant()) {
            connect();
            synchronized (this.mQueueLock) {
                this.mQueue.add(new Runnable() {
                    /* class com.oculus.assistant.api.$$Lambda$Am9BROqB8w9h2BGDsFT1StyLF6s */

                    public final void run() {
                        OculusAssistantSDK.this.activateApplication();
                    }
                });
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.APP_ID, this.mAppId);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.VOICE_SDK_ACTIVATE_ASSISTANT_APPLICATION, bundle);
    }

    public void deactivateApplication() {
        Log.d(TAG, "Deactivate Application: " + this.mAppId);
        if (!this.mAssistantConnection.isConnectedToAssistant()) {
            Log.e(TAG, "Assistant not connected, ignoring deactivate.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.APP_ID, this.mAppId);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.VOICE_SDK_DEACTIVATE_ASSISTANT_APPLICATION, bundle);
    }

    public void registerVoiceCommand(String str, byte[] bArr, OculusAssistantSDKCallback oculusAssistantSDKCallback) {
        Log.d(TAG, "Register Voice Command: " + this.mAppId);
        lambda$registerVoiceCommandInternal$0$OculusAssistantSDK(str, null, bArr, oculusAssistantSDKCallback);
    }

    public void registerDebugVoiceCommand(String str, String str2, byte[] bArr, OculusAssistantSDKCallback oculusAssistantSDKCallback) {
        Log.d(TAG, "Register Debug Voice Command: " + this.mAppId);
        lambda$registerVoiceCommandInternal$0$OculusAssistantSDK(str, str2, bArr, oculusAssistantSDKCallback);
    }

    public void unregisterVoiceCommand(String str) {
        Log.d(TAG, "Unregister Voice Command: " + this.mAppId);
        if (!this.mAssistantConnection.isConnectedToAssistant()) {
            Log.e(TAG, "Assistant not connected, ignoring unregister.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.APP_ID, this.mAppId);
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.ACTION_ID, str);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.VOICE_SDK_UNREGISTER_VOICE_COMMAND, bundle);
    }

    /* access modifiers changed from: private */
    /* renamed from: registerVoiceCommandInternal */
    public void lambda$registerVoiceCommandInternal$0$OculusAssistantSDK(String str, String str2, byte[] bArr, OculusAssistantSDKCallback oculusAssistantSDKCallback) {
        Log.d(TAG, "Register Voice Command: " + this.mAppId);
        if (!this.mAssistantConnection.isConnectedToAssistant()) {
            Log.d(TAG, "Not connected.  Queuing.");
            synchronized (this.mQueueLock) {
                this.mQueue.add(new Runnable(str, str2, bArr, oculusAssistantSDKCallback) {
                    /* class com.oculus.assistant.api.$$Lambda$OculusAssistantSDK$B68eayt59ejRAboEmAc56mA8d4I */
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ byte[] f$3;
                    private final /* synthetic */ OculusAssistantSDK.OculusAssistantSDKCallback f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        OculusAssistantSDK.this.lambda$registerVoiceCommandInternal$0$OculusAssistantSDK(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
            return;
        }
        synchronized (this.mSubscriptionLock) {
            this.mSubscriptions.put(str, oculusAssistantSDKCallback);
        }
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.APP_ID, this.mAppId);
        bundle.putString(OculusAssistantMessageTypes.VoiceSdk.ACTION_ID, str);
        if (str2 != null) {
            bundle.putString(OculusAssistantMessageTypes.VoiceSdk.DEBUG_PHRASE, str2);
        }
        bundle.putByteArray(OculusAssistantMessageTypes.VoiceSdk.COMMAND_INPUT, bArr);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.VOICE_SDK_REGISTER_VOICE_COMMAND, bundle);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void registerCallback() {
        this.mAssistantConnection.subscribe(this.mCallbackSubscription);
    }
}

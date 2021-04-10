package com.oculus.assistant.api;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import com.oculus.assistant.api.OculusAssistantMessageTypes;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class OculusAssistantSpeechSDK {
    private static final String TAG = "OCAssistantSpeakingSDK";
    private final OculusAssistantConnection mAssistantConnection = new OculusAssistantConnection() {
        /* class com.oculus.assistant.api.OculusAssistantSpeechSDK.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantConnected() {
            OculusAssistantSpeechSDK.this.mSafeServiceExecutor.setReady(true);
            OculusAssistantSpeechSDK.this.mIsConnecting.set(false);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantDisconnected() {
            OculusAssistantSpeechSDK.this.mSafeServiceExecutor.setReady(false);
            OculusAssistantSpeechSDK.this.mIsTTSActive.set(false);
        }
    };
    private final Context mContext;
    @Nullable
    private AssistantSpeakingListener mCurrentListener;
    private final AtomicBoolean mIsConnecting = new AtomicBoolean();
    private final AtomicBoolean mIsTTSActive = new AtomicBoolean();
    private final SafeServiceExecutor mSafeServiceExecutor = new SafeServiceExecutor();
    private final OculusAssistantSubscription mSpeakingStateSubscriber = new OculusAssistantSubscription(OculusAssistantMessageTypes.SPEAKING_STATE_CHANGED, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantSpeechSDK.AnonymousClass2 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantSpeechSDK.this.mCurrentListener != null) {
                String string = bundle.getString(OculusAssistantMessageTypes.SpeakingStateProperties.STATE, "");
                char c = 65535;
                int hashCode = string.hashCode();
                if (hashCode != -1352032560) {
                    if (hashCode == 807292011 && string.equals(OculusAssistantMessageTypes.SpeechStates.INACTIVE)) {
                        c = 1;
                    }
                } else if (string.equals(OculusAssistantMessageTypes.SpeechStates.SPEAKING)) {
                    c = 0;
                }
                if (c == 0) {
                    OculusAssistantSpeechSDK.this.mCurrentListener.onStartSpeaking();
                } else if (c == 1) {
                    OculusAssistantSpeechSDK.this.mIsTTSActive.set(false);
                    OculusAssistantSpeechSDK.this.mCurrentListener.onStopSpeaking();
                    OculusAssistantSpeechSDK.this.unregisterCallback();
                }
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantSpeechSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantSpeechSDK.TAG, "Deactivated " + str);
        }
    }, null);

    public OculusAssistantSpeechSDK(Context context) {
        this.mContext = context;
    }

    private void executeOrConnect(Runnable runnable) {
        if (this.mSafeServiceExecutor.isReady()) {
            runnable.run();
            return;
        }
        if (!this.mIsConnecting.getAndSet(true)) {
            OculusAssistant.bindService(this.mContext, this.mAssistantConnection);
        }
        this.mSafeServiceExecutor.safeExecute(runnable);
    }

    public void speak(String str, AssistantSpeakingListener assistantSpeakingListener) {
        this.mIsTTSActive.set(true);
        executeOrConnect(new Runnable(assistantSpeakingListener, str) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantSpeechSDK$nV4q2XJYJnz35m39r_aWOMMpfo */
            private final /* synthetic */ AssistantSpeakingListener f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                OculusAssistantSpeechSDK.this.lambda$speak$0$OculusAssistantSpeechSDK(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$speak$0$OculusAssistantSpeechSDK(AssistantSpeakingListener assistantSpeakingListener, String str) {
        registerCallback();
        this.mCurrentListener = assistantSpeakingListener;
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.START_SPEAKING, bundle);
    }

    public void stopSpeaking() {
        if (this.mIsTTSActive.getAndSet(false)) {
            executeOrConnect(new Runnable() {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantSpeechSDK$XtC7oJhilpHtZ9a4vCkNO1Mu_Uc */

                public final void run() {
                    OculusAssistantSpeechSDK.this.lambda$stopSpeaking$1$OculusAssistantSpeechSDK();
                }
            });
        }
    }

    public /* synthetic */ void lambda$stopSpeaking$1$OculusAssistantSpeechSDK() {
        this.mAssistantConnection.post(OculusAssistantMessageTypes.STOP_SPEAKING, new Bundle());
    }

    public void shutdown() {
        if (this.mSafeServiceExecutor.isReady() || this.mIsConnecting.get()) {
            executeOrConnect(new Runnable() {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantSpeechSDK$Ul0bRt8gRUZMdmc5KqaDMebrkRI */

                public final void run() {
                    OculusAssistantSpeechSDK.this.lambda$shutdown$2$OculusAssistantSpeechSDK();
                }
            });
        }
    }

    public /* synthetic */ void lambda$shutdown$2$OculusAssistantSpeechSDK() {
        this.mSafeServiceExecutor.setReady(false);
        unregisterCallback();
        this.mContext.unbindService(this.mAssistantConnection);
        this.mCurrentListener = null;
    }

    private void registerCallback() {
        this.mAssistantConnection.subscribe(this.mSpeakingStateSubscriber);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterCallback() {
        this.mAssistantConnection.unsubscribe(this.mSpeakingStateSubscriber);
    }
}

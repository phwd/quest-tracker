package com.oculus.assistant.api;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import com.oculus.assistant.api.OculusAssistantMessageTypes;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class OculusAssistantDictationSDK {
    private static final String TAG = "OCAssistantDictationSDK";
    private final OculusAssistantConnection mAssistantConnection = new OculusAssistantConnection() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantConnected() {
            OculusAssistantDictationSDK.this.mIsConnecting.set(false);
            OculusAssistantDictationSDK.this.mSafeServiceExecutor.setReady(true);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantDisconnected() {
            OculusAssistantDictationSDK.this.mSafeServiceExecutor.setReady(false);
            OculusAssistantDictationSDK.this.mIsConnecting.set(false);
            OculusAssistantDictationSDK.this.mIsDictationActive.set(false);
        }
    };
    private DictationConfiguration mConfiguration = new DictationConfiguration();
    private final Context mContext;
    @Nullable
    private DictationListener mCurrentListener;
    private final OculusAssistantSubscription mErrorSubscriber = new OculusAssistantSubscription(OculusAssistantMessageTypes.DICTATION_ERROR, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass6 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantDictationSDK.this.mCurrentListener != null) {
                String string = bundle.getString(OculusAssistantMessageTypes.Dictation.ERROR_TYPE, "UNKNOWN");
                String string2 = bundle.getString(OculusAssistantMessageTypes.Dictation.ERROR_MESSAGE, "");
                OculusAssistantDictationSDK.this.mCurrentListener.onError(bundle.getString("interactionId", ""), string, string2);
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Deactivated " + str);
        }
    }, null);
    private final OculusAssistantSubscription mFinalSubscription = new OculusAssistantSubscription(OculusAssistantMessageTypes.DICTATION_FINAL_RESPONSE, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass3 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantDictationSDK.this.mCurrentListener != null) {
                OculusAssistantDictationSDK.this.mCurrentListener.onFinalTranscription(bundle.getString("interactionId", ""), bundle.getString(OculusAssistantMessageTypes.Dictation.RESPONSE, ""));
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Deactivated " + str);
        }
    }, null);
    private final AtomicBoolean mIsConnecting = new AtomicBoolean();
    private final AtomicBoolean mIsDictationActive = new AtomicBoolean();
    private final OculusAssistantSubscription mMicVolumeChanged = new OculusAssistantSubscription(OculusAssistantMessageTypes.DICTATION_MIC_VOLUME, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass5 */
        int lastVolume = 0;

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantDictationSDK.this.mCurrentListener != null) {
                int i = bundle.getInt(OculusAssistantMessageTypes.Dictation.MIC_VOLUME, this.lastVolume);
                String string = bundle.getString("interactionId", "");
                if (i != this.lastVolume) {
                    OculusAssistantDictationSDK.this.mCurrentListener.onMicAudioLevel(string, i);
                    this.lastVolume = i;
                }
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Deactivated " + str);
        }
    }, null);
    private final OculusAssistantSubscription mPartialSubscription = new OculusAssistantSubscription(OculusAssistantMessageTypes.DICTATION_PARTIAL_RESPONSE, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass2 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantDictationSDK.this.mCurrentListener != null) {
                OculusAssistantDictationSDK.this.mCurrentListener.onPartialTranscription(bundle.getString("interactionId", ""), bundle.getString(OculusAssistantMessageTypes.Dictation.RESPONSE, ""));
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Deactivated " + str);
        }
    }, null);
    private final SafeServiceExecutor mSafeServiceExecutor = new SafeServiceExecutor();
    private final OculusAssistantSubscription mStateChangedSubscribed = new OculusAssistantSubscription(OculusAssistantMessageTypes.DICTATION_STATE_CHANGED, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantDictationSDK.AnonymousClass4 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null && OculusAssistantDictationSDK.this.mCurrentListener != null) {
                String string = bundle.getString("interactionId", "");
                String upperCase = bundle.getString(OculusAssistantMessageTypes.Dictation.STATE, "").toUpperCase();
                char c = 65535;
                int hashCode = upperCase.hashCode();
                if (hashCode != 807292011) {
                    if (hashCode == 1567879323 && upperCase.equals("LISTENING")) {
                        c = 0;
                    }
                } else if (upperCase.equals(OculusAssistantMessageTypes.SpeechStates.INACTIVE)) {
                    c = 1;
                }
                if (c == 0) {
                    OculusAssistantDictationSDK.this.mCurrentListener.onStart(string);
                } else if (c == 1) {
                    OculusAssistantDictationSDK.this.mCurrentListener.onStopped(string);
                    OculusAssistantDictationSDK.this.mCurrentListener = null;
                    OculusAssistantDictationSDK.this.unregisterCallback();
                }
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Activated " + str);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantDictationSDK.TAG, "Deactivated " + str);
        }
    }, null);

    public OculusAssistantDictationSDK(Context context) {
        this.mContext = context;
    }

    private void executeOrConnect(Runnable runnable) {
        if (this.mSafeServiceExecutor.isReady()) {
            runnable.run();
            return;
        }
        if (!this.mIsConnecting.get()) {
            this.mIsConnecting.set(true);
            OculusAssistant.bindService(this.mContext, this.mAssistantConnection);
        }
        this.mSafeServiceExecutor.safeExecute(runnable);
    }

    public void startDictation(DictationListener dictationListener, DictationConfiguration dictationConfiguration) {
        this.mIsDictationActive.set(true);
        executeOrConnect(new Runnable(dictationListener, dictationConfiguration) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantDictationSDK$vgtU6XNE1vg07spc54UtHzeVm8 */
            private final /* synthetic */ DictationListener f$1;
            private final /* synthetic */ DictationConfiguration f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                OculusAssistantDictationSDK.this.lambda$startDictation$0$OculusAssistantDictationSDK(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$startDictation$0$OculusAssistantDictationSDK(DictationListener dictationListener, DictationConfiguration dictationConfiguration) {
        registerCallback();
        this.mCurrentListener = dictationListener;
        this.mConfiguration = dictationConfiguration;
        Bundle bundle = new Bundle();
        bundle.putBundle("configuration", configurationToBundle(this.mConfiguration));
        this.mAssistantConnection.post(OculusAssistantMessageTypes.START_DICTATION, bundle);
    }

    private static Bundle configurationToBundle(DictationConfiguration dictationConfiguration) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(OculusAssistantMessageTypes.DictationConfiguration.MULTIPHRASE, dictationConfiguration.multiPhrase);
        bundle.putString("scenario", dictationConfiguration.scenario);
        bundle.putString("input_type", dictationConfiguration.inputType);
        return bundle;
    }

    public void stopDictation() {
        if (this.mIsDictationActive.getAndSet(false)) {
            executeOrConnect(new Runnable() {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantDictationSDK$p_CtZ8wp7LMfKkfeM_Hd698DWg */

                public final void run() {
                    OculusAssistantDictationSDK.this.lambda$stopDictation$1$OculusAssistantDictationSDK();
                }
            });
        }
    }

    public /* synthetic */ void lambda$stopDictation$1$OculusAssistantDictationSDK() {
        this.mAssistantConnection.post(OculusAssistantMessageTypes.STOP_DICTATION, new Bundle());
    }

    public void shutdown() {
        if (this.mSafeServiceExecutor.isReady() || this.mIsConnecting.get()) {
            executeOrConnect(new Runnable() {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantDictationSDK$JMBIWka5N3H3g5XGXf1sSRjsDsk */

                public final void run() {
                    OculusAssistantDictationSDK.this.lambda$shutdown$2$OculusAssistantDictationSDK();
                }
            });
        }
    }

    public /* synthetic */ void lambda$shutdown$2$OculusAssistantDictationSDK() {
        this.mContext.unbindService(this.mAssistantConnection);
        this.mCurrentListener = null;
    }

    public void submitFeedback(String str, String str2) {
        executeOrConnect(new Runnable(str, str2) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantDictationSDK$yO8WcgmzYc41dXM0FZpKOOmR7B0 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                OculusAssistantDictationSDK.this.lambda$submitFeedback$3$OculusAssistantDictationSDK(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$submitFeedback$3$OculusAssistantDictationSDK(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.SubmitTranscriptionFeedback.FEEDBACK, str);
        bundle.putString("interactionId", str2);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.SUBMIT_TRANSCRIPTION_FEEDBACK, bundle);
    }

    private void registerCallback() {
        this.mAssistantConnection.subscribe(this.mPartialSubscription);
        this.mAssistantConnection.subscribe(this.mFinalSubscription);
        this.mAssistantConnection.subscribe(this.mStateChangedSubscribed);
        this.mAssistantConnection.subscribe(this.mMicVolumeChanged);
        this.mAssistantConnection.subscribe(this.mErrorSubscriber);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterCallback() {
        this.mAssistantConnection.unsubscribe(this.mPartialSubscription);
        this.mAssistantConnection.unsubscribe(this.mFinalSubscription);
        this.mAssistantConnection.unsubscribe(this.mStateChangedSubscribed);
        this.mAssistantConnection.unsubscribe(this.mMicVolumeChanged);
        this.mAssistantConnection.unsubscribe(this.mErrorSubscriber);
    }
}

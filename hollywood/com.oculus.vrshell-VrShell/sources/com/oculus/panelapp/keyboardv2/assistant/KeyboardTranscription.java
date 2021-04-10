package com.oculus.panelapp.keyboardv2.assistant;

import android.os.Handler;
import android.util.Log;
import com.oculus.assistant.api.DictationConfiguration;
import com.oculus.assistant.api.DictationListener;
import com.oculus.assistant.api.OculusAssistantDictationSDK;
import com.oculus.panelapp.keyboardv2.ActionType;
import com.oculus.panelapp.keyboardv2.InputType;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardTranscription;

public class KeyboardTranscription {
    private static final String TAG = "KeyboardTranscription";
    private DictationConfiguration mDictationConfiguration = new DictationConfiguration();
    private final DictationListener mDictationListener = new DictationListener() {
        /* class com.oculus.panelapp.keyboardv2.assistant.KeyboardTranscription.AnonymousClass1 */

        @Override // com.oculus.assistant.api.DictationListener
        public void onError(String str, String str2, String str3) {
        }

        @Override // com.oculus.assistant.api.DictationListener
        public void onStart(String str) {
            KeyboardTranscription.this.mMainLooperHandler.post(new Runnable(str) {
                /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$KeyboardTranscription$1$68GUqP_b9GKSp_FRDcdHctSaVWg */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    KeyboardTranscription.AnonymousClass1.this.lambda$onStart$0$KeyboardTranscription$1(this.f$1);
                }
            });
            KeyboardTranscription.this.mIsActive = true;
        }

        public /* synthetic */ void lambda$onStart$0$KeyboardTranscription$1(String str) {
            KeyboardTranscription.this.mLastInteractionId = str;
            KeyboardTranscription.this.mKeyboardPanelApp.setTranscriptionListening(true);
            KeyboardTranscription.this.mKeyboardPanelApp.updateTranscriptionInteractionId(str);
        }

        @Override // com.oculus.assistant.api.DictationListener
        public void onMicAudioLevel(String str, int i) {
            KeyboardTranscription.this.mMainLooperHandler.post(new Runnable(i) {
                /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$KeyboardTranscription$1$LzW_rm81QhySO4fDkxoMVp30L1Y */
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    KeyboardTranscription.AnonymousClass1.this.lambda$onMicAudioLevel$1$KeyboardTranscription$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onMicAudioLevel$1$KeyboardTranscription$1(int i) {
            KeyboardTranscription.this.mKeyboardPanelApp.updateTranscriptionVolumeLevel(((float) i) / 100.0f);
        }

        @Override // com.oculus.assistant.api.DictationListener
        public void onPartialTranscription(String str, String str2) {
            KeyboardTranscription.this.mDidTranscribe = true;
            KeyboardTranscription.this.mMainLooperHandler.post(new Runnable(str2) {
                /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$KeyboardTranscription$1$Gs6ogCay_eBwAZBDHovWCtj_q0M */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    KeyboardTranscription.AnonymousClass1.this.lambda$onPartialTranscription$2$KeyboardTranscription$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onPartialTranscription$2$KeyboardTranscription$1(String str) {
            KeyboardTranscription.this.mKeyboardPanelApp.updateTranscription(str, true);
        }

        @Override // com.oculus.assistant.api.DictationListener
        public void onFinalTranscription(String str, String str2) {
            KeyboardTranscription.this.mDidTranscribe = true;
            KeyboardTranscription.this.mMainLooperHandler.post(new Runnable(str2) {
                /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$KeyboardTranscription$1$uOQVau3PKIBShk9cySqDm0aQYNo */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    KeyboardTranscription.AnonymousClass1.this.lambda$onFinalTranscription$3$KeyboardTranscription$1(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onFinalTranscription$3$KeyboardTranscription$1(String str) {
            KeyboardTranscription.this.mKeyboardPanelApp.updateTranscription(str, false);
            KeyboardTranscription.this.mKeyboardPanelApp.sendTranscription();
        }

        @Override // com.oculus.assistant.api.DictationListener
        public void onStopped(String str) {
            KeyboardTranscription keyboardTranscription = KeyboardTranscription.this;
            keyboardTranscription.cleanup(keyboardTranscription.mDidTranscribe);
        }
    };
    private boolean mDidTranscribe = false;
    private boolean mIsActive = false;
    private final KeyboardPanelApp mKeyboardPanelApp;
    private String mLastInteractionId;
    private final Handler mMainLooperHandler;
    private OculusAssistantDictationSDK mOculusAssistantDictationSDK;

    public KeyboardTranscription(KeyboardPanelApp keyboardPanelApp) {
        this.mKeyboardPanelApp = keyboardPanelApp;
        this.mMainLooperHandler = new Handler(keyboardPanelApp.getContext().getMainLooper());
        this.mOculusAssistantDictationSDK = new OculusAssistantDictationSDK(this.mKeyboardPanelApp.getContext());
    }

    public void startTranscription(InputType inputType, ActionType actionType, String str) {
        Log.d(TAG, "startTranscription");
        this.mDidTranscribe = false;
        this.mDictationConfiguration.inputType = inputType.name();
        DictationConfiguration dictationConfiguration = this.mDictationConfiguration;
        dictationConfiguration.scenario = str;
        this.mOculusAssistantDictationSDK.startDictation(this.mDictationListener, dictationConfiguration);
        this.mIsActive = true;
    }

    public void stopTranscription() {
        if (this.mIsActive) {
            Log.d(TAG, "stopTranscription");
            this.mOculusAssistantDictationSDK.stopDictation();
            cleanup(this.mDidTranscribe);
        }
    }

    public void submitFeedback(String str) {
        Log.d(TAG, "submitFeedback " + str);
        this.mOculusAssistantDictationSDK.submitFeedback(str, this.mLastInteractionId);
        this.mKeyboardPanelApp.playPressSound();
    }

    public void hoverFeedback() {
        Log.d(TAG, "hoverFeedback");
        this.mKeyboardPanelApp.playHoverSound();
    }

    public synchronized void destroy() {
        if (this.mIsActive) {
            this.mIsActive = false;
            Log.d(TAG, "destroy");
            this.mOculusAssistantDictationSDK.stopDictation();
            this.mMainLooperHandler.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void cleanup(final boolean z) {
        if (this.mIsActive) {
            this.mIsActive = false;
            this.mMainLooperHandler.post(new Runnable() {
                /* class com.oculus.panelapp.keyboardv2.assistant.KeyboardTranscription.AnonymousClass2 */

                public void run() {
                    KeyboardTranscription.this.mKeyboardPanelApp.onTranscriptionStopped(z);
                }
            });
        }
    }

    public boolean isActive() {
        return this.mIsActive;
    }
}

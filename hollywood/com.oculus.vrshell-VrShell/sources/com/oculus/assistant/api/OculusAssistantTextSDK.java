package com.oculus.assistant.api;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import com.oculus.assistant.api.OculusAssistantMessageTypes;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class OculusAssistantTextSDK {
    private static final String TAG = "OCAssistantTextSDK";
    private final OculusAssistantConnection mAssistantConnection = new OculusAssistantConnection() {
        /* class com.oculus.assistant.api.OculusAssistantTextSDK.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantConnected() {
            OculusAssistantTextSDK.this.mSafeServiceExecutor.setReady(true);
            OculusAssistantTextSDK.this.mIsConnecting.set(false);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.assistant.api.OculusAssistantConnection
        public void onAssistantDisconnected() {
            OculusAssistantTextSDK.this.mSafeServiceExecutor.setReady(false);
            OculusAssistantTextSDK.this.mSafeTypeAheadExecutor.setReady(false);
            OculusAssistantTextSDK.this.mIsTypeAheadActive.set(false);
        }
    };
    private TypeAheadConfiguration mConfiguration = new TypeAheadConfiguration();
    private final Context mContext;
    @Nullable
    private TypeAheadListener mCurrentListener;
    private final AtomicBoolean mIsConnecting = new AtomicBoolean();
    private final AtomicBoolean mIsTypeAheadActive = new AtomicBoolean();
    private final SafeServiceExecutor mSafeServiceExecutor = new SafeServiceExecutor();
    private final SafeServiceExecutor mSafeTypeAheadExecutor = new SafeServiceExecutor();
    private final OculusAssistantSubscription mTypeAheadSuggestionSubscriber = new OculusAssistantSubscription(OculusAssistantMessageTypes.ON_TYPEAHEAD_SUGGESTION, new IOculusAssistantSubscriber.Stub() {
        /* class com.oculus.assistant.api.OculusAssistantTextSDK.AnonymousClass2 */

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onMessage(String str, Bundle bundle) throws RemoteException {
            if (bundle != null) {
                OculusAssistantTextSDK.this.mCurrentListener.onSuggestion(bundle.getString(OculusAssistantMessageTypes.OnTypeaheadSuggestion.SUGGESTED_WORD, ""), bundle.getStringArray(OculusAssistantMessageTypes.OnTypeaheadSuggestion.OTHER_SUGGESTIONS_LIST));
            }
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onActivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantTextSDK.TAG, "Activated " + str);
            OculusAssistantTextSDK.this.mSafeTypeAheadExecutor.setReady(true);
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public void onDeactivated(String str, String str2) throws RemoteException {
            Log.d(OculusAssistantTextSDK.TAG, "Deactivated " + str);
            OculusAssistantTextSDK.this.mSafeTypeAheadExecutor.setReady(false);
        }
    }, null);

    public OculusAssistantTextSDK(Context context) {
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

    private void executeWhenReady(Runnable runnable) {
        if (this.mSafeTypeAheadExecutor.isReady()) {
            runnable.run();
        } else {
            this.mSafeTypeAheadExecutor.safeExecute(runnable);
        }
    }

    public void startTypeAhead(TypeAheadListener typeAheadListener, TypeAheadConfiguration typeAheadConfiguration) {
        this.mIsTypeAheadActive.set(true);
        executeOrConnect(new Runnable(typeAheadListener, typeAheadConfiguration) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantTextSDK$BHhPyYrDlStktbmcSDFbTSz44w */
            private final /* synthetic */ TypeAheadListener f$1;
            private final /* synthetic */ TypeAheadConfiguration f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                OculusAssistantTextSDK.this.lambda$startTypeAhead$0$OculusAssistantTextSDK(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$startTypeAhead$0$OculusAssistantTextSDK(TypeAheadListener typeAheadListener, TypeAheadConfiguration typeAheadConfiguration) {
        registerCallback();
        this.mCurrentListener = typeAheadListener;
        if (typeAheadConfiguration != null) {
            this.mConfiguration = typeAheadConfiguration;
        } else {
            this.mConfiguration = new TypeAheadConfiguration();
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("configuration", configurationToBundle(typeAheadConfiguration));
        this.mAssistantConnection.post(OculusAssistantMessageTypes.START_TYPEAHEAD_SESSION, bundle);
    }

    private static Bundle configurationToBundle(TypeAheadConfiguration typeAheadConfiguration) {
        Bundle bundle = new Bundle();
        bundle.putString("scenario", typeAheadConfiguration.scenario);
        bundle.putString("input_type", typeAheadConfiguration.inputType);
        return bundle;
    }

    public void predictWords(String str, String str2) {
        executeWhenReady(new Runnable(str, str2) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantTextSDK$liKhnhMGtLsdn_2pRR3ZGQXD28g */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                OculusAssistantTextSDK.this.lambda$predictWords$1$OculusAssistantTextSDK(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$predictWords$1$OculusAssistantTextSDK(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.GetTypeaheadSuggestion.INPUT_TEXT, str);
        bundle.putString(OculusAssistantMessageTypes.GetTypeaheadSuggestion.INPUT_CONTEXT, str2);
        bundle.putString(OculusAssistantMessageTypes.GetTypeaheadSuggestion.REQUESTING_PANEL, this.mConfiguration.scenario);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.GET_TYPEAHEAD, bundle);
    }

    public void stopTypeAhead(String str) {
        if (this.mIsTypeAheadActive.getAndSet(false)) {
            executeOrConnect(new Runnable(str) {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantTextSDK$i3i0aK7Ah1A4TunfFSEQnUGYdqU */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    OculusAssistantTextSDK.this.lambda$stopTypeAhead$2$OculusAssistantTextSDK(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$stopTypeAhead$2$OculusAssistantTextSDK(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(OculusAssistantMessageTypes.GetTypeaheadSuggestion.INPUT_TEXT, str);
        bundle.putString(OculusAssistantMessageTypes.GetTypeaheadSuggestion.REQUESTING_PANEL, this.mConfiguration.scenario);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.STOP_TYPEAHEAD_SESSION, bundle);
        unregisterCallback();
    }

    public void shutdown() {
        if (this.mSafeServiceExecutor.isReady() || this.mIsConnecting.get()) {
            executeOrConnect(new Runnable() {
                /* class com.oculus.assistant.api.$$Lambda$OculusAssistantTextSDK$DoeeKP9ZgYvsoI_ipVdR3fRJznU */

                public final void run() {
                    OculusAssistantTextSDK.this.lambda$shutdown$3$OculusAssistantTextSDK();
                }
            });
        }
    }

    public /* synthetic */ void lambda$shutdown$3$OculusAssistantTextSDK() {
        this.mSafeServiceExecutor.setReady(false);
        this.mContext.unbindService(this.mAssistantConnection);
        this.mCurrentListener = null;
    }

    public void logEntryUsed(int i) {
        executeOrConnect(new Runnable(i) {
            /* class com.oculus.assistant.api.$$Lambda$OculusAssistantTextSDK$lBrUiUKH9yvpKiFl6GVKlPjyEeM */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                OculusAssistantTextSDK.this.lambda$logEntryUsed$4$OculusAssistantTextSDK(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$logEntryUsed$4$OculusAssistantTextSDK(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(OculusAssistantMessageTypes.OnSuggestionClicked.KEYSTROKES_SAVED, i);
        this.mAssistantConnection.post(OculusAssistantMessageTypes.ON_SUGGESTION_CLICKED, bundle);
    }

    private void registerCallback() {
        this.mAssistantConnection.subscribe(this.mTypeAheadSuggestionSubscriber);
    }

    private void unregisterCallback() {
        this.mAssistantConnection.unsubscribe(this.mTypeAheadSuggestionSubscriber);
    }
}

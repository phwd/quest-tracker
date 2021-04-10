package com.oculus.assistant.service.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.assistant.listeners.Callback;
import com.facebook.assistant.listeners.ListenerManager;
import com.facebook.assistant.listeners.Post;
import com.oculus.assistant.service.api.IOculusAssistantService;
import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService;
import com.oculus.assistant.service.api.attention.OculusAssistantAttentionListener;
import com.oculus.assistant.service.api.control.OculusAssistantControlService;
import com.oculus.assistant.service.api.transcription.OculusAssistantTranscriptionService;

public class AssistantServiceConnection implements ServiceConnection {
    private static final String TAG = "Assistant:ASC";
    private final IOculusAssistantAttentionListener mAttentionListener = new IOculusAssistantAttentionListener.Stub() {
        /* class com.oculus.assistant.service.api.AssistantServiceConnection.AnonymousClass1 */

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState) throws RemoteException {
            if (AnonymousClass2.$SwitchMap$com$oculus$assistant$service$api$attention$AssistantInteractionState[assistantInteractionState.ordinal()] != 1) {
                AssistantServiceConnection.this.mIsActive = true;
            } else {
                AssistantServiceConnection.this.mIsActive = false;
            }
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback() {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$u8ZgtDn2HEcvO9nOx9Zqy2BN3t8 */

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onInteractionStateChanged(AssistantInteractionState.this);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionPropertiesChanged(float f) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback(f) {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$qYHdKKOch9XShJPMbhsPV_DX_oo */
                private final /* synthetic */ float f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onInteractionPropertiesChanged(this.f$0);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onTranscriptionChanged(String str, boolean z) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback(str, z) {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$MThdmEajmJC5JH28Wag9_Ay9OQ0 */
                private final /* synthetic */ String f$0;
                private final /* synthetic */ boolean f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onTranscriptionChanged(this.f$0, this.f$1);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onAssistantResponse(String str) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback(str) {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$RMOP5GeKASIslvGi5bpIjyuhE */
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onAssistantResponse(this.f$0);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionCompleted(boolean z) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback(z) {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$XNa2dKJVFPQodCC9KSdLvgC9mE */
                private final /* synthetic */ boolean f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onInteractionCompleted(this.f$0);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onError(AssistantErrorType assistantErrorType) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback() {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$y0Vq9vlgjM9OxyitN6fO2l44XU */

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onError(AssistantErrorType.this);
                }
            });
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionId(String str) throws RemoteException {
            return AssistantServiceConnection.this.mAttentionListeners.fire(new Callback(str) {
                /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$1$B3Q0qyneol7GMPK6PYm9MZVaZHA */
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.facebook.assistant.listeners.Callback
                public final boolean run(Object obj) {
                    return ((OculusAssistantAttentionListener) obj).onInteractionId(this.f$0);
                }
            });
        }
    };
    private String mAttentionListenerKey;
    final ListenerManager<OculusAssistantAttentionListener> mAttentionListeners = new ListenerManager<>();
    private boolean mIsActive;
    final ListenerManager<AssistantServiceLifecycleListener> mLifecycleListeners = new ListenerManager<>();
    private IOculusAssistantAttentionService mOculusAssistantAttentionService;
    private OculusAssistantControlService mOculusControlService;
    private OculusAssistantTranscriptionService mOculusTranscriptionService;

    /* renamed from: com.oculus.assistant.service.api.AssistantServiceConnection$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$assistant$service$api$attention$AssistantInteractionState = new int[AssistantInteractionState.values().length];

        static {
            try {
                $SwitchMap$com$oculus$assistant$service$api$attention$AssistantInteractionState[AssistantInteractionState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IOculusAssistantService asInterface = IOculusAssistantService.Stub.asInterface(iBinder);
        if (asInterface == null) {
            this.mAttentionListeners.fire($$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY.INSTANCE);
            return;
        }
        try {
            this.mOculusControlService = new OculusAssistantControlService(asInterface.getControlService());
            this.mOculusTranscriptionService = new OculusAssistantTranscriptionService(asInterface.getTranscriptionService());
            this.mOculusAssistantAttentionService = asInterface.getAttentionService();
            this.mAttentionListenerKey = this.mOculusAssistantAttentionService.registerAttentionListener(this.mAttentionListener);
            onControlServiceConnected(this.mOculusControlService);
            onTranscriptionServiceConnected(this.mOculusTranscriptionService);
            onAttentionServiceConnected();
            onServiceReady();
        } catch (RemoteException e) {
            Log.e(TAG, "onSeviceConnected: Failed to get control service", e);
            this.mOculusControlService = null;
            this.mOculusTranscriptionService = null;
            this.mOculusAssistantAttentionService = null;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.mOculusControlService = null;
        onControlServiceDisconnected();
        this.mOculusTranscriptionService = null;
        onTranscriptionServiceDisconnected();
        this.mOculusAssistantAttentionService = null;
        onAttentionServiceDisconnected();
        onServiceDisconnected();
    }

    public OculusAssistantControlService getControlService() {
        return this.mOculusControlService;
    }

    public OculusAssistantTranscriptionService getTranscriptionService() {
        return this.mOculusTranscriptionService;
    }

    public void registerListener(OculusAssistantAttentionListener oculusAssistantAttentionListener) {
        this.mAttentionListeners.registerListener(oculusAssistantAttentionListener);
    }

    public void unregisterListener(OculusAssistantAttentionListener oculusAssistantAttentionListener) {
        this.mAttentionListeners.unregisterListener(oculusAssistantAttentionListener);
    }

    public void registerLifecycleListener(AssistantServiceLifecycleListener assistantServiceLifecycleListener) {
        this.mLifecycleListeners.registerListener(assistantServiceLifecycleListener);
    }

    public void unregisterLifecycleListener(AssistantServiceLifecycleListener assistantServiceLifecycleListener) {
        this.mLifecycleListeners.unregisterListener(assistantServiceLifecycleListener);
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public boolean isServiceConnected() {
        return this.mOculusControlService != null;
    }

    /* access modifiers changed from: protected */
    public void onAttentionServiceConnected() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onTranscriptionServiceConnected(OculusAssistantTranscriptionService oculusAssistantTranscriptionService) {
        this.mLifecycleListeners.post(new Post() {
            /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$hnkxb5oeRn60Bo1pqvkNu_8oC50 */

            @Override // com.facebook.assistant.listeners.Post
            public final void run(Object obj) {
                ((AssistantServiceLifecycleListener) obj).onTranscriptionServiceConnected(OculusAssistantTranscriptionService.this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onControlServiceConnected(OculusAssistantControlService oculusAssistantControlService) {
        this.mLifecycleListeners.post(new Post() {
            /* class com.oculus.assistant.service.api.$$Lambda$AssistantServiceConnection$nh0eSbp1PEb0F59_DjPcU5_6D5g */

            @Override // com.facebook.assistant.listeners.Post
            public final void run(Object obj) {
                ((AssistantServiceLifecycleListener) obj).onControlServiceConnected(OculusAssistantControlService.this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttentionServiceDisconnected() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onTranscriptionServiceDisconnected() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onControlServiceDisconnected() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onServiceReady() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onServiceDisconnected() {
        this.mLifecycleListeners.post($$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ.INSTANCE);
    }
}

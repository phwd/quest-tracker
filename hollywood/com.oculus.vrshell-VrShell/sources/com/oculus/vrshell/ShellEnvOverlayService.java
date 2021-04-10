package com.oculus.vrshell;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.oculus.common.httpclient.HttpClient;
import com.oculus.common.httpclient.VrShellUserAgentInterceptor;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.config.BootConfig;
import com.oculus.vrshell.util.BluetoothInputHelper;

public class ShellEnvOverlayService extends BaseOverlayService implements ShellApplication.IVrClient, BluetoothInputHelper.NativeInputSink {
    public static final String ENTERPRISE_CONFIG_CHANGED = "com.oculus.alpenglow.config.CONFIG_CHANGED";
    public static final String EXTRA_INSTANCE_SESSION_ID = "extra_instance_session_id";
    private static final int INPUT_SOURCE_REMOTE = 1698898178;
    private static final String INTENT_ASSISTANT_COMMAND = "com.oculus.assistant.COMMAND";
    private static final String INTENT_ASSISTANT_LAUNCH = "com.oculus.assistant.LAUNCH";
    private static final String INTENT_HANDS = "com.oculus.vrshell.intent.action.HANDS";
    public static final String INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT = "com.oculus.vrshell.SHELL_ENV_BROADCAST_INTENT";
    public static final String INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT_ACTION = "shellenv.original_action";
    private static final String INTENT_SHELL_ENVIRONMENT_NEW_INTENT = "com.oculus.vrshell.SHELL_ENV_NEW_INTENT";
    private static final String INTENT_SHELL_ENVIRONMENT_START_SERVICE = "com.oculus.vrshell.SHELL_ENV_START_SERVICE";
    private static final String INTENT_TOAST_DISMISS = "com.oculus.vrshell.intent.action.DISMISS_NOTIFICATION";
    private static final String INTENT_TOAST_NOTIFICATION = "com.oculus.vrshell.intent.action.TOAST_NOTIFICATION";
    private static final String KEY_SHELL_ENVIRONMENT_IPC_WINDOW_TOKEN = "ShellEnvIPCWindowToken";
    public static final String LOCAL_STREAM_PRIVACY_CHECK = "com.oculus.systemactivities.LOCAL_STREAM_PRIVACY_CHECK";
    public static final String LOCAL_STREAM_STATE_UPDATE = "com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE";
    private static final int MSG_ON_PAUSE = 3;
    private static final int MSG_ON_RESUME = 2;
    private static final int MSG_SETUP = 1;
    private static final String TAG = LoggingUtil.tag(ShellEnvOverlayService.class);
    private BluetoothInputHelper mBluetoothInputHelper;
    private HttpClient mHttpClient;
    private boolean mIsResumed = false;
    private long mNativeAppPtr = 0;
    private Messenger mShellEnvMessenger;
    private String mShellEnvSessionId;
    private IBinder mWindowToken;

    private static native long nativeInit(Context context, boolean z, String[] strArr, String str, int i, boolean z2, String str2, String str3, String str4, String str5);

    private static native void nativeJoypadAxis(long j, float f, float f2, float f3, float f4);

    private static native void nativeKeyEvent(long j, int i, int i2, int i3, boolean z, boolean z2);

    private static native void nativeNewIntent(long j, String[] strArr);

    private static native void nativeOnDestroy(long j);

    private static native void nativeOnPause(long j);

    /* access modifiers changed from: private */
    public static native void nativeOnResume(long j);

    /* access modifiers changed from: private */
    public static native void nativeOnShellEnvConnect(long j);

    private static native void nativeTouch(long j, int i, float f, float f2, int i2, float f3, float f4, String str, int i3);

    private static native void nativeTrustedAppLaunch(long j, String[] strArr);

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyAppScopedToken(String str) {
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyUserStatus(boolean z, boolean z2, String str, String str2) {
    }

    private class ShellEnvIncomingHandler extends Handler {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private ShellEnvIncomingHandler() {
        }

        public void handleMessage(Message message) {
            if (ShellEnvOverlayService.this.mNativeAppPtr == 0) {
                Log.e(ShellEnvOverlayService.TAG, String.format("Handling message with a null native app pointer (msg: %d) ", Integer.valueOf(message.what)));
                super.handleMessage(message);
                return;
            }
            int i = message.what;
            if (i == 1) {
                ShellEnvOverlayService.this.mWindowToken = message.getData().getBinder(ShellEnvOverlayService.KEY_SHELL_ENVIRONMENT_IPC_WINDOW_TOKEN);
                ShellEnvOverlayService.nativeOnShellEnvConnect(ShellEnvOverlayService.this.mNativeAppPtr);
            } else if (i == 2) {
                Log.d(ShellEnvOverlayService.TAG, "Message Handler: onResume");
                if (!ShellEnvOverlayService.this.mIsResumed) {
                    ShellEnvOverlayService.nativeOnResume(ShellEnvOverlayService.this.mNativeAppPtr);
                    ShellEnvOverlayService.this.mIsResumed = true;
                    ShellEnvOverlayService.this.refreshFromExternalStates();
                }
            } else if (i != 3) {
                String str = ShellEnvOverlayService.TAG;
                Log.e(str, "Unexpected message: " + message.what);
                super.handleMessage(message);
            } else {
                Log.d(ShellEnvOverlayService.TAG, "Message Handler: onPause");
                ShellEnvOverlayService.this.maybeCallNativePause();
            }
        }
    }

    @Override // com.oculus.vrshell.BaseOverlayService
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        ShellApplication shellApplication = (ShellApplication) getApplication();
        shellApplication.setActivityContext(null);
        BootConfig.BootConfigResult bootConfig = shellApplication.getBootConfig();
        this.mHttpClient = HttpClient.getInstance(new VrShellUserAgentInterceptor(bootConfig.versionName, String.valueOf(bootConfig.versionCode), "com.oculus.vrshell", getResources().getConfiguration().getLocales().get(0).toString()), HttpClient.getOkHttpCacheDir(getApplicationInfo().dataDir, "ShellEnvOverlay"));
        this.mBluetoothInputHelper = new BluetoothInputHelper(this);
        shellApplication.notifyOsSetPanelState(true, "com.oculus.vrshell", "com.oculus.vrshell.ShellEnvOverlayService");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str = TAG;
        Log.d(str, "onStartCommand() with action " + intent.getAction());
        handleCommandIntent(intent);
        return 2;
    }

    private void handleCommandIntent(Intent intent) {
        if (intent.hasExtra(NavigationRouter.INTENT_KEY_FORWARDED_INTENT_ORIGINAL_ACTION)) {
            Intent intent2 = new Intent(intent);
            intent2.setAction(intent.getStringExtra(NavigationRouter.INTENT_KEY_FORWARDED_INTENT_ORIGINAL_ACTION));
            if (((ShellApplication) getApplication()).isTrustedAppLaunch(intent2)) {
                long j = this.mNativeAppPtr;
                if (j == 0) {
                    initNative(intent2, true);
                    return;
                } else {
                    nativeTrustedAppLaunch(j, IntentParser.ToEnvironment(intent2));
                    return;
                }
            }
        }
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -560480690) {
            if (hashCode != -290799155) {
                if (hashCode == 1431933605 && action.equals(INTENT_SHELL_ENVIRONMENT_START_SERVICE)) {
                    c = 0;
                }
            } else if (action.equals(INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT)) {
                c = 2;
            }
        } else if (action.equals(INTENT_SHELL_ENVIRONMENT_NEW_INTENT)) {
            c = 1;
        }
        if (c == 0) {
            Intent intent3 = new Intent(intent);
            long j2 = this.mNativeAppPtr;
            if (j2 == 0) {
                initNative(intent3, false);
            } else {
                nativeNewIntent(j2, IntentParser.ToEnvironment(intent3));
            }
        } else if (c == 1) {
            long j3 = this.mNativeAppPtr;
            if (j3 == 0) {
                Log.w(TAG, "A new intent was received by the Service, but ShellEnv hasn't connected to the service yet.");
            } else {
                nativeNewIntent(j3, IntentParser.ToEnvironment(intent));
            }
        } else if (c == 2) {
            if (this.mNativeAppPtr == 0) {
                Log.w(TAG, "A broadcast intent was received by the Service, but ShellEnv hasn't connected to the service yet.");
                return;
            }
            intent.setAction(intent.getStringExtra(INTENT_SHELL_ENVIRONMENT_BROADCAST_INTENT_ACTION));
            if (shouldForwardBroadcastIntent(intent)) {
                forwardToMinOverlay(intent);
            } else {
                ShellApplication.nativeBroadcastIntent(this.mNativeAppPtr, IntentParser.ToEnvironment(intent));
            }
        }
    }

    private boolean shouldForwardBroadcastIntent(Intent intent) {
        if (!this.mIsResumed && !LOCAL_STREAM_STATE_UPDATE.equals(intent.getAction()) && !LOCAL_STREAM_PRIVACY_CHECK.equals(intent.getAction()) && !ENTERPRISE_CONFIG_CHANGED.equals(intent.getAction())) {
            return true;
        }
        return false;
    }

    private void forwardToMinOverlay(Intent intent) {
        getShellApplication().getNavigationRouter().startOverlayIntent(this, intent);
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        if (!INTENT_SHELL_ENVIRONMENT_START_SERVICE.equals(intent.getAction())) {
            return null;
        }
        if (!isSessionIdValid(intent, false)) {
            shutdownProcess();
        }
        if (this.mNativeAppPtr == 0) {
            handleCommandIntent(intent);
        }
        this.mShellEnvMessenger = new Messenger(new ShellEnvIncomingHandler());
        return this.mShellEnvMessenger.getBinder();
    }

    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind");
        if (INTENT_SHELL_ENVIRONMENT_START_SERVICE.equals(intent.getAction()) && !isSessionIdValid(intent, true)) {
            shutdownProcess();
        }
    }

    private boolean isSessionIdValid(Intent intent, boolean z) {
        String str;
        if (intent != null) {
            if (intent.getData() != null) {
                str = intent.getData().getQueryParameter(EXTRA_INSTANCE_SESSION_ID);
            } else if (!z) {
                str = intent.getStringExtra(EXTRA_INSTANCE_SESSION_ID);
            }
            if (str == null && this.mShellEnvSessionId == null) {
                this.mShellEnvSessionId = str;
                return true;
            } else if (str == null && str.equals(this.mShellEnvSessionId)) {
                return true;
            }
        } else {
            Log.wtf(TAG, "Service bind called with a null intent - this should never happen");
        }
        str = null;
        if (str == null) {
        }
        return str == null ? false : false;
    }

    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        maybeCallNativePause();
        return true;
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        HttpClient httpClient = this.mHttpClient;
        if (httpClient != null) {
            httpClient.release();
            this.mHttpClient = null;
        }
        destroyNative();
        super.onDestroy();
    }

    private void initNative(Intent intent, boolean z) {
        ShellApplication shellApplication = (ShellApplication) getApplication();
        BootConfig.BootConfigResult bootConfig = shellApplication.getBootConfig();
        this.mNativeAppPtr = nativeInit(shellApplication, z, IntentParser.ToEnvironment(intent), bootConfig.versionName, bootConfig.versionCode, bootConfig.isAutomationEnabled, bootConfig.bootConfiguration, getPackageName(), ShellApplication.sAnalyticsSessionId.toString(), MainActivity.getInternalStorageCacheDir(this));
        if (this.mIsResumed) {
            nativeOnResume(this.mNativeAppPtr);
        }
        shellApplication.setVrClient(this);
        shellApplication.startVrNotificationService();
        if (shellApplication.getBootConfig().isShellEnvEnabled) {
            shellApplication.registerCaptivePortalHandler();
        }
        refreshFromExternalStates();
        updateExperimentValues();
    }

    private void destroyNative() {
        maybeCallNativePause();
        long j = this.mNativeAppPtr;
        if (j != 0) {
            nativeOnDestroy(j);
            this.mNativeAppPtr = 0;
        }
        this.mShellEnvSessionId = null;
    }

    private void shutdownProcess() {
        System.exit(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeCallNativePause() {
        if (this.mIsResumed) {
            long j = this.mNativeAppPtr;
            if (j != 0) {
                nativeOnPause(j);
                this.mIsResumed = false;
            }
        }
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public long getNativeAppPtr() {
        return this.mNativeAppPtr;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public IBinder getWindowToken() {
        return this.mWindowToken;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public HttpClient getHttpClient() {
        return this.mHttpClient;
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.vrshell.BaseOverlayService
    public FrameLayout createEventInterceptLayout() {
        return new FrameLayout(this) {
            /* class com.oculus.vrshell.ShellEnvOverlayService.AnonymousClass1 */

            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() != 125) {
                    return ShellEnvOverlayService.this.mBluetoothInputHelper.dispatchKeyEvent(keyEvent);
                }
                if (keyEvent.getSource() == ShellEnvOverlayService.INPUT_SOURCE_REMOTE || keyEvent.getAction() == 1) {
                }
                return true;
            }

            public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
                return ShellEnvOverlayService.this.mBluetoothInputHelper.dispatchGenericMotionEvent(motionEvent);
            }

            public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                return ShellEnvOverlayService.this.mBluetoothInputHelper.dispatchTouchEvent(motionEvent);
            }
        };
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardKeyEvent(int i, int i2, int i3, boolean z, boolean z2) {
        long j = this.mNativeAppPtr;
        if (j != 0) {
            nativeKeyEvent(j, i, i2, i3, z, z2);
        }
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardTouchEvent(int i, float f, float f2, int i2, float f3, float f4, String str, int i3) {
        long j = this.mNativeAppPtr;
        if (j != 0) {
            nativeTouch(j, i, f, f2, i2, f3, f4, str, i3);
        }
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardJoypadAxisEvent(float f, float f2, float f3, float f4) {
        long j = this.mNativeAppPtr;
        if (j != 0) {
            nativeJoypadAxis(j, f, f2, f3, f4);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void refreshFromExternalStates() {
        Application application = getApplication();
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("refreshFromExternalStates application non null:");
        sb.append(application != null);
        Log.d(str, sb.toString());
        if (application != null) {
            ((ShellApplication) application).refreshFromExternalStates();
        }
    }
}

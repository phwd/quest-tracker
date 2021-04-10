package com.oculus.vrshell;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.oculus.common.httpclient.HttpClient;
import com.oculus.common.httpclient.VrShellUserAgentInterceptor;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.vrshell.IShellOverlayService;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.config.BootConfig;
import com.oculus.vrshell.memorypressure.MemoryPressure;
import com.oculus.vrshell.memorypressure.MemoryPressureCallback;
import com.oculus.vrshell.memorypressure.MemoryPressureObserver;
import com.oculus.vrshell.util.BluetoothInputHelper;
import com.oculus.vrshell.util.OverlayCommand;
import java.util.HashMap;

public class ShellOverlayService extends BaseOverlayService implements ShellApplication.IVrClient, BluetoothInputHelper.NativeInputSink {
    private static final String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    private static final int INPUT_SOURCE_REMOTE = 1698898178;
    private static final String INTENT_PRIMARY_PACKAGE_CHANGED = "com.oculus.vrshell.intent.action.PRIMARY_PACKAGE_CHANGED";
    private static final String NOTIFICATION_CHANNEL_ID = "com.oculus.shell.overlay";
    private static final String NOTIFICATION_CHANNEL_NAME = "OverlayNotifications";
    private static final String NOTIFICATION_TYPE = "oculus_mobile_shell_overlay_foreground";
    private static final String TAG = LoggingUtil.tag(ShellOverlayService.class);
    private long mAppPtr = 0;
    private BluetoothInputHelper mBluetoothInputHelper;
    private HttpClient mHttpClient;
    private MemoryPressureObserver mMemoryPressureObserver;
    private Intent mStartIntent = null;
    private BroadcastReceiver mStateReceiver;

    private static native long nativeInit(Service service, Context context, String str, String str2, String str3, String str4, String str5, boolean z, String str6);

    private static native void nativeJoypadAxis(long j, float f, float f2, float f3, float f4);

    private static native void nativeKeyEvent(long j, int i, int i2, int i3, boolean z, boolean z2);

    private static native void nativeSendOverlayCommand(long j, String[] strArr);

    private static native void nativeThreadJoin(long j);

    private static native void nativeTouch(long j, int i, float f, float f2, int i2, float f3, float f4, String str, int i3);

    private static native void nativeWakeNative(long j);

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public IBinder getWindowToken() {
        return null;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyAppScopedToken(String str) {
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyUserStatus(boolean z, boolean z2, String str, String str2) {
    }

    @Override // com.oculus.vrshell.BaseOverlayService
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        ShellApplication shellApplication = getShellApplication();
        if (shellApplication == null) {
            Log.e(TAG, "onCreate: shellApplication null. Aborting");
            return;
        }
        shellApplication.setActivityContext(null);
        BootConfig.BootConfigResult bootConfig = shellApplication.getBootConfig();
        shellApplication.getNavigationRouter().startNavigationContextServer(shellApplication);
        this.mHttpClient = HttpClient.getInstance(new VrShellUserAgentInterceptor(bootConfig.versionName, String.valueOf(bootConfig.versionCode), "com.oculus.vrshell", getResources().getConfiguration().getLocales().get(0).toString()), HttpClient.getOkHttpCacheDir(getApplicationInfo().dataDir, "ShellOverlay"));
        this.mBluetoothInputHelper = new BluetoothInputHelper(this);
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this).setPriority(-1).setSmallIcon(R.drawable.ic_system_dialog_oculus_icon).setOngoing(true);
        if (Build.VERSION.SDK_INT >= 26) {
            ongoing.setChannelId(ensureNotificationChannel());
        }
        Bundle bundle = new Bundle();
        bundle.putString(NotificationConstants.EXTRA_TYPE, NOTIFICATION_TYPE);
        ongoing.setExtras(bundle);
        startForeground(3, ongoing.build());
        ensureNativeApp();
        shellApplication.setVrClient(this);
        this.mStateReceiver = new BroadcastReceiver() {
            /* class com.oculus.vrshell.ShellOverlayService.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                Log.d(ShellOverlayService.TAG, "screenStateReceiver Received broadcast: " + intent.getAction());
                String action = intent.getAction();
                if (action == null) {
                    Log.d(ShellOverlayService.TAG, "screenStateReceiver Received broadcast with null action");
                    return;
                }
                char c = 65535;
                switch (action.hashCode()) {
                    case -2128145023:
                        if (action.equals("android.intent.action.SCREEN_OFF")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1542846910:
                        if (action.equals(ShellOverlayService.ACTION_MOUNT_STATE_CHANGED)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1454123155:
                        if (action.equals("android.intent.action.SCREEN_ON")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 823795052:
                        if (action.equals("android.intent.action.USER_PRESENT")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 990628838:
                        if (action.equals(ShellOverlayService.INTENT_PRIMARY_PACKAGE_CHANGED)) {
                            c = 4;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    ShellOverlayService.this.onScreenStateChanged(false);
                } else if (c == 1) {
                    ShellOverlayService.this.onScreenStateChanged(true);
                } else if (c == 2) {
                    ShellOverlayService.this.onMountStateChanged(intent.getExtras().getBoolean("state"));
                } else if (c == 3) {
                    ShellOverlayService.this.onUserPresent();
                } else if (c == 4) {
                    ShellOverlayService.this.onPrimaryPackageChanged();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(ACTION_MOUNT_STATE_CHANGED);
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction(INTENT_PRIMARY_PACKAGE_CHANGED);
        registerReceiver(this.mStateReceiver, intentFilter);
        updateExperimentValues();
        this.mMemoryPressureObserver = new MemoryPressureObserver(new MemoryPressureCallback() {
            /* class com.oculus.vrshell.ShellOverlayService.AnonymousClass2 */

            @Override // com.oculus.vrshell.memorypressure.MemoryPressureCallback
            public void OnMemoryPressureStateChanged(MemoryPressure memoryPressure) {
                String str = ShellOverlayService.TAG;
                Log.d(str, "OnMemoryPressureStateChanged: memoryPressure = " + memoryPressure);
                ShellOverlayService.this.sendOverlayCommand("onMemoryPressureChange", "level", memoryPressure.stringVal());
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPrimaryPackageChanged() {
        if (getShellApplication() == null) {
            Log.d(TAG, "onPrimaryPackageChanged - shellApplication null");
        } else {
            sendOverlayCommand("primaryPackageChanged", new String[0]);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onScreenStateChanged(boolean z) {
        String str = TAG;
        Log.d(str, "onScreenStateChanged: " + z);
        String[] strArr = new String[2];
        strArr[0] = "state";
        strArr[1] = z ? "1" : "0";
        sendOverlayCommand("screenStateChanged", strArr);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onMountStateChanged(boolean z) {
        String str = TAG;
        Log.d(str, "onMountStateChanged: " + z);
        String[] strArr = new String[2];
        strArr[0] = "state";
        strArr[1] = z ? "1" : "0";
        sendOverlayCommand("mountStateChanged", strArr);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onUserPresent() {
        Log.d(TAG, "onUserPresent");
        if (!isDeviceLocked()) {
            sendOverlayCommand("deviceUnlocked", new String[0]);
        }
    }

    @RequiresApi(26)
    private String ensureNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, 3);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notificationChannel.getId();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str = TAG;
        Log.i(str, "onStartCommand(): " + intent);
        if (intent != null) {
            this.mStartIntent = intent;
        }
        if (this.mStartIntent != null) {
            ensureNativeApp();
            ShellApplication.nativeBroadcastIntent(this.mAppPtr, IntentParser.ToEnvironment(this.mStartIntent, new HashMap()));
            wakeNative();
            return 1;
        }
        Log.e(TAG, "onStartCommand called without an intent");
        return 1;
    }

    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        sendOverlayCommand("onDestroy", new String[0]);
        ShellApplication shellApplication = (ShellApplication) getApplication();
        if (shellApplication != null) {
            shellApplication.setVrClient(null);
        }
        long j = this.mAppPtr;
        if (j != 0) {
            nativeThreadJoin(j);
        }
        HttpClient httpClient = this.mHttpClient;
        if (httpClient != null) {
            httpClient.release();
            this.mHttpClient = null;
        }
        unregisterReceiver(this.mStateReceiver);
        super.onDestroy();
        this.mAppPtr = 0;
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return new IShellOverlayService.Stub() {
            /* class com.oculus.vrshell.ShellOverlayService.AnonymousClass3 */
        };
    }

    public void onTrimMemory(int i) {
        this.mMemoryPressureObserver.onNativeTrimMemory(i);
        super.onTrimMemory(i);
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public long getNativeAppPtr() {
        return this.mAppPtr;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public HttpClient getHttpClient() {
        return this.mHttpClient;
    }

    private void ensureNativeApp() {
        if (this.mAppPtr == 0) {
            Log.i(TAG, "EnsureNativeApp");
            BootConfig.BootConfigResult bootConfig = ((ShellApplication) getApplication()).getBootConfig();
            this.mAppPtr = nativeInit(this, getApplication(), getPackageName(), ShellApplication.sAnalyticsSessionId.toString(), bootConfig.versionName, String.valueOf(bootConfig.versionCode), MainActivity.getInternalStorageCacheDir(this), bootConfig.isAutomationEnabled, bootConfig.bootConfiguration);
        }
    }

    /* access modifiers changed from: package-private */
    public void sendOverlayCommand(String str, String... strArr) {
        OverlayCommand overlayCommand = new OverlayCommand(str, strArr);
        long j = this.mAppPtr;
        if (j != 0) {
            nativeSendOverlayCommand(j, overlayCommand.toStringArray());
        }
        wakeNative();
    }

    /* access modifiers changed from: package-private */
    public void wakeNative() {
        long j = this.mAppPtr;
        if (j != 0) {
            nativeWakeNative(j);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.vrshell.BaseOverlayService
    public FrameLayout createEventInterceptLayout() {
        return new FrameLayout(this) {
            /* class com.oculus.vrshell.ShellOverlayService.AnonymousClass4 */

            public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
                return ShellOverlayService.this.mBluetoothInputHelper.dispatchGenericMotionEvent(motionEvent);
            }

            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 125) {
                    if (keyEvent.getSource() == ShellOverlayService.INPUT_SOURCE_REMOTE) {
                        return true;
                    }
                    if (keyEvent.getAction() == 1) {
                        ShellOverlayService.this.sendOverlayCommand("invokeHomeButtonEvent", new String[0]);
                    }
                }
                return ShellOverlayService.this.mBluetoothInputHelper.dispatchKeyEvent(keyEvent);
            }

            public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                return ShellOverlayService.this.mBluetoothInputHelper.dispatchTouchEvent(motionEvent);
            }
        };
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardKeyEvent(int i, int i2, int i3, boolean z, boolean z2) {
        nativeKeyEvent(getNativeAppPtr(), i, i2, i3, z, z2);
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardTouchEvent(int i, float f, float f2, int i2, float f3, float f4, String str, int i3) {
        nativeTouch(getNativeAppPtr(), i, f, f2, i2, f3, f4, str, i3);
    }

    @Override // com.oculus.vrshell.util.BluetoothInputHelper.NativeInputSink
    public void forwardJoypadAxisEvent(float f, float f2, float f3, float f4) {
        nativeJoypadAxis(getNativeAppPtr(), f, f2, f3, f4);
    }
}

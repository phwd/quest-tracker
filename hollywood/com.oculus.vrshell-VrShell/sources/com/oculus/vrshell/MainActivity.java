package com.oculus.vrshell;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.oculus.android.exoplayer2.C;
import com.oculus.common.httpclient.HttpClient;
import com.oculus.common.httpclient.VrShellUserAgentInterceptor;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.config.BootConfig;
import com.oculus.vrshell.util.BluetoothInputHelper;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public final class MainActivity extends Activity implements ShellApplication.IVrClient, SurfaceHolder.Callback, BluetoothInputHelper.NativeInputSink {
    private static final String TAG = LoggingUtil.tag(MainActivity.class);
    private long mActivityPauseTime = 0;
    private long mActivityStartTime = 0;
    private IBinder mActivityToken;
    private BluetoothInputHelper mBluetoothInputHelper;
    private HttpClient mHttpClient;
    private Timer mIdleShutdownTimer;
    private LowStorageNotificationScheduler mLowStorageNotificationScheduler;
    private long mNativeAppPtr = 0;
    private long mRequestedIdleShutdownMillis;
    private SurfaceHolder mSurfaceHolder;
    private UnifiedTelemetryLogger mUnifiedTelemetryLogger = null;
    private UserSwitchReceiver mUserSwitchReceiver;

    private static native long nativeInit(Activity activity, Context context, boolean z, String[] strArr, String str, int i, boolean z2, String str2, String str3, String str4, String str5);

    private static native void nativeJoypadAxis(long j, float f, float f2, float f3, float f4);

    private static native void nativeKeyEvent(long j, int i, int i2, int i3, boolean z, boolean z2);

    private static native void nativeNewIntent(long j, String[] strArr);

    private static native void nativeOnDestroy(long j);

    private static native void nativeOnPause(long j);

    private static native void nativeOnResume(long j);

    private static native void nativeSurfaceChanged(long j, Surface surface);

    private static native void nativeSurfaceCreated(long j, Surface surface);

    private static native void nativeSurfaceDestroyed(long j);

    private static native void nativeTouch(long j, int i, float f, float f2, int i2, float f3, float f4, String str, int i3);

    private static native void nativeTrustedAppLaunch(long j, String[] strArr);

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyAppScopedToken(String str) {
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public void notifyUserStatus(boolean z, boolean z2, String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public synchronized void onCreate(Bundle bundle) {
        String str = TAG;
        Log.d(str, this + " onCreate()");
        super.onCreate(bundle);
        ShellApplication shellApplication = (ShellApplication) getApplication();
        if (shellApplication.getNavigationRouter().maybeSwitchToShellEnv(getApplicationContext(), getPackageManager(), getIntent(), shouldForceFullOverlay(getIntent()))) {
            finish();
            Log.d(TAG, "Switching to ShellEnv and exiting onCreate()");
            return;
        }
        BootConfig.BootConfigResult bootConfig = shellApplication.getBootConfig();
        this.mHttpClient = HttpClient.getInstance(new VrShellUserAgentInterceptor(bootConfig.versionName, String.valueOf(bootConfig.versionCode), "com.oculus.vrshell", getResources().getConfiguration().getLocales().get(0).toString()), HttpClient.getOkHttpCacheDir(getApplicationInfo().dataDir, "VrShell"));
        this.mBluetoothInputHelper = new BluetoothInputHelper(this);
        this.mUnifiedTelemetryLogger = shellApplication.getUnifiedTelemetryLogger();
        this.mNativeAppPtr = nativeInit(this, shellApplication, shellApplication.isTrustedAppLaunch(getIntent()), IntentParser.ToEnvironment(getIntent()), bootConfig.versionName, bootConfig.versionCode, bootConfig.isAutomationEnabled, bootConfig.bootConfiguration, getPackageName(), ShellApplication.sAnalyticsSessionId.toString(), getInternalStorageCacheDir(this));
        shellApplication.setVrClient(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        refreshFromExternalStates();
        shellApplication.startVrNotificationService();
        this.mLowStorageNotificationScheduler = new LowStorageNotificationScheduler();
        this.mLowStorageNotificationScheduler.scheduleStorageCheck(5000, getApplicationContext(), this.mUnifiedTelemetryLogger);
        shellApplication.registerCaptivePortalHandler();
        attributes.rotationAnimation = 2;
        getWindow().setAttributes(attributes);
        setTheme(R.style.NoAnimAndTitleBar);
        shellApplication.setActivityContext(this);
        SurfaceView surfaceView = shellApplication.getSurfaceView();
        setContentView(surfaceView);
        surfaceView.getHolder().addCallback(this);
        try {
            this.mActivityToken = (IBinder) Activity.class.getDeclaredMethod("getActivityToken", new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable th) {
            Log.e(TAG, "failed to get activity token ", th);
            this.mActivityToken = null;
        }
        registerUserSwitchReceiver();
        Log.d(TAG, "Exiting onCreate()");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        if (((ShellApplication) getApplication()).getNavigationRouter().maybeSwitchToShellEnv(getApplicationContext(), getPackageManager(), getIntent(), shouldForceFullOverlay(getIntent()))) {
            finish();
            Log.d(TAG, "Switching to ShellEnv and exiting onResume()");
            return;
        }
        this.mActivityPauseTime = 0;
        cancelIdleShutdownTimer();
        overridePendingTransition(0, 0);
        nativeOnResume(getNativeAppPtr());
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("app_state_change");
        analyticsEvent.setExtra("event_type", "app_started");
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        refreshFromExternalStates();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
        if (this.mNativeAppPtr != 0) {
            overridePendingTransition(0, 0);
            nativeOnPause(getNativeAppPtr());
            int nanoTime = (int) ((System.nanoTime() - this.mActivityStartTime) / C.NANOS_PER_SECOND);
            this.mActivityStartTime = 0;
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("app_state_change");
            analyticsEvent.setExtra("event_type", "app_paused");
            analyticsEvent.setExtra("active_duration_ms", Integer.valueOf(nanoTime));
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
            this.mActivityPauseTime = System.currentTimeMillis();
            long j = this.mRequestedIdleShutdownMillis;
            if (j > 0) {
                shutdownWhenIdle(j);
            }
        }
    }

    /* renamed from: onDestroy */
    public void lambda$shutdown$4$MainActivity() {
        Log.d(TAG, "onDestroy()");
        LowStorageNotificationScheduler lowStorageNotificationScheduler = this.mLowStorageNotificationScheduler;
        if (lowStorageNotificationScheduler != null) {
            lowStorageNotificationScheduler.cancelStorageChecks();
            this.mLowStorageNotificationScheduler = null;
        }
        if (this.mNativeAppPtr != 0) {
            unregisterUserSwitchReceiver();
            HttpClient httpClient = this.mHttpClient;
            if (httpClient != null) {
                httpClient.release();
                this.mHttpClient = null;
            }
            if (this.mSurfaceHolder != null) {
                nativeSurfaceDestroyed(getNativeAppPtr());
            }
            nativeOnDestroy(getNativeAppPtr());
            this.mNativeAppPtr = 0;
        }
        super.onDestroy();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        String str = TAG;
        Log.d(str, this + " surfaceCreated()");
        if (getNativeAppPtr() != 0) {
            nativeSurfaceCreated(getNativeAppPtr(), surfaceHolder.getSurface());
            this.mSurfaceHolder = surfaceHolder;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str = TAG;
        Log.d(str, this + " surfaceChanged() format: " + i + " width: " + i2 + " height: " + i3);
        if (getNativeAppPtr() != 0) {
            nativeSurfaceChanged(getNativeAppPtr(), surfaceHolder.getSurface());
            this.mSurfaceHolder = surfaceHolder;
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        String str = TAG;
        Log.d(str, this + " surfaceDestroyed()");
        if (((ShellApplication) getApplication()).getBootConfig().isAutomationEnabled) {
            Log.d(TAG, "T72044425 - Dumping stacktrace to understand where surfaceDestroyed is being called from", new Exception());
        }
        if (getNativeAppPtr() != 0) {
            nativeSurfaceDestroyed(getNativeAppPtr());
            this.mSurfaceHolder = null;
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.mBluetoothInputHelper.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.mBluetoothInputHelper.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mBluetoothInputHelper.dispatchTouchEvent(motionEvent);
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

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent()");
        setIntent(intent);
        if (this.mNativeAppPtr == 0) {
            return;
        }
        if (((ShellApplication) getApplication()).isTrustedAppLaunch(intent)) {
            nativeTrustedAppLaunch(getNativeAppPtr(), IntentParser.ToEnvironment(intent));
        } else {
            nativeNewIntent(getNativeAppPtr(), IntentParser.ToEnvironment(intent));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "onConfigurationChanged()");
        super.onConfigurationChanged(configuration);
    }

    public void shutdown() {
        Log.d(TAG, "shutdown()");
        LowStorageNotificationScheduler lowStorageNotificationScheduler = this.mLowStorageNotificationScheduler;
        if (lowStorageNotificationScheduler != null) {
            lowStorageNotificationScheduler.cancelStorageChecks();
            this.mLowStorageNotificationScheduler = null;
        }
        new Handler(getMainLooper()).post(new Runnable() {
            /* class com.oculus.vrshell.$$Lambda$MainActivity$JnN2fKOKw7RDEh5T7Dh1FHErEq0 */

            public final void run() {
                MainActivity.this.lambda$shutdown$4$MainActivity();
            }
        });
    }

    public void shutdownWhenIdle(long j) {
        String str = TAG;
        Log.d(str, "Requesting shutdown when idle " + (j / 1000) + " seconds.");
        cancelIdleShutdownTimer();
        this.mRequestedIdleShutdownMillis = j;
        if (this.mActivityPauseTime > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.mActivityPauseTime;
            if (currentTimeMillis >= this.mRequestedIdleShutdownMillis) {
                shutdown();
                return;
            }
            this.mIdleShutdownTimer = new Timer();
            this.mIdleShutdownTimer.schedule(new TimerTask() {
                /* class com.oculus.vrshell.MainActivity.AnonymousClass1 */

                public void run() {
                    MainActivity.this.shutdown();
                }
            }, this.mRequestedIdleShutdownMillis - currentTimeMillis);
        }
    }

    private void cancelIdleShutdownTimer() {
        Timer timer = this.mIdleShutdownTimer;
        if (timer != null) {
            timer.cancel();
            this.mIdleShutdownTimer.purge();
            this.mIdleShutdownTimer = null;
        }
    }

    private static boolean shouldForceFullOverlay(Intent intent) {
        return !intent.hasExtra(ShellApplication.INTENT_KEY_FROM_PKG) || intent.getStringExtra(ShellApplication.INTENT_KEY_FROM_PKG).isEmpty();
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public long getNativeAppPtr() {
        return this.mNativeAppPtr;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public IBinder getWindowToken() {
        return this.mActivityToken;
    }

    @Override // com.oculus.vrshell.ShellApplication.IVrClient
    public HttpClient getHttpClient() {
        return this.mHttpClient;
    }

    private static String toFolderPathFormat(String str) {
        if (str == null || str.length() == 0) {
            return "/";
        }
        if (str.charAt(str.length() - 1) == '/') {
            return str;
        }
        return str + "/";
    }

    public static String getInternalStorageCacheDir(Context context) {
        try {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null) {
                return toFolderPathFormat(cacheDir.getPath());
            }
            Log.d(TAG, "App getCacheDir() path does not exist!");
            return null;
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "Exception: " + e);
            return null;
        }
    }

    private void registerUserSwitchReceiver() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_BACKGROUND");
        intentFilter.addAction("android.intent.action.USER_FOREGROUND");
        this.mUserSwitchReceiver = new UserSwitchReceiver();
        registerReceiver(this.mUserSwitchReceiver, intentFilter);
    }

    private void unregisterUserSwitchReceiver() {
        unregisterReceiver(this.mUserSwitchReceiver);
    }

    /* access modifiers changed from: private */
    public final class UserSwitchReceiver extends BroadcastReceiver {
        private UserSwitchReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_BACKGROUND".equals(intent.getAction())) {
                Log.w(MainActivity.TAG, "Stopping VrShell due to ACTION_USER_BACKGROUND");
                ShellApplication.nativeBroadcastIntent(MainActivity.this.getNativeAppPtr(), IntentParser.ToEnvironment(intent));
            }
        }
    }

    private void refreshFromExternalStates() {
        Application application = getApplication();
        if (application != null) {
            ((ShellApplication) application).refreshFromExternalStates();
        }
    }

    private static String getSanitizedDeviceName(InputEvent inputEvent) {
        String name;
        InputDevice device = inputEvent.getDevice();
        return (device == null || (name = device.getName()) == null) ? "<unknown>" : name;
    }
}

package com.oculus.nux.ota;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import oculus.internal.remote.IRemoteService;

public class NuxOta {
    private static final long CONTROLLERS_FIRMWARE_UPDATE_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(2);
    public static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final long REMOTE_SERVICE_CONNECTION_RETRY_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    private static final String TAG = "NuxOta";
    private static final long VERIFY_CONTROLLERS_CONNECTABLE_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final long VERIFY_CONTROLLERS_CONNECTABLE_WAIT_TIME_FOR_UPDATE_PENDING_MS = TimeUnit.SECONDS.toMillis(20);
    private static final long VERIFY_CONTROLLERS_CONNECTABLE_WAIT_TIME_FOR_UPDATING_MS = TimeUnit.SECONDS.toMillis(1);
    private static NuxOta sInstance = null;
    private Context mContext;
    private HighPriAppsUpdateTracker mHighPriAppsUpdateTracker;
    private BroadcastReceiver mLoginStatusReceiver;
    private ConnectivityManager.NetworkCallback mNetworkStatusCallback;
    private OsUpdateTracker mOsUpdateTracker;
    private NuxOtaSettings mSettings = new NuxOtaSettings();
    private SettingsObserverCallback mSettingsObserverCallback;
    private int mSfxUpdateDoneResourceId;
    private StateMachine mStateMachine;
    private Telemetry mTelemetry;

    static /* synthetic */ boolean lambda$waitForControllersFirmwareUpdateComplete$0(int i) {
        return i == 1 || i == 2;
    }

    static /* synthetic */ boolean lambda$waitForControllersFirmwareUpdateComplete$1(int i) {
        return i == 2;
    }

    public static synchronized NuxOta tryGetInstance() {
        NuxOta nuxOta;
        synchronized (NuxOta.class) {
            nuxOta = sInstance;
        }
        return nuxOta;
    }

    public static synchronized NuxOta initialize(Context context, int i) {
        NuxOta nuxOta;
        synchronized (NuxOta.class) {
            if (sInstance == null) {
                sInstance = new NuxOta(context, i);
            } else {
                Log.w(TAG, "NuxOta is already initialized; returning its current instance.");
            }
            nuxOta = sInstance;
        }
        return nuxOta;
    }

    private NuxOta(Context context, int i) {
        this.mContext = context;
        this.mSfxUpdateDoneResourceId = i;
        this.mTelemetry = new Telemetry(context);
        maybeInitializeStateMachine();
        maybeInitializeOsUpdateTracker();
        maybeInitializeHighPriAppsUpdateTracker();
        maybeRegisterNetworkStatusCallback();
        maybeRegisterLoginStatusReceiver();
        maybeRegisterSettingsObservers();
        if (isConnectedToNetworkWithInternetAccess()) {
            onValidatedNetworkInternetAccess();
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanUp() {
        unregisterNetworkStatusCallback();
        unregisterLoginStatusReceiver();
    }

    private void maybeInitializeStateMachine() {
        if (!FirstTimeNuxManager.isOtaComplete()) {
            this.mStateMachine = new StateMachine(this.mContext, this, this.mTelemetry, this.mSettings, this.mSfxUpdateDoneResourceId);
            this.mStateMachine.start();
        }
    }

    private void maybeInitializeOsUpdateTracker() {
        if (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete()) {
            this.mOsUpdateTracker = new OsUpdateTracker(this.mContext, this, this.mStateMachine, this.mSettings, this.mTelemetry);
            this.mOsUpdateTracker.initialize();
        }
    }

    private void maybeInitializeHighPriAppsUpdateTracker() {
        if (FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete() && !FirstTimeNuxManager.isOtaComplete()) {
            this.mHighPriAppsUpdateTracker = new HighPriAppsUpdateTracker(this.mContext, this, this.mStateMachine, this.mSettings, this.mTelemetry);
            this.mHighPriAppsUpdateTracker.initialize();
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeStartHighPriAppUpdates() {
        HighPriAppsUpdateTracker highPriAppsUpdateTracker = this.mHighPriAppsUpdateTracker;
        if (highPriAppsUpdateTracker != null) {
            highPriAppsUpdateTracker.maybeStartUpdateRequests();
        }
    }

    private void maybeRegisterNetworkStatusCallback() {
        if (!FirstTimeNuxManager.isOtaComplete()) {
            this.mNetworkStatusCallback = new ConnectivityManager.NetworkCallback() {
                /* class com.oculus.nux.ota.NuxOta.AnonymousClass1 */

                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    if (NuxOta.this.networkHasValidatedInternetCapability(networkCapabilities)) {
                        NuxOta.this.onValidatedNetworkInternetAccess();
                    }
                }
            };
            ((ConnectivityManager) this.mContext.getSystemService("connectivity")).registerDefaultNetworkCallback(this.mNetworkStatusCallback);
        }
    }

    public void onValidatedNetworkInternetAccess() {
        maybeStartHighPriAppUpdates();
        if (!FirstTimeNuxManager.isOtaComplete()) {
            Object lock = this.mStateMachine.getLock();
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isConnectedToNetworkWithInternetAccess() {
        Network activeNetwork;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) || (activeNetwork = connectivityManager.getActiveNetwork()) == null) {
            return false;
        }
        return networkHasValidatedInternetCapability(connectivityManager.getNetworkCapabilities(activeNetwork));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean networkHasValidatedInternetCapability(NetworkCapabilities networkCapabilities) {
        return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
    }

    private void unregisterNetworkStatusCallback() {
        ((ConnectivityManager) this.mContext.getSystemService("connectivity")).unregisterNetworkCallback(this.mNetworkStatusCallback);
        this.mNetworkStatusCallback = null;
    }

    private void maybeRegisterLoginStatusReceiver() {
        if (!FirstTimeNuxManager.isOtaComplete()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.oculus.auth.BROADCAST_LOGIN");
            this.mLoginStatusReceiver = new BroadcastReceiver() {
                /* class com.oculus.nux.ota.NuxOta.AnonymousClass2 */

                public void onReceive(Context context, Intent intent) {
                    if ("com.oculus.auth.BROADCAST_LOGIN".equals(intent.getAction())) {
                        NuxOta.this.onLoginStatusChanged();
                    }
                }
            };
            this.mContext.registerReceiver(this.mLoginStatusReceiver, intentFilter);
        }
    }

    private void unregisterLoginStatusReceiver() {
        this.mContext.unregisterReceiver(this.mLoginStatusReceiver);
        this.mLoginStatusReceiver = null;
    }

    public void onLoginStatusChanged() {
        if (!FirstTimeNuxManager.isOtaComplete()) {
            Object lock = this.mStateMachine.getLock();
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

    public float getUpdateProgress() {
        NuxOtaState state = this.mSettings.getState();
        if (FirstTimeNuxManager.isOtaComplete()) {
            return 100.0f;
        }
        OsUpdateTracker osUpdateTracker = this.mOsUpdateTracker;
        if (osUpdateTracker != null) {
            return osUpdateTracker.getProgress();
        }
        HighPriAppsUpdateTracker highPriAppsUpdateTracker = this.mHighPriAppsUpdateTracker;
        if (highPriAppsUpdateTracker != null) {
            return highPriAppsUpdateTracker.getProgress();
        }
        String str = TAG;
        Log.e(str, "Unable to get progress check. NuxOtaState: " + state.toString());
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void sendUpdateProgressBroadcast(float f) {
        Intent intent = new Intent("com.oculus.nux.ota.NUX_OTA_PROGRESS_UPDATE");
        intent.putExtra("PROGRESS", f);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.SYSTEM);
    }

    public void setOkayToReboot() {
        Log.d(TAG, "Signaling okay to reboot.");
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                this.mStateMachine.setOkayToReboot(true);
                lock.notifyAll();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void waitForControllersFirmwareUpdateComplete() {
        try {
            int[] deviceTypes = getDeviceTypes();
            if (deviceTypes != null) {
                if (deviceTypes.length != 0) {
                    long elapsedRealtime = SystemClock.elapsedRealtime() + CONTROLLERS_FIRMWARE_UPDATE_TIMEOUT_MS;
                    do {
                        int[] verifyConnectable = verifyConnectable(deviceTypes);
                        if (verifyConnectable != null) {
                            if (verifyConnectable.length != 0) {
                                if (!IntStream.of(verifyConnectable).noneMatch($$Lambda$NuxOta$ImyuaFmqMancazxo4C1Pa9EFaNA.INSTANCE)) {
                                    long j = VERIFY_CONTROLLERS_CONNECTABLE_WAIT_TIME_FOR_UPDATING_MS;
                                    if (IntStream.of(verifyConnectable).anyMatch($$Lambda$NuxOta$nqg0qjl4yWuMHgLS03Gv58qveZo.INSTANCE)) {
                                        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
                                        if (powerManager != null) {
                                            Log.d(TAG, "Waking headset to allow controller firmware update to proceed.");
                                            powerManager.wakeUp(SystemClock.uptimeMillis(), "NuxOta:fw-update");
                                            j = VERIFY_CONTROLLERS_CONNECTABLE_WAIT_TIME_FOR_UPDATE_PENDING_MS;
                                        } else {
                                            Log.e(TAG, "Failed to get PowerManager object.");
                                            this.mTelemetry.recordError("Failed to get PowerManager object.");
                                            return;
                                        }
                                    }
                                    Thread.sleep(j);
                                } else {
                                    return;
                                }
                            }
                        }
                        Log.e(TAG, "Failed to verify connectable controllers.");
                        this.mTelemetry.recordError("Failed to verify connectable controllers.");
                        return;
                    } while (SystemClock.elapsedRealtime() < elapsedRealtime);
                    return;
                }
            }
            Log.e(TAG, "Failed to get controller device types.");
            this.mTelemetry.recordError("Failed to get controller device types.");
        } catch (RemoteException | InterruptedException | RuntimeException e) {
            Log.e(TAG, "Failure while waiting for controller firmware update.", e);
            this.mTelemetry.recordError("Failure while waiting for controller firmware update.");
            Thread.currentThread().interrupt();
        }
    }

    private int[] getDeviceTypes() throws InterruptedException, RemoteException {
        IRemoteService connectToRemoteService = connectToRemoteService();
        if (connectToRemoteService != null) {
            return connectToRemoteService.getSupportedDeviceTypes();
        }
        return null;
    }

    private int[] verifyConnectable(int[] iArr) throws InterruptedException, RemoteException {
        IRemoteService connectToRemoteService = connectToRemoteService();
        if (connectToRemoteService != null) {
            return connectToRemoteService.verifyControllersConnectable(iArr, (int) VERIFY_CONTROLLERS_CONNECTABLE_TIMEOUT_MS, 0);
        }
        return null;
    }

    private IRemoteService connectToRemoteService() throws InterruptedException {
        IRemoteService iRemoteService = null;
        for (int i = 0; i < 10 && iRemoteService == null; i++) {
            IBinder service = ServiceManager.getService("OVRRemoteService");
            if (service == null) {
                Thread.sleep(REMOTE_SERVICE_CONNECTION_RETRY_DELAY_MS);
            } else {
                iRemoteService = IRemoteService.Stub.asInterface(service);
            }
        }
        if (iRemoteService == null) {
            Log.e(TAG, "Failed to connect to remote service.");
            this.mTelemetry.recordError("Failed to connect to remote service.");
        }
        return iRemoteService;
    }

    /* access modifiers changed from: package-private */
    public void disableBleAdvertising() {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            /* class com.oculus.nux.ota.NuxOta.AnonymousClass3 */

            public void run() {
                Log.d(NuxOta.TAG, "Requesting BLE advertising to be disabled.");
                Intent intent = new Intent();
                intent.setAction("companion.DISABLE_BLE_ADV");
                intent.setClassName("com.oculus.companion.server", "com.oculus.companion.server.CompanionService");
                NuxOta.this.mContext.startServiceAsUser(intent, UserHandle.SYSTEM);
            }
        });
    }

    private void maybeRegisterSettingsObservers() {
        if (!FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            this.mSettingsObserverCallback = new SettingsObserverCallback() {
                /* class com.oculus.nux.ota.NuxOta.AnonymousClass4 */

                public void onSettingChange(String str) {
                    NuxOta.this.onSettingChanged(str);
                }
            };
            registerManagedDeviceSettingsObserver();
            regsiterFirstTimeNuxCompleteSettingObserver();
        }
    }

    private void registerManagedDeviceSettingsObserver() {
        if (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete()) {
            new SettingsManager(this.mContext).registerSettingsObserver("managed_device", this.mSettingsObserverCallback, new Handler(Looper.getMainLooper()));
        }
    }

    private void unregisterManagedDeviceSettingsObserver() {
        new SettingsManager(this.mContext).unregisterSettingsObserver("managed_device", this.mSettingsObserverCallback);
    }

    private void regsiterFirstTimeNuxCompleteSettingObserver() {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "user_setup_complete", 0) != 1) {
            new SettingsManager(this.mContext).registerSettingsObserver("first_time_nux_complete", this.mSettingsObserverCallback, new Handler(Looper.getMainLooper()));
        }
    }

    private void unregisterFirstTimeNuxCompleteSettingObserver() {
        new SettingsManager(this.mContext).unregisterSettingsObserver("first_time_nux_complete", this.mSettingsObserverCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onSettingChanged(java.lang.String r5) {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.nux.ota.NuxOta.onSettingChanged(java.lang.String):void");
    }

    private void onManagedDeviceSettingChanged() {
        Object lock = this.mStateMachine.getLock();
        synchronized (lock) {
            this.mStateMachine.setInEnterpriseMode(this.mSettings.isInEnterpriseMode());
            lock.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeEnableWifiSleepMode() {
        if (this.mContext.getPackageManager().hasSystemFeature("oculus.software.wifi.sleep")) {
            Log.d(TAG, "Enabling Wi-Fi sleep mode.");
            Settings.Global.putInt(this.mContext.getContentResolver(), "wifi_sleep_policy", 1);
            return;
        }
        Log.w(TAG, "Wi-Fi sleep mode is not supported.");
    }

    /* access modifiers changed from: package-private */
    public void ensureMarauderDeviceId() {
        SettingsManager settingsManager = new SettingsManager(this.mContext);
        if (TextUtils.isEmpty(settingsManager.getString("marauder_device_id", ""))) {
            String uuid = UUID.randomUUID().toString();
            if (!settingsManager.setString("marauder_device_id", uuid)) {
                Log.w(TAG, String.format("Generated device ID \"%s\" but could not write it to settings", uuid));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkForUpdates() {
        OsUpdateTracker osUpdateTracker = this.mOsUpdateTracker;
        if (osUpdateTracker != null) {
            osUpdateTracker.checkForUpdates();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateOsUpdateProgress(float f) {
        OsUpdateTracker osUpdateTracker = this.mOsUpdateTracker;
        if (osUpdateTracker != null) {
            osUpdateTracker.updateProgress(f);
        }
    }

    public void onHighPriAppsUpdateCompletedOrSkipped() {
        HighPriAppsUpdateTracker highPriAppsUpdateTracker = this.mHighPriAppsUpdateTracker;
        if (highPriAppsUpdateTracker != null) {
            highPriAppsUpdateTracker.onUpdateCompletedOrSkipped();
        }
    }
}

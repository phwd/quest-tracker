package com.oculus.companion.server;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IDeviceIdleController;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.support.coordinatorlayout.R$styleable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.LogWriter;
import com.oculus.companion.server.Protocol$HmdStatusResponse;
import com.oculus.devicecertservice.DeviceCert;
import com.oculus.devicecertservice.DeviceCertInterface;
import com.oculus.nux.ota.NuxOtaSettings;
import com.oculus.nux.ota.NuxOtaState;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CompanionState {
    private static final String TAG = "CompanionState";
    private static boolean horizonLoggedIn = false;
    private static final Object monitorLock = new Object();
    private BleModule bleModule;
    private Context context;
    private ControllerManager controllerManager;
    private byte[] currentStatus = ((Protocol$HmdStatusResponse) this.statusBuilder.build()).toByteArray();
    private DeviceCert mDeviceCert = new DeviceCert();
    private IDeviceIdleController mDeviceIdleController;
    private BroadcastReceiver mNuxOtaProgressReceiver = null;
    private BroadcastReceiver mOtaStateReceiver = null;
    private SettingsObserverCallback mSettingsObserverCallback = null;
    private Timer monitorTimer;
    private SettingsManager osSDKSettingsManager;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        /* class com.oculus.companion.server.CompanionState.AnonymousClass1 */

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (CompanionServer.DEBUG) {
                Log.d(CompanionState.TAG, "CompanionState Receiver:" + action);
            }
            char c = 65535;
            switch (action.hashCode()) {
                case -2128145023:
                    if (action.equals("android.intent.action.SCREEN_OFF")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1886648615:
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1538406691:
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        c = 0;
                        break;
                    }
                    break;
                case -839983671:
                    if (action.equals("com.oculus.companion.server.WifiModule.WIFI_DISCONNECTED")) {
                        c = 3;
                        break;
                    }
                    break;
                case 502473491:
                    if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                        c = 7;
                        break;
                    }
                    break;
                case 505380757:
                    if (action.equals("android.intent.action.TIME_SET")) {
                        c = 6;
                        break;
                    }
                    break;
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 833559602:
                    if (action.equals("android.intent.action.USER_UNLOCKED")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1019184907:
                    if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1022234951:
                    if (action.equals("com.oculus.companion.server.WifiModule.INTERNET_CONNECTED")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1098225179:
                    if (action.equals("com.oculus.companion.server.WifiModule.WIFI_CONNECTED")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CompanionState.this.updateBatteryState(intent);
                    return;
                case 1:
                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                    CompanionState.this.updatePowerState();
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                    CompanionState.this.updateNetworkState(intent);
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                case 7:
                    CompanionState.this.wifiModule.tryConnectToInternet(2);
                    return;
                case '\b':
                case '\t':
                case '\n':
                    CompanionState.this.updatePinState();
                    return;
                default:
                    if (CompanionServer.DEBUG) {
                        Log.d(CompanionState.TAG, action);
                        return;
                    }
                    return;
            }
        }
    };
    private IntentFilter receiverFilter;
    private Monitor refreshMonitor = null;
    private SecureStorage secureStorage;
    private final Protocol$HmdStatusResponse.Builder statusBuilder = Protocol$HmdStatusResponse.newBuilder();
    private Telemetry telemetry;
    private WifiModule wifiModule;

    /* access modifiers changed from: package-private */
    public class Monitor extends TimerTask {
        Monitor() {
        }

        public void run() {
            try {
                CompanionState.this.refreshAll();
                if (CompanionState.this.bleModule.hasNotify()) {
                    CompanionState.this.notifyUpdate();
                }
            } catch (RuntimeException e) {
                String str = CompanionState.TAG;
                Log.e(str, "RuntimeException: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e2) {
                String str2 = CompanionState.TAG;
                Log.e(str2, "Exception: " + e2.getMessage());
            }
        }
    }

    private void createReceiverFilter() {
        this.receiverFilter = new IntentFilter();
        this.receiverFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.receiverFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        this.receiverFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.receiverFilter.addAction("android.intent.action.SCREEN_OFF");
        this.receiverFilter.addAction("android.intent.action.USER_PRESENT");
        this.receiverFilter.addAction("android.intent.action.USER_UNLOCKED");
        this.receiverFilter.addAction("android.intent.action.TIME_SET");
        this.receiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
    }

    private Protocol$HeadsetState pmState2Protocol(int i) {
        if (i == 1) {
            return Protocol$HeadsetState.HEADSET_MOUNTED;
        }
        if (i == 2) {
            return Protocol$HeadsetState.HEADSET_UNMOUNTED;
        }
        if (i == 3) {
            return Protocol$HeadsetState.STANDBY;
        }
        if (i != 4) {
            return Protocol$HeadsetState.STANDBY;
        }
        return Protocol$HeadsetState.WAITING_FOR_SLEEP_MSG;
    }

    private Protocol$NuxStatus companion2Protocol(NuxOtaState nuxOtaState) {
        switch (AnonymousClass5.$SwitchMap$com$oculus$nux$ota$NuxOtaState[nuxOtaState.ordinal()]) {
            case 1:
                return Protocol$NuxStatus.NEW_DEVICE;
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return Protocol$NuxStatus.DAY0_NO_OTA;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return Protocol$NuxStatus.DAY0_OTA_READY;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return Protocol$NuxStatus.APP_NUX_COMPLETE;
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                return Protocol$NuxStatus.WAITING_FOR_REBOOT;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                return Protocol$NuxStatus.REBOOTING;
            case 7:
                return Protocol$NuxStatus.WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD;
            case 8:
                return Protocol$NuxStatus.NOTIFY_ENDPOINT;
            case 9:
                return Protocol$NuxStatus.NUX_COMPLETE;
            default:
                return Protocol$NuxStatus.NEW_DEVICE;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.companion.server.CompanionState$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$devicecertservice$DeviceCertInterface$ProvisionType = new int[DeviceCertInterface.ProvisionType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$nux$ota$NuxOtaState = new int[NuxOtaState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0089 */
        static {
            /*
            // Method dump skipped, instructions count: 150
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionState.AnonymousClass5.<clinit>():void");
        }
    }

    private Protocol$ProvisionType provisionType2Protocol(DeviceCertInterface.ProvisionType provisionType) {
        int i = AnonymousClass5.$SwitchMap$com$oculus$devicecertservice$DeviceCertInterface$ProvisionType[provisionType.ordinal()];
        if (i == 1) {
            return Protocol$ProvisionType.FACTORY_PROVISIONED;
        }
        if (i == 2) {
            return Protocol$ProvisionType.PROTOTYPE_PROVISIONED;
        }
        if (i != 3) {
            return Protocol$ProvisionType.UNKNOWN_PROVISIONING;
        }
        return Protocol$ProvisionType.UNPROVISIONED;
    }

    public CompanionState initialize(Context context2, BleModule bleModule2, WifiModule wifiModule2, ControllerManager controllerManager2, SecureStorage secureStorage2, Telemetry telemetry2, SettingsManager settingsManager) {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Initializing server state monitor");
        }
        this.context = context2;
        this.bleModule = bleModule2;
        this.wifiModule = wifiModule2;
        this.controllerManager = controllerManager2;
        this.secureStorage = secureStorage2;
        this.telemetry = telemetry2;
        this.osSDKSettingsManager = settingsManager;
        this.mDeviceIdleController = IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
        initializeNuxOta();
        initializeStatus();
        initializeManagedDevice();
        createReceiverFilter();
        context2.registerReceiver(this.receiver, this.receiverFilter);
        initializeWifiModule();
        if (!FirstTimeNuxManager.isOtaComplete() && CompanionService.isAdbEnabled(context2) && SystemProperties.getInt("ro.boot.adb", -1) <= 0) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "disabling adb");
            }
            CompanionService.setAdbEnabled(context2, false);
        }
        startMonitor();
        return this;
    }

    private void initializeManagedDevice() {
        int i = SystemProperties.getInt("com.oculus.managed", -1);
        if (i > -1) {
            this.secureStorage.storeValue("managed_device", i);
            this.osSDKSettingsManager.setInt("managed_device", i);
        }
    }

    private void initializeNuxOta() {
        this.statusBuilder.setNuxStatus(companion2Protocol(new NuxOtaSettings().getState()));
        if (!FirstTimeNuxManager.getFirstTimeNuxPreOtaComplete()) {
            registerOtaStateReceiver();
            regsiterNuxOtaProgressReceiver();
        }
        if (!FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            registerSettingsObservers();
        }
    }

    private void initializeWifiModule() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.companion.server.WifiModule.WIFI_DISCONNECTED");
        intentFilter.addAction("com.oculus.companion.server.WifiModule.WIFI_CONNECTED");
        intentFilter.addAction("com.oculus.companion.server.WifiModule.INTERNET_CONNECTED");
        LocalBroadcastManager lbm = this.wifiModule.getLbm();
        if (lbm == null) {
            Log.e(TAG, "Working with unintialized WifiModule, got no LBM!");
        } else {
            lbm.registerReceiver(this.receiver, intentFilter);
        }
    }

    private void initializeStatus() {
        this.statusBuilder.setBatteryLevel(100);
        this.statusBuilder.setChargerConnected(false);
        this.statusBuilder.setFastChargerConnected(false);
        this.statusBuilder.setCharging(false);
        this.statusBuilder.setWifiEnabled(false);
        this.statusBuilder.setWifiConfigured(false);
        this.statusBuilder.setWifiConnected(false);
        this.statusBuilder.setWifiOculusReachable(false);
        this.statusBuilder.setWifiIpAddress("");
        this.statusBuilder.setWifiDeviceName("");
        this.statusBuilder.setControllerPrimaryConfigured(false);
        this.statusBuilder.setControllerPrimaryConnected(false);
        this.statusBuilder.setControllerSecondaryConfigured(false);
        this.statusBuilder.setControllerSecondaryConnected(false);
        this.statusBuilder.setControllerOtherConfigured(false);
        this.statusBuilder.setControllerOtherConnected(false);
        this.statusBuilder.setOtaAvailable(false);
        this.statusBuilder.setOtaReady(false);
        this.statusBuilder.setHmdUpdatePercentageComplete(0);
        this.statusBuilder.setPinConfigured(false);
        this.statusBuilder.setPinLocked(false);
        this.statusBuilder.setHorizonLoggedIn(false);
        this.statusBuilder.setDeveloperMode(false);
        this.statusBuilder.setAdbEnabled(false);
        this.statusBuilder.setHswCompleted(this.osSDKSettingsManager.getBoolean("first_time_nux_health_safety_complete", false));
        this.statusBuilder.setNuxCompleted(FirstTimeNuxManager.isOtaComplete());
        this.statusBuilder.setNuxStatus(companion2Protocol(new NuxOtaSettings().getState()));
        this.statusBuilder.setSystemSoftwareLocked(true);
        this.statusBuilder.setHeadsetState(pmState2Protocol(3));
        this.statusBuilder.setProvisionType(Protocol$ProvisionType.UNKNOWN_PROVISIONING);
        this.statusBuilder.setProvisionedSerial("");
    }

    public void refreshAll() {
        refreshBatteryPowerState();
        refreshControllerState();
        refreshNetworkState();
        refreshHorizonState();
        refreshPinState();
        refreshMiscState();
    }

    public Protocol$HmdStatusResponse getUpdateMessage() {
        return (Protocol$HmdStatusResponse) this.statusBuilder.build();
    }

    public void startMonitor() {
        startMonitor(180000);
    }

    public void startMonitor(long j) {
        synchronized (monitorLock) {
            if (this.monitorTimer == null && this.refreshMonitor == null) {
                if (CompanionServer.DEBUG) {
                    String str = TAG;
                    Log.d(str, "Starting monitor. Period: " + j);
                }
                this.monitorTimer = new Timer("Companion Monitor Thread");
                this.refreshMonitor = new Monitor();
                this.monitorTimer.scheduleAtFixedRate(this.refreshMonitor, 0, j);
            } else if (CompanionServer.DEBUG) {
                Log.d(TAG, "Monitor active");
            }
        }
    }

    public void stopMonitor() {
        synchronized (monitorLock) {
            if (this.monitorTimer != null) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Stopping monitor");
                }
                this.monitorTimer.cancel();
                this.monitorTimer.purge();
                this.refreshMonitor.cancel();
                this.refreshMonitor = null;
                this.monitorTimer = null;
            } else if (CompanionServer.DEBUG) {
                Log.d(TAG, "Monitor inactive");
            }
        }
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Monitor stopped");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateBatteryState(Intent intent) {
        refreshBatteryState(intent);
        notifyUpdate();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePowerState() {
        refreshBatteryPowerState();
        notifyUpdate();
    }

    public void updateControllerState() {
        refreshControllerState();
        notifyUpdate();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePinState() {
        refreshPinState();
        notifyUpdate();
    }

    public void updateMiscState() {
        refreshMiscState();
        notifyUpdate();
    }

    public void updateHorizonState() {
        refreshHorizonState();
        notifyUpdate();
    }

    public void maybeExitDoze(String str) {
        try {
            if (this.context.getPackageManager().hasSystemFeature("oculus.software.wifi.sleep")) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Exiting from Doze");
                }
                if (this.mDeviceIdleController == null) {
                    this.mDeviceIdleController = IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
                }
                this.mDeviceIdleController.exitIdle(str);
            }
        } catch (RemoteException | NullPointerException e) {
            Log.e(TAG, "Couldn't exit doze", e);
        }
    }

    public void updateHeadsetState(int i) {
        this.statusBuilder.setHeadsetState(pmState2Protocol(i));
        notifyUpdate();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateNetworkState(android.content.Intent r6) {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionState.updateNetworkState(android.content.Intent):void");
    }

    private void refreshBatteryState(Intent intent) {
        boolean z = false;
        int intExtra = intent.getIntExtra("level", 0);
        int intExtra2 = intent.getIntExtra("scale", -1);
        this.statusBuilder.setBatteryLevel(((intExtra * 100) + (intExtra2 / 2)) / intExtra2);
        int intExtra3 = intent.getIntExtra("plugged", -1);
        this.statusBuilder.setChargerConnected((intExtra3 == 2) || (intExtra3 == 1));
        int intExtra4 = intent.getIntExtra("status", -1);
        this.statusBuilder.setCharging(intExtra4 == 2 || (intExtra4 == 5 && intExtra3 != 0));
        BatteryManager batteryManager = (BatteryManager) this.context.getSystemService("batterymanager");
        if (batteryManager != null) {
            int intProperty = batteryManager.getIntProperty(2);
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Charging current=" + intProperty);
            }
            Protocol$HmdStatusResponse.Builder builder = this.statusBuilder;
            if (intProperty > 150000) {
                z = true;
            }
            builder.setFastChargerConnected(z);
        }
    }

    private void refreshBatteryPowerState() {
        refreshBatteryState(this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
    }

    private void refreshControllerState() {
        try {
            int[] deviceTypes = ControllerManager.getDeviceTypes();
            for (int i : deviceTypes) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Type=" + i);
                }
                int batteryLevel = ControllerManager.getBatteryLevel(i);
                boolean z = ControllerManager.getPairedDevice(i) != null;
                boolean z2 = z && ControllerManager.isConnected(i);
                if (i == 0) {
                    this.statusBuilder.setControllerPrimaryConfigured(z);
                    this.statusBuilder.setControllerPrimaryConnected(z2);
                    this.statusBuilder.setControllerPrimaryBatteryLevel(batteryLevel);
                } else {
                    this.statusBuilder.setControllerSecondaryConfigured(z);
                    this.statusBuilder.setControllerSecondaryConnected(z2);
                    this.statusBuilder.setControllerSecondaryBatteryLevel(batteryLevel);
                }
            }
        } catch (InterruptedException unused) {
            Log.e(TAG, "Controller State Refresh Interrupted");
        }
        Set<BluetoothDevice> bluetoothBondedThirdPartyDevices = ControllerManager.bluetoothBondedThirdPartyDevices();
        this.statusBuilder.setControllerOtherConnected(false);
        if (bluetoothBondedThirdPartyDevices == null || bluetoothBondedThirdPartyDevices.size() <= 0) {
            this.statusBuilder.setControllerOtherConfigured(false);
            return;
        }
        this.statusBuilder.setControllerOtherConfigured(true);
        for (BluetoothDevice bluetoothDevice : bluetoothBondedThirdPartyDevices) {
            if (this.controllerManager.isBluetoothDeviceConnected(bluetoothDevice.getAddress())) {
                this.statusBuilder.setControllerOtherConnected(true);
                return;
            }
        }
    }

    private void refreshPinState() {
        boolean z = false;
        int pinStatus = CompanionService.pinStatus(this.context, 0);
        this.statusBuilder.setPinConfigured((pinStatus & 1) > 0);
        Protocol$HmdStatusResponse.Builder builder = this.statusBuilder;
        if ((pinStatus & 2) > 0) {
            z = true;
        }
        builder.setPinLocked(z);
    }

    private void refreshMiscState() {
        this.statusBuilder.setHswCompleted(this.osSDKSettingsManager.getBoolean("first_time_nux_health_safety_complete", false, ActivityManager.getCurrentUser()));
        this.statusBuilder.setNuxCompleted(FirstTimeNuxManager.isOtaComplete());
        this.statusBuilder.setAdbEnabled(CompanionService.isAdbEnabled(this.context));
        this.statusBuilder.setDeveloperMode(false);
        this.statusBuilder.setSystemSoftwareLocked(SystemProperties.getBoolean("ro.boot.flash.locked", true));
        try {
            DeviceCertInterface.ProvisionState deviceProvisionState = this.mDeviceCert.getDeviceProvisionState();
            this.statusBuilder.setProvisionType(provisionType2Protocol(deviceProvisionState.provisionType));
            this.statusBuilder.setProvisionedSerial(deviceProvisionState.provisionSerial);
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Error fetching provisioning state: " + e.getMessage());
            this.statusBuilder.setProvisionType(Protocol$ProvisionType.UNKNOWN_PROVISIONING);
            this.statusBuilder.setProvisionedSerial("unknown");
        }
    }

    private void refreshHorizonState() {
        if (CompanionUtil.isUserUnlocked(this.context)) {
            try {
                boolean z = true;
                if (CompanionService.horizonStatus(this.context, UserHandle.SYSTEM) != 1) {
                    z = false;
                }
                horizonLoggedIn = z;
            } catch (InterruptedException unused) {
                Log.e(TAG, "Refresh Horizon State interrupted");
            }
        } else {
            horizonLoggedIn = this.secureStorage.getBooleanValue("login_status");
            String str = TAG;
            Log.w(str, "User is locked, holdup horizon refresh, get last known status : " + horizonLoggedIn);
        }
        if (horizonLoggedIn) {
            CompanionDeviceAdmin.setActive(this.context);
        }
        this.statusBuilder.setHorizonLoggedIn(horizonLoggedIn);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:24|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        android.util.Log.e(com.oculus.companion.server.CompanionState.TAG, "Interrupted while awaiting internet connection status.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0085, code lost:
        r4.wifiModule.getLbm().unregisterReceiver(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void refreshNetworkState() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionState.refreshNetworkState():void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyUpdate() {
        byte[] byteArray = ((Protocol$HmdStatusResponse) this.statusBuilder.build()).toByteArray();
        if (!Arrays.equals(byteArray, this.currentStatus)) {
            this.currentStatus = byteArray;
            this.bleModule.notifyStatusChange();
        }
    }

    public void dumpState() {
        dumpInfo(new LogWriter(3, TAG));
    }

    public void dumpInfo(Writer writer) {
        try {
            writer.write("------------------------ BEGIN COMPANION STATE ------------------------\n");
            writer.write("Battery Level: " + this.statusBuilder.getBatteryLevel() + "\n");
            writer.write("Charging: " + this.statusBuilder.getChargerConnected() + "\n");
            writer.write("Fast Charging: " + this.statusBuilder.getFastChargerConnected() + "\n");
            writer.write("Wifi enabled: " + this.statusBuilder.getWifiEnabled() + "\n");
            writer.write("Wifi configured: " + this.statusBuilder.getWifiConfigured() + "\n");
            writer.write("Wifi connected: " + this.statusBuilder.getWifiConnected() + "\n");
            writer.write("Wifi server reachable: " + this.statusBuilder.getWifiOculusReachable() + "\n");
            writer.write("Wifi IP address: " + this.statusBuilder.getWifiIpAddress() + "\n");
            writer.write("Wifi device name: " + this.statusBuilder.getWifiDeviceName() + "\n");
            writer.write("Controller primary configured: " + this.statusBuilder.getControllerPrimaryConfigured() + "\n");
            writer.write("Controller primary connected: " + this.statusBuilder.getControllerPrimaryConnected() + "\n");
            writer.write("Controller secondary configured: " + this.statusBuilder.getControllerSecondaryConfigured() + "\n");
            writer.write("Controller secondary connected: " + this.statusBuilder.getControllerSecondaryConnected() + "\n");
            writer.write("Controller other configured: " + this.statusBuilder.getControllerOtherConfigured() + "\n");
            writer.write("Controller other connected: " + this.statusBuilder.getControllerOtherConnected() + "\n");
            writer.write("OTA available: " + this.statusBuilder.getOtaAvailable() + "\n");
            writer.write("OTA ready: " + this.statusBuilder.getOtaReady() + "\n");
            writer.write("HMD Update percentage: " + this.statusBuilder.getHmdUpdatePercentageComplete() + "\n");
            writer.write("PIN configured: " + this.statusBuilder.getPinConfigured() + "\n");
            writer.write("PIN locked: " + this.statusBuilder.getPinLocked() + "\n");
            writer.write("Horizon logged in: " + this.statusBuilder.getHorizonLoggedIn() + "\n");
            writer.write("Developer mode: " + this.statusBuilder.getDeveloperMode() + "\n");
            writer.write("ADB: " + this.statusBuilder.getAdbEnabled() + "\n");
            writer.write("HSW complete: " + this.statusBuilder.getHswCompleted() + "\n");
            writer.write("NUX complete: " + this.statusBuilder.getNuxCompleted() + "\n");
            writer.write("NUX State: " + this.statusBuilder.getNuxStatus() + "\n");
            writer.write("Headset State: " + this.statusBuilder.getHeadsetState() + "\n");
            writer.write("Provision type: " + this.statusBuilder.getProvisionType() + "\n");
            writer.write("Provisioned serial: " + this.statusBuilder.getProvisionedSerial() + "\n");
            writer.write("------------------------- END COMPANION STATE -------------------------\n");
        } catch (IOException e) {
            String str = TAG;
            Log.w(str, "dumpInfo unhandled IOException: " + e);
        }
    }

    private void registerOtaStateReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.updater.STATE_NOTIFICATION");
        this.mOtaStateReceiver = new BroadcastReceiver() {
            /* class com.oculus.companion.server.CompanionState.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                CompanionState.this.onOtaStateChanged(intent);
            }
        };
        this.context.registerReceiver(this.mOtaStateReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: public */
    private void onOtaStateChanged(Intent intent) {
        char c;
        String stringExtra = intent.getStringExtra("state");
        switch (stringExtra.hashCode()) {
            case -1510287249:
                if (stringExtra.equals("checking_for_updates")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -252387059:
                if (stringExtra.equals("waiting_for_reboot")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -132584012:
                if (stringExtra.equals("applying_update")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1160636847:
                if (stringExtra.equals("error_while_applying_update")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1567873686:
                if (stringExtra.equals("no_updates_available")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1645945172:
                if (stringExtra.equals("error_while_checking_for_updates")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1757372671:
                if (stringExtra.equals("verifying_update")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                this.statusBuilder.setOtaAvailable(false);
                this.statusBuilder.setOtaReady(false);
                return;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                this.statusBuilder.setOtaAvailable(true);
                this.statusBuilder.setOtaReady(false);
                return;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                this.statusBuilder.setOtaAvailable(true);
                this.statusBuilder.setOtaReady(true);
                return;
            default:
                return;
        }
    }

    private void regsiterNuxOtaProgressReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.nux.ota.NUX_OTA_PROGRESS_UPDATE");
        this.mNuxOtaProgressReceiver = new BroadcastReceiver() {
            /* class com.oculus.companion.server.CompanionState.AnonymousClass3 */

            public void onReceive(Context context, Intent intent) {
                CompanionState.this.onNuxOtaProgressChanged(intent);
            }
        };
        this.context.registerReceiver(this.mNuxOtaProgressReceiver, intentFilter);
    }

    private void unregsiterNuxOtaProgressReceiver() {
        BroadcastReceiver broadcastReceiver = this.mNuxOtaProgressReceiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
            this.mNuxOtaProgressReceiver = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onNuxOtaProgressChanged(Intent intent) {
        float floatExtra = intent.getFloatExtra("PROGRESS", 0.0f);
        this.statusBuilder.setHmdUpdatePercentageComplete((int) floatExtra);
        notifyUpdate();
        if (floatExtra == 100.0f) {
            unregsiterNuxOtaProgressReceiver();
        }
    }

    private void registerSettingsObservers() {
        this.mSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.companion.server.CompanionState.AnonymousClass4 */

            public void onSettingChange(String str) {
                CompanionState.this.onSettingChanged(str);
            }
        };
        registerFirstTimeNuxStateSettingObserver();
    }

    private void registerFirstTimeNuxStateSettingObserver() {
        if (new NuxOtaSettings().getState() != NuxOtaState.COMPLETE) {
            new SettingsManager(this.context).registerSettingsObserver("first_time_nux_ota_state", this.mSettingsObserverCallback, new Handler(Looper.getMainLooper()));
        }
    }

    private void unregisterFirstTimeNuxStateSettingObserver() {
        new SettingsManager(this.context).unregisterSettingsObserver("first_time_nux_ota_state", this.mSettingsObserverCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onSettingChanged(String str) {
        if (((str.hashCode() == -1458272643 && str.equals("first_time_nux_ota_state")) ? (char) 0 : 65535) == 0) {
            NuxOtaState state = new NuxOtaSettings().getState();
            this.statusBuilder.setNuxStatus(companion2Protocol(state));
            updateMiscState();
            if (state == NuxOtaState.COMPLETE) {
                unregisterFirstTimeNuxStateSettingObserver();
            }
        }
    }
}

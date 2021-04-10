package com.oculus.companion.server;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.companion.server.utils.BluetoothA2dpWrapper;
import com.oculus.companion.server.utils.BluetoothInputDeviceWrapper;
import com.oculus.companion.server.utils.BluetoothProfileWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ControllerManager {
    private static final String TAG = "ControllerManager";
    private static final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static List<BluetoothProfileWrapper> bluetoothProfiles = new ArrayList();
    private static volatile CountDownLatch btBondLatch;
    private static final Vector<BluetoothDevice> btDeviceList = new Vector<>();
    private static final Map<String, BluetoothDevice> btDeviceMapBond = new ConcurrentHashMap();
    private static final Map<String, Integer> btDeviceMapPairingKey = new ConcurrentHashMap();
    private static final Map<String, BluetoothDevice> btDeviceMapScan = new ConcurrentHashMap();
    private static volatile CountDownLatch btScanLatch;
    private static volatile CountDownLatch btUuidLatch;
    private static volatile String btUuidPendingDevice;
    private static CompanionState companionState;
    private static final ConcurrentHashMap<String, Integer> oculusActiveControllers = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Integer> oculusControllers = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BluetoothDevice> otherActiveControllers = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BluetoothDevice> otherControllers = new ConcurrentHashMap<>();
    private static Telemetry telemetry;
    private BroadcastReceiver btDeviceReceiver = new BroadcastReceiver() {
        /* class com.oculus.companion.server.ControllerManager.AnonymousClass1 */

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice != null) {
                    BluetoothProfileWrapper profileForDevice = ControllerManager.getProfileForDevice(bluetoothDevice);
                    if (profileForDevice == null || !profileForDevice.isReady()) {
                        String name = profileForDevice != null ? profileForDevice.getName() : "null";
                        String str = ControllerManager.TAG;
                        Log.w(str, "Profile " + name + " not ready");
                        return;
                    }
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    synchronized (ControllerManager.otherActiveControllers) {
                        if (intExtra == 2) {
                            try {
                                String str2 = ControllerManager.TAG;
                                Log.i(str2, "Controller Connected: " + bluetoothDevice.getAddress());
                                ControllerManager.otherActiveControllers.put(bluetoothDevice.getAddress(), bluetoothDevice);
                            } catch (Throwable th) {
                                throw th;
                            }
                        } else if (intExtra == 0) {
                            String str3 = ControllerManager.TAG;
                            Log.i(str3, "Controller Disconnected: " + bluetoothDevice.getAddress());
                            ControllerManager.otherActiveControllers.remove(bluetoothDevice.getAddress());
                        } else {
                            String str4 = ControllerManager.TAG;
                            Log.i(str4, "Controller State (" + intExtra + "): " + bluetoothDevice.getAddress());
                        }
                    }
                }
                ControllerManager.updateControllerState();
            }
        }
    };
    private final Context context;

    public enum ControllerScanAndPairResult {
        UNKNOWN_ERROR,
        TIMED_OUT,
        FAILED_TO_PAIR,
        ALREADY_IN_PROGRESS,
        SUCCESS,
        INTERNAL_ERROR
    }

    public void setCompanionState(CompanionState companionState2) {
        companionState = companionState2;
    }

    public static void updateControllerState() {
        CompanionState companionState2 = companionState;
        if (companionState2 != null) {
            companionState2.updateControllerState();
        }
    }

    private static Set<String> getSupportedProfiles() {
        return new HashSet(Arrays.asList(SystemProperties.get("ro.bluetooth.profiles").split(",")));
    }

    public ControllerManager(Context context2, Telemetry telemetry2) {
        this.context = context2;
        bluetoothProfiles = new ArrayList();
        bluetoothProfiles.add(new BluetoothInputDeviceWrapper(context2, bluetoothAdapter));
        if (getSupportedProfiles().contains("a2dp")) {
            bluetoothProfiles.add(new BluetoothA2dpWrapper(context2, bluetoothAdapter));
        }
        telemetry = telemetry2;
    }

    public void asyncInit() {
        try {
            initializeOculusController();
            initializeBluetoothController();
            refreshBluetoothControllerStatus();
            updateControllerState();
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted in ControllerManager.initialize()", e);
        }
    }

    private void initializeBluetoothController() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Initializing bluetooth profiles");
        }
        for (BluetoothProfileWrapper bluetoothProfileWrapper : bluetoothProfiles) {
            bluetoothProfileWrapper.initialize();
        }
        this.context.registerReceiver(this.btDeviceReceiver, new IntentFilter("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED"));
    }

    private void initializeOculusController() throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Initializing Oculus Controllers");
        }
        if (OculusController.connectRemoteService()) {
            refreshOculusControllerStatus();
        } else {
            Log.e(TAG, "Could not connect to OVRRemoteService. Controller Initialization failed.");
        }
    }

    public static ControllerScanAndPairResult scanAndPairDevice(int i, int i2) throws InterruptedException {
        ControllerScanAndPairResult scanAndPairDevice = OculusController.scanAndPairDevice(i, i2);
        refreshOculusControllerStatus();
        updateControllerState();
        return scanAndPairDevice;
    }

    public static int[] verifyConnectable(int[] iArr, int i, int i2) throws InterruptedException {
        return OculusController.verifyConnectable(iArr, i, i2);
    }

    public static String getPairedDevice(int i) throws InterruptedException {
        return OculusController.getPairedDevice(i);
    }

    public static boolean unpairDevice(int i) throws InterruptedException {
        String pairedDevice = OculusController.getPairedDevice(i);
        if (pairedDevice != null) {
            boolean unpairDevice = OculusController.unpairDevice(i);
            if (!unpairDevice) {
                return unpairDevice;
            }
            oculusActiveControllers.remove(pairedDevice);
            oculusControllers.remove(pairedDevice);
            updateControllerState();
            return unpairDevice;
        }
        Log.e(TAG, "Inconsistent behavior - device already unpaired!!!");
        return false;
    }

    public static int[] getDeviceTypes() throws InterruptedException {
        return OculusController.getDeviceTypes();
    }

    public static boolean isConnected(int i) throws InterruptedException {
        String pairedDevice = OculusController.getPairedDevice(i);
        if (pairedDevice != null) {
            return oculusActiveControllers.containsKey(pairedDevice);
        }
        return OculusController.isConnected(i);
    }

    public static int getBatteryLevel(int i) throws InterruptedException {
        return OculusController.getBatteryLevel(i);
    }

    public static String getFirmwareVersion(int i) throws InterruptedException {
        return OculusController.getFirmwareVersion(i);
    }

    /* access modifiers changed from: private */
    public static BluetoothProfileWrapper getProfileForDevice(BluetoothDevice bluetoothDevice) {
        ParcelUuid[] uuids;
        if (!(bluetoothDevice == null || (uuids = bluetoothDevice.getUuids()) == null || uuids.length == 0)) {
            for (BluetoothProfileWrapper bluetoothProfileWrapper : bluetoothProfiles) {
                int length = uuids.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (bluetoothProfileWrapper.isUuidSupported(uuids[i])) {
                            return bluetoothProfileWrapper;
                        }
                        i++;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static BluetoothProfileWrapper getProfileForUuid(UUID uuid) {
        for (BluetoothProfileWrapper bluetoothProfileWrapper : bluetoothProfiles) {
            if (bluetoothProfileWrapper.isUuidSupported(new ParcelUuid(uuid))) {
                return bluetoothProfileWrapper;
            }
        }
        return null;
    }

    private static boolean addressIsLikelyOculusController(String str) {
        return str.toLowerCase().startsWith("2c:26:17");
    }

    public boolean isControllerDevice(String str) {
        for (String str2 : oculusControllers.keySet()) {
            if (CompanionServer.DEBUG) {
                String str3 = TAG;
                Log.d(str3, "isOculusControllerDevice: " + str2);
            }
        }
        for (String str4 : otherControllers.keySet()) {
            if (CompanionServer.DEBUG) {
                String str5 = TAG;
                Log.d(str5, "isOtherControllerDevice: " + str4);
            }
        }
        if (!addressIsLikelyOculusController(str) && !otherControllers.containsKey(str) && !oculusControllers.containsKey(str)) {
            return false;
        }
        return true;
    }

    public void notifyDisconnected(String str) {
        if (CompanionServer.DEBUG) {
            String str2 = TAG;
            Log.d(str2, "notifyDisconnected: " + str);
        }
        if (oculusActiveControllers.containsKey(str)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Removing Oculus controller from active list");
            }
            oculusActiveControllers.remove(str);
        }
        if (otherActiveControllers.containsKey(str)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Removing controller from active list");
            }
            otherActiveControllers.remove(str);
        }
        updateControllerState();
    }

    public void notifyConnected(String str, BluetoothDevice bluetoothDevice) {
        if (CompanionServer.DEBUG) {
            String str2 = TAG;
            Log.d(str2, "notifyConnected: " + str);
        }
        if (oculusControllers.containsKey(str) || addressIsLikelyOculusController(str)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Adding Oculus controller to active list");
            }
            if (oculusControllers.containsKey(str)) {
                oculusActiveControllers.put(str, oculusControllers.get(str));
            } else {
                oculusActiveControllers.put(str, 0);
            }
        }
        if (otherControllers.containsKey(str)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Adding controller to active list");
            }
            otherActiveControllers.put(str, bluetoothDevice);
        }
        updateControllerState();
    }

    public boolean isBluetoothDeviceConnected(String str) {
        return otherActiveControllers.containsKey(str) || oculusActiveControllers.containsKey(str);
    }

    private static void refreshOculusControllerStatus() throws InterruptedException {
        oculusControllers.clear();
        int[] deviceTypes = OculusController.getDeviceTypes();
        for (int i : deviceTypes) {
            String pairedDevice = OculusController.getPairedDevice(i);
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Oculus controller [" + pairedDevice + "]");
            }
            if (pairedDevice != null) {
                oculusControllers.put(pairedDevice, Integer.valueOf(i));
                synchronized (oculusActiveControllers) {
                    if (OculusController.isConnected(i)) {
                        if (!oculusActiveControllers.containsKey(pairedDevice)) {
                            if (CompanionServer.DEBUG) {
                                Log.d(TAG, "Adding device");
                            }
                            oculusActiveControllers.put(pairedDevice, Integer.valueOf(i));
                        } else if (CompanionServer.DEBUG) {
                            Log.d(TAG, "Device already connected");
                        }
                    } else if (oculusActiveControllers.containsKey(pairedDevice)) {
                        if (CompanionServer.DEBUG) {
                            Log.d(TAG, "Device not connected. Removing");
                        }
                        oculusActiveControllers.remove(pairedDevice);
                    } else if (CompanionServer.DEBUG) {
                        Log.d(TAG, "Device not connected");
                    }
                }
            }
        }
    }

    private void refreshBluetoothControllerStatus() throws InterruptedException {
        boolean z;
        Set<BluetoothDevice> bluetoothBondedThirdPartyDevices = bluetoothBondedThirdPartyDevices();
        if (bluetoothBondedThirdPartyDevices != null) {
            otherControllers.clear();
            for (BluetoothDevice bluetoothDevice : bluetoothBondedThirdPartyDevices) {
                BluetoothProfileWrapper profileForDevice = getProfileForDevice(bluetoothDevice);
                if (profileForDevice != null) {
                    if (CompanionServer.DEBUG) {
                        Log.d(TAG, "Bluetooth device[" + bluetoothDevice.getAddress() + "], profile " + profileForDevice.getName());
                    }
                    z = profileForDevice.isDeviceConnected(bluetoothDevice);
                } else {
                    z = false;
                }
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Device connected: " + z);
                }
                if (!z) {
                    if (CompanionServer.DEBUG) {
                        Log.d(TAG, "Attempting to connect");
                    }
                    z = bluetoothConnectDevice(this.context, bluetoothDevice.getAddress(), false);
                }
                synchronized (otherActiveControllers) {
                    if (z) {
                        try {
                            if (!otherActiveControllers.containsKey(bluetoothDevice.getAddress())) {
                                if (CompanionServer.DEBUG) {
                                    Log.d(TAG, "Adding device");
                                }
                                otherActiveControllers.put(bluetoothDevice.getAddress(), bluetoothDevice);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    if (z) {
                        Log.e(TAG, "Reconnected device: " + bluetoothDevice.getAddress());
                    } else {
                        otherActiveControllers.remove(bluetoothDevice.getAddress());
                        if (CompanionServer.DEBUG) {
                            Log.d(TAG, "Device not found.");
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean isDeviceSupported(BluetoothDevice bluetoothDevice) {
        for (BluetoothProfileWrapper bluetoothProfileWrapper : bluetoothProfiles) {
            if (bluetoothProfileWrapper.isDeviceSupported(bluetoothDevice)) {
                return true;
            }
        }
        return false;
    }

    private static BroadcastReceiver getBluetoothDeviceReceiverScan() {
        return new BroadcastReceiver() {
            /* class com.oculus.companion.server.ControllerManager.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (CompanionServer.DEBUG) {
                    String str = ControllerManager.TAG;
                    Log.d(str, "BTReceiver got action: " + action);
                }
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    String name = bluetoothDevice.getName();
                    String address = bluetoothDevice.getAddress();
                    int type = bluetoothDevice.getType();
                    BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
                    int deviceClass = bluetoothClass.getDeviceClass();
                    int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
                    Log.i(ControllerManager.TAG, String.format("Found device %s[%s] Type[%d] Class[%d] MajorClass[%d]", name, address, Integer.valueOf(type), Integer.valueOf(deviceClass), Integer.valueOf(majorDeviceClass)));
                    if (type != 0 && ControllerManager.isDeviceSupported(bluetoothDevice)) {
                        ControllerManager.btDeviceList.add(bluetoothDevice);
                    }
                } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    if (CompanionServer.DEBUG) {
                        Log.d(ControllerManager.TAG, "BT Discovery complete");
                    }
                    ControllerManager.btScanLatch.countDown();
                } else if ("android.bluetooth.device.action.UUID".equals(action)) {
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (!TextUtils.equals(bluetoothDevice2.getAddress(), ControllerManager.btUuidPendingDevice)) {
                        String str2 = ControllerManager.TAG;
                        Log.w(str2, "Expecting uuids for " + ControllerManager.btUuidPendingDevice + ", got for " + bluetoothDevice2.getAddress());
                        return;
                    }
                    BluetoothProfileWrapper profileForDevice = ControllerManager.getProfileForDevice(bluetoothDevice2);
                    if (profileForDevice != null) {
                        ControllerManager.btDeviceMapScan.put(bluetoothDevice2.getAddress(), bluetoothDevice2);
                        if (CompanionServer.DEBUG) {
                            String str3 = ControllerManager.TAG;
                            Log.d(str3, bluetoothDevice2.getName() + " is supported by profile " + profileForDevice.getName());
                        }
                    }
                    if (CompanionServer.DEBUG) {
                        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (!(bluetoothDevice2 == null || parcelableArrayExtra == null)) {
                            int length = parcelableArrayExtra.length;
                            for (int i = 0; i < length; i++) {
                                String str4 = ControllerManager.TAG;
                                Log.d(str4, "UUID: " + ((ParcelUuid) parcelableArrayExtra[i]).toString());
                            }
                        }
                    }
                    if (TextUtils.equals(bluetoothDevice2.getAddress(), ControllerManager.btUuidPendingDevice)) {
                        ControllerManager.btUuidLatch.countDown();
                    }
                }
            }
        };
    }

    private static BroadcastReceiver getBluetoothDeviceReceiverBond() {
        return new BroadcastReceiver() {
            /* class com.oculus.companion.server.ControllerManager.AnonymousClass3 */

            public void onReceive(Context context, Intent intent) {
                BluetoothDevice bluetoothDevice;
                String action = intent.getAction();
                if (CompanionServer.DEBUG) {
                    String str = ControllerManager.TAG;
                    Log.d(str, "BTReceiver got action: " + action);
                }
                if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                    Bundle extras = intent.getExtras();
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) extras.getParcelable("android.bluetooth.device.extra.DEVICE");
                    int i = extras.getInt("android.bluetooth.device.extra.BOND_STATE");
                    Log.i(ControllerManager.TAG, String.format("Bond state changed: Device: %s (%s) State=%d", bluetoothDevice2.getName(), bluetoothDevice2.getAddress(), Integer.valueOf(i)));
                    if (12 == i) {
                        ControllerManager.btDeviceMapBond.put(bluetoothDevice2.getAddress(), bluetoothDevice2);
                        ControllerManager.btBondLatch.countDown();
                    }
                } else if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(action) && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null) {
                    if (CompanionServer.DEBUG) {
                        String str2 = ControllerManager.TAG;
                        Log.d(str2, "Received Pairing request from: " + bluetoothDevice.getName());
                    }
                    int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", -1);
                    ControllerManager.telemetry.recordBluetoothPairingRequest(intExtra);
                    if (intExtra == 0 || intExtra == 7) {
                        int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", -1);
                        if (CompanionServer.DEBUG) {
                            String str3 = ControllerManager.TAG;
                            Log.d(str3, "PAIRING_VARIANT_PIN. Key = " + intExtra2);
                        }
                    } else if (intExtra == 2 || intExtra == 3) {
                        if (CompanionServer.DEBUG) {
                            Log.d(ControllerManager.TAG, "PAIRING_VARIANT_PASSKEY_CONFIRMATION/CONSENT");
                        }
                        bluetoothDevice.setPairingConfirmation(true);
                    } else if (intExtra == 4 || intExtra == 5) {
                        int intExtra3 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", -1);
                        if (CompanionServer.DEBUG) {
                            String str4 = ControllerManager.TAG;
                            Log.d(str4, "PAIRING_VARIANT_DISPLAY_PASSKEY/PIN (" + intExtra3 + ")");
                        }
                        ControllerManager.btDeviceMapPairingKey.put(bluetoothDevice.getAddress(), Integer.valueOf(intExtra3));
                        ControllerManager.btBondLatch.countDown();
                    } else if (CompanionServer.DEBUG) {
                        String str5 = ControllerManager.TAG;
                        Log.d(str5, "Unhandled pairing variant: " + intExtra);
                    }
                }
            }
        };
    }

    public static boolean bluetoothConnectDevice(Context context2, String str, boolean z) throws InterruptedException {
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        boolean z2 = false;
        if (!(remoteDevice == null || remoteDevice.getBondState() == 12)) {
            if (CompanionServer.DEBUG) {
                String str2 = TAG;
                Log.d(str2, "Device " + str + " not bonded. Bonding device first");
            }
            if (!bluetoothBondDevice(context2, str, z)) {
                Log.w(TAG, "Could not bond device. Aborting");
                return false;
            }
        }
        if (CompanionServer.DEBUG) {
            String str3 = TAG;
            Log.d(str3, "Connecting device: " + str);
        }
        BluetoothProfileWrapper profileForDevice = getProfileForDevice(remoteDevice);
        if (profileForDevice != null) {
            if (CompanionServer.DEBUG) {
                String str4 = TAG;
                Log.d(str4, "Using profile " + profileForDevice.getName());
            }
            z2 = profileForDevice.connect(remoteDevice);
        }
        String str5 = TAG;
        Log.i(str5, "Connecting device: " + str + " result=" + z2);
        return z2;
    }

    /* JADX INFO: finally extract failed */
    public static boolean bluetoothBondDevice(Context context2, String str, boolean z) throws InterruptedException {
        if (bluetoothAdapter.isDiscovering()) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Bluetooth adapter in discovery mode. Cancelling discovery.");
            }
            bluetoothAdapter.cancelDiscovery();
        }
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Bonding device: " + str + " reusing? " + z);
        }
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        if (!(remoteDevice == null || remoteDevice.getBondState() == 10 || !CompanionServer.DEBUG)) {
            Log.d(TAG, "Device " + str + " already bonded or bonding. Trying anyway");
        }
        btDeviceMapBond.clear();
        btDeviceMapPairingKey.clear();
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        for (int i = 0; i <= 1; i++) {
            BroadcastReceiver bluetoothDeviceReceiverBond = getBluetoothDeviceReceiverBond();
            try {
                btBondLatch = new CountDownLatch(1);
                context2.registerReceiver(bluetoothDeviceReceiverBond, intentFilter);
                if (!z) {
                    remoteDevice.createBond();
                }
                btBondLatch.await(20000, TimeUnit.MILLISECONDS);
                if (btDeviceMapBond.get(str) != null) {
                    telemetry.recordHidBondEvent(remoteDevice, true);
                    context2.unregisterReceiver(bluetoothDeviceReceiverBond);
                    return true;
                } else if (btDeviceMapPairingKey.get(str) != null) {
                    Log.i(TAG, "received pairing key; aborting bond to reattempt");
                    context2.unregisterReceiver(bluetoothDeviceReceiverBond);
                    return false;
                } else {
                    Log.w(TAG, "failed to bond on attempt " + i + " of " + 1);
                    context2.unregisterReceiver(bluetoothDeviceReceiverBond);
                }
            } catch (Throwable th) {
                context2.unregisterReceiver(bluetoothDeviceReceiverBond);
                throw th;
            }
        }
        Log.e(TAG, "failed to bond and out of retries, failing.");
        telemetry.recordHidBondEvent(remoteDevice, false);
        return false;
    }

    public static Integer getPairingKeyForAddress(String str) {
        return btDeviceMapPairingKey.get(str);
    }

    public static Set<BluetoothDevice> bluetoothBondedThirdPartyDevices() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Retrieving BT bonded devices");
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        HashSet hashSet = new HashSet();
        if (bondedDevices != null) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                BluetoothProfileWrapper profileForDevice = getProfileForDevice(bluetoothDevice);
                if (profileForDevice != null) {
                    if (CompanionServer.DEBUG) {
                        String str = TAG;
                        Log.d(str, "Found bluetooth device " + bluetoothDevice.getName() + ", address " + bluetoothDevice.getAddress() + ", profile " + profileForDevice.getName());
                    }
                    hashSet.add(bluetoothDevice);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: finally extract failed */
    public static HashMap<String, BluetoothDevice> bluetoothDeviceScan(Context context2) throws InterruptedException {
        btDeviceMapScan.clear();
        btDeviceList.clear();
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.UUID");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        BroadcastReceiver bluetoothDeviceReceiverScan = getBluetoothDeviceReceiverScan();
        try {
            context2.registerReceiver(bluetoothDeviceReceiverScan, intentFilter);
            if (bluetoothAdapter.isDiscovering()) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Device is currently in discovery mode");
                }
                return new HashMap<>(btDeviceMapScan);
            }
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Beginning BT Scan");
            }
            bluetoothAdapter.startDiscovery();
            try {
                btScanLatch = new CountDownLatch(1);
                btScanLatch.await(20000, TimeUnit.MILLISECONDS);
                bluetoothAdapter.cancelDiscovery();
                telemetry.recordBluetoothScanEvent(btDeviceList.size());
                Iterator<BluetoothDevice> it = btDeviceList.iterator();
                while (it.hasNext()) {
                    BluetoothDevice next = it.next();
                    if (CompanionServer.DEBUG) {
                        String str = TAG;
                        Log.d(str, "Getting UUID for: " + next.getName());
                    }
                    if (next.getType() == 2) {
                        next.connectGatt(context2, false, new GattCallback());
                    } else {
                        next.fetchUuidsWithSdp();
                    }
                    btUuidLatch = new CountDownLatch(1);
                    btUuidPendingDevice = next.getAddress();
                    btUuidLatch.await(10000, TimeUnit.MILLISECONDS);
                }
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Done UUID discovery");
                }
                for (Map.Entry<String, BluetoothDevice> entry : btDeviceMapScan.entrySet()) {
                    BluetoothDevice value = entry.getValue();
                    if (CompanionServer.DEBUG) {
                        String str2 = TAG;
                        Log.d(str2, value.getName() + "[" + entry.getKey() + "]");
                    }
                }
                HashMap<String, BluetoothDevice> hashMap = new HashMap<>(btDeviceMapScan);
                context2.unregisterReceiver(bluetoothDeviceReceiverScan);
                return hashMap;
            } catch (Throwable th) {
                bluetoothAdapter.cancelDiscovery();
                throw th;
            }
        } finally {
            context2.unregisterReceiver(bluetoothDeviceReceiverScan);
        }
    }

    public static boolean bluetoothUnpairDevice(String str) {
        BluetoothAdapter bluetoothAdapter2 = bluetoothAdapter;
        if (bluetoothAdapter2 == null) {
            return false;
        }
        try {
            return bluetoothAdapter2.getRemoteDevice(str).removeBond();
        } catch (IllegalArgumentException e) {
            String str2 = TAG;
            Log.e(str2, "Error unpairing BT device: " + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class GattCallback extends BluetoothGattCallback {
        private boolean mFinished;

        private GattCallback() {
            this.mFinished = false;
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            if (!this.mFinished && i2 == 2 && !bluetoothGatt.discoverServices()) {
                String str = ControllerManager.TAG;
                Log.w(str, "BT Gatt service discovery failed for " + bluetoothGatt.getDevice().getName());
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (!this.mFinished) {
                BluetoothDevice device = bluetoothGatt.getDevice();
                BluetoothProfileWrapper bluetoothProfileWrapper = null;
                String str = ControllerManager.TAG;
                Log.d(str, device.getName() + " services discovered! ");
                Iterator<BluetoothGattService> it = bluetoothGatt.getServices().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BluetoothGattService next = it.next();
                    BluetoothProfileWrapper profileForUuid = ControllerManager.getProfileForUuid(next.getUuid());
                    if (profileForUuid != null) {
                        bluetoothProfileWrapper = profileForUuid;
                        break;
                    }
                    String str2 = ControllerManager.TAG;
                    Log.d(str2, "UUID: " + next.getUuid() + " failed to find a profile");
                    bluetoothProfileWrapper = profileForUuid;
                }
                if (bluetoothProfileWrapper != null) {
                    ControllerManager.btDeviceMapScan.put(device.getAddress(), device);
                    if (CompanionServer.DEBUG) {
                        String str3 = ControllerManager.TAG;
                        Log.d(str3, device.getName() + " is supported by profile " + bluetoothProfileWrapper.getName());
                    }
                }
                if (TextUtils.equals(device.getAddress(), ControllerManager.btUuidPendingDevice)) {
                    ControllerManager.btUuidLatch.countDown();
                    this.mFinished = true;
                }
            }
        }
    }
}

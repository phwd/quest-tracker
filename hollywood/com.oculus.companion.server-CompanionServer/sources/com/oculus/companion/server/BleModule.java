package com.oculus.companion.server;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.support.coordinatorlayout.R$styleable;
import android.util.Log;
import com.oculus.companion.bletransport.TransportLayer;
import com.oculus.companion.server.CompanionServer;
import com.oculus.companion.server.utils.BluetoothWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import oculus.internal.BuildCompat;

public class BleModule {
    private static int BLE_PAYLOAD = 5;
    private static final UUID CLIENT_CHARACTERISTIC_CONFIGURATION_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final UUID COMPANION_CCS_UUID = UUID.fromString("7a442881-509c-47fa-ac02-b06a37d9eb76");
    private static final UUID COMPANION_SERVICE_UUID = UUID.fromString("0000FEB8-0000-1000-8000-00805F9B34FB");
    public static final UUID COMPANION_STATUS_UUID = UUID.fromString("7a442666-509c-47fa-ac02-b06a37d9eb76");
    private static byte IS_CLAIMED = 1;
    private static byte IS_MANAGED = 4;
    private static byte IS_SAFEMODE = 2;
    private static final String TAG = "BleModule";
    private static Object btAdvLock = new Object();
    private static AtomicBoolean btAdvStarted = new AtomicBoolean(false);
    private static AtomicBoolean btEnabled = new AtomicBoolean(false);
    private static AtomicBoolean btrequestEnable = new AtomicBoolean(false);
    private static final byte[] companionNotificationCount = new byte[1];
    private static KeepAlive keepAlive;
    private static final Object messageDispatcherLock = new Object();
    private byte advertiseByte = 0;
    private AdvertiseData advertiseData;
    private AdvertiseData advertiseScanResponse;
    private AdvertiseSettings advertiseSettings;
    private BluetoothGattService bluetoothGattService;
    private BluetoothLeAdvertiser bluetoothLeAdvertiser;
    private final ArrayList<BluetoothDevice> bluetoothNotifyDevices = new ArrayList<>();
    private CompanionServer companionServer = null;
    private Context context;
    private ControllerManager controllerManager = null;
    private final AdvertiseCallback mAdvCallback = new AdvertiseCallback() {
        /* class com.oculus.companion.server.BleModule.AnonymousClass2 */

        public void onStartFailure(int i) {
            super.onStartFailure(i);
            String str = BleModule.TAG;
            Log.e(str, "Not broadcasting: " + i);
            if (i == 1) {
                Log.w(BleModule.TAG, "Data too large");
            } else if (i == 2) {
                Log.w(BleModule.TAG, "Too many advertisers");
            } else if (i == 3) {
                Log.w(BleModule.TAG, "App was already advertising");
            } else if (i == 4) {
                Log.w(BleModule.TAG, "Internal error");
            } else if (i != 5) {
                String str2 = BleModule.TAG;
                Log.wtf(str2, "Unhandled error: " + i);
            } else {
                Log.w(BleModule.TAG, "Feature unsupported");
            }
            synchronized (BleModule.btAdvLock) {
                BleModule.btAdvStarted.set(false);
                BleModule.btAdvLock.notifyAll();
            }
        }

        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            super.onStartSuccess(advertiseSettings);
            Log.d(BleModule.TAG, "Broadcasting");
            if (BleModule.this.mBluetooth.getGatt() != null) {
                for (BluetoothGattService bluetoothGattService : BleModule.this.mBluetooth.getGatt().getServices()) {
                    if (CompanionServer.DEBUG) {
                        String str = BleModule.TAG;
                        Log.d(str, "Advertising: " + bluetoothGattService.getUuid().toString());
                    }
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        if (CompanionServer.DEBUG) {
                            String str2 = BleModule.TAG;
                            Log.d(str2, "Characteristic: " + bluetoothGattCharacteristic.getUuid().toString());
                        }
                    }
                }
            }
            synchronized (BleModule.btAdvLock) {
                BleModule.btAdvStarted.set(true);
                BleModule.btAdvLock.notifyAll();
            }
        }
    };
    private BluetoothWrapper mBluetooth;
    private final ArrayList<BluetoothDevice> mBluetoothDevices = new ArrayList<>();
    private final BluetoothGattServerCallback mGattServerCallback = new BluetoothGattServerCallback() {
        /* class com.oculus.companion.server.BleModule.AnonymousClass3 */

        public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2) {
            super.onConnectionStateChange(bluetoothDevice, i, i2);
            if (i != 0) {
                synchronized (BleModule.this.mBluetoothDevices) {
                    String str = BleModule.TAG;
                    Log.e(str, "Error when connecting: " + i);
                    BleModule.this.mBluetoothDevices.remove(bluetoothDevice);
                    BleModule.this.bluetoothNotifyDevices.remove(bluetoothDevice);
                }
                if (BleModule.this.companionServer != null) {
                    CompanionServer companionServer = BleModule.this.companionServer;
                    companionServer.logEvent("bluetooth_gatt", "Error when connecting: " + i, 6, true);
                }
            } else if (BleModule.this.controllerManager != null && !BleModule.this.controllerManager.isControllerDevice(bluetoothDevice.getAddress())) {
                if (i2 == 2) {
                    String str2 = BleModule.TAG;
                    Log.i(str2, "GATT Connected to device: " + bluetoothDevice.getAddress());
                    synchronized (BleModule.this.mBluetoothDevices) {
                        if (!BleModule.this.mBluetoothDevices.contains(bluetoothDevice)) {
                            if (CompanionServer.DEBUG) {
                                String str3 = BleModule.TAG;
                                Log.d(str3, "GATT adding to list " + bluetoothDevice.getAddress());
                            }
                            BleModule.this.mBluetoothDevices.add(bluetoothDevice);
                        } else if (CompanionServer.DEBUG) {
                            String str4 = BleModule.TAG;
                            Log.d(str4, "GATT already on list " + bluetoothDevice.getAddress());
                        }
                    }
                } else if (i2 == 0) {
                    if (BleModule.this.companionServer != null) {
                        BleModule.this.companionServer.dropConnection(CompanionServer.DisconnectKey.ALL);
                        BleModule.this.companionServer.stopIfRenderingKeyboard(bluetoothDevice.getAddress());
                    }
                    synchronized (BleModule.this.mBluetoothDevices) {
                        String str5 = BleModule.TAG;
                        Log.i(str5, "GATT Disconnected from device" + bluetoothDevice.getAddress());
                        if (BleModule.this.mBluetoothDevices.contains(bluetoothDevice)) {
                            if (CompanionServer.DEBUG) {
                                Log.d(BleModule.TAG, "GATT removing from list");
                            }
                            BleModule.this.mBluetoothDevices.remove(bluetoothDevice);
                        }
                        if (BleModule.this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                            if (CompanionServer.DEBUG) {
                                Log.d(BleModule.TAG, "GATT removing from notification list");
                            }
                            BleModule.this.bluetoothNotifyDevices.remove(bluetoothDevice);
                        }
                    }
                } else if (CompanionServer.DEBUG) {
                    String str6 = BleModule.TAG;
                    Log.d(str6, "Controller device state=" + i2);
                }
            }
        }

        public void onMtuChanged(BluetoothDevice bluetoothDevice, int i) {
            if (CompanionServer.DEBUG) {
                String str = BleModule.TAG;
                Log.d(str, "received mtu change request of " + i);
            }
            String str2 = BleModule.TAG;
            Log.i(str2, "Setting MTU to: " + (i - BleModule.BLE_PAYLOAD));
            BleModule.this.transportLayer.setMtu(i - BleModule.BLE_PAYLOAD);
        }

        public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicReadRequest(bluetoothDevice, i, i2, bluetoothGattCharacteristic);
            if (i2 != 0) {
                BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, 7, i2, null);
                if (BleModule.this.companionServer != null) {
                    CompanionServer companionServer = BleModule.this.companionServer;
                    companionServer.logEvent("bluetooth_gatt", "onCharacteristicReadRequest Invalid offset" + i2, 6, true);
                    return;
                }
                return;
            }
            synchronized (bluetoothGattCharacteristic) {
                BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, 0, i2, bluetoothGattCharacteristic.getValue());
                bluetoothGattCharacteristic.notify();
            }
        }

        public void onNotificationSent(BluetoothDevice bluetoothDevice, int i) {
            super.onNotificationSent(bluetoothDevice, i);
            if (CompanionServer.DEBUG) {
                String str = BleModule.TAG;
                Log.d(str, "Notification sent. Status: " + i);
            }
        }

        public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
            String str;
            super.onCharacteristicWriteRequest(bluetoothDevice, i, bluetoothGattCharacteristic, z, z2, i2, bArr);
            int i3 = 257;
            if (bluetoothGattCharacteristic.getUuid().equals(BleModule.COMPANION_CCS_UUID)) {
                try {
                    BleModule.this.transportLayer.read(bluetoothGattCharacteristic, bArr);
                    str = "";
                    i3 = 0;
                } catch (IllegalArgumentException e) {
                    Log.e(BleModule.TAG, "IllegalArguementException here ", e);
                    str = e.getMessage();
                } catch (IllegalStateException e2) {
                    Log.e(BleModule.TAG, "IllegalStateException here", e2);
                    str = e2.getMessage();
                }
            } else {
                str = "WriteRequest to UUID: " + bluetoothGattCharacteristic.getUuid().toString();
            }
            if (z2) {
                BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, i3, 0, null);
            }
            if (i3 != 0 && BleModule.this.companionServer != null) {
                if (CompanionServer.DEBUG) {
                    Log.d(BleModule.TAG, "Resetting connection counters here!!");
                }
                BleModule.this.companionServer.logEvent("bluetooth_gatt", "onCharacteristicWriteRequest: " + str, 3, true);
                BleModule.this.transportLayer.reset(bluetoothGattCharacteristic);
            }
        }

        public void onDescriptorWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr) {
            super.onDescriptorWriteRequest(bluetoothDevice, i, bluetoothGattDescriptor, z, z2, i2, bArr);
            if (Arrays.equals(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, bArr)) {
                if (CompanionServer.DEBUG) {
                    String str = BleModule.TAG;
                    Log.d(str, "Adding device to notif list " + bluetoothDevice + " == " + bluetoothDevice.getAddress());
                }
                if (!BleModule.this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                    BleModule.this.bluetoothNotifyDevices.add(bluetoothDevice);
                }
            } else if (Arrays.equals(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE, bArr)) {
                if (CompanionServer.DEBUG) {
                    String str2 = BleModule.TAG;
                    Log.d(str2, "Removing device from notif list " + bluetoothDevice + " == " + bluetoothDevice.getAddress());
                }
                BleModule.this.bluetoothNotifyDevices.remove(bluetoothDevice);
            }
            if (z2) {
                BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, 0, 0, null);
            }
        }

        public void onDescriptorReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor) {
            byte[] bArr;
            super.onDescriptorReadRequest(bluetoothDevice, i, i2, bluetoothGattDescriptor);
            if (BleModule.this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                bArr = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
            } else {
                bArr = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
            }
            BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, 0, i2, bArr);
            if (BleModule.this.companionServer != null) {
                BleModule.this.companionServer.logEvent("bluetooth_gatt", "onDescriptorReadRequest", 3, true);
            }
        }

        public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i, boolean z) {
            super.onExecuteWrite(bluetoothDevice, i, z);
            if (CompanionServer.DEBUG) {
                String str = BleModule.TAG;
                Log.v(str, "OnExecuteWrite: requestID=" + i + ", execute=" + z);
            }
            BleModule.this.mBluetooth.getGatt().sendResponse(bluetoothDevice, i, 0, 0, null);
        }
    };
    private CompanionServer.CompanionBLEReceiver mServiceHandler;
    private final BroadcastReceiver mShutdownBroadcastReceiver = new BroadcastReceiver() {
        /* class com.oculus.companion.server.BleModule.AnonymousClass4 */

        public void onReceive(Context context, Intent intent) {
            if (CompanionServer.DEBUG) {
                Log.d(BleModule.TAG, "Notified that system is shutting down");
            }
            BleModule.this.shuttingDown = true;
        }
    };
    private MessageDispatcher messageDispatcher = null;
    public BroadcastReceiver receiver;
    private boolean shuttingDown = false;
    private TransportLayer transportLayer;

    /* access modifiers changed from: private */
    public class MessageDispatcher extends Thread {
        private UUID characteristic;
        private Handler mServiceHandler;

        MessageDispatcher(CompanionServer.CompanionBLEReceiver companionBLEReceiver, UUID uuid) {
            super("CS BLE Receiver");
            this.mServiceHandler = companionBLEReceiver;
            this.characteristic = uuid;
            if (CompanionServer.DEBUG) {
                Log.d(BleModule.TAG, "Creating new message dispatcher");
            }
        }

        public void run() {
            if (CompanionServer.DEBUG) {
                String str = BleModule.TAG;
                Log.d(str, "Starting ble receiver thread: " + Thread.currentThread().getId());
            }
            while (true) {
                try {
                    byte[] receive = BleModule.this.transportLayer.receive(this.characteristic);
                    Message obtainMessage = this.mServiceHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("BLE_MESSAGE_KEY", receive);
                    obtainMessage.setData(bundle);
                    this.mServiceHandler.sendMessage(obtainMessage);
                } catch (InterruptedException unused) {
                    if (CompanionServer.DEBUG) {
                        Log.d(BleModule.TAG, "Interrupted: Resetting ble receiver thread");
                    }
                    BleModule.this.transportLayer.reset(this.characteristic);
                } catch (IllegalArgumentException e) {
                    String str2 = BleModule.TAG;
                    Log.e(str2, "Received IllegalArguement " + e);
                    if (BleModule.this.companionServer != null) {
                        CompanionServer companionServer = BleModule.this.companionServer;
                        companionServer.logEvent("bluetooth_gatt", "Received IllegalArguement run()" + e.getMessage(), 6, true);
                    }
                }
            }
        }
    }

    public BleModule initialize(CompanionServer companionServer2) throws Exception {
        this.companionServer = companionServer2;
        this.context = companionServer2.getApplicationContext();
        AddReceiver();
        try {
            this.mBluetooth = new BluetoothWrapper(this.context);
            setupGattService();
            this.context.registerReceiver(this.mShutdownBroadcastReceiver, new IntentFilter("android.intent.action.ACTION_SHUTDOWN"));
            keepAlive = new KeepAlive("BT Keep Alive Thread");
            keepAlive.start();
            return this;
        } catch (Exception unused) {
            Log.e(TAG, "Could not connect to BLE service");
            throw new Exception("BLE Service not available");
        }
    }

    public void asyncInit() {
        enableBluetooth();
        setupGattService();
    }

    public void setControllerManager(ControllerManager controllerManager2) {
        this.controllerManager = controllerManager2;
    }

    private void setupGattService() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Setting up Gatt service");
        }
        this.bluetoothGattService = new BluetoothGattService(COMPANION_SERVICE_UUID, 0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(COMPANION_CCS_UUID, 10, 17);
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(COMPANION_STATUS_UUID, 18, 1);
        bluetoothGattCharacteristic.addDescriptor(getClientCharacteristicConfigurationDescriptor());
        bluetoothGattCharacteristic2.addDescriptor(getClientCharacteristicConfigurationDescriptor());
        this.bluetoothGattService.addCharacteristic(bluetoothGattCharacteristic);
        this.bluetoothGattService.addCharacteristic(bluetoothGattCharacteristic2);
        this.transportLayer = new TransportLayer();
        this.transportLayer.attachCharacteristic(bluetoothGattCharacteristic);
        this.transportLayer.attachCharacteristic(bluetoothGattCharacteristic2);
    }

    public void send(byte[] bArr) throws InterruptedException {
        this.transportLayer.send(COMPANION_CCS_UUID, bArr);
    }

    public void dropFirst() {
        this.transportLayer.dropFirst(COMPANION_CCS_UUID);
    }

    public void resetReceiver() {
        synchronized (messageDispatcherLock) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "resetReceiver()");
            }
            if (this.messageDispatcher != null) {
                this.messageDispatcher.interrupt();
            }
        }
    }

    public void startReceiver(CompanionServer.CompanionBLEReceiver companionBLEReceiver) {
        synchronized (messageDispatcherLock) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "startReceiver()");
            }
            this.mServiceHandler = companionBLEReceiver;
            if (this.messageDispatcher == null) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Creating new MessageDispatcher");
                }
                this.messageDispatcher = new MessageDispatcher(companionBLEReceiver, COMPANION_CCS_UUID);
                this.messageDispatcher.start();
            }
        }
    }

    public void stopReceiver() {
        synchronized (messageDispatcherLock) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "stopReceiver()");
            }
            if (this.messageDispatcher != null) {
                this.messageDispatcher.interrupt();
            }
            this.messageDispatcher = null;
        }
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mBluetoothDevices) {
            z = !this.mBluetoothDevices.isEmpty();
        }
        return z;
    }

    public boolean hasNotify() {
        boolean z;
        synchronized (this.mBluetoothDevices) {
            z = !this.bluetoothNotifyDevices.isEmpty();
        }
        return z;
    }

    public String getMostRecentBluetoothDeviceAddress() {
        if (this.mBluetoothDevices.isEmpty()) {
            return null;
        }
        ArrayList<BluetoothDevice> arrayList = this.mBluetoothDevices;
        return arrayList.get(arrayList.size() - 1).getAddress();
    }

    private void AddReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.receiver = new BroadcastReceiver() {
            /* class com.oculus.companion.server.BleModule.AnonymousClass1 */

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (CompanionServer.DEBUG) {
                    Log.d(BleModule.TAG, "Broadcast Receiver:" + action);
                }
                char c = 65535;
                switch (action.hashCode()) {
                    case -1530327060:
                        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1492944353:
                        if (action.equals("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -301431627:
                        if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -223687943:
                        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1123270207:
                        if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1167529923:
                        if (action.equals("android.bluetooth.device.action.FOUND")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1821585647:
                        if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 2116862345:
                        if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (CompanionServer.DEBUG) {
                            Log.d(BleModule.TAG, "Action bond state changed");
                        }
                        switch (bluetoothDevice.getBondState()) {
                            case 10:
                                if (CompanionServer.DEBUG) {
                                    Log.d(BleModule.TAG, "Bond_NONE");
                                    return;
                                }
                                return;
                            case 11:
                                if (CompanionServer.DEBUG) {
                                    Log.d(BleModule.TAG, "Bonding");
                                    return;
                                }
                                return;
                            case 12:
                                if (CompanionServer.DEBUG) {
                                    Log.d(BleModule.TAG, "Bonded");
                                    return;
                                }
                                return;
                            default:
                                Log.w(BleModule.TAG, "Weird default, " + bluetoothDevice.getBondState());
                                return;
                        }
                    case 1:
                        BleModule.this.onDeviceConnected((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                        return;
                    case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                        BleModule.this.onDeviceDisconnected((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                        return;
                    case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                        if (CompanionServer.DEBUG) {
                            Log.d(BleModule.TAG, "Action ACL Disconnect requested");
                            return;
                        }
                        return;
                    case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                        if (CompanionServer.DEBUG) {
                            Log.d(BleModule.TAG, "Action found");
                            return;
                        }
                        return;
                    case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                        if (CompanionServer.DEBUG) {
                            Log.d(BleModule.TAG, "Pairing Request");
                            return;
                        }
                        return;
                    case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                        int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
                        Log.i(BleModule.TAG, "BluetoothAdapter: Device[" + ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress() + "] Connection State changed from " + intExtra2 + " to " + intExtra);
                        return;
                    case 7:
                        int intExtra3 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                        int intExtra4 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 10);
                        Log.i(BleModule.TAG, "BluetoothAdapter: State changed from " + intExtra4 + " to " + intExtra3);
                        if (intExtra3 == 12) {
                            BleModule.btEnabled.set(true);
                        } else {
                            synchronized (BleModule.this.mBluetoothDevices) {
                                BleModule.this.mBluetoothDevices.clear();
                                BleModule.this.bluetoothNotifyDevices.clear();
                            }
                            BleModule.btEnabled.set(false);
                        }
                        synchronized (BleModule.btEnabled) {
                            BleModule.btEnabled.notifyAll();
                        }
                        break;
                }
                if (CompanionServer.DEBUG) {
                    Log.d(BleModule.TAG, action);
                }
            }
        };
        this.context.registerReceiver(this.receiver, intentFilter);
    }

    /* access modifiers changed from: package-private */
    public void onDeviceDisconnected(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        Log.i(str, "Action ACL disconnected. Device:" + bluetoothDevice.getAddress());
        ControllerManager controllerManager2 = this.controllerManager;
        if (controllerManager2 == null || !controllerManager2.isControllerDevice(bluetoothDevice.getAddress())) {
            synchronized (this.mBluetoothDevices) {
                if (this.mBluetoothDevices.contains(bluetoothDevice)) {
                    if (CompanionServer.DEBUG) {
                        String str2 = TAG;
                        Log.d(str2, "Removing device: " + bluetoothDevice.getAddress());
                    }
                    this.mBluetoothDevices.remove(bluetoothDevice);
                }
                if (this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                    if (CompanionServer.DEBUG) {
                        String str3 = TAG;
                        Log.d(str3, "Removing device from notification: " + bluetoothDevice.getAddress());
                    }
                    this.bluetoothNotifyDevices.remove(bluetoothDevice);
                }
            }
            return;
        }
        this.controllerManager.notifyDisconnected(bluetoothDevice.getAddress());
    }

    /* access modifiers changed from: package-private */
    public void onDeviceConnected(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        Log.i(str, "Action ACL connected. Device:" + bluetoothDevice.getAddress());
        ControllerManager controllerManager2 = this.controllerManager;
        if (controllerManager2 == null || !controllerManager2.isControllerDevice(bluetoothDevice.getAddress())) {
            synchronized (this.mBluetoothDevices) {
                if (!this.mBluetoothDevices.contains(bluetoothDevice)) {
                    if (CompanionServer.DEBUG) {
                        String str2 = TAG;
                        Log.d(str2, "Adding device to mBluetoothDevices: " + bluetoothDevice.getAddress());
                    }
                    this.mBluetoothDevices.add(bluetoothDevice);
                } else {
                    Log.w(TAG, "Device already added");
                }
            }
            return;
        }
        this.controllerManager.notifyConnected(bluetoothDevice.getAddress(), bluetoothDevice);
    }

    public void disconnectLastDevices() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "disconnectLastDevices()");
        }
        synchronized (this.mBluetoothDevices) {
            if (this.mBluetoothDevices.size() == 1) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "disconnecting last device index 0");
                }
                this.mBluetooth.getGatt().cancelConnection(this.mBluetoothDevices.get(0));
                this.mBluetoothDevices.clear();
                this.bluetoothNotifyDevices.clear();
            } else if (this.mBluetoothDevices.size() > 1) {
                for (int i = 1; i < this.mBluetoothDevices.size(); i++) {
                    if (CompanionServer.DEBUG) {
                        String str = TAG;
                        Log.d(str, "disconnecting last device index " + i);
                    }
                    this.mBluetooth.getGatt().cancelConnection(this.mBluetoothDevices.get(i));
                }
                BluetoothDevice bluetoothDevice = this.mBluetoothDevices.get(0);
                this.mBluetoothDevices.clear();
                this.mBluetoothDevices.add(bluetoothDevice);
                if (this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                    this.bluetoothNotifyDevices.clear();
                    this.bluetoothNotifyDevices.add(bluetoothDevice);
                } else {
                    this.bluetoothNotifyDevices.clear();
                }
            }
        }
    }

    public void disconnectAllKeepLast() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "disconnectAllKeepLast()");
        }
        synchronized (this.mBluetoothDevices) {
            if (this.mBluetoothDevices.size() > 1) {
                for (int i = 0; i < this.mBluetoothDevices.size() - 1; i++) {
                    if (CompanionServer.DEBUG) {
                        String str = TAG;
                        Log.d(str, "disconnecting device index " + i);
                    }
                    this.mBluetooth.getGatt().cancelConnection(this.mBluetoothDevices.get(i));
                }
                BluetoothDevice bluetoothDevice = this.mBluetoothDevices.get(this.mBluetoothDevices.size() - 1);
                this.mBluetoothDevices.clear();
                this.mBluetoothDevices.add(bluetoothDevice);
                if (this.bluetoothNotifyDevices.contains(bluetoothDevice)) {
                    this.bluetoothNotifyDevices.clear();
                    this.bluetoothNotifyDevices.add(bluetoothDevice);
                } else {
                    this.bluetoothNotifyDevices.clear();
                }
            }
        }
    }

    public void disconnectAll() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "disconnectAll()");
        }
        synchronized (this.mBluetoothDevices) {
            for (int i = 0; i < this.mBluetoothDevices.size(); i++) {
                if (CompanionServer.DEBUG) {
                    String str = TAG;
                    Log.d(str, "disconnecting device index " + i);
                }
                this.mBluetooth.getGatt().cancelConnection(this.mBluetoothDevices.get(i));
            }
            this.mBluetoothDevices.clear();
            this.bluetoothNotifyDevices.clear();
        }
    }

    public boolean isBleEnabled() {
        return this.mBluetooth.isEnabled();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void enableBluetooth() {
        btEnabled.set(isBleEnabled());
        if (!btEnabled.get() && !btrequestEnable.get()) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Attempting to enable BT");
            }
            btrequestEnable.set(true);
            try {
                synchronized (btEnabled) {
                    while (!btEnabled.get()) {
                        this.mBluetooth.enable();
                        btEnabled.wait(10000);
                    }
                }
            } catch (InterruptedException unused) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "Wait for BT enable interrupted");
                }
            }
            if (btEnabled.get()) {
                btrequestEnable.set(false);
            } else {
                btrequestEnable.set(false);
                throw new RuntimeException("Bluetooth is not enabled");
            }
        }
    }

    /* access modifiers changed from: private */
    public class KeepAlive extends Thread {
        public KeepAlive(String str) {
            super(str);
        }

        public void run() {
            if (CompanionServer.DEBUG) {
                Log.d(BleModule.TAG, "Starting Keep Alive Thread");
            }
            while (true) {
                try {
                    synchronized (BleModule.btEnabled) {
                        BleModule.btEnabled.wait();
                    }
                    if (CompanionServer.DEBUG) {
                        String str = BleModule.TAG;
                        Log.d(str, "BT Keep Alive. Enabled=" + BleModule.btEnabled.get() + " shuttingDown= " + BleModule.this.shuttingDown);
                    }
                    if (!BleModule.btEnabled.get() && !BleModule.this.shuttingDown) {
                        BleModule.this.enableBluetooth();
                        BleModule.this.restartAdvertising();
                    }
                } catch (InterruptedException unused) {
                    Log.e(BleModule.TAG, "BT Keep Alive Interrupted");
                    return;
                }
            }
        }
    }

    public void setClaimed(boolean z) {
        if (z) {
            this.advertiseByte = (byte) (this.advertiseByte | IS_CLAIMED);
        } else {
            this.advertiseByte = (byte) (this.advertiseByte & (~IS_CLAIMED));
        }
        restartAdvertising();
    }

    public void setManaged(boolean z) {
        if (z) {
            this.advertiseByte = (byte) (this.advertiseByte | IS_MANAGED);
        } else {
            this.advertiseByte = (byte) (this.advertiseByte & (~IS_MANAGED));
        }
        restartAdvertising();
    }

    private byte[] buildAdvData() {
        byte[] bArr = new byte[2];
        if (CompanionServer.DEBUG) {
            String str = TAG;
            Log.d(str, "Product=" + Build.PRODUCT.toLowerCase());
        }
        int integer = this.context.getResources().getInteger(R.integer.adv_byte_0);
        if (CompanionServer.DEBUG) {
            String str2 = TAG;
            Log.d(str2, "Resource = " + integer);
        }
        bArr[0] = (byte) integer;
        bArr[1] = this.advertiseByte;
        if (CompanionServer.DEBUG) {
            String str3 = TAG;
            Log.d(str3, "Advertising Data = " + ((int) bArr[0]) + " + " + ((int) bArr[1]));
        }
        return bArr;
    }

    public boolean startAdvertising() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Start Advertising");
        }
        if (this.mBluetooth.getGatt() != null) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Closing previous instance of gatt server");
            }
            this.mBluetooth.getGatt().close();
            this.mBluetooth.clearGatt();
        }
        this.mBluetooth.openGattServer(this.context, this.mGattServerCallback);
        if (this.mBluetooth.getGatt() == null) {
            Log.e(TAG, "Could not start GATT server!!!");
            return false;
        }
        this.bluetoothGattService.getCharacteristic(COMPANION_CCS_UUID).setValue("----");
        BluetoothGattCharacteristic characteristic = this.bluetoothGattService.getCharacteristic(COMPANION_STATUS_UUID);
        synchronized (companionNotificationCount) {
            companionNotificationCount[0] = 0;
        }
        characteristic.setValue(companionNotificationCount);
        this.mBluetooth.getGatt().addService(this.bluetoothGattService);
        ParcelUuid parcelUuid = new ParcelUuid(COMPANION_SERVICE_UUID);
        this.advertiseSettings = new AdvertiseSettings.Builder().setAdvertiseMode(1).setTxPowerLevel(2).setConnectable(true).build();
        this.advertiseData = new AdvertiseData.Builder().addServiceUuid(parcelUuid).build();
        this.advertiseScanResponse = new AdvertiseData.Builder().addManufacturerData(1422, new BuildCompat().getSerial().getBytes()).addServiceData(parcelUuid, buildAdvData()).build();
        if (this.mBluetooth.isMultipleAdvertisementSupported()) {
            this.bluetoothLeAdvertiser = this.mBluetooth.getBluetoothLeAdvertiser();
            if (this.bluetoothLeAdvertiser != null) {
                try {
                    synchronized (btAdvLock) {
                        btAdvStarted.set(false);
                        this.bluetoothLeAdvertiser.startAdvertising(this.advertiseSettings, this.advertiseData, this.advertiseScanResponse, this.mAdvCallback);
                        btAdvLock.wait(1000);
                    }
                } catch (InterruptedException unused) {
                    Log.e(TAG, "Interrupted while starting advertising");
                }
                return btAdvStarted.get();
            }
            throw new RuntimeException("BluetoothLeAdvertiser is null!");
        }
        Log.w(TAG, "no multiple advertiser support");
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean restartAdvertising() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Restarting Advertising");
        }
        if (btAdvStarted.get()) {
            this.bluetoothLeAdvertiser.stopAdvertising(this.mAdvCallback);
            this.mBluetooth.reinit(this.context);
            setupGattService();
            stopReceiver();
            startReceiver(this.mServiceHandler);
            return startAdvertising();
        } else if (!CompanionServer.DEBUG) {
            return false;
        } else {
            Log.d(TAG, "Advertising not started yet.");
            return false;
        }
    }

    public boolean stopAdvertising() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Stopping Advertising");
        }
        if (btAdvStarted.get()) {
            this.bluetoothLeAdvertiser.stopAdvertising(this.mAdvCallback);
            this.mBluetooth.reinit(this.context);
            setupGattService();
            stopReceiver();
            startReceiver(this.mServiceHandler);
            return true;
        } else if (!CompanionServer.DEBUG) {
            return false;
        } else {
            Log.d(TAG, "Advertising did not stop because its not running.");
            return false;
        }
    }

    public void notifyStatusChange() {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService bluetoothGattService2 = this.bluetoothGattService;
        if (bluetoothGattService2 != null && (characteristic = bluetoothGattService2.getCharacteristic(COMPANION_STATUS_UUID)) != null) {
            synchronized (companionNotificationCount) {
                byte[] bArr = companionNotificationCount;
                bArr[0] = (byte) (bArr[0] + 1);
            }
            characteristic.setValue(companionNotificationCount);
            Iterator<BluetoothDevice> it = this.bluetoothNotifyDevices.iterator();
            while (it.hasNext()) {
                BluetoothDevice next = it.next();
                String str = TAG;
                Log.i(str, "Notifying device: " + next.toString());
                try {
                    this.mBluetooth.getGatt().notifyCharacteristicChanged(next, characteristic, false);
                } catch (RuntimeException unused) {
                    String str2 = TAG;
                    Log.e(str2, "Exception while notifying device (possibly disconnected): " + next.getAddress());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        synchronized (btAdvLock) {
            if (this.bluetoothLeAdvertiser != null) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "destroy: stop advertising");
                }
                this.bluetoothLeAdvertiser.stopAdvertising(this.mAdvCallback);
                this.bluetoothLeAdvertiser = null;
            }
            if (this.mBluetooth.getGatt() != null) {
                if (CompanionServer.DEBUG) {
                    Log.d(TAG, "destroy: stop services");
                }
                this.mBluetooth.getGatt().clearServices();
                this.mBluetooth.getGatt().close();
                this.mBluetooth.clearGatt();
            }
        }
    }

    private static BluetoothGattDescriptor getClientCharacteristicConfigurationDescriptor() {
        return new BluetoothGattDescriptor(CLIENT_CHARACTERISTIC_CONFIGURATION_UUID, 17);
    }
}

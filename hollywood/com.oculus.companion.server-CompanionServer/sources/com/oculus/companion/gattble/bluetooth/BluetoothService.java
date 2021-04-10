package com.oculus.companion.gattble.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.companion.gattble.phonenotifications.IOSNotificationServiceHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BluetoothService implements BluetoothServiceDelegate {
    private static final String TAG = "BluetoothService";
    private BroadcastReceiver bondBroadcastReceiver = new BroadcastReceiver() {
        /* class com.oculus.companion.gattble.bluetooth.BluetoothService.AnonymousClass3 */

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x004c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r4, android.content.Intent r5) {
            /*
            // Method dump skipped, instructions count: 130
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.gattble.bluetooth.BluetoothService.AnonymousClass3.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattOperationThread mBluetoothGattOperationThread = new BluetoothGattOperationThread();
    private final BlockingQueue<BluetoothGattOperation> mBluetoothGattOperations = new LinkedBlockingQueue();
    private final Context mContext;
    private String mDeviceAddress;
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        /* class com.oculus.companion.gattble.bluetooth.BluetoothService.AnonymousClass1 */

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                String str = BluetoothService.TAG;
                Log.e(str, "onConnectionStateChange status is not GATT_SUCCESS, it is: " + i);
                BluetoothService.this.disconnect();
            }
            if (i2 == 0) {
                Log.d(BluetoothService.TAG, "onConnectionStateChange: Disconnected");
                BluetoothService.this.disconnect();
            } else if (i2 == 1) {
                Log.d(BluetoothService.TAG, "onConnectionStateChange: Connecting");
            } else if (i2 == 2) {
                Log.d(BluetoothService.TAG, "onConnectionStateChange: Connected");
                BluetoothDevice remoteDevice = BluetoothService.this.mBluetoothAdapter.getRemoteDevice(BluetoothService.this.mDeviceAddress);
                int bondState = remoteDevice.getBondState();
                if (bondState == 10) {
                    String str2 = BluetoothService.TAG;
                    Log.i(str2, "BOND_NONE, initiating bond with device: " + remoteDevice.getAddress());
                    remoteDevice.createBond(2);
                    BluetoothService.this.addBondBroadcastReceiver();
                } else if (bondState == 12) {
                    Log.i(BluetoothService.TAG, "devices already bonded, discovering services");
                    BluetoothService.this.mBluetoothGatt.discoverServices();
                }
            } else if (i2 != 3) {
                String str3 = BluetoothService.TAG;
                Log.e(str3, "Unrecognized new connection state: " + i2);
                BluetoothService.this.disconnect();
            } else {
                Log.d(BluetoothService.TAG, "onConnectionStateChange: Disconnecting");
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i != 0) {
                String str = BluetoothService.TAG;
                Log.e(str, "onServicesDiscovered failed; status: " + i);
                return;
            }
            BluetoothService.this.prepareServiceHandlers(bluetoothGatt);
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BluetoothService.this.mBluetoothGattOperationThread.next();
            BluetoothServiceHandler serviceHandler = BluetoothService.this.getServiceHandler((BluetoothService) bluetoothGattCharacteristic);
            if (serviceHandler != null) {
                serviceHandler.onCharacteristicRead(bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BluetoothService.this.mBluetoothGattOperationThread.next();
            BluetoothServiceHandler serviceHandler = BluetoothService.this.getServiceHandler((BluetoothService) bluetoothGattCharacteristic);
            if (serviceHandler != null) {
                serviceHandler.onCharacteristicWrite(bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BluetoothServiceHandler serviceHandler = BluetoothService.this.getServiceHandler((BluetoothService) bluetoothGattCharacteristic);
            if (serviceHandler != null) {
                serviceHandler.onCharacteristicChanged(bluetoothGattCharacteristic);
                return;
            }
            String str = BluetoothService.TAG;
            Log.d(str, "ServiceHandler for characteristic: " + bluetoothGattCharacteristic.toString() + " is null");
        }

        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BluetoothService.this.mBluetoothGattOperationThread.next();
            BluetoothServiceHandler serviceHandler = BluetoothService.this.getServiceHandler((BluetoothService) bluetoothGattDescriptor);
            if (serviceHandler != null) {
                serviceHandler.onDescriptorRead(bluetoothGattDescriptor, i);
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BluetoothService.this.mBluetoothGattOperationThread.next();
            BluetoothServiceHandler serviceHandler = BluetoothService.this.getServiceHandler((BluetoothService) bluetoothGattDescriptor);
            if (serviceHandler != null) {
                serviceHandler.onDescriptorWrite(bluetoothGattDescriptor, i);
            }
        }
    };
    private List<BroadcastReceiver> mReceivers = new ArrayList();
    private final Map<UUID, BluetoothServiceHandler> mServiceHandlers = new HashMap();

    public BluetoothService(Context context) {
        Log.d(TAG, "BluetoothService() constructor");
        this.mContext = context;
        BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        if (bluetoothManager == null) {
            Log.e(TAG, "Unable to initialize BluetoothManager");
            return;
        }
        this.mBluetoothAdapter = bluetoothManager.getAdapter();
        if (this.mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to initialize BluetoothAdapter");
        }
        addConnectionBroadcastReceiver();
    }

    private void addReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
        this.mReceivers.add(broadcastReceiver);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeReceiver(BroadcastReceiver broadcastReceiver) {
        this.mContext.unregisterReceiver(broadcastReceiver);
        return this.mReceivers.remove(broadcastReceiver);
    }

    private boolean clearReceivers() {
        for (BroadcastReceiver broadcastReceiver : this.mReceivers) {
            if (!removeReceiver(broadcastReceiver)) {
                return false;
            }
        }
        return true;
    }

    private void addConnectionBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        addReceiver(new BroadcastReceiver() {
            /* class com.oculus.companion.gattble.bluetooth.BluetoothService.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (((action.hashCode() == -301431627 && action.equals("android.bluetooth.device.action.ACL_CONNECTED")) ? (char) 0 : 65535) != 0) {
                    String str = BluetoothService.TAG;
                    Log.d(str, "Unhandled action: " + action);
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice.getBondState() != 12) {
                    return;
                }
                if (bluetoothDevice.getType() == 3 || bluetoothDevice.getType() == 2) {
                    String str2 = BluetoothService.TAG;
                    Log.d(str2, "Newly connected device is already bonded: " + bluetoothDevice.getAddress() + ". Re-establishing gatt connection");
                    BluetoothService.this.connect(bluetoothDevice.getAddress());
                    return;
                }
                String str3 = BluetoothService.TAG;
                Log.d(str3, "Newly connected device: " + bluetoothDevice.getAddress() + " does not support BLE connections. Doing nothing.");
            }
        }, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleActionPairingRequest(Intent intent) {
        try {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", 0);
            String str = TAG;
            Log.d(str, "Setting device pin and confirming with PIN value: " + intExtra);
            bluetoothDevice.setPin(("" + intExtra).getBytes(StandardCharsets.UTF_8));
            bluetoothDevice.setPairingConfirmation(true);
        } catch (Exception unused) {
            Log.e(TAG, "Error while auto-pairing");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBondBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        addReceiver(this.bondBroadcastReceiver, intentFilter);
    }

    public void destroy() {
        disconnect();
        clearReceivers();
    }

    public boolean connect(String str) {
        if (!str.equals(this.mDeviceAddress) || this.mBluetoothGatt == null) {
            BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
            if (remoteDevice == null) {
                Log.e(TAG, String.format(Locale.US, "Unable to connect to %s: Device not found", str));
                return false;
            }
            openGattConnection(remoteDevice);
            return true;
        }
        Log.v(TAG, "Attempting to connect with an existing valid connection. No-op.");
        return false;
    }

    private void openGattConnection(BluetoothDevice bluetoothDevice) {
        if (this.mBluetoothGatt != null || !this.mServiceHandlers.isEmpty() || !this.mBluetoothGattOperations.isEmpty() || this.mBluetoothGattOperationThread.isAlive()) {
            Log.w(TAG, "BluetoothService state not ready for new GATT connection. Did something unexpected happen to the GATT connection? Verify that edge cases where the GATT connection is lost properly clean up the instance state.");
            debugPrintStatus();
            disconnect();
        }
        this.mDeviceAddress = bluetoothDevice.getAddress();
        this.mBluetoothGatt = bluetoothDevice.connectGatt(this.mContext, false, this.mGattCallback, 2);
        this.mBluetoothGattOperationThread = new BluetoothGattOperationThread();
        this.mBluetoothGattOperationThread.start();
    }

    public void disconnect() {
        for (BluetoothServiceHandler bluetoothServiceHandler : this.mServiceHandlers.values()) {
            bluetoothServiceHandler.onDisconnect();
        }
        this.mServiceHandlers.clear();
        this.mBluetoothGattOperations.clear();
        if (this.mBluetoothGattOperationThread.isAlive()) {
            this.mBluetoothGattOperationThread.interrupt();
        }
        if (this.mBluetoothGatt != null) {
            Log.v(TAG, "Closing GATT Connection");
            this.mBluetoothGatt.disconnect();
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
            return;
        }
        Log.v(TAG, "Attempted to close a GATT Connection that is already closed. No-op.");
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothServiceDelegate
    public void addBluetoothGattOperation(BluetoothGattOperation bluetoothGattOperation) {
        this.mBluetoothGattOperations.offer(bluetoothGattOperation);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void prepareServiceHandlers(BluetoothGatt bluetoothGatt) {
        for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
            UUID uuid = bluetoothGattService.getUuid();
            IOSNotificationServiceHandler iOSNotificationServiceHandler = null;
            if (uuid.equals(IOSNotificationServiceHandler.UUID_SERVICE)) {
                iOSNotificationServiceHandler = new IOSNotificationServiceHandler(this, this.mContext);
            }
            if (iOSNotificationServiceHandler != null) {
                iOSNotificationServiceHandler.subscribeToCharacteristicsIfAny();
                this.mServiceHandlers.put(uuid, iOSNotificationServiceHandler);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BluetoothServiceHandler getServiceHandler(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.mServiceHandlers.get(bluetoothGattCharacteristic.getService().getUuid());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BluetoothServiceHandler getServiceHandler(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return getServiceHandler(bluetoothGattDescriptor.getCharacteristic());
    }

    private void debugPrintStatus() {
        Object obj;
        String str = TAG;
        Object[] objArr = new Object[5];
        Map<UUID, BluetoothServiceHandler> map = this.mServiceHandlers;
        String str2 = "null";
        objArr[0] = map == null ? str2 : map.toString();
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        objArr[1] = bluetoothGatt == null ? str2 : bluetoothGatt.toString();
        objArr[2] = this.mDeviceAddress;
        BluetoothGattOperationThread bluetoothGattOperationThread = this.mBluetoothGattOperationThread;
        if (bluetoothGattOperationThread == null) {
            obj = str2;
        } else {
            obj = Boolean.valueOf(bluetoothGattOperationThread.isAlive());
        }
        objArr[3] = obj;
        BlockingQueue<BluetoothGattOperation> blockingQueue = this.mBluetoothGattOperations;
        if (blockingQueue != null) {
            str2 = blockingQueue.toString();
        }
        objArr[4] = str2;
        Log.v(str, String.format("BluetoothService status:\nServiceHandlers: %s,\nmBluetoothGatt: %s,\nmDeviceAddress: %s,\nmBluetoothGattOperationThread.isAlive: %b,\nmBluetoothGattOperations: %s", objArr));
    }

    /* access modifiers changed from: private */
    public class BluetoothGattOperationThread extends Thread {
        private final Object lock;

        private BluetoothGattOperationThread() {
            this.lock = new Object();
        }

        public void run() {
            while (true) {
                try {
                    synchronized (this.lock) {
                        if (((BluetoothGattOperation) BluetoothService.this.mBluetoothGattOperations.take()).run(BluetoothService.this.mBluetoothGatt)) {
                            this.lock.wait();
                        }
                    }
                } catch (InterruptedException unused) {
                    Log.d(BluetoothService.TAG, "Killing GattOperationThread upon interruption.");
                    return;
                }
            }
        }

        public void next() {
            synchronized (this.lock) {
                this.lock.notify();
            }
        }
    }
}

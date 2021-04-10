package com.oculus.companion.bletransport;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.HashMap;
import java.util.UUID;

public class TransportLayer {
    protected final HashMap<BluetoothGattCharacteristic, AsyncBLETransport> channelMap = new HashMap<>();
    protected final HashMap<UUID, BluetoothGattCharacteristic> uuidMap = new HashMap<>();

    public void attachCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.channelMap.put(bluetoothGattCharacteristic, new AsyncBLETransport(bluetoothGattCharacteristic));
        this.uuidMap.put(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic);
    }

    public void read(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        AsyncBLETransport asyncBLETransport = this.channelMap.get(bluetoothGattCharacteristic);
        if (asyncBLETransport != null) {
            asyncBLETransport.read(bArr);
            return;
        }
        throw new IllegalArgumentException("Invalid channel!!");
    }

    public byte[] receive(UUID uuid) throws InterruptedException {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.uuidMap.get(uuid);
        return bluetoothGattCharacteristic != null ? receive(bluetoothGattCharacteristic) : new byte[0];
    }

    public byte[] receive(BluetoothGattCharacteristic bluetoothGattCharacteristic) throws InterruptedException {
        AsyncBLETransport asyncBLETransport = this.channelMap.get(bluetoothGattCharacteristic);
        if (asyncBLETransport != null) {
            return asyncBLETransport.receive();
        }
        throw new IllegalArgumentException("Invalid channel!!");
    }

    public void send(UUID uuid, byte[] bArr) throws InterruptedException {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.uuidMap.get(uuid);
        if (bluetoothGattCharacteristic != null) {
            send(bluetoothGattCharacteristic, bArr);
            return;
        }
        throw new IllegalArgumentException("Invalid characteristic!!");
    }

    public void send(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws InterruptedException {
        AsyncBLETransport asyncBLETransport = this.channelMap.get(bluetoothGattCharacteristic);
        if (asyncBLETransport != null) {
            asyncBLETransport.send(bArr);
            return;
        }
        throw new IllegalArgumentException("Invalid characteristic!!");
    }

    public void dropFirst(UUID uuid) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.uuidMap.get(uuid);
        if (bluetoothGattCharacteristic != null) {
            dropFirst(bluetoothGattCharacteristic);
            return;
        }
        throw new IllegalArgumentException("Invalid characteristic!!");
    }

    public void dropFirst(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        AsyncBLETransport asyncBLETransport = this.channelMap.get(bluetoothGattCharacteristic);
        if (asyncBLETransport != null) {
            asyncBLETransport.dropFirst();
            return;
        }
        throw new IllegalArgumentException("Invalid characteristic!!");
    }

    public void setMtu(int i) {
        for (AsyncBLETransport asyncBLETransport : this.channelMap.values()) {
            asyncBLETransport.setMtu(i);
        }
    }

    public void reset(UUID uuid) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.uuidMap.get(uuid);
        if (bluetoothGattCharacteristic != null) {
            reset(bluetoothGattCharacteristic);
            return;
        }
        throw new IllegalArgumentException("Invalid characteristic!!");
    }

    public void reset(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        AsyncBLETransport asyncBLETransport = this.channelMap.get(bluetoothGattCharacteristic);
        if (asyncBLETransport != null) {
            asyncBLETransport.reset();
            return;
        }
        throw new IllegalArgumentException("Invalid channel!!");
    }
}

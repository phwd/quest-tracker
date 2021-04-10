package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattDescriptorWrapper;

/* renamed from: tz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5008tz1 extends BluetoothGattCallback {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4498qz1 f11379a;
    public final Wrappers$BluetoothDeviceWrapper b;

    public C5008tz1(AbstractC4498qz1 qz1, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.f11379a = qz1;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        AbstractC1220Ua0.d("Bluetooth", "wrapper onCharacteristicChanged.", new Object[0]);
        AbstractC4498qz1 qz1 = this.f11379a;
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic);
        C0407Gq gq = (C0407Gq) qz1;
        Objects.requireNonNull(gq);
        AbstractC1220Ua0.d("Bluetooth", "device onCharacteristicChanged.", new Object[0]);
        byte[] value = wrappers$BluetoothGattCharacteristicWrapper.f10951a.getValue();
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0102Bq bq = new RunnableC0102Bq(gq, wrappers$BluetoothGattCharacteristicWrapper, value);
        Objects.requireNonNull(a2);
        ThreadUtils.g(bq);
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0163Cq cq = new RunnableC0163Cq(gq, (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic), i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(cq);
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0224Dq dq = new RunnableC0224Dq(gq, (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic), i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(dq);
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i2 == 2 ? "Connected" : "Disconnected";
        AbstractC1220Ua0.d("Bluetooth", "onConnectionStateChange status:%d newState:%s", objArr);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC5999zq zqVar = new RunnableC5999zq(gq, i2, i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(zqVar);
    }

    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0285Eq eq = new RunnableC0285Eq(gq, (Wrappers$BluetoothGattDescriptorWrapper) this.b.c.get(bluetoothGattDescriptor), i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(eq);
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0346Fq fq = new RunnableC0346Fq(gq, (Wrappers$BluetoothGattDescriptorWrapper) this.b.c.get(bluetoothGattDescriptor), i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(fq);
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        C0407Gq gq = (C0407Gq) this.f11379a;
        Objects.requireNonNull(gq);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? "OK" : "Error";
        AbstractC1220Ua0.d("Bluetooth", "onServicesDiscovered status:%d==%s", objArr);
        C5518wz1 a2 = C5518wz1.a();
        RunnableC0041Aq aq = new RunnableC0041Aq(gq, i);
        Objects.requireNonNull(a2);
        ThreadUtils.g(aq);
    }
}

package defpackage;

import J.N;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.ParcelUuid;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.device.bluetooth.ChromeBluetoothAdapter;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;

/* renamed from: uz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5178uz1 extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C5659xq f11452a;

    public C5178uz1(C5659xq xqVar) {
        this.f11452a = xqVar;
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onBatchScanResults(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C5348vz1((ScanResult) it.next()));
        }
        Objects.requireNonNull(this.f11452a);
    }

    public void onScanFailed(int i) {
        C5659xq xqVar = this.f11452a;
        Objects.requireNonNull(xqVar);
        AbstractC1220Ua0.f("Bluetooth", "onScanFailed: %d", Integer.valueOf(i));
        ChromeBluetoothAdapter chromeBluetoothAdapter = xqVar.f11639a;
        N.Mq3WoOFf(chromeBluetoothAdapter.f10942a, chromeBluetoothAdapter);
    }

    public void onScanResult(int i, ScanResult scanResult) {
        String[] strArr;
        byte[][] bArr;
        String[] strArr2;
        byte[][] bArr2;
        int[] iArr;
        C5659xq xqVar = this.f11452a;
        Objects.requireNonNull(xqVar);
        new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()).a();
        BluetoothDevice device = scanResult.getDevice();
        new Wrappers$BluetoothDeviceWrapper(device);
        device.getName();
        List<ParcelUuid> serviceUuids = scanResult.getScanRecord().getServiceUuids();
        if (serviceUuids == null) {
            strArr = new String[0];
        } else {
            String[] strArr3 = new String[serviceUuids.size()];
            for (int i2 = 0; i2 < serviceUuids.size(); i2++) {
                strArr3[i2] = serviceUuids.get(i2).toString();
            }
            strArr = strArr3;
        }
        Map<ParcelUuid, byte[]> serviceData = scanResult.getScanRecord().getServiceData();
        if (serviceData == null) {
            strArr2 = new String[0];
            bArr = new byte[0][];
        } else {
            String[] strArr4 = new String[serviceData.size()];
            byte[][] bArr3 = new byte[serviceData.size()][];
            int i3 = 0;
            for (Map.Entry<ParcelUuid, byte[]> entry : serviceData.entrySet()) {
                strArr4[i3] = entry.getKey().toString();
                bArr3[i3] = entry.getValue();
                i3++;
            }
            strArr2 = strArr4;
            bArr = bArr3;
        }
        SparseArray<byte[]> manufacturerSpecificData = scanResult.getScanRecord().getManufacturerSpecificData();
        if (manufacturerSpecificData == null) {
            iArr = new int[0];
            bArr2 = new byte[0][];
        } else {
            int[] iArr2 = new int[manufacturerSpecificData.size()];
            byte[][] bArr4 = new byte[manufacturerSpecificData.size()][];
            for (int i4 = 0; i4 < manufacturerSpecificData.size(); i4++) {
                iArr2[i4] = manufacturerSpecificData.keyAt(i4);
                bArr4[i4] = manufacturerSpecificData.valueAt(i4);
            }
            iArr = iArr2;
            bArr2 = bArr4;
        }
        ChromeBluetoothAdapter chromeBluetoothAdapter = xqVar.f11639a;
        long j = chromeBluetoothAdapter.f10942a;
        if (j != 0) {
            N.MOuw3NGo(j, chromeBluetoothAdapter, new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()).a(), new Wrappers$BluetoothDeviceWrapper(scanResult.getDevice()), scanResult.getScanRecord().getDeviceName(), scanResult.getRssi(), strArr, scanResult.getScanRecord().getTxPowerLevel(), strArr2, bArr, iArr, bArr2, scanResult.getScanRecord().getAdvertiseFlags());
        }
    }
}

package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothScanFilterBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ScanFilter.Builder f10947a = new ScanFilter.Builder();

    public static ChromeBluetoothScanFilterBuilder create() {
        return new ChromeBluetoothScanFilterBuilder();
    }

    public ScanFilter build() {
        return this.f10947a.build();
    }

    public final void setDeviceName(String str) {
        if (str != null) {
            this.f10947a.setDeviceName(str);
        }
    }

    public final void setServiceUuid(String str) {
        if (str != null) {
            this.f10947a.setServiceUuid(ParcelUuid.fromString(str));
        }
    }
}

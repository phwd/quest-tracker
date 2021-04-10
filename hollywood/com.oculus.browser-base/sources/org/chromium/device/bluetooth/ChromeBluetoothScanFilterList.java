package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothScanFilterList {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f10948a = new ArrayList();

    public static ChromeBluetoothScanFilterList create() {
        return new ChromeBluetoothScanFilterList();
    }

    public final void addFilter(ScanFilter scanFilter) {
        this.f10948a.add(scanFilter);
    }

    public ArrayList getList() {
        return this.f10948a;
    }
}

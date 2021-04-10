package org.chromium.device.usb;

import J.N;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeUsbService {

    /* renamed from: a  reason: collision with root package name */
    public long f10966a;
    public UsbManager b = ((UsbManager) ContextUtils.getApplicationContext().getSystemService("usb"));
    public BroadcastReceiver c = new C3284ju(this);

    public ChromeUsbService(long j) {
        this.f10966a = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("org.chromium.device.ACTION_USB_PERMISSION");
        ContextUtils.getApplicationContext().registerReceiver(this.c, intentFilter);
    }

    public static ChromeUsbService create(long j) {
        return new ChromeUsbService(j);
    }

    public final void close() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.c);
        this.c = null;
    }

    public final Object[] getDevices() {
        return this.b.getDeviceList().values().toArray();
    }

    public final boolean hasDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        return this.b.hasPermission(chromeUsbDevice.f10963a);
    }

    public final UsbDeviceConnection openDevice(ChromeUsbDevice chromeUsbDevice) {
        return this.b.openDevice(chromeUsbDevice.f10963a);
    }

    public final void requestDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        UsbDevice usbDevice = chromeUsbDevice.f10963a;
        if (this.b.hasPermission(usbDevice)) {
            N.MDvFAfgT(this.f10966a, this, usbDevice.getDeviceId(), true);
            return;
        }
        this.b.requestPermission(chromeUsbDevice.f10963a, PendingIntent.getBroadcast(ContextUtils.getApplicationContext(), 0, new Intent("org.chromium.device.ACTION_USB_PERMISSION"), 0));
    }
}

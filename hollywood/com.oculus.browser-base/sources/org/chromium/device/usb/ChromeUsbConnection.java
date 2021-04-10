package org.chromium.device.usb;

import android.hardware.usb.UsbDeviceConnection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeUsbConnection {

    /* renamed from: a  reason: collision with root package name */
    public final UsbDeviceConnection f10962a;

    public ChromeUsbConnection(UsbDeviceConnection usbDeviceConnection) {
        this.f10962a = usbDeviceConnection;
    }

    public static ChromeUsbConnection create(UsbDeviceConnection usbDeviceConnection) {
        return new ChromeUsbConnection(usbDeviceConnection);
    }

    public final void close() {
        this.f10962a.close();
    }

    public final int getFileDescriptor() {
        return this.f10962a.getFileDescriptor();
    }
}

package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeUsbEndpoint {

    /* renamed from: a  reason: collision with root package name */
    public final UsbEndpoint f10964a;

    public ChromeUsbEndpoint(UsbEndpoint usbEndpoint) {
        this.f10964a = usbEndpoint;
    }

    public static ChromeUsbEndpoint create(UsbEndpoint usbEndpoint) {
        return new ChromeUsbEndpoint(usbEndpoint);
    }

    public final int getAddress() {
        return this.f10964a.getAddress();
    }

    public final int getAttributes() {
        return this.f10964a.getAttributes();
    }

    public final int getInterval() {
        return this.f10964a.getInterval();
    }

    public final int getMaxPacketSize() {
        return this.f10964a.getMaxPacketSize();
    }
}

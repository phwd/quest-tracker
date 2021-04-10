package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeUsbInterface {

    /* renamed from: a  reason: collision with root package name */
    public final UsbInterface f10965a;

    public ChromeUsbInterface(UsbInterface usbInterface) {
        this.f10965a = usbInterface;
    }

    public static ChromeUsbInterface create(UsbInterface usbInterface) {
        return new ChromeUsbInterface(usbInterface);
    }

    public final int getAlternateSetting() {
        return this.f10965a.getAlternateSetting();
    }

    public final UsbEndpoint[] getEndpoints() {
        int endpointCount = this.f10965a.getEndpointCount();
        UsbEndpoint[] usbEndpointArr = new UsbEndpoint[endpointCount];
        for (int i = 0; i < endpointCount; i++) {
            usbEndpointArr[i] = this.f10965a.getEndpoint(i);
        }
        return usbEndpointArr;
    }

    public final int getInterfaceClass() {
        return this.f10965a.getInterfaceClass();
    }

    public final int getInterfaceNumber() {
        return this.f10965a.getId();
    }

    public final int getInterfaceProtocol() {
        return this.f10965a.getInterfaceProtocol();
    }

    public final int getInterfaceSubclass() {
        return this.f10965a.getInterfaceSubclass();
    }
}

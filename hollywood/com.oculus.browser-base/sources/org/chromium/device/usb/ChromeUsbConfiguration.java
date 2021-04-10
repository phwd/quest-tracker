package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbInterface;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeUsbConfiguration {

    /* renamed from: a  reason: collision with root package name */
    public final UsbConfiguration f10961a;

    public ChromeUsbConfiguration(UsbConfiguration usbConfiguration) {
        this.f10961a = usbConfiguration;
    }

    public static ChromeUsbConfiguration create(UsbConfiguration usbConfiguration) {
        return new ChromeUsbConfiguration(usbConfiguration);
    }

    public final int getConfigurationValue() {
        return this.f10961a.getId();
    }

    public final UsbInterface[] getInterfaces() {
        int interfaceCount = this.f10961a.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = this.f10961a.getInterface(i);
        }
        return usbInterfaceArr;
    }

    public final int getMaxPower() {
        return this.f10961a.getMaxPower();
    }

    public final boolean isRemoteWakeup() {
        return this.f10961a.isRemoteWakeup();
    }

    public final boolean isSelfPowered() {
        return this.f10961a.isSelfPowered();
    }
}

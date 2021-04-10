package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeUsbDevice {

    /* renamed from: a  reason: collision with root package name */
    public final UsbDevice f10963a;

    public ChromeUsbDevice(UsbDevice usbDevice) {
        this.f10963a = usbDevice;
    }

    public static ChromeUsbDevice create(UsbDevice usbDevice) {
        return new ChromeUsbDevice(usbDevice);
    }

    public final UsbConfiguration[] getConfigurations() {
        int configurationCount = this.f10963a.getConfigurationCount();
        UsbConfiguration[] usbConfigurationArr = new UsbConfiguration[configurationCount];
        for (int i = 0; i < configurationCount; i++) {
            usbConfigurationArr[i] = this.f10963a.getConfiguration(i);
        }
        return usbConfigurationArr;
    }

    public final int getDeviceClass() {
        return this.f10963a.getDeviceClass();
    }

    public final int getDeviceId() {
        return this.f10963a.getDeviceId();
    }

    public final int getDeviceProtocol() {
        return this.f10963a.getDeviceProtocol();
    }

    public final int getDeviceSubclass() {
        return this.f10963a.getDeviceSubclass();
    }

    public final int getDeviceVersion() {
        String[] split = this.f10963a.getVersion().split("\\.");
        return Integer.parseInt(split[1]) | (Integer.parseInt(split[0]) << 8);
    }

    public final String getManufacturerName() {
        return this.f10963a.getManufacturerName();
    }

    public final int getProductId() {
        return this.f10963a.getProductId();
    }

    public final String getProductName() {
        return this.f10963a.getProductName();
    }

    public final String getSerialNumber() {
        return this.f10963a.getSerialNumber();
    }

    public final int getVendorId() {
        return this.f10963a.getVendorId();
    }
}

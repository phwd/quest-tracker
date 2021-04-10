package org.chromium.midi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UsbMidiDeviceFactoryAndroid {

    /* renamed from: a  reason: collision with root package name */
    public UsbManager f10994a = ((UsbManager) ContextUtils.getApplicationContext().getSystemService("usb"));
    public BroadcastReceiver b;
    public final List c = new ArrayList();
    public Set d;
    public boolean e;
    public long f;

    public UsbMidiDeviceFactoryAndroid(long j) {
        this.f = j;
        this.b = new Nr1(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("org.chromium.midi.USB_PERMISSION");
        ContextUtils.getApplicationContext().registerReceiver(this.b, intentFilter);
        this.d = new HashSet();
    }

    public static UsbMidiDeviceFactoryAndroid create(long j) {
        return new UsbMidiDeviceFactoryAndroid(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001d, code lost:
        if (r7.getBooleanExtra("permission", false) == false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Intent r7) {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.midi.UsbMidiDeviceFactoryAndroid.a(android.content.Intent):void");
    }

    public final void b(UsbDevice usbDevice) {
        for (UsbDevice usbDevice2 : this.d) {
            if (usbDevice2.getDeviceId() == usbDevice.getDeviceId()) {
                return;
            }
        }
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.f10994a.requestPermission(usbDevice, PendingIntent.getBroadcast(ContextUtils.getApplicationContext(), 0, new Intent("org.chromium.midi.USB_PERMISSION"), 0));
                this.d.add(usbDevice);
                return;
            }
        }
    }

    public void close() {
        this.f = 0;
        ContextUtils.getApplicationContext().unregisterReceiver(this.b);
    }

    public boolean enumerateDevices() {
        this.e = true;
        HashMap<String, UsbDevice> deviceList = this.f10994a.getDeviceList();
        if (deviceList.isEmpty()) {
            this.e = false;
            return false;
        }
        for (UsbDevice usbDevice : deviceList.values()) {
            b(usbDevice);
        }
        return true ^ this.d.isEmpty();
    }
}

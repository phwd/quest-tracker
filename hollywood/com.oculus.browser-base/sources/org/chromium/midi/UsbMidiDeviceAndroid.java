package org.chromium.midi;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UsbMidiDeviceAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final UsbDeviceConnection f10993a;
    public final SparseArray b = new SparseArray();
    public final Map c = new HashMap();
    public final Handler d = new Handler();
    public boolean e;
    public boolean f;
    public long g;
    public UsbDevice h;

    public UsbMidiDeviceAndroid(UsbManager usbManager, UsbDevice usbDevice) {
        this.f10993a = usbManager.openDevice(usbDevice);
        this.h = usbDevice;
        this.e = false;
        this.f = false;
        this.g = 0;
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.f10993a.claimInterface(usbInterface, true);
                for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                    if (endpoint.getDirection() == 0) {
                        this.b.put(endpoint.getEndpointNumber(), endpoint);
                    }
                }
            }
        }
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < usbDevice.getInterfaceCount(); i3++) {
            UsbInterface usbInterface2 = usbDevice.getInterface(i3);
            if (usbInterface2.getInterfaceClass() == 1 && usbInterface2.getInterfaceSubclass() == 3) {
                for (int i4 = 0; i4 < usbInterface2.getEndpointCount(); i4++) {
                    UsbEndpoint endpoint2 = usbInterface2.getEndpoint(i4);
                    if (endpoint2.getDirection() == 128) {
                        ByteBuffer allocate = ByteBuffer.allocate(endpoint2.getMaxPacketSize());
                        UsbRequest usbRequest = new UsbRequest();
                        usbRequest.initialize(this.f10993a, endpoint2);
                        usbRequest.queue(allocate, allocate.remaining());
                        hashMap.put(endpoint2, allocate);
                    }
                }
            }
        }
        if (!hashMap.isEmpty()) {
            this.f = true;
            new Lr1(this, hashMap).start();
        }
    }

    public void close() {
        this.b.clear();
        for (UsbRequest usbRequest : this.c.values()) {
            usbRequest.close();
        }
        this.c.clear();
        this.f10993a.close();
        this.g = 0;
        this.e = true;
    }

    public byte[] getDescriptors() {
        UsbDeviceConnection usbDeviceConnection = this.f10993a;
        if (usbDeviceConnection == null) {
            return new byte[0];
        }
        return usbDeviceConnection.getRawDescriptors();
    }

    public byte[] getStringDescriptor(int i) {
        UsbDeviceConnection usbDeviceConnection = this.f10993a;
        if (usbDeviceConnection == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[255];
        int controlTransfer = usbDeviceConnection.controlTransfer(128, 6, i | 768, 0, bArr, 255, 0);
        if (controlTransfer < 0) {
            return new byte[0];
        }
        return Arrays.copyOf(bArr, controlTransfer);
    }

    public void registerSelf(long j) {
        this.g = j;
    }

    public void send(int i, byte[] bArr) {
        UsbEndpoint usbEndpoint;
        if (this.e || (usbEndpoint = (UsbEndpoint) this.b.get(i)) == null) {
            return;
        }
        if (this.f) {
            this.f10993a.bulkTransfer(usbEndpoint, bArr, bArr.length, 100);
            return;
        }
        UsbRequest usbRequest = (UsbRequest) this.c.get(usbEndpoint);
        if (usbRequest == null) {
            usbRequest = new UsbRequest();
            usbRequest.initialize(this.f10993a, usbEndpoint);
            this.c.put(usbEndpoint, usbRequest);
        }
        usbRequest.queue(ByteBuffer.wrap(bArr), bArr.length);
    }
}

package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Parcelable;
import java.util.Iterator;
import org.chromium.midi.UsbMidiDeviceAndroid;
import org.chromium.midi.UsbMidiDeviceFactoryAndroid;

/* renamed from: Nr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Nr1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ UsbMidiDeviceFactoryAndroid f8582a;

    public Nr1(UsbMidiDeviceFactoryAndroid usbMidiDeviceFactoryAndroid) {
        this.f8582a = usbMidiDeviceFactoryAndroid;
    }

    public void onReceive(Context context, Intent intent) {
        Parcelable parcelableExtra = intent.getParcelableExtra("device");
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            this.f8582a.b((UsbDevice) parcelableExtra);
        }
        if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
            UsbMidiDeviceFactoryAndroid usbMidiDeviceFactoryAndroid = this.f8582a;
            UsbDevice usbDevice = (UsbDevice) parcelableExtra;
            Iterator it = usbMidiDeviceFactoryAndroid.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UsbDevice usbDevice2 = (UsbDevice) it.next();
                if (usbDevice2.getDeviceId() == usbDevice.getDeviceId()) {
                    usbMidiDeviceFactoryAndroid.d.remove(usbDevice2);
                    break;
                }
            }
            int i = 0;
            while (true) {
                if (i >= usbMidiDeviceFactoryAndroid.c.size()) {
                    break;
                }
                UsbMidiDeviceAndroid usbMidiDeviceAndroid = (UsbMidiDeviceAndroid) usbMidiDeviceFactoryAndroid.c.get(i);
                if (!usbMidiDeviceAndroid.e && usbMidiDeviceAndroid.h.getDeviceId() == usbDevice.getDeviceId()) {
                    usbMidiDeviceAndroid.close();
                    if (usbMidiDeviceFactoryAndroid.e) {
                        usbMidiDeviceFactoryAndroid.c.remove(i);
                    } else {
                        long j = usbMidiDeviceFactoryAndroid.f;
                        if (j != 0) {
                            N.MQPqHj_p(j, i);
                        }
                    }
                } else {
                    i++;
                }
            }
        }
        if ("org.chromium.midi.USB_PERMISSION".equals(intent.getAction())) {
            this.f8582a.a(intent);
        }
    }
}

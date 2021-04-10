package defpackage;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import java.nio.ByteBuffer;
import java.util.Map;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* renamed from: Lr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Lr1 extends Thread {
    public final /* synthetic */ Map F;
    public final /* synthetic */ UsbMidiDeviceAndroid G;

    public Lr1(UsbMidiDeviceAndroid usbMidiDeviceAndroid, Map map) {
        this.G = usbMidiDeviceAndroid;
        this.F = map;
    }

    public void run() {
        while (true) {
            UsbRequest requestWait = this.G.f10993a.requestWait();
            if (requestWait != null) {
                UsbEndpoint endpoint = requestWait.getEndpoint();
                if (endpoint.getDirection() == 128) {
                    ByteBuffer byteBuffer = (ByteBuffer) this.F.get(endpoint);
                    int position = byteBuffer.position();
                    int i = 0;
                    while (true) {
                        if (i >= position) {
                            break;
                        } else if (byteBuffer.get(i) == 0) {
                            position = i;
                            break;
                        } else {
                            i += 4;
                        }
                    }
                    if (position > 0) {
                        byteBuffer.rewind();
                        byte[] bArr = new byte[position];
                        byteBuffer.get(bArr, 0, position);
                        UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.G;
                        usbMidiDeviceAndroid.d.post(new Mr1(usbMidiDeviceAndroid, endpoint.getEndpointNumber(), bArr));
                    }
                    byteBuffer.rewind();
                    requestWait.queue(byteBuffer, byteBuffer.capacity());
                }
            } else {
                return;
            }
        }
    }
}

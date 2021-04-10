package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MidiDeviceAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final MidiDevice f10989a;
    public final MidiInputPortAndroid[] b;
    public final MidiOutputPortAndroid[] c;
    public boolean d = true;

    public MidiDeviceAndroid(MidiDevice midiDevice) {
        this.f10989a = midiDevice;
        this.c = new MidiOutputPortAndroid[a().getInputPortCount()];
        int i = 0;
        int i2 = 0;
        while (true) {
            MidiOutputPortAndroid[] midiOutputPortAndroidArr = this.c;
            if (i2 >= midiOutputPortAndroidArr.length) {
                break;
            }
            midiOutputPortAndroidArr[i2] = new MidiOutputPortAndroid(midiDevice, i2);
            i2++;
        }
        this.b = new MidiInputPortAndroid[a().getOutputPortCount()];
        while (true) {
            MidiInputPortAndroid[] midiInputPortAndroidArr = this.b;
            if (i < midiInputPortAndroidArr.length) {
                midiInputPortAndroidArr[i] = new MidiInputPortAndroid(midiDevice, i);
                i++;
            } else {
                return;
            }
        }
    }

    public MidiDeviceInfo a() {
        return this.f10989a.getInfo();
    }

    public final String b(String str) {
        return this.f10989a.getInfo().getProperties().getString(str);
    }

    public MidiInputPortAndroid[] getInputPorts() {
        return this.b;
    }

    public String getManufacturer() {
        return b("manufacturer");
    }

    public MidiOutputPortAndroid[] getOutputPorts() {
        return this.c;
    }

    public String getProduct() {
        String b2 = b("product");
        if (b2 == null || b2.isEmpty()) {
            return b("name");
        }
        return b2;
    }

    public String getVersion() {
        return b("version");
    }
}

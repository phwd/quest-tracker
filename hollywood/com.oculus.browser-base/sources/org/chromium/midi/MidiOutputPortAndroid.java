package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MidiOutputPortAndroid {

    /* renamed from: a  reason: collision with root package name */
    public MidiInputPort f10992a;
    public final MidiDevice b;
    public final int c;

    public MidiOutputPortAndroid(MidiDevice midiDevice, int i) {
        this.b = midiDevice;
        this.c = i;
    }

    public void close() {
        MidiInputPort midiInputPort = this.f10992a;
        if (midiInputPort != null) {
            try {
                midiInputPort.close();
            } catch (IOException unused) {
            }
            this.f10992a = null;
        }
    }

    public boolean open() {
        if (this.f10992a != null) {
            return true;
        }
        MidiInputPort openInputPort = this.b.openInputPort(this.c);
        this.f10992a = openInputPort;
        if (openInputPort != null) {
            return true;
        }
        return false;
    }

    public void send(byte[] bArr) {
        MidiInputPort midiInputPort = this.f10992a;
        if (midiInputPort != null) {
            try {
                midiInputPort.send(bArr, 0, bArr.length);
            } catch (IOException e) {
                AbstractC1220Ua0.a("midi", "MidiOutputPortAndroid.send: " + e, new Object[0]);
            }
        }
    }
}

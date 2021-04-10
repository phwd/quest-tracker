package org.chromium.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiOutputPort;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MidiInputPortAndroid {

    /* renamed from: a  reason: collision with root package name */
    public MidiOutputPort f10990a;
    public long b;
    public final MidiDevice c;
    public final int d;

    public MidiInputPortAndroid(MidiDevice midiDevice, int i) {
        this.c = midiDevice;
        this.d = i;
    }

    public synchronized void close() {
        MidiOutputPort midiOutputPort = this.f10990a;
        if (midiOutputPort != null) {
            try {
                midiOutputPort.close();
            } catch (IOException unused) {
            }
            this.b = 0;
            this.f10990a = null;
        }
    }

    public boolean open(long j) {
        if (this.f10990a != null) {
            return true;
        }
        MidiOutputPort openOutputPort = this.c.openOutputPort(this.d);
        this.f10990a = openOutputPort;
        if (openOutputPort == null) {
            return false;
        }
        this.b = j;
        openOutputPort.connect(new C5302vk0(this));
        return true;
    }
}

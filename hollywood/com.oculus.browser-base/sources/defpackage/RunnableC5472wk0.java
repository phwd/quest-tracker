package defpackage;

import J.N;
import org.chromium.midi.MidiManagerAndroid;

/* renamed from: wk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5472wk0 implements Runnable {
    public final /* synthetic */ MidiManagerAndroid F;

    public RunnableC5472wk0(MidiManagerAndroid midiManagerAndroid) {
        this.F = midiManagerAndroid;
    }

    public void run() {
        synchronized (this.F) {
            MidiManagerAndroid midiManagerAndroid = this.F;
            if (!midiManagerAndroid.g) {
                N.MfmZ2$zu(midiManagerAndroid.f);
            }
        }
    }
}

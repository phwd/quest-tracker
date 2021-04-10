package defpackage;

import J.N;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiManagerAndroid;

/* renamed from: yk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5812yk0 implements Runnable {
    public final /* synthetic */ MidiManagerAndroid F;

    public RunnableC5812yk0(MidiManagerAndroid midiManagerAndroid) {
        this.F = midiManagerAndroid;
    }

    public void run() {
        synchronized (this.F) {
            MidiManagerAndroid midiManagerAndroid = this.F;
            if (!midiManagerAndroid.g) {
                if (midiManagerAndroid.c.isEmpty()) {
                    MidiManagerAndroid midiManagerAndroid2 = this.F;
                    if (!midiManagerAndroid2.f10991a) {
                        N.M3znzcE9(midiManagerAndroid2.f, (MidiDeviceAndroid[]) midiManagerAndroid2.b.toArray(new MidiDeviceAndroid[0]));
                        this.F.f10991a = true;
                    }
                }
            }
        }
    }
}

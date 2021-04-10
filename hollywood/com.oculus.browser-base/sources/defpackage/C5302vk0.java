package defpackage;

import J.N;
import android.media.midi.MidiReceiver;
import org.chromium.midi.MidiInputPortAndroid;

/* renamed from: vk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5302vk0 extends MidiReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MidiInputPortAndroid f11493a;

    public C5302vk0(MidiInputPortAndroid midiInputPortAndroid) {
        this.f11493a = midiInputPortAndroid;
    }

    @Override // android.media.midi.MidiReceiver
    public void onSend(byte[] bArr, int i, int i2, long j) {
        synchronized (this.f11493a) {
            MidiInputPortAndroid midiInputPortAndroid = this.f11493a;
            if (midiInputPortAndroid.f10990a != null) {
                N.MzDIdqgH(midiInputPortAndroid.b, bArr, i, i2, j);
            }
        }
    }
}

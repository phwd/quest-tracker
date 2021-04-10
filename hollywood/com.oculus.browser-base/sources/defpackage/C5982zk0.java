package defpackage;

import J.N;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiManagerAndroid;

/* renamed from: zk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5982zk0 implements MidiManager.OnDeviceOpenedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MidiDeviceInfo f11765a;
    public final /* synthetic */ MidiManagerAndroid b;

    public C5982zk0(MidiManagerAndroid midiManagerAndroid, MidiDeviceInfo midiDeviceInfo) {
        this.b = midiManagerAndroid;
        this.f11765a = midiDeviceInfo;
    }

    public void onDeviceOpened(MidiDevice midiDevice) {
        MidiManagerAndroid midiManagerAndroid = this.b;
        MidiDeviceInfo midiDeviceInfo = this.f11765a;
        synchronized (midiManagerAndroid) {
            if (!midiManagerAndroid.g) {
                midiManagerAndroid.c.remove(midiDeviceInfo);
                if (midiDevice != null) {
                    MidiDeviceAndroid midiDeviceAndroid = new MidiDeviceAndroid(midiDevice);
                    midiManagerAndroid.b.add(midiDeviceAndroid);
                    if (midiManagerAndroid.f10991a) {
                        N.MEOWUhL5(midiManagerAndroid.f, midiDeviceAndroid);
                    }
                }
                if (!midiManagerAndroid.f10991a && midiManagerAndroid.c.isEmpty()) {
                    N.M3znzcE9(midiManagerAndroid.f, (MidiDeviceAndroid[]) midiManagerAndroid.b.toArray(new MidiDeviceAndroid[0]));
                    midiManagerAndroid.f10991a = true;
                }
            }
        }
    }
}

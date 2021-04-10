package defpackage;

import J.N;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import org.chromium.midi.MidiDeviceAndroid;
import org.chromium.midi.MidiInputPortAndroid;
import org.chromium.midi.MidiManagerAndroid;
import org.chromium.midi.MidiOutputPortAndroid;

/* renamed from: xk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5642xk0 extends MidiManager.DeviceCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MidiManagerAndroid f11630a;

    public C5642xk0(MidiManagerAndroid midiManagerAndroid) {
        this.f11630a = midiManagerAndroid;
    }

    public void onDeviceAdded(MidiDeviceInfo midiDeviceInfo) {
        MidiManagerAndroid midiManagerAndroid = this.f11630a;
        if (!midiManagerAndroid.f10991a) {
            midiManagerAndroid.c.add(midiDeviceInfo);
        }
        midiManagerAndroid.d.openDevice(midiDeviceInfo, new C5982zk0(midiManagerAndroid, midiDeviceInfo), midiManagerAndroid.e);
    }

    public void onDeviceRemoved(MidiDeviceInfo midiDeviceInfo) {
        MidiManagerAndroid midiManagerAndroid = this.f11630a;
        synchronized (midiManagerAndroid) {
            if (!midiManagerAndroid.g) {
                for (MidiDeviceAndroid midiDeviceAndroid : midiManagerAndroid.b) {
                    if (midiDeviceAndroid.d && midiDeviceAndroid.a().getId() == midiDeviceInfo.getId()) {
                        midiDeviceAndroid.d = false;
                        for (MidiInputPortAndroid midiInputPortAndroid : midiDeviceAndroid.b) {
                            midiInputPortAndroid.close();
                        }
                        for (MidiOutputPortAndroid midiOutputPortAndroid : midiDeviceAndroid.c) {
                            midiOutputPortAndroid.close();
                        }
                        N.Md3XPFG5(midiManagerAndroid.f, midiDeviceAndroid);
                    }
                }
            }
        }
    }
}

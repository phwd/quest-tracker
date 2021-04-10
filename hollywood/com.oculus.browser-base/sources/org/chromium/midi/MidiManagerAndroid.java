package org.chromium.midi;

import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MidiManagerAndroid {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10991a;
    public final List b = new ArrayList();
    public final Set c = new HashSet();
    public final MidiManager d = ((MidiManager) ContextUtils.getApplicationContext().getSystemService("midi"));
    public final Handler e = new Handler(ThreadUtils.c());
    public final long f;
    public boolean g;

    public MidiManagerAndroid(long j) {
        this.f = j;
    }

    public static MidiManagerAndroid create(long j) {
        return new MidiManagerAndroid(j);
    }

    public static boolean hasSystemFeatureMidi() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.software.midi");
    }

    public void initialize() {
        MidiManager midiManager = this.d;
        if (midiManager == null) {
            this.e.post(new RunnableC5472wk0(this));
            return;
        }
        midiManager.registerDeviceCallback(new C5642xk0(this), this.e);
        MidiDeviceInfo[] devices = this.d.getDevices();
        for (MidiDeviceInfo midiDeviceInfo : devices) {
            this.c.add(midiDeviceInfo);
            this.d.openDevice(midiDeviceInfo, new C5982zk0(this, midiDeviceInfo), this.e);
        }
        this.e.post(new RunnableC5812yk0(this));
    }

    public synchronized void stop() {
        this.g = true;
    }
}

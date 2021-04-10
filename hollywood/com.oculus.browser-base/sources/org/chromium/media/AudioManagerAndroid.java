package org.chromium.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioManagerAndroid {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f10968a = {"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset", "USB audio"};
    public static final Integer[] b = {0, 1, 2, 3, 4};
    public static final Method c;
    public final AudioManager d;
    public final long e;
    public boolean f;
    public boolean g;
    public int h = -1;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l = -1;
    public final Object m = new Object();
    public boolean[] n = new boolean[5];
    public final ContentResolver o;
    public ContentObserver p;
    public HandlerThread q;
    public BroadcastReceiver r;
    public BroadcastReceiver s;
    public BroadcastReceiver t;
    public final UsbManager u;
    public BroadcastReceiver v;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class AudioDeviceName {

        /* renamed from: a  reason: collision with root package name */
        public final int f10969a;
        public final String b;

        public AudioDeviceName(int i, String str, C3227jb jbVar) {
            this.f10969a = i;
            this.b = str;
        }

        public final String id() {
            return String.valueOf(this.f10969a);
        }

        public final String name() {
            return this.b;
        }
    }

    static {
        Method method;
        try {
            method = AudioManager.class.getMethod("getOutputLatency", Integer.TYPE);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        c = method;
    }

    public AudioManagerAndroid(long j2) {
        this.e = j2;
        this.d = (AudioManager) ContextUtils.getApplicationContext().getSystemService("audio");
        this.o = ContextUtils.getApplicationContext().getContentResolver();
        this.u = (UsbManager) ContextUtils.getApplicationContext().getSystemService("usb");
    }

    public static boolean a(AudioManagerAndroid audioManagerAndroid) {
        boolean z;
        synchronized (audioManagerAndroid.m) {
            z = audioManagerAndroid.l != -1;
        }
        return z;
    }

    public static boolean acousticEchoCancelerIsAvailable() {
        return AcousticEchoCanceler.isAvailable();
    }

    public static void b(AudioManagerAndroid audioManagerAndroid) {
        int i2;
        boolean[] zArr;
        synchronized (audioManagerAndroid.m) {
            i2 = audioManagerAndroid.l;
            zArr = (boolean[]) audioManagerAndroid.n.clone();
        }
        if (i2 == -1) {
            f("Unable to activate device since no device is selected");
        } else if (i2 == -2 || !zArr[i2]) {
            audioManagerAndroid.h(g(zArr));
        } else {
            audioManagerAndroid.h(i2);
        }
    }

    public static AudioManagerAndroid createAudioManagerAndroid(long j2) {
        return new AudioManagerAndroid(j2);
    }

    public static void f(String str) {
        AbstractC1220Ua0.a("media", str, new Object[0]);
    }

    public static int g(boolean[] zArr) {
        if (zArr[1]) {
            return 1;
        }
        if (zArr[4]) {
            return 4;
        }
        if (zArr[3]) {
            return 3;
        }
        return 0;
    }

    public static int getMinInputFrameSize(int i2, int i3) {
        int i4;
        if (i3 == 1) {
            i4 = 16;
        } else if (i3 != 2) {
            return -1;
        } else {
            i4 = 12;
        }
        return (AudioRecord.getMinBufferSize(i2, i4, 2) / 2) / i3;
    }

    public static int getMinOutputFrameSize(int i2, int i3) {
        int i4;
        if (i3 == 1) {
            i4 = 4;
        } else if (i3 != 2) {
            return -1;
        } else {
            i4 = 12;
        }
        return (AudioTrack.getMinBufferSize(i2, i4, 2) / 2) / i3;
    }

    public final boolean c(String str) {
        return ContextUtils.getApplicationContext().checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    public final void close() {
        if (this.i) {
            k();
            ContextUtils.getApplicationContext().unregisterReceiver(this.r);
            this.r = null;
            if (this.g) {
                this.d.stopBluetoothSco();
                ContextUtils.getApplicationContext().unregisterReceiver(this.s);
                this.s = null;
                ContextUtils.getApplicationContext().unregisterReceiver(this.t);
                this.t = null;
            }
            ContextUtils.getApplicationContext().unregisterReceiver(this.v);
            this.v = null;
            this.i = false;
        }
    }

    public final boolean d() {
        try {
            for (UsbDevice usbDevice : this.u.getDeviceList().values()) {
                if (e(usbDevice)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final boolean e(UsbDevice usbDevice) {
        for (int i2 = 0; i2 < usbDevice.getInterfaceCount(); i2++) {
            UsbInterface usbInterface = usbDevice.getInterface(i2);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 2) {
                return true;
            }
        }
        return false;
    }

    public final AudioDeviceName[] getAudioInputDeviceNames() {
        boolean[] zArr;
        if (!this.i) {
            return null;
        }
        boolean c2 = c("android.permission.RECORD_AUDIO");
        if (!this.f || !c2) {
            AbstractC1220Ua0.f("media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. No audio device will be available for recording", new Object[0]);
            return null;
        }
        synchronized (this.m) {
            zArr = (boolean[]) this.n.clone();
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            if (zArr[i3]) {
                i2++;
            }
        }
        AudioDeviceName[] audioDeviceNameArr = new AudioDeviceName[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < 5; i5++) {
            if (zArr[i5]) {
                String[] strArr = f10968a;
                audioDeviceNameArr[i4] = new AudioDeviceName(i5, strArr[i5], null);
                arrayList.add(strArr[i5]);
                i4++;
            }
        }
        return audioDeviceNameArr;
    }

    public final int getAudioLowLatencyOutputFrameSize() {
        String property = this.d.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (property == null) {
            return 256;
        }
        return Integer.parseInt(property);
    }

    public final int getNativeOutputSampleRate() {
        String property = this.d.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return 44100;
        }
        return Integer.parseInt(property);
    }

    public final int getOutputLatency() {
        Method method = c;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.d, 3)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public final void h(int i2) {
        int i3;
        if (i2 != 3) {
            j();
        } else if (!(!this.g || (i3 = this.h) == 1 || i3 == 2)) {
            if (this.d.isBluetoothScoOn()) {
                this.h = 1;
            } else {
                this.h = 2;
                this.d.startBluetoothSco();
            }
        }
        if (i2 == 0) {
            i(true);
        } else if (i2 == 1) {
            i(false);
        } else if (i2 == 2) {
            i(false);
        } else if (i2 == 3) {
        } else {
            if (i2 != 4) {
                f("Invalid audio device selection");
            } else {
                i(false);
            }
        }
    }

    public final void i(boolean z) {
        if (this.d.isSpeakerphoneOn() != z) {
            this.d.setSpeakerphoneOn(z);
        }
    }

    public final void init() {
        if (!this.i) {
            this.f = c("android.permission.MODIFY_AUDIO_SETTINGS");
            this.n[2] = ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
            this.n[1] = this.d.isWiredHeadsetOn();
            this.n[4] = d();
            boolean z = false;
            this.n[0] = true;
            boolean c2 = c("android.permission.BLUETOOTH");
            this.g = c2;
            if (!c2) {
                AbstractC1220Ua0.f("media", "Requires BLUETOOTH permission", new Object[0]);
            } else {
                boolean[] zArr = this.n;
                if (!c2) {
                    AbstractC1220Ua0.f("media", "hasBluetoothHeadset() requires BLUETOOTH permission", new Object[0]);
                } else {
                    BluetoothAdapter adapter = ((BluetoothManager) ContextUtils.getApplicationContext().getSystemService("bluetooth")).getAdapter();
                    if (adapter != null) {
                        int profileConnectionState = adapter.getProfileConnectionState(1);
                        if (adapter.isEnabled() && profileConnectionState == 2) {
                            z = true;
                        }
                    }
                }
                zArr[3] = z;
                IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                this.s = new C3398kb(this);
                ContextUtils.getApplicationContext().registerReceiver(this.s, intentFilter);
                IntentFilter intentFilter2 = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
                this.t = new C3569lb(this);
                ContextUtils.getApplicationContext().registerReceiver(this.t, intentFilter2);
            }
            IntentFilter intentFilter3 = new IntentFilter("android.intent.action.HEADSET_PLUG");
            this.r = new C3227jb(this);
            ContextUtils.getApplicationContext().registerReceiver(this.r, intentFilter3);
            this.v = new C3911nb(this);
            IntentFilter intentFilter4 = new IntentFilter();
            intentFilter4.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter4.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            ContextUtils.getApplicationContext().registerReceiver(this.v, intentFilter4);
            this.i = true;
        }
    }

    public final boolean isAudioLowLatencySupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    public final void j() {
        if (this.g) {
            int i2 = this.h;
            if (i2 != 1 && i2 != 2) {
                return;
            }
            if (!this.d.isBluetoothScoOn()) {
                f("Unable to stop BT SCO since it is already disabled");
                this.h = 0;
                return;
            }
            this.h = 3;
            this.d.stopBluetoothSco();
        }
    }

    public final void k() {
        if (this.q != null) {
            this.o.unregisterContentObserver(this.p);
            this.p = null;
            this.q.quit();
            try {
                this.q.join();
            } catch (InterruptedException e2) {
                AbstractC1220Ua0.a("media", "Thread.join() exception: ", e2);
            }
            this.q = null;
        }
    }

    public final void setCommunicationAudioModeOn(boolean z) {
        if (this.i) {
            if (!this.f) {
                AbstractC1220Ua0.f("media", "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality", new Object[0]);
                return;
            }
            if (z) {
                this.j = this.d.isSpeakerphoneOn();
                this.k = this.d.isMicrophoneMute();
                if (this.q == null) {
                    HandlerThread handlerThread = new HandlerThread("SettingsObserver");
                    this.q = handlerThread;
                    handlerThread.start();
                    C3740mb mbVar = new C3740mb(this, new Handler(this.q.getLooper()));
                    this.p = mbVar;
                    this.o.registerContentObserver(Settings.System.CONTENT_URI, true, mbVar);
                }
            } else {
                k();
                j();
                synchronized (this.m) {
                    this.l = -1;
                }
                boolean z2 = this.k;
                if (this.d.isMicrophoneMute() != z2) {
                    this.d.setMicrophoneMute(z2);
                }
                i(this.j);
            }
            if (z) {
                try {
                    this.d.setMode(3);
                } catch (SecurityException e2) {
                    String str = Build.VERSION.RELEASE;
                    String str2 = Build.BRAND;
                    String str3 = Build.DEVICE;
                    String str4 = Build.ID;
                    String str5 = Build.HARDWARE;
                    String str6 = Build.MANUFACTURER;
                    String str7 = Build.MODEL;
                    String str8 = Build.PRODUCT;
                    throw e2;
                }
            } else {
                try {
                    this.d.setMode(0);
                } catch (SecurityException e3) {
                    String str9 = Build.VERSION.RELEASE;
                    String str10 = Build.BRAND;
                    String str11 = Build.DEVICE;
                    String str12 = Build.ID;
                    String str13 = Build.HARDWARE;
                    String str14 = Build.MANUFACTURER;
                    String str15 = Build.MODEL;
                    String str16 = Build.PRODUCT;
                    throw e3;
                }
            }
        }
    }

    public final boolean setDevice(String str) {
        boolean[] zArr;
        if (!this.i) {
            return false;
        }
        boolean c2 = c("android.permission.RECORD_AUDIO");
        if (!this.f || !c2) {
            AbstractC1220Ua0.f("media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. Selected device will not be available for recording", new Object[0]);
            return false;
        }
        int parseInt = str.isEmpty() ? -2 : Integer.parseInt(str);
        if (parseInt == -2) {
            synchronized (this.m) {
                zArr = (boolean[]) this.n.clone();
                this.l = -2;
            }
            h(g(zArr));
            return true;
        } else if (!Arrays.asList(b).contains(Integer.valueOf(parseInt)) || !this.n[parseInt]) {
            return false;
        } else {
            synchronized (this.m) {
                this.l = parseInt;
            }
            h(parseInt);
            return true;
        }
    }
}

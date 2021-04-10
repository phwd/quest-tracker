package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.base.ContextUtils;
import org.chromium.media.AudioManagerAndroid;

/* renamed from: nb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3911nb extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioManagerAndroid f10498a;

    public C3911nb(AudioManagerAndroid audioManagerAndroid) {
        this.f10498a = audioManagerAndroid;
    }

    public void onReceive(Context context, Intent intent) {
        AudioManagerAndroid audioManagerAndroid = this.f10498a;
        String[] strArr = AudioManagerAndroid.f10968a;
        if (audioManagerAndroid.e((UsbDevice) intent.getParcelableExtra("device"))) {
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                synchronized (this.f10498a.m) {
                    if (!this.f10498a.d.isWiredHeadsetOn()) {
                        boolean[] zArr = this.f10498a.n;
                        zArr[4] = true;
                        zArr[2] = false;
                    }
                }
            } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()) && !this.f10498a.d()) {
                synchronized (this.f10498a.m) {
                    if (!this.f10498a.d.isWiredHeadsetOn()) {
                        this.f10498a.n[4] = false;
                        if (ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                            this.f10498a.n[2] = true;
                        }
                    }
                }
            }
            if (AudioManagerAndroid.a(this.f10498a)) {
                AudioManagerAndroid.b(this.f10498a);
            }
        }
    }
}

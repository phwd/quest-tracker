package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.media.AudioManagerAndroid;

/* renamed from: kb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3398kb extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioManagerAndroid f10287a;

    public C3398kb(AudioManagerAndroid audioManagerAndroid) {
        this.f10287a = audioManagerAndroid;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        if (intExtra == 0) {
            synchronized (this.f10287a.m) {
                this.f10287a.n[3] = false;
            }
        } else if (intExtra == 1) {
        } else {
            if (intExtra == 2) {
                synchronized (this.f10287a.m) {
                    this.f10287a.n[3] = true;
                }
            } else if (intExtra != 3) {
                AudioManagerAndroid.f("Invalid state");
            }
        }
    }
}

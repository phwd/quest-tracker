package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.media.AudioManagerAndroid;

/* renamed from: jb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3227jb extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioManagerAndroid f10215a;

    public C3227jb(AudioManagerAndroid audioManagerAndroid) {
        this.f10215a = audioManagerAndroid;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("state", 0);
        if (intExtra == 0) {
            synchronized (this.f10215a.m) {
                AudioManagerAndroid audioManagerAndroid = this.f10215a;
                audioManagerAndroid.n[1] = false;
                if (audioManagerAndroid.d()) {
                    boolean[] zArr = this.f10215a.n;
                    zArr[4] = true;
                    zArr[2] = false;
                } else {
                    Objects.requireNonNull(this.f10215a);
                    if (ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                        boolean[] zArr2 = this.f10215a.n;
                        zArr2[2] = true;
                        zArr2[4] = false;
                    }
                }
            }
        } else if (intExtra != 1) {
            AudioManagerAndroid.f("Invalid state");
        } else {
            synchronized (this.f10215a.m) {
                boolean[] zArr3 = this.f10215a.n;
                zArr3[1] = true;
                zArr3[2] = false;
                zArr3[4] = false;
            }
        }
        if (AudioManagerAndroid.a(this.f10215a)) {
            AudioManagerAndroid.b(this.f10215a);
        }
    }
}

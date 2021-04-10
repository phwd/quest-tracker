package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.media.AudioManagerAndroid;

/* renamed from: lb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3569lb extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioManagerAndroid f10355a;

    public C3569lb(AudioManagerAndroid audioManagerAndroid) {
        this.f10355a = audioManagerAndroid;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
        if (intExtra == 0) {
            AudioManagerAndroid audioManagerAndroid = this.f10355a;
            if (audioManagerAndroid.h != 3 && AudioManagerAndroid.a(audioManagerAndroid)) {
                AudioManagerAndroid.b(this.f10355a);
            }
            this.f10355a.h = 0;
        } else if (intExtra == 1) {
            this.f10355a.h = 1;
        } else if (intExtra != 2) {
            AudioManagerAndroid.f("Invalid state");
        }
    }
}

package defpackage;

import J.N;
import android.database.ContentObserver;
import android.os.Handler;
import org.chromium.media.AudioManagerAndroid;

/* renamed from: mb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3740mb extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioManagerAndroid f10433a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3740mb(AudioManagerAndroid audioManagerAndroid, Handler handler) {
        super(handler);
        this.f10433a = audioManagerAndroid;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        boolean z2 = false;
        int streamVolume = this.f10433a.d.getStreamVolume(0);
        AudioManagerAndroid audioManagerAndroid = this.f10433a;
        long j = audioManagerAndroid.e;
        if (streamVolume == 0) {
            z2 = true;
        }
        N.MCgftn_d(j, audioManagerAndroid, z2);
    }
}

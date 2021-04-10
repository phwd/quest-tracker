package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.oculus.browser.R;

/* renamed from: Tr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1201Tr extends AbstractC1018Qr {
    public BroadcastReceiver c = new C1140Sr(this);

    public C1201Tr() {
        super(R.id.media_playback_notification);
    }

    @Override // defpackage.AbstractC1677aZ0
    public void b() {
        this.f9437a.registerReceiver(this.c, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
    }

    @Override // defpackage.AbstractC1018Qr, defpackage.AbstractC1677aZ0
    public void c() {
        this.f9437a.unregisterReceiver(this.c);
        super.c();
    }
}

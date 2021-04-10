package defpackage;

import android.support.v4.media.MediaMetadataCompat;

/* renamed from: gg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2731gg0 extends AbstractC0559Jd0 {
    public final /* synthetic */ DialogC5460wg0 d;

    public C2731gg0(DialogC5460wg0 wg0) {
        this.d = wg0;
    }

    @Override // defpackage.AbstractC0559Jd0
    public void a(MediaMetadataCompat mediaMetadataCompat) {
        this.d.o0 = mediaMetadataCompat == null ? null : mediaMetadataCompat.c();
        this.d.e();
        this.d.l();
    }

    @Override // defpackage.AbstractC0559Jd0
    public void c() {
        DialogC5460wg0 wg0 = this.d;
        C0985Qd0 qd0 = wg0.m0;
        if (qd0 != null) {
            qd0.d(wg0.n0);
            this.d.m0 = null;
        }
    }
}

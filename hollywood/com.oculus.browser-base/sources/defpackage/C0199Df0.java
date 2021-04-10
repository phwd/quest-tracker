package defpackage;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: Df0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0199Df0 extends AbstractC0559Jd0 {
    public final /* synthetic */ DialogC0504If0 d;

    public C0199Df0(DialogC0504If0 if0) {
        this.d = if0;
    }

    @Override // defpackage.AbstractC0559Jd0
    public void a(MediaMetadataCompat mediaMetadataCompat) {
        this.d.z0 = mediaMetadataCompat == null ? null : mediaMetadataCompat.c();
        this.d.u();
        this.d.s(false);
    }

    @Override // defpackage.AbstractC0559Jd0
    public void b(PlaybackStateCompat playbackStateCompat) {
        DialogC0504If0 if0 = this.d;
        if0.y0 = playbackStateCompat;
        if0.s(false);
    }

    @Override // defpackage.AbstractC0559Jd0
    public void c() {
        DialogC0504If0 if0 = this.d;
        C0985Qd0 qd0 = if0.w0;
        if (qd0 != null) {
            qd0.d(if0.x0);
            this.d.w0 = null;
        }
    }
}

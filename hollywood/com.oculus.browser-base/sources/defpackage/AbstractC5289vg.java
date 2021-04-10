package defpackage;

import android.content.Intent;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;

/* renamed from: vg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5289vg implements AbstractC0135Ce0, AbstractC5799yg {
    public C0013Ae0 F;
    public final AbstractC0018Ag G;

    public AbstractC5289vg(AbstractC0018Ag ag) {
        this.G = ag;
    }

    @Override // defpackage.AbstractC0135Ce0
    public void a(int i) {
        if (this.G.h()) {
            this.G.e().n();
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void b(long j) {
    }

    @Override // defpackage.AbstractC0135Ce0
    public void c(int i) {
        if (this.G.h()) {
            this.G.c();
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void d(int i) {
    }

    @Override // defpackage.AbstractC0135Ce0
    public void e(int i) {
        if (this.G.h()) {
            this.G.e().o();
        }
    }

    public abstract Intent f();

    public abstract int g();

    public final void h() {
        MediaMetadata mediaMetadata;
        org.chromium.services.media_session.MediaMetadata mediaMetadata2 = new org.chromium.services.media_session.MediaMetadata("", "", "");
        this.F.f7683a = mediaMetadata2;
        if (this.G.h()) {
            CastDevice e = this.G.f7686a.e();
            if (e != null) {
                mediaMetadata2.f11009a = e.I;
            }
            MediaInfo c = this.G.e().c();
            if (c != null && (mediaMetadata = c.I) != null) {
                String x = mediaMetadata.x("com.google.android.gms.cast.metadata.TITLE");
                if (x != null) {
                    mediaMetadata2.f11009a = x;
                }
                String x2 = mediaMetadata.x("com.google.android.gms.cast.metadata.ARTIST");
                if (x2 == null) {
                    x2 = mediaMetadata.x("com.google.android.gms.cast.metadata.ALBUM_ARTIST");
                }
                if (x2 != null) {
                    mediaMetadata2.b = x2;
                }
                String x3 = mediaMetadata.x("com.google.android.gms.cast.metadata.ALBUM_TITLE");
                if (x3 != null) {
                    mediaMetadata2.c = x3;
                }
            }
        }
    }
}

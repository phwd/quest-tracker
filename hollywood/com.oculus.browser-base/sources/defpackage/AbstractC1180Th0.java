package defpackage;

import java.util.List;
import java.util.Set;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* renamed from: Th0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1180Th0 {

    /* renamed from: a  reason: collision with root package name */
    public MediaSessionImpl f8975a;

    public AbstractC1180Th0(AbstractC5123uh0 uh0) {
        if (uh0 instanceof MediaSessionImpl) {
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) uh0;
            this.f8975a = mediaSessionImpl;
            mediaSessionImpl.b.b(this);
        }
    }

    public void a(Set set) {
    }

    public void b(List list) {
    }

    public void c() {
    }

    public void d(MediaMetadata mediaMetadata) {
    }

    public void e(MediaPosition mediaPosition) {
    }

    public abstract void f(boolean z, boolean z2);

    public final void g() {
        MediaSessionImpl mediaSessionImpl = this.f8975a;
        if (mediaSessionImpl != null) {
            mediaSessionImpl.b.c(this);
            this.f8975a = null;
        }
    }
}

package org.chromium.content.browser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.chromium.services.media_session.MediaImage;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaSessionImpl extends AbstractC5123uh0 {

    /* renamed from: a  reason: collision with root package name */
    public long f10915a;
    public C1322Vq0 b;
    public C1261Uq0 c;
    public boolean d;

    public MediaSessionImpl(long j) {
        this.f10915a = j;
        C1322Vq0 vq0 = new C1322Vq0();
        this.b = vq0;
        this.c = vq0.e();
    }

    public static MediaSessionImpl create(long j) {
        return new MediaSessionImpl(j);
    }

    public final boolean hasObservers() {
        return !this.b.isEmpty();
    }

    public final void mediaSessionActionsChanged(int[] iArr) {
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).a(hashSet);
        }
    }

    public final void mediaSessionArtworkChanged(MediaImage[] mediaImageArr) {
        List asList = Arrays.asList(mediaImageArr);
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).b(asList);
        }
    }

    public final void mediaSessionDestroyed() {
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).c();
        }
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).g();
        }
        this.b.clear();
        this.f10915a = 0;
    }

    public final void mediaSessionMetadataChanged(MediaMetadata mediaMetadata) {
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).d(mediaMetadata);
        }
    }

    public final void mediaSessionPositionChanged(MediaPosition mediaPosition) {
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).e(mediaPosition);
        }
    }

    public final void mediaSessionStateChanged(boolean z, boolean z2) {
        this.d = z;
        this.c.b();
        while (this.c.hasNext()) {
            ((AbstractC1180Th0) this.c.next()).f(z, z2);
        }
    }
}

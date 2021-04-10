package org.chromium.components.media_router;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaStatusBridge {

    /* renamed from: a  reason: collision with root package name */
    public MediaStatus f10852a;

    public MediaStatusBridge(MediaStatus mediaStatus) {
        this.f10852a = mediaStatus;
    }

    public boolean canMute() {
        return this.f10852a.A(8);
    }

    public boolean canPlayPause() {
        return this.f10852a.A(1);
    }

    public boolean canSeek() {
        return this.f10852a.A(2);
    }

    public boolean canSetVolume() {
        return this.f10852a.A(4);
    }

    public long currentTime() {
        return this.f10852a.L;
    }

    public long duration() {
        MediaInfo mediaInfo = this.f10852a.F;
        if (mediaInfo == null) {
            return 0;
        }
        return mediaInfo.f9642J;
    }

    public int idleReason() {
        return this.f10852a.K;
    }

    public boolean isMuted() {
        return this.f10852a.O;
    }

    public int playerState() {
        return this.f10852a.f9645J;
    }

    public String title() {
        MediaMetadata mediaMetadata;
        MediaInfo mediaInfo = this.f10852a.F;
        if (mediaInfo == null || (mediaMetadata = mediaInfo.I) == null) {
            return "";
        }
        return mediaMetadata.x("com.google.android.gms.cast.metadata.TITLE");
    }

    public double volume() {
        return this.f10852a.N;
    }
}

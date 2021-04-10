package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.SessionToken2;
import android.support.v4.media.session.MediaSessionCompat$Token;

final class SessionToken2ImplLegacy implements SessionToken2.SupportLibraryImpl {
    private final MediaSessionCompat$Token mLegacyToken;

    SessionToken2ImplLegacy(MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.mLegacyToken = mediaSessionCompat$Token;
    }

    public int hashCode() {
        return this.mLegacyToken.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2ImplLegacy)) {
            return false;
        }
        return this.mLegacyToken.equals(((SessionToken2ImplLegacy) obj).mLegacyToken);
    }

    public String toString() {
        return "SessionToken2 {legacyToken=" + this.mLegacyToken + "}";
    }

    public static SessionToken2ImplLegacy fromBundle(Bundle bundle) {
        return new SessionToken2ImplLegacy(MediaSessionCompat$Token.fromBundle(bundle.getBundle("android.media.token.LEGACY")));
    }
}

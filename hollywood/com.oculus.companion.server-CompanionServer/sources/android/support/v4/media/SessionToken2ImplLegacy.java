package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.SessionToken2;
import android.support.v4.media.session.MediaSessionCompat;

/* access modifiers changed from: package-private */
public final class SessionToken2ImplLegacy implements SessionToken2.SupportLibraryImpl {
    private final MediaSessionCompat.Token mLegacyToken;

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public String getSessionId() {
        return null;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public int getType() {
        return 0;
    }

    SessionToken2ImplLegacy(MediaSessionCompat.Token token) {
        this.mLegacyToken = token;
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
        return new SessionToken2ImplLegacy(MediaSessionCompat.Token.fromBundle(bundle.getBundle("android.media.token.LEGACY")));
    }
}

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

public final class SessionToken2 {
    private final SupportLibraryImpl mImpl;

    /* access modifiers changed from: package-private */
    public interface SupportLibraryImpl {
        String getSessionId();

        int getType();
    }

    public SessionToken2(Context context, ComponentName componentName) {
        this.mImpl = new SessionToken2ImplBase(context, componentName);
    }

    SessionToken2(SupportLibraryImpl supportLibraryImpl) {
        this.mImpl = supportLibraryImpl;
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2)) {
            return false;
        }
        return this.mImpl.equals(((SessionToken2) obj).mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }

    public String getId() {
        return this.mImpl.getSessionId();
    }

    public int getType() {
        return this.mImpl.getType();
    }

    public static SessionToken2 fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt("android.media.token.type", -1) == 100) {
            return new SessionToken2(SessionToken2ImplLegacy.fromBundle(bundle));
        }
        return new SessionToken2(SessionToken2ImplBase.fromBundle(bundle));
    }
}

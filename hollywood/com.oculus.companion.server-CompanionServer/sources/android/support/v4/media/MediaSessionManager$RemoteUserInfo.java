package android.support.v4.media;

import android.os.Build;

public final class MediaSessionManager$RemoteUserInfo {
    MediaSessionManager$RemoteUserInfoImpl mImpl;

    public MediaSessionManager$RemoteUserInfo(String str, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new MediaSessionManagerImplApi28$RemoteUserInfo(str, i, i2);
        } else {
            this.mImpl = new MediaSessionManagerImplBase$RemoteUserInfo(str, i, i2);
        }
    }

    public boolean equals(Object obj) {
        return this.mImpl.equals(obj);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }
}

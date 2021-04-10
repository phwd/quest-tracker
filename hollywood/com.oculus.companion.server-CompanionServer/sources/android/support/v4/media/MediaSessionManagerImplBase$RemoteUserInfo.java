package android.support.v4.media;

import android.support.v4.util.ObjectsCompat;
import android.text.TextUtils;

/* access modifiers changed from: package-private */
public class MediaSessionManagerImplBase$RemoteUserInfo implements MediaSessionManager$RemoteUserInfoImpl {
    private String mPackageName;
    private int mPid;
    private int mUid;

    MediaSessionManagerImplBase$RemoteUserInfo(String str, int i, int i2) {
        this.mPackageName = str;
        this.mPid = i;
        this.mUid = i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaSessionManagerImplBase$RemoteUserInfo)) {
            return false;
        }
        MediaSessionManagerImplBase$RemoteUserInfo mediaSessionManagerImplBase$RemoteUserInfo = (MediaSessionManagerImplBase$RemoteUserInfo) obj;
        if (TextUtils.equals(this.mPackageName, mediaSessionManagerImplBase$RemoteUserInfo.mPackageName) && this.mPid == mediaSessionManagerImplBase$RemoteUserInfo.mPid && this.mUid == mediaSessionManagerImplBase$RemoteUserInfo.mUid) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.mPackageName, Integer.valueOf(this.mPid), Integer.valueOf(this.mUid));
    }
}

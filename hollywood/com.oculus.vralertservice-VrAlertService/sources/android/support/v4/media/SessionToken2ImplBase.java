package android.support.v4.media;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.IMediaSession2;
import android.support.v4.media.SessionToken2;
import android.text.TextUtils;

final class SessionToken2ImplBase implements SessionToken2.SupportLibraryImpl {
    private final ComponentName mComponentName;
    private final IMediaSession2 mISession2;
    private final String mPackageName;
    private final String mServiceName;
    private final String mSessionId;
    private final int mType;
    private final int mUid;

    SessionToken2ImplBase(int i, int i2, String str, String str2, String str3, IMediaSession2 iMediaSession2) {
        this.mUid = i;
        this.mType = i2;
        this.mPackageName = str;
        this.mServiceName = str2;
        this.mComponentName = this.mType == 0 ? null : new ComponentName(str, str2);
        this.mSessionId = str3;
        this.mISession2 = iMediaSession2;
    }

    public int hashCode() {
        int i = this.mType;
        int i2 = this.mUid;
        int hashCode = this.mPackageName.hashCode();
        int hashCode2 = this.mSessionId.hashCode();
        String str = this.mServiceName;
        return i + ((i2 + ((hashCode + ((hashCode2 + ((str != null ? str.hashCode() : 0) * 31)) * 31)) * 31)) * 31);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2ImplBase)) {
            return false;
        }
        SessionToken2ImplBase sessionToken2ImplBase = (SessionToken2ImplBase) obj;
        if (this.mUid != sessionToken2ImplBase.mUid || !TextUtils.equals(this.mPackageName, sessionToken2ImplBase.mPackageName) || !TextUtils.equals(this.mServiceName, sessionToken2ImplBase.mServiceName) || !TextUtils.equals(this.mSessionId, sessionToken2ImplBase.mSessionId) || this.mType != sessionToken2ImplBase.mType || !sessionBinderEquals(this.mISession2, sessionToken2ImplBase.mISession2)) {
            return false;
        }
        return true;
    }

    private boolean sessionBinderEquals(IMediaSession2 iMediaSession2, IMediaSession2 iMediaSession22) {
        if (iMediaSession2 == null || iMediaSession22 == null) {
            return iMediaSession2 == iMediaSession22;
        }
        return iMediaSession2.asBinder().equals(iMediaSession22.asBinder());
    }

    public String toString() {
        return "SessionToken {pkg=" + this.mPackageName + " id=" + this.mSessionId + " type=" + this.mType + " service=" + this.mServiceName + " IMediaSession2=" + this.mISession2 + "}";
    }

    public static SessionToken2ImplBase fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int i = bundle.getInt("android.media.token.uid");
        int i2 = bundle.getInt("android.media.token.type", -1);
        String string = bundle.getString("android.media.token.package_name");
        String string2 = bundle.getString("android.media.token.service_name");
        String string3 = bundle.getString("android.media.token.session_id");
        IMediaSession2 asInterface = IMediaSession2.Stub.asInterface(BundleCompat.getBinder(bundle, "android.media.token.session_binder"));
        if (i2 != 0) {
            if (i2 != 1 && i2 != 2) {
                throw new IllegalArgumentException("Invalid type");
            } else if (TextUtils.isEmpty(string2)) {
                throw new IllegalArgumentException("Session service needs service name");
            }
        } else if (asInterface == null) {
            throw new IllegalArgumentException("Unexpected token for session, binder=" + asInterface);
        }
        if (!TextUtils.isEmpty(string) && string3 != null) {
            return new SessionToken2ImplBase(i, i2, string, string2, string3, asInterface);
        }
        throw new IllegalArgumentException("Package name nor ID cannot be null.");
    }
}

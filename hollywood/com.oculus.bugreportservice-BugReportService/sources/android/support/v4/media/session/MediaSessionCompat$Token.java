package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.SessionToken2;
import android.support.v4.media.session.IMediaSession;

public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /* class android.support.v4.media.session.MediaSessionCompat$Token.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$Token createFromParcel(Parcel parcel) {
            Object obj;
            if (Build.VERSION.SDK_INT >= 21) {
                obj = parcel.readParcelable(null);
            } else {
                obj = parcel.readStrongBinder();
            }
            return new MediaSessionCompat$Token(obj);
        }

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$Token[] newArray(int i) {
            return new MediaSessionCompat$Token[i];
        }
    };
    private IMediaSession mExtraBinder;
    private final Object mInner;
    private SessionToken2 mSessionToken2;

    public int describeContents() {
        return 0;
    }

    MediaSessionCompat$Token(Object obj) {
        this(obj, null, null);
    }

    MediaSessionCompat$Token(Object obj, IMediaSession iMediaSession, SessionToken2 sessionToken2) {
        this.mInner = obj;
        this.mExtraBinder = iMediaSession;
        this.mSessionToken2 = sessionToken2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            parcel.writeParcelable((Parcelable) this.mInner, i);
        } else {
            parcel.writeStrongBinder((IBinder) this.mInner);
        }
    }

    @Override // java.lang.Object
    public int hashCode() {
        Object obj = this.mInner;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Object obj2 = this.mInner;
        if (obj2 == null) {
            return mediaSessionCompat$Token.mInner == null;
        }
        Object obj3 = mediaSessionCompat$Token.mInner;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public IMediaSession getExtraBinder() {
        return this.mExtraBinder;
    }

    public void setExtraBinder(IMediaSession iMediaSession) {
        this.mExtraBinder = iMediaSession;
    }

    public void setSessionToken2(SessionToken2 sessionToken2) {
        this.mSessionToken2 = sessionToken2;
    }

    public static MediaSessionCompat$Token fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
        SessionToken2 fromBundle = SessionToken2.fromBundle(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2"));
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) bundle.getParcelable("android.support.v4.media.session.TOKEN");
        if (mediaSessionCompat$Token == null) {
            return null;
        }
        return new MediaSessionCompat$Token(mediaSessionCompat$Token.mInner, asInterface, fromBundle);
    }
}

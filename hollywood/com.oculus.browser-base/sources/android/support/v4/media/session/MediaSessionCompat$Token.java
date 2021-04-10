package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0510Ih0();
    public final Object F = new Object();
    public final Object G;
    public TY H;
    public Ns1 I;

    public MediaSessionCompat$Token(Object obj, TY ty, Ns1 ns1) {
        this.G = obj;
        this.H = ty;
        this.I = ns1;
    }

    public TY b() {
        TY ty;
        synchronized (this.F) {
            ty = this.H;
        }
        return ty;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Object obj2 = this.G;
        if (obj2 == null) {
            return mediaSessionCompat$Token.G == null;
        }
        Object obj3 = mediaSessionCompat$Token.G;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public int hashCode() {
        Object obj = this.G;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable((Parcelable) this.G, i);
    }
}

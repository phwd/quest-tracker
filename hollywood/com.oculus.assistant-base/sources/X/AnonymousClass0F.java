package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$Token;

/* renamed from: X.0F  reason: invalid class name */
public final class AnonymousClass0F implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$Token(parcel.readParcelable(null));
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new MediaSessionCompat$Token[i];
    }
}

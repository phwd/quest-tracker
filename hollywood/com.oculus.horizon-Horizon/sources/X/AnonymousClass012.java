package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$Token;

/* renamed from: X.012  reason: invalid class name */
public class AnonymousClass012 implements Parcelable.Creator<MediaSessionCompat$Token> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$Token createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$Token(parcel.readParcelable(null));
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$Token[] newArray(int i) {
        return new MediaSessionCompat$Token[i];
    }
}

package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: X.017  reason: invalid class name */
public class AnonymousClass017 implements Parcelable.Creator<PlaybackStateCompat> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final PlaybackStateCompat createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final PlaybackStateCompat[] newArray(int i) {
        return new PlaybackStateCompat[i];
    }
}

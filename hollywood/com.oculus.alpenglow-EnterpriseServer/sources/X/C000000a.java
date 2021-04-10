package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaMetadataCompat;

/* renamed from: X.00a  reason: invalid class name and case insensitive filesystem */
public class C000000a implements Parcelable.Creator<MediaMetadataCompat> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaMetadataCompat createFromParcel(Parcel parcel) {
        return new MediaMetadataCompat(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaMetadataCompat[] newArray(int i) {
        return new MediaMetadataCompat[i];
    }
}

package X;

import android.media.MediaDescription;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* renamed from: X.00X  reason: invalid class name */
public class AnonymousClass00X implements Parcelable.Creator<MediaDescriptionCompat> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaDescriptionCompat createFromParcel(Parcel parcel) {
        return MediaDescriptionCompat.A00(MediaDescription.CREATOR.createFromParcel(parcel));
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaDescriptionCompat[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}

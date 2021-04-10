package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.ParcelableVolumeInfo;

/* renamed from: X.15  reason: invalid class name */
public class AnonymousClass15 implements Parcelable.Creator<ParcelableVolumeInfo> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ParcelableVolumeInfo createFromParcel(Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ParcelableVolumeInfo[] newArray(int i) {
        return new ParcelableVolumeInfo[i];
    }
}

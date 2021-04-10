package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.ParcelImpl;

/* renamed from: X.0HD  reason: invalid class name */
public class AnonymousClass0HD implements Parcelable.Creator<ParcelImpl> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ParcelImpl createFromParcel(Parcel parcel) {
        return new ParcelImpl(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ParcelImpl[] newArray(int i) {
        return new ParcelImpl[i];
    }
}

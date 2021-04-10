package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.ParcelImpl;

/* renamed from: X.Ef  reason: case insensitive filesystem */
public class C0053Ef implements Parcelable.Creator<ParcelImpl> {
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

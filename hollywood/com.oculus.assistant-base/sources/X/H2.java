package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.papaya.client.PapayaMetadataInternal;

public final class H2 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new PapayaMetadataInternal(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new PapayaMetadataInternal[i];
    }
}

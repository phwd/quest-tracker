package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.papaya.client.PapayaMetadata;

public final class H0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new PapayaMetadata(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new PapayaMetadata[i];
    }
}

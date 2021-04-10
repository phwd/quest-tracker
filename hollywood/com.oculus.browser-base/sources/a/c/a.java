package a.c;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.C1728ParcelImpl;

/* compiled from: chromium-webapk7.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new C1728ParcelImpl(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new C1728ParcelImpl[i];
    }
}

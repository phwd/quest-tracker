package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.flexbox.FlexboxLayoutManager;

/* renamed from: X.1CA  reason: invalid class name */
public class AnonymousClass1CA implements Parcelable.Creator<FlexboxLayoutManager.LayoutParams> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayoutManager.LayoutParams createFromParcel(Parcel parcel) {
        return new FlexboxLayoutManager.LayoutParams(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayoutManager.LayoutParams[] newArray(int i) {
        return new FlexboxLayoutManager.LayoutParams[i];
    }
}

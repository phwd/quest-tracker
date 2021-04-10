package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.flexbox.FlexboxLayout;

/* renamed from: X.1E1  reason: invalid class name */
public class AnonymousClass1E1 implements Parcelable.Creator<FlexboxLayout.LayoutParams> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayout.LayoutParams createFromParcel(Parcel parcel) {
        return new FlexboxLayout.LayoutParams(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayout.LayoutParams[] newArray(int i) {
        return new FlexboxLayout.LayoutParams[i];
    }
}

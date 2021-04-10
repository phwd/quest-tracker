package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.flexbox.FlexboxLayoutManager;

/* renamed from: X.1CB  reason: invalid class name */
public class AnonymousClass1CB implements Parcelable.Creator<FlexboxLayoutManager.SavedState> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayoutManager.SavedState createFromParcel(Parcel parcel) {
        return new FlexboxLayoutManager.SavedState(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FlexboxLayoutManager.SavedState[] newArray(int i) {
        return new FlexboxLayoutManager.SavedState[i];
    }
}

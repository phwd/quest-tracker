package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManagerState;

/* renamed from: X.09c  reason: invalid class name and case insensitive filesystem */
public class C003409c implements Parcelable.Creator<FragmentManagerState> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FragmentManagerState createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FragmentManagerState[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}

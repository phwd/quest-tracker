package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentState;

/* renamed from: X.09e  reason: invalid class name and case insensitive filesystem */
public class C003509e implements Parcelable.Creator<FragmentState> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FragmentState createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FragmentState[] newArray(int i) {
        return new FragmentState[i];
    }
}

package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.BackStackState;

/* renamed from: X.0CF  reason: invalid class name */
public class AnonymousClass0CF implements Parcelable.Creator<BackStackState> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final BackStackState createFromParcel(Parcel parcel) {
        return new BackStackState(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final BackStackState[] newArray(int i) {
        return new BackStackState[i];
    }
}

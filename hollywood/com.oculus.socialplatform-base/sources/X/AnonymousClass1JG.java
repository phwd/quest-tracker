package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.AppCompatSpinner$SavedState;

/* renamed from: X.1JG  reason: invalid class name */
public class AnonymousClass1JG implements Parcelable.Creator<AppCompatSpinner$SavedState> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final AppCompatSpinner$SavedState createFromParcel(Parcel parcel) {
        return new AppCompatSpinner$SavedState(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final AppCompatSpinner$SavedState[] newArray(int i) {
        return new AppCompatSpinner$SavedState[i];
    }
}

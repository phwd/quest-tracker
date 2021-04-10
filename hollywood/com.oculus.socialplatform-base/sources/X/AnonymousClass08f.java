package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* renamed from: X.08f  reason: invalid class name */
public class AnonymousClass08f implements Parcelable.ClassLoaderCreator<AbsSavedState> {
    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new AbsSavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        if (parcel.readParcelable(null) == null) {
            return AbsSavedState.A01;
        }
        throw new IllegalStateException("superState must be null");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final /* bridge */ /* synthetic */ AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.A01;
        }
        throw new IllegalStateException("superState must be null");
    }
}

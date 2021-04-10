package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

/* renamed from: X.09F  reason: invalid class name */
public class AnonymousClass09F implements Parcelable.ClassLoaderCreator<Fragment.SavedState> {
    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new Fragment.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new Fragment.SavedState(parcel, null);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Fragment.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Fragment.SavedState(parcel, classLoader);
    }
}

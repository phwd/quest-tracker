package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.05c  reason: invalid class name and case insensitive filesystem */
public class C004605c implements Parcelable.ClassLoaderCreator<Toolbar.SavedState> {
    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new Toolbar.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new Toolbar.SavedState(parcel, null);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Toolbar.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Toolbar.SavedState(parcel, classLoader);
    }
}

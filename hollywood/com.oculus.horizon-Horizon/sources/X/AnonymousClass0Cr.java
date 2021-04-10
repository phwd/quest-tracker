package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0Cr  reason: invalid class name */
public class AnonymousClass0Cr implements Parcelable.ClassLoaderCreator<ViewPager.SavedState> {
    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ViewPager.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new ViewPager.SavedState(parcel, null);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final ViewPager.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager.SavedState(parcel, classLoader);
    }
}

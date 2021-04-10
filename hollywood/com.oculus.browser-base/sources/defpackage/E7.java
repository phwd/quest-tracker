package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.appbar.AppBarLayout$BaseBehavior$SavedState;

/* renamed from: E7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class E7 implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new AppBarLayout$BaseBehavior$SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new AppBarLayout$BaseBehavior$SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new AppBarLayout$BaseBehavior$SavedState(parcel, null);
    }
}

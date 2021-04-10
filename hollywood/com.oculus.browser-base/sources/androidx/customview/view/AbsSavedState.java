package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbsSavedState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1936c();
    public static final AbsSavedState F = new AbsSavedState() {
        /* class androidx.customview.view.AbsSavedState.AnonymousClass1 */
    };
    public final Parcelable G;

    public AbsSavedState() {
        this.G = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.G, i);
    }

    public AbsSavedState(Parcelable parcelable) {
        if (parcelable != null) {
            this.G = parcelable == F ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.G = readParcelable == null ? F : readParcelable;
    }
}

package androidx.customview.view;

import X.C00930Bx;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint({"BanParcelableUsage"})
public abstract class AbsSavedState implements Parcelable {
    public static final AbsSavedState A01 = new AbsSavedState() {
        /* class androidx.customview.view.AbsSavedState.AnonymousClass1 */
    };
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new C00930Bx();
    public final Parcelable A00;

    public final int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.A00, i);
    }

    public AbsSavedState() {
        this.A00 = null;
    }

    public AbsSavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.A00 = readParcelable == null ? A01 : readParcelable;
    }

    public AbsSavedState(@NonNull Parcelable parcelable) {
        if (parcelable != null) {
            this.A00 = parcelable == A01 ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }
}

package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.TwoStatePreference$SavedState;

/* renamed from: Io1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Io1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new TwoStatePreference$SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new TwoStatePreference$SavedState[i];
    }
}

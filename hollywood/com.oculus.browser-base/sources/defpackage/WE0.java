package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.Preference;

/* renamed from: WE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WE0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new Preference.BaseSavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Preference.BaseSavedState[i];
    }
}

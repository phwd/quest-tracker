package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.ListPreference;

/* renamed from: q90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4357q90 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new ListPreference.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new ListPreference.SavedState[i];
    }
}

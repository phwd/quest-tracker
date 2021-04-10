package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.PreferenceGroup$SavedState;

/* renamed from: gF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2666gF0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new PreferenceGroup$SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new PreferenceGroup$SavedState[i];
    }
}

package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.Preference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PreferenceGroup$SavedState extends Preference.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new C2666gF0();
    public int F;

    public PreferenceGroup$SavedState(Parcel parcel) {
        super(parcel);
        this.F = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.F);
    }

    public PreferenceGroup$SavedState(Parcelable parcelable, int i) {
        super(parcelable);
        this.F = i;
    }
}

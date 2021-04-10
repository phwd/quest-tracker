package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.Preference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TwoStatePreference$SavedState extends Preference.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new Io1();
    public boolean F;

    public TwoStatePreference$SavedState(Parcel parcel) {
        super(parcel);
        this.F = parcel.readInt() != 1 ? false : true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.F ? 1 : 0);
    }

    public TwoStatePreference$SavedState(Parcelable parcelable) {
        super(parcelable);
    }
}

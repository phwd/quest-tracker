package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C3379kS();
    public final Bundle F;

    public Fragment$SavedState(Bundle bundle) {
        this.F = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.F);
    }

    public Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle();
        this.F = readBundle;
        if (classLoader != null && readBundle != null) {
            readBundle.setClassLoader(classLoader);
        }
    }
}

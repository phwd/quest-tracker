package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: I8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I8 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new AppCompatSpinner.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new AppCompatSpinner.SavedState[i];
    }
}

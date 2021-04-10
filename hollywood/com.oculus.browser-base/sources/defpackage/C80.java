package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.LinearLayoutManager;

/* renamed from: C80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C80 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new LinearLayoutManager.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new LinearLayoutManager.SavedState[i];
    }
}

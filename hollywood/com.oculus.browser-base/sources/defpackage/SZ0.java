package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* renamed from: SZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SZ0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new StaggeredGridLayoutManager.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new StaggeredGridLayoutManager.SavedState[i];
    }
}

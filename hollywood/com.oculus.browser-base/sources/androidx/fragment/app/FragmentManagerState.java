package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new LS();
    public ArrayList F;
    public ArrayList G;
    public BackStackState[] H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public String f9471J = null;

    public FragmentManagerState() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.F);
        parcel.writeStringList(this.G);
        parcel.writeTypedArray(this.H, i);
        parcel.writeInt(this.I);
        parcel.writeString(this.f9471J);
    }

    public FragmentManagerState(Parcel parcel) {
        this.F = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.G = parcel.createStringArrayList();
        this.H = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.I = parcel.readInt();
        this.f9471J = parcel.readString();
    }
}

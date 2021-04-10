package androidx.fragment.app;

import X.C00980Cl;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new C00980Cl();
    public int A00;
    public String A01 = null;
    public ArrayList<FragmentState> A02;
    public ArrayList<String> A03;
    public BackStackState[] A04;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.A02);
        parcel.writeStringList(this.A03);
        parcel.writeTypedArray(this.A04, i);
        parcel.writeInt(this.A00);
        parcel.writeString(this.A01);
    }

    public FragmentManagerState() {
    }

    public FragmentManagerState(Parcel parcel) {
        this.A02 = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.A03 = parcel.createStringArrayList();
        this.A04 = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.A00 = parcel.readInt();
        this.A01 = parcel.readString();
    }
}

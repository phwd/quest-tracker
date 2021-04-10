package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* access modifiers changed from: package-private */
public class FragmentTabHost$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<FragmentTabHost$SavedState> CREATOR = new Parcelable.Creator<FragmentTabHost$SavedState>() {
        /* class android.support.v4.app.FragmentTabHost$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FragmentTabHost$SavedState createFromParcel(Parcel parcel) {
            return new FragmentTabHost$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FragmentTabHost$SavedState[] newArray(int i) {
            return new FragmentTabHost$SavedState[i];
        }
    };
    String curTab;

    FragmentTabHost$SavedState(Parcel parcel) {
        super(parcel);
        this.curTab = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.curTab);
    }

    public String toString() {
        return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
    }
}

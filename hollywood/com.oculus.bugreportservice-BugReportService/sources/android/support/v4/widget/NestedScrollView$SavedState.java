package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* access modifiers changed from: package-private */
public class NestedScrollView$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /* class android.support.v4.widget.NestedScrollView$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public NestedScrollView$SavedState createFromParcel(Parcel parcel) {
            return new NestedScrollView$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public NestedScrollView$SavedState[] newArray(int i) {
            return new NestedScrollView$SavedState[i];
        }
    };
    public int scrollPosition;

    NestedScrollView$SavedState(Parcel parcel) {
        super(parcel);
        this.scrollPosition = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.scrollPosition);
    }

    @Override // java.lang.Object
    public String toString() {
        new StringBuilder().append("HorizontalScrollView.SavedState{");
        Integer.toHexString(System.identityHashCode(this));
        throw null;
    }
}

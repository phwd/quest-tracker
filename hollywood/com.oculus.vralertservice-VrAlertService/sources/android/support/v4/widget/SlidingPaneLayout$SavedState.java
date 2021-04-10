package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* access modifiers changed from: package-private */
public class SlidingPaneLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SlidingPaneLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SlidingPaneLayout$SavedState>() {
        /* class android.support.v4.widget.SlidingPaneLayout$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public SlidingPaneLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SlidingPaneLayout$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public SlidingPaneLayout$SavedState createFromParcel(Parcel parcel) {
            return new SlidingPaneLayout$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public SlidingPaneLayout$SavedState[] newArray(int i) {
            return new SlidingPaneLayout$SavedState[i];
        }
    };
    boolean isOpen;

    SlidingPaneLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isOpen = parcel.readInt() != 0;
    }

    @Override // android.support.v4.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.isOpen ? 1 : 0);
    }
}

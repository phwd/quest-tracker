package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

public class Toolbar$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
        /* class android.support.v7.widget.Toolbar$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public Toolbar$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new Toolbar$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public Toolbar$SavedState createFromParcel(Parcel parcel) {
            return new Toolbar$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public Toolbar$SavedState[] newArray(int i) {
            return new Toolbar$SavedState[i];
        }
    };
    int expandedMenuItemId;
    boolean isOverflowOpen;

    public Toolbar$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.expandedMenuItemId = parcel.readInt();
        this.isOverflowOpen = parcel.readInt() != 0;
    }

    @Override // android.support.v4.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.expandedMenuItemId);
        parcel.writeInt(this.isOverflowOpen ? 1 : 0);
    }
}

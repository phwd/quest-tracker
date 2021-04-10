package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* access modifiers changed from: package-private */
public class SearchView$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
        /* class android.support.v7.widget.SearchView$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public SearchView$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SearchView$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public SearchView$SavedState createFromParcel(Parcel parcel) {
            return new SearchView$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public SearchView$SavedState[] newArray(int i) {
            return new SearchView$SavedState[i];
        }
    };
    boolean isIconified;

    public SearchView$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isIconified = ((Boolean) parcel.readValue(null)).booleanValue();
    }

    @Override // android.support.v4.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(Boolean.valueOf(this.isIconified));
    }

    @Override // java.lang.Object
    public String toString() {
        new StringBuilder().append("SearchView.SavedState{");
        Integer.toHexString(System.identityHashCode(this));
        throw null;
    }
}

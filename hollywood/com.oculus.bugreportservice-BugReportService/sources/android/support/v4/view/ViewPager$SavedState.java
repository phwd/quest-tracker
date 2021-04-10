package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewPager$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
        /* class android.support.v4.view.ViewPager$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public ViewPager$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ViewPager$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public ViewPager$SavedState createFromParcel(Parcel parcel) {
            return new ViewPager$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public ViewPager$SavedState[] newArray(int i) {
            return new ViewPager$SavedState[i];
        }
    };
    Parcelable adapterState;
    ClassLoader loader;
    int position;

    @Override // android.support.v4.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.position);
        parcel.writeParcelable(this.adapterState, i);
    }

    @Override // java.lang.Object
    public String toString() {
        new StringBuilder().append("FragmentPager.SavedState{");
        Integer.toHexString(System.identityHashCode(this));
        throw null;
    }

    ViewPager$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        classLoader = classLoader == null ? ViewPager$SavedState.class.getClassLoader() : classLoader;
        this.position = parcel.readInt();
        this.adapterState = parcel.readParcelable(classLoader);
        this.loader = classLoader;
    }
}

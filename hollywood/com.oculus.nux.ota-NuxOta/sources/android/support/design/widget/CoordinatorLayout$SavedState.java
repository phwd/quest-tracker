package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.util.SparseArray;

public class CoordinatorLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<CoordinatorLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<CoordinatorLayout$SavedState>() {
        /* class android.support.design.widget.CoordinatorLayout$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.ClassLoaderCreator
        public CoordinatorLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new CoordinatorLayout$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public CoordinatorLayout$SavedState createFromParcel(Parcel parcel) {
            return new CoordinatorLayout$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public CoordinatorLayout$SavedState[] newArray(int i) {
            return new CoordinatorLayout$SavedState[i];
        }
    };
    SparseArray<Parcelable> behaviorStates;

    public CoordinatorLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        int[] iArr = new int[readInt];
        parcel.readIntArray(iArr);
        Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
        this.behaviorStates = new SparseArray<>(readInt);
        for (int i = 0; i < readInt; i++) {
            this.behaviorStates.append(iArr[i], readParcelableArray[i]);
        }
    }

    @Override // android.support.v4.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        SparseArray<Parcelable> sparseArray = this.behaviorStates;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.behaviorStates.keyAt(i2);
            parcelableArr[i2] = this.behaviorStates.valueAt(i2);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i);
    }
}

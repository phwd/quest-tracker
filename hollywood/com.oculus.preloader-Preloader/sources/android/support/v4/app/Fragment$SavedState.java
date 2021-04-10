package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator<Fragment$SavedState> CREATOR = new Parcelable.Creator<Fragment$SavedState>() {
        /* class android.support.v4.app.Fragment$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Fragment$SavedState createFromParcel(Parcel parcel) {
            return new Fragment$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public Fragment$SavedState[] newArray(int i) {
            return new Fragment$SavedState[i];
        }
    };
    final Bundle mState;

    public int describeContents() {
        return 0;
    }

    Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle bundle;
        this.mState = parcel.readBundle();
        if (classLoader != null && (bundle = this.mState) != null) {
            bundle.setClassLoader(classLoader);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mState);
    }
}

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* access modifiers changed from: package-private */
public class ActionMenuPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /* class android.support.v7.widget.ActionMenuPresenter$SavedState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ActionMenuPresenter$SavedState createFromParcel(Parcel parcel) {
            return new ActionMenuPresenter$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ActionMenuPresenter$SavedState[] newArray(int i) {
            return new ActionMenuPresenter$SavedState[i];
        }
    };
    public int openSubMenuId;

    public int describeContents() {
        return 0;
    }

    ActionMenuPresenter$SavedState() {
    }

    ActionMenuPresenter$SavedState(Parcel parcel) {
        this.openSubMenuId = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.openSubMenuId);
    }
}

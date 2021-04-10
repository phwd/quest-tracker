package androidx.appcompat.widget;

import X.AnonymousClass1JG;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class AppCompatSpinner$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<AppCompatSpinner$SavedState> CREATOR = new AnonymousClass1JG();
    public boolean A00;

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.A00 ? (byte) 1 : 0);
    }

    public AppCompatSpinner$SavedState(Parcel parcel) {
        super(parcel);
        this.A00 = parcel.readByte() != 0;
    }

    public AppCompatSpinner$SavedState(Parcelable parcelable) {
        super(parcelable);
    }
}

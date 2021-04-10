package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableByte extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableByte> CREATOR = new Parcelable.Creator<ObservableByte>() {
        /* class androidx.databinding.ObservableByte.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ObservableByte createFromParcel(Parcel parcel) {
            return new ObservableByte(parcel.readByte());
        }

        @Override // android.os.Parcelable.Creator
        public ObservableByte[] newArray(int i) {
            return new ObservableByte[i];
        }
    };
    static final long serialVersionUID = 1;
    private byte mValue;

    public int describeContents() {
        return 0;
    }

    public ObservableByte(byte b) {
        this.mValue = b;
    }

    public ObservableByte() {
    }

    public ObservableByte(Observable... observableArr) {
        super(observableArr);
    }

    public byte get() {
        return this.mValue;
    }

    public void set(byte b) {
        if (b != this.mValue) {
            this.mValue = b;
            notifyChange();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mValue);
    }
}

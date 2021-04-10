package androidx.recyclerview.widget;

import X.AnonymousClass1C8;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

@SuppressLint({"BanParcelableUsage"})
public class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> CREATOR = new AnonymousClass1C8();
    public int A00;
    public boolean A01;
    public int A02;
    public int[] A03;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FullSpanItem{mPosition=");
        sb.append(this.A02);
        sb.append(", mGapDir=");
        sb.append(this.A00);
        sb.append(", mHasUnwantedGapAfter=");
        sb.append(this.A01);
        sb.append(", mGapPerSpan=");
        sb.append(Arrays.toString(this.A03));
        sb.append('}');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int length;
        parcel.writeInt(this.A02);
        parcel.writeInt(this.A00);
        parcel.writeInt(this.A01 ? 1 : 0);
        int[] iArr = this.A03;
        if (iArr == null || (length = iArr.length) <= 0) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(length);
        parcel.writeIntArray(this.A03);
    }

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem() {
    }

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(Parcel parcel) {
        this.A02 = parcel.readInt();
        this.A00 = parcel.readInt();
        this.A01 = parcel.readInt() != 1 ? false : true;
        int readInt = parcel.readInt();
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            this.A03 = iArr;
            parcel.readIntArray(iArr);
        }
    }
}

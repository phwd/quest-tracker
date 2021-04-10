package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new QZ0();
    public int F;
    public int G;
    public int[] H;
    public boolean I;

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(Parcel parcel) {
        this.F = parcel.readInt();
        this.G = parcel.readInt();
        this.I = parcel.readInt() != 1 ? false : true;
        int readInt = parcel.readInt();
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            this.H = iArr;
            parcel.readIntArray(iArr);
        }
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("FullSpanItem{mPosition=");
        i.append(this.F);
        i.append(", mGapDir=");
        i.append(this.G);
        i.append(", mHasUnwantedGapAfter=");
        i.append(this.I);
        i.append(", mGapPerSpan=");
        i.append(Arrays.toString(this.H));
        i.append('}');
        return i.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.I ? 1 : 0);
        int[] iArr = this.H;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(iArr.length);
        parcel.writeIntArray(this.H);
    }
}

package androidx.fragment.app;

import X.AR;
import X.B5;
import X.B6;
import X.Ts;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new AR();
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final CharSequence A04;
    public final CharSequence A05;
    public final String A06;
    public final ArrayList<String> A07;
    public final ArrayList<String> A08;
    public final ArrayList<String> A09;
    public final boolean A0A;
    public final int[] A0B;
    public final int[] A0C;
    public final int[] A0D;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.A0D);
        parcel.writeStringList(this.A07);
        parcel.writeIntArray(this.A0C);
        parcel.writeIntArray(this.A0B);
        parcel.writeInt(this.A03);
        parcel.writeString(this.A06);
        parcel.writeInt(this.A02);
        parcel.writeInt(this.A01);
        TextUtils.writeToParcel(this.A05, parcel, 0);
        parcel.writeInt(this.A00);
        TextUtils.writeToParcel(this.A04, parcel, 0);
        parcel.writeStringList(this.A08);
        parcel.writeStringList(this.A09);
        parcel.writeInt(this.A0A ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.A0D = parcel.createIntArray();
        this.A07 = parcel.createStringArrayList();
        this.A0C = parcel.createIntArray();
        this.A0B = parcel.createIntArray();
        this.A03 = parcel.readInt();
        this.A06 = parcel.readString();
        this.A02 = parcel.readInt();
        this.A01 = parcel.readInt();
        this.A05 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.A00 = parcel.readInt();
        this.A04 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.A08 = parcel.createStringArrayList();
        this.A09 = parcel.createStringArrayList();
        this.A0A = parcel.readInt() != 0;
    }

    public BackStackState(Ts ts) {
        String str;
        ArrayList<B5> arrayList = ts.A0A;
        int size = arrayList.size();
        int[] iArr = new int[(size * 5)];
        this.A0D = iArr;
        if (ts.A0D) {
            ArrayList<String> arrayList2 = new ArrayList<>(size);
            this.A07 = arrayList2;
            int[] iArr2 = new int[size];
            this.A0C = iArr2;
            int[] iArr3 = new int[size];
            this.A0B = iArr3;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                B5 b5 = arrayList.get(i2);
                int i3 = i + 1;
                iArr[i] = b5.A00;
                if (b5.A05 != null) {
                    str = b5.A05.A0P;
                } else {
                    str = null;
                }
                arrayList2.add(str);
                int i4 = i3 + 1;
                iArr[i3] = b5.A01;
                int i5 = i4 + 1;
                iArr[i4] = b5.A02;
                int i6 = i5 + 1;
                iArr[i5] = b5.A03;
                i = i6 + 1;
                iArr[i6] = b5.A04;
                iArr2[i2] = b5.A07.ordinal();
                iArr3[i2] = b5.A06.ordinal();
            }
            this.A03 = ts.A06;
            this.A06 = ts.A09;
            this.A02 = ts.A00;
            this.A01 = ((B6) ts).A01;
            this.A05 = ts.A08;
            this.A00 = ((B6) ts).A00;
            this.A04 = ts.A07;
            this.A08 = ts.A0B;
            this.A09 = ts.A0C;
            this.A0A = ts.A0E;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }
}

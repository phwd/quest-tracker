package androidx.fragment.app;

import X.AbstractC004109o;
import X.AnonymousClass095;
import X.AnonymousClass0sD;
import X.C004009n;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new AnonymousClass095();
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

    public BackStackState(AnonymousClass0sD r13) {
        String str;
        ArrayList<C004009n> arrayList = r13.A0A;
        int size = arrayList.size();
        int[] iArr = new int[(size * 5)];
        this.A0D = iArr;
        if (r13.A0D) {
            ArrayList<String> arrayList2 = new ArrayList<>(size);
            this.A07 = arrayList2;
            int[] iArr2 = new int[size];
            this.A0C = iArr2;
            int[] iArr3 = new int[size];
            this.A0B = iArr3;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                C004009n r10 = arrayList.get(i2);
                int i3 = i + 1;
                iArr[i] = r10.A00;
                if (r10.A05 != null) {
                    str = r10.A05.mWho;
                } else {
                    str = null;
                }
                arrayList2.add(str);
                int i4 = i3 + 1;
                iArr[i3] = r10.A01;
                int i5 = i4 + 1;
                iArr[i4] = r10.A02;
                int i6 = i5 + 1;
                iArr[i5] = r10.A03;
                i = i6 + 1;
                iArr[i6] = r10.A04;
                iArr2[i2] = r10.A07.ordinal();
                iArr3[i2] = r10.A06.ordinal();
            }
            this.A03 = r13.A06;
            this.A06 = r13.A09;
            this.A02 = r13.A01;
            this.A01 = ((AbstractC004109o) r13).A01;
            this.A05 = r13.A08;
            this.A00 = ((AbstractC004109o) r13).A00;
            this.A04 = r13.A07;
            this.A08 = r13.A0B;
            this.A09 = r13.A0C;
            this.A0A = r13.A0E;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }
}

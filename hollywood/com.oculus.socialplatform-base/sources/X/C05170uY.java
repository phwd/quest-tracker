package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02C.LIBRARY})
/* renamed from: X.0uY  reason: invalid class name and case insensitive filesystem */
public final class C05170uY extends AnonymousClass0Cb {
    public int A00;
    public int A01;
    public int A02;
    public final int A03;
    public final int A04;
    public final Parcel A05;
    public final SparseIntArray A06;
    public final String A07;

    @Override // X.AnonymousClass0Cb
    public final int A01() {
        return this.A05.readInt();
    }

    @Override // X.AnonymousClass0Cb
    public final <T extends Parcelable> T A03() {
        return (T) this.A05.readParcelable(getClass().getClassLoader());
    }

    @Override // X.AnonymousClass0Cb
    public final AnonymousClass0Cb A04() {
        Parcel parcel = this.A05;
        int dataPosition = parcel.dataPosition();
        int i = this.A02;
        if (i == this.A04) {
            i = this.A03;
        }
        return new C05170uY(parcel, dataPosition, i, AnonymousClass006.A07(this.A07, "  "), super.A01, super.A02, super.A00);
    }

    @Override // X.AnonymousClass0Cb
    public final CharSequence A06() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.A05);
    }

    @Override // X.AnonymousClass0Cb
    public final String A07() {
        return this.A05.readString();
    }

    @Override // X.AnonymousClass0Cb
    public final void A08() {
        int i = this.A00;
        if (i >= 0) {
            int i2 = this.A06.get(i);
            Parcel parcel = this.A05;
            int dataPosition = parcel.dataPosition();
            parcel.setDataPosition(i2);
            parcel.writeInt(dataPosition - i2);
            parcel.setDataPosition(dataPosition);
        }
    }

    @Override // X.AnonymousClass0Cb
    public final void A0A(int i) {
        this.A05.writeInt(i);
    }

    @Override // X.AnonymousClass0Cb
    public final void A0B(Parcelable parcelable) {
        this.A05.writeParcelable(parcelable, 0);
    }

    @Override // X.AnonymousClass0Cb
    public final void A0D(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.A05, 0);
    }

    @Override // X.AnonymousClass0Cb
    public final void A0E(String str) {
        this.A05.writeString(str);
    }

    @Override // X.AnonymousClass0Cb
    public final void A0F(boolean z) {
        this.A05.writeInt(z ? 1 : 0);
    }

    @Override // X.AnonymousClass0Cb
    public final void A0G(byte[] bArr) {
        if (bArr != null) {
            Parcel parcel = this.A05;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
            return;
        }
        this.A05.writeInt(-1);
    }

    @Override // X.AnonymousClass0Cb
    public final boolean A0H() {
        if (this.A05.readInt() != 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0Cb
    public final boolean A0I(int i) {
        while (true) {
            int i2 = this.A02;
            if (i2 < this.A03) {
                int i3 = this.A01;
                if (i3 == i) {
                    return true;
                }
                if (String.valueOf(i3).compareTo(String.valueOf(i)) > 0) {
                    return false;
                }
                Parcel parcel = this.A05;
                parcel.setDataPosition(i2);
                int readInt = parcel.readInt();
                this.A01 = parcel.readInt();
                this.A02 += readInt;
            } else if (this.A01 != i) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override // X.AnonymousClass0Cb
    public final byte[] A0J() {
        Parcel parcel = this.A05;
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        parcel.readByteArray(bArr);
        return bArr;
    }

    @Override // X.AnonymousClass0Cb
    public final void A09(int i) {
        A08();
        this.A00 = i;
        this.A06.put(i, this.A05.dataPosition());
        A0A(0);
        A0A(i);
    }

    public C05170uY(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new C000502v(), new C000502v(), new C000502v());
    }

    public C05170uY(Parcel parcel, int i, int i2, String str, C000502v<String, Method> r7, C000502v<String, Method> r8, C000502v<String, Class<?>> r9) {
        super(r7, r8, r9);
        this.A06 = new SparseIntArray();
        this.A00 = -1;
        this.A02 = 0;
        this.A01 = -1;
        this.A05 = parcel;
        this.A04 = i;
        this.A03 = i2;
        this.A02 = i;
        this.A07 = str;
    }
}

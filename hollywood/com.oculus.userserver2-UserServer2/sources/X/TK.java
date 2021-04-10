package X;

import android.os.Parcel;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass2O.LIBRARY})
public final class TK extends AbstractC0056El {
    public int A00;
    public int A01;
    public int A02;
    public final int A03;
    public final int A04;
    public final Parcel A05;
    public final SparseIntArray A06;
    public final String A07;

    public TK(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new AnonymousClass3C(), new AnonymousClass3C(), new AnonymousClass3C());
    }

    public TK(Parcel parcel, int i, int i2, String str, AnonymousClass3C<String, Method> r7, AnonymousClass3C<String, Method> r8, AnonymousClass3C<String, Class<?>> r9) {
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

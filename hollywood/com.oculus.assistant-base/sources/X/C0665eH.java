package X;

import android.os.Parcel;
import android.util.SparseIntArray;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.eH  reason: case insensitive filesystem */
public final class C0665eH extends AbstractC00293k {
    public int A00;
    public int A01;
    public int A02;
    public final int A03;
    public final int A04;
    public final Parcel A05;
    public final SparseIntArray A06;
    public final String A07;

    public C0665eH(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), OacrConstants.AUTO_SPEECH_DOMAIN, new AnonymousClass0m(), new AnonymousClass0m(), new AnonymousClass0m());
    }

    public C0665eH(Parcel parcel, int i, int i2, String str, AnonymousClass0m r7, AnonymousClass0m r8, AnonymousClass0m r9) {
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

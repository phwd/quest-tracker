package X;

import com.squareup.okhttp.internal.framed.Hpack;

/* renamed from: X.0Fw  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01270Fw extends AnonymousClass0LP {
    public static final int[] A04 = C06060lt.A06;
    public int A00;
    public AbstractC05960li A01 = C02600aN.A01;
    public int[] A02 = A04;
    public final C06080lv A03;

    public AbstractC01270Fw(C06080lv r2, int i, AbstractC05940lg r4) {
        super(i, r4);
        this.A03 = r2;
        if (A0a(EnumC05870lY.ESCAPE_NON_ASCII)) {
            this.A00 = Hpack.PREFIX_7_BITS;
        }
    }
}

package X;

import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public final class MG {
    public final int A00 = 32;
    public final It<YF> A01 = new It<>(2);
    public final It<YE> A02 = new It<>(8);
    public final int A03 = 16;

    public static MG A00() {
        return new MG();
    }

    public final YF A01() {
        YF A10 = this.A01.A10();
        if (A10 == null) {
            A10 = new YF(this.A03);
        }
        A10.A05(this);
        return A10;
    }

    public final YE A02() {
        YE A10 = this.A02.A10();
        if (A10 == null) {
            A10 = new YE(this.A03);
        }
        A10.A05(this);
        return A10;
    }
}

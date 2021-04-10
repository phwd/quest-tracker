package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class KS {
    public static KR A00;
    public static final int[] A01 = {4096};

    static {
        KR A002 = YL.A00();
        if (A002 == null) {
            A002 = YM.A00();
        }
        A00 = A002;
    }
}

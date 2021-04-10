package X;

import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.12M  reason: invalid class name */
public final class AnonymousClass12M {
    public final TriState[] A00;
    public final TriState[] A01;
    public final TriState[] A02;

    public AnonymousClass12M() {
        TriState triState;
        TriState[] triStateArr = new TriState[2];
        int i = 0;
        do {
            triState = TriState.UNSET;
            triStateArr[i] = triState;
            i++;
        } while (i < 2);
        this.A02 = triStateArr;
        TriState[] triStateArr2 = new TriState[2];
        int i2 = 0;
        do {
            triStateArr2[i2] = triState;
            i2++;
        } while (i2 < 2);
        this.A01 = triStateArr2;
        TriState[] triStateArr3 = new TriState[2];
        int i3 = 0;
        do {
            triStateArr3[i3] = triState;
            i3++;
        } while (i3 < 2);
        this.A00 = triStateArr3;
    }
}

package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class G1 {
    public final G0 A00;
    public final MG A01;

    public G1(MG mg, G0 g0) {
        this.A01 = mg;
        this.A00 = g0;
    }
}

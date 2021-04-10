package X;

import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@ThreadSafe
@javax.annotation.concurrent.ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Y8 implements GatekeeperWriter {
    public final Y9 A00;
    public final OF A01 = new OF();
    public final OL A02;
    @Nullable
    public final Y5 A03;

    public Y8(Y9 y9, OL ol, @Nullable Y5 y5) {
        this.A00 = y9;
        this.A02 = ol;
        this.A03 = y5;
    }
}

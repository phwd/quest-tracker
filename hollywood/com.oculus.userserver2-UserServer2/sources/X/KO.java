package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public final class KO {
    public final long A00;

    public KO(long j) {
        this.A00 = j;
    }
}

package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class G0 {
    public final C0260Yj A00;
    public final String A01;
    public final AtomicInteger A02 = new AtomicInteger(-1);

    public G0(C0260Yj yj, String str) {
        this.A01 = str;
        this.A00 = yj;
    }
}

package X;

import java.io.IOException;
import java.util.List;

public class E6 implements Wc {
    @Override // X.Wc
    public final boolean onData(int i, Dp dp, int i2, boolean z) throws IOException {
        dp.A3i((long) i2);
        return true;
    }

    @Override // X.Wc
    public final boolean onHeaders(int i, List<C0165Wq> list, boolean z) {
        return true;
    }

    @Override // X.Wc
    public final boolean onRequest(int i, List<C0165Wq> list) {
        return true;
    }
}

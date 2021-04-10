package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0Oq  reason: invalid class name and case insensitive filesystem */
public class C01920Oq implements AbstractC04720hN {
    @Override // X.AbstractC04720hN
    public final boolean onData(int i, AnonymousClass0Od r4, int i2, boolean z) throws IOException {
        r4.A8T((long) i2);
        return true;
    }

    @Override // X.AbstractC04720hN
    public final boolean onHeaders(int i, List<C04870hr> list, boolean z) {
        return true;
    }

    @Override // X.AbstractC04720hN
    public final boolean onRequest(int i, List<C04870hr> list) {
        return true;
    }
}

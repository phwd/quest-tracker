package X;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Df extends WE {
    @Override // X.WE
    public final WE deadlineNanoTime(long j) {
        return this;
    }

    @Override // X.WE
    public final void throwIfReached() throws IOException {
    }

    @Override // X.WE
    public final WE timeout(long j, TimeUnit timeUnit) {
        return this;
    }
}

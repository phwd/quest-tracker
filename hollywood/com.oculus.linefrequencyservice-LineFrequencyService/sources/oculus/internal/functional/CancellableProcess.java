package oculus.internal.functional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CancellableProcess<T> extends CompletableFuture<T> {
    private volatile boolean lastCancelInterrupted;
    protected volatile Future upstream;

    public CancellableProcess() {
        this(null);
    }

    public CancellableProcess(Future upstream2) {
        this.upstream = upstream2;
        this.lastCancelInterrupted = false;
    }

    public void setUpstream(Future upstream2) {
        if (isCancelled() && upstream2 != null && !upstream2.isCancelled()) {
            upstream2.cancel(this.lastCancelInterrupted);
        }
        this.upstream = upstream2;
    }

    public Future getUpstream() {
        return this.upstream;
    }

    public boolean cancel(boolean interrupt) {
        if (isCancelled()) {
            return true;
        }
        this.lastCancelInterrupted = interrupt;
        boolean result = super.cancel(interrupt);
        Future localUpstream = this.upstream;
        if (result && localUpstream != null && !localUpstream.isCancelled()) {
            localUpstream.cancel(interrupt);
        }
        return result;
    }
}

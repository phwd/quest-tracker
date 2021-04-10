package X;

import java.util.concurrent.Callable;

/* renamed from: X.1wv  reason: invalid class name and case insensitive filesystem */
public final class CallableC12161wv extends AbstractC12151wu implements Callable<Void> {
    public static final long serialVersionUID = 1811839108042568751L;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Void call() throws Exception {
        this.runner = Thread.currentThread();
        try {
            this.runnable.run();
            return null;
        } finally {
            lazySet(AbstractC12151wu.A01);
            this.runner = null;
        }
    }

    public CallableC12161wv(Runnable runnable) {
        super(runnable);
    }
}

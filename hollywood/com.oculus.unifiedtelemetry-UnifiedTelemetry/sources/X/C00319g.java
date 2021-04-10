package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@Beta
@GwtIncompatible
/* renamed from: X.9g  reason: invalid class name and case insensitive filesystem */
public final class C00319g implements Closeable {
    public static final AnonymousClass9Y A03;
    @MonotonicNonNullDecl
    public Throwable A00;
    @VisibleForTesting
    public final AnonymousClass9Y A01;
    public final Deque<Closeable> A02 = new ArrayDeque(4);

    static {
        AnonymousClass9Y r0;
        if (UQ.A01 != null) {
            r0 = UQ.A00;
        } else {
            r0 = UR.A00;
        }
        A03 = r0;
    }

    public final void A00(Throwable th) throws IOException {
        this.A00 = th;
        Throwables.propagateIfInstanceOf(th, IOException.class);
        if ((th instanceof RuntimeException) || (th instanceof Error)) {
            throw th;
        }
        throw new RuntimeException(th);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        Throwable th = this.A00;
        while (true) {
            Deque<Closeable> deque = this.A02;
            if (deque.isEmpty()) {
                break;
            }
            Closeable removeFirst = deque.removeFirst();
            try {
                removeFirst.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.A01.A5S(removeFirst, th, th2);
                }
            }
        }
        if (this.A00 == null && th != null) {
            Throwables.propagateIfInstanceOf(th, IOException.class);
            if ((th instanceof RuntimeException) || (th instanceof Error)) {
                throw th;
            }
            throw new AssertionError(th);
        }
    }

    @VisibleForTesting
    public C00319g(AnonymousClass9Y r3) {
        this.A01 = r3;
    }
}

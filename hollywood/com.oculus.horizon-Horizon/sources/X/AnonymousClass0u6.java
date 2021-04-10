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
/* renamed from: X.0u6  reason: invalid class name */
public final class AnonymousClass0u6 implements Closeable {
    public static final AnonymousClass0u5 A03;
    @MonotonicNonNullDecl
    public Throwable A00;
    @VisibleForTesting
    public final AnonymousClass0u5 A01;
    public final Deque<Closeable> A02 = new ArrayDeque(4);

    static {
        AnonymousClass0u5 r0;
        if (C03490d4.A01 != null) {
            r0 = C03490d4.A00;
        } else {
            r0 = C03500d5.A00;
        }
        A03 = r0;
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
                    this.A01.A9b(removeFirst, th, th2);
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
    public AnonymousClass0u6(AnonymousClass0u5 r3) {
        this.A01 = r3;
    }
}

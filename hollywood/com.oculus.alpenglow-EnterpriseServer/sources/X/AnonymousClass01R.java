package X;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Iterator;

/* renamed from: X.01R  reason: invalid class name */
public final class AnonymousClass01R {
    public final ArrayDeque<AnonymousClass01Q> A00;
    @Nullable
    public final Runnable A01;

    @MainThread
    public final void A00() {
        Iterator<AnonymousClass01Q> descendingIterator = this.A00.descendingIterator();
        while (descendingIterator.hasNext()) {
            AnonymousClass01Q next = descendingIterator.next();
            if (next.A01) {
                next.A00();
                return;
            }
        }
        Runnable runnable = this.A01;
        if (runnable != null) {
            runnable.run();
        }
    }

    public AnonymousClass01R() {
        this(null);
    }

    public AnonymousClass01R(@Nullable Runnable runnable) {
        this.A00 = new ArrayDeque<>();
        this.A01 = runnable;
    }
}

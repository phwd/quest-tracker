package X;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Iterator;

/* renamed from: X.1R  reason: invalid class name */
public final class AnonymousClass1R {
    public final ArrayDeque<AnonymousClass1Q> A00;
    @Nullable
    public final Runnable A01;

    @MainThread
    public final void A00() {
        Iterator<AnonymousClass1Q> descendingIterator = this.A00.descendingIterator();
        while (descendingIterator.hasNext()) {
            AnonymousClass1Q next = descendingIterator.next();
            if (next.A01) {
                Au au = ((To) next).A00;
                au.A0e(true);
                if (au.A0L.A01) {
                    Au.A0D(au);
                    return;
                } else {
                    au.A01.A00();
                    return;
                }
            }
        }
        Runnable runnable = this.A01;
        if (runnable != null) {
            runnable.run();
        }
    }

    public AnonymousClass1R() {
        this(null);
    }

    public AnonymousClass1R(@Nullable Runnable runnable) {
        this.A00 = new ArrayDeque<>();
        this.A01 = runnable;
    }
}

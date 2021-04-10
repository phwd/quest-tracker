package X;

import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.Queue;

@VisibleForTesting
/* renamed from: X.1cM  reason: invalid class name */
public final class AnonymousClass1cM<A> {
    public static final Queue<AnonymousClass1cM<?>> A01 = new ArrayDeque(0);
    public A A00;

    public final int hashCode() {
        return 0 + this.A00.hashCode();
    }

    /* JADX WARN: Incorrect args count in method signature: <A:Ljava/lang/Object;>(TA;II)LX/1cM<TA;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public static AnonymousClass1cM A00(Object obj) {
        AnonymousClass1cM<?> poll;
        Queue<AnonymousClass1cM<?>> queue = A01;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new AnonymousClass1cM<>();
        }
        poll.A00 = obj;
        return poll;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1cM) || !this.A00.equals(((AnonymousClass1cM) obj).A00)) {
            return false;
        }
        return true;
    }
}

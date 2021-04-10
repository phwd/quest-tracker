package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sA  reason: invalid class name */
public final class AnonymousClass1sA {
    public static final AnonymousClass1sA A01 = new AnonymousClass1sA();
    public final Queue<AnonymousClass1qF> A00 = new ArrayBlockingQueue(20);

    public final String toString() {
        return this.A00.toString();
    }
}

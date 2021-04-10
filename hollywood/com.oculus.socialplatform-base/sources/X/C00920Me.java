package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Me  reason: invalid class name and case insensitive filesystem */
public final class C00920Me {
    public static final C00920Me A01 = new C00920Me();
    public final Queue<EnumC00910Md> A00 = new ArrayBlockingQueue(20);

    public final void A00(EnumC00910Md r4) {
        Queue<EnumC00910Md> queue = this.A00;
        if (queue.size() + 1 > 20) {
            queue.poll();
        }
        queue.add(r4);
    }

    public final String toString() {
        return this.A00.toString();
    }
}

package X;

import androidx.recyclerview.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1xl  reason: invalid class name and case insensitive filesystem */
public final class C12541xl {
    public static void A00(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 != RecyclerView.FOREVER_NS) {
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = RecyclerView.FOREVER_NS;
                }
            } else {
                return;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
    }
}

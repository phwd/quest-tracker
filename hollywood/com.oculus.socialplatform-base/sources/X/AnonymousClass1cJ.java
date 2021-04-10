package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

/* renamed from: X.1cJ  reason: invalid class name */
public class AnonymousClass1cJ<T, Y> {
    public long A00;
    public long A01;
    public final Map<T, Y> A02 = new LinkedHashMap(100, 0.75f, true);

    public int A00(@Nullable Y y) {
        return 1;
    }

    @Nullable
    public final synchronized Y A01(@NonNull T t) {
        return this.A02.get(t);
    }

    public final synchronized void A02(long j) {
        while (this.A00 > j) {
            Iterator<Map.Entry<T, Y>> it = this.A02.entrySet().iterator();
            Map.Entry<T, Y> next = it.next();
            Y value = next.getValue();
            this.A00 -= (long) A00(value);
            T key = next.getKey();
            it.remove();
            A03(key, value);
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (TT;TY;)TY; */
    @Nullable
    public final synchronized void A04(@NonNull Object obj, @Nullable Object obj2) {
        long A002 = (long) A00(obj2);
        if (A002 >= this.A01) {
            A03(obj, obj2);
        } else {
            if (obj2 != null) {
                this.A00 += A002;
            }
            Y put = this.A02.put(obj, obj2);
            if (put != null) {
                this.A00 -= (long) A00(put);
                if (!put.equals(obj2)) {
                    A03(obj, put);
                }
            }
            A02(this.A01);
        }
    }

    private final void A03(@NonNull T t, @Nullable Y y) {
        if (this instanceof AnonymousClass1cL) {
            Queue<AnonymousClass1cM<?>> queue = AnonymousClass1cM.A01;
            synchronized (queue) {
                queue.offer(t);
            }
        } else if (this instanceof C07821dS) {
            Y y2 = y;
            AbstractC08671f5 r0 = ((C07821dS) this).A00;
            if (r0 != null && y2 != null) {
                r0.A06.A00(y2, true);
            }
        }
    }

    public AnonymousClass1cJ(long j) {
        this.A01 = j;
    }
}

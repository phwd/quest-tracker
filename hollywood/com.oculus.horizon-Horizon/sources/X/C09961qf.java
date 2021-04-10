package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qf  reason: invalid class name and case insensitive filesystem */
public final class C09961qf<T> {
    @GuardedBy("itself")
    public static final Map<Object, Integer> A03 = new IdentityHashMap();
    @GuardedBy("this")
    public int A00;
    @GuardedBy("this")
    @Nullable
    public T A01;
    public final AnonymousClass1ou<T> A02;

    public static void A00(C09961qf r2) {
        boolean z;
        synchronized (r2) {
            z = false;
            if (r2.A00 > 0) {
                z = true;
            }
        }
        if (!z) {
            throw new C10501td();
        }
    }

    @Nullable
    public final synchronized T A01() {
        return this.A01;
    }

    public C09961qf(T t, AnonymousClass1ou<T> r5) {
        if (t != null) {
            this.A01 = t;
            if (r5 != null) {
                this.A02 = r5;
                this.A00 = 1;
                Map<Object, Integer> map = A03;
                synchronized (map) {
                    Integer num = map.get(t);
                    if (num == null) {
                        map.put(t, 1);
                    } else {
                        map.put(t, Integer.valueOf(num.intValue() + 1));
                    }
                }
                return;
            }
        }
        throw null;
    }
}

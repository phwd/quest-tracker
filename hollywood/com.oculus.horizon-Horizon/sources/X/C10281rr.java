package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rr  reason: invalid class name and case insensitive filesystem */
public final class C10281rr<K, V> {
    @GuardedBy("this")
    public int A00 = 0;
    public final AnonymousClass1tZ<V> A01;
    @GuardedBy("this")
    public final LinkedHashMap<K, V> A02 = new LinkedHashMap<>();

    public final synchronized int A00() {
        return this.A02.size();
    }

    public final synchronized int A01() {
        return this.A00;
    }

    @Nullable
    public final synchronized V A02(K k) {
        V remove;
        int i;
        remove = this.A02.remove(k);
        int i2 = this.A00;
        if (remove == null) {
            i = 0;
        } else {
            i = this.A01.A4N(remove);
        }
        this.A00 = i2 - i;
        return remove;
    }

    /* JADX WARN: Incorrect return type in method signature: (TK;TV;)TV; */
    @Nullable
    public final synchronized void A03(Object obj, Object obj2) {
        int i;
        LinkedHashMap<K, V> linkedHashMap = this.A02;
        V remove = linkedHashMap.remove(obj);
        int i2 = this.A00;
        if (remove == null) {
            i = 0;
        } else {
            i = this.A01.A4N(remove);
        }
        this.A00 = i2 - i;
        linkedHashMap.put(obj, obj2);
        this.A00 += this.A01.A4N(obj2);
    }

    public C10281rr(AnonymousClass1tZ<V> r2) {
        this.A01 = r2;
    }
}

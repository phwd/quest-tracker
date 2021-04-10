package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@VisibleForTesting
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1hr  reason: invalid class name and case insensitive filesystem */
public class C09391hr<V> {
    public int A00;
    public final int A01;
    public final int A02;
    public final Queue A03;

    @Nullable
    public final V A00() {
        if (!(this instanceof C09381hq)) {
            return (V) this.A03.poll();
        }
        C09381hq r4 = (C09381hq) this;
        C00830Jv<V> r3 = (C00830Jv) r4.A03.poll();
        SoftReference<T> softReference = r3.A00;
        T t = softReference == null ? (V) null : softReference.get();
        if (softReference != null) {
            softReference.clear();
            r3.A00 = null;
        }
        SoftReference<T> softReference2 = r3.A01;
        if (softReference2 != null) {
            softReference2.clear();
            r3.A01 = null;
        }
        SoftReference<T> softReference3 = r3.A02;
        if (softReference3 != null) {
            softReference3.clear();
            r3.A02 = null;
        }
        r4.A00.add(r3);
        return (V) t;
    }

    public C09391hr(int i, int i2, int i3) {
        boolean z = true;
        C00740Ii.A03(i > 0);
        C00740Ii.A03(i2 >= 0);
        C00740Ii.A03(i3 < 0 ? false : z);
        this.A01 = i;
        this.A02 = i2;
        this.A03 = new LinkedList();
        this.A00 = i3;
    }
}

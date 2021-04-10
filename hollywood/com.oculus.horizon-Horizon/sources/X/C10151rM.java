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
/* renamed from: X.1rM  reason: invalid class name and case insensitive filesystem */
public class C10151rM<V> {
    public int A00;
    public final int A01;
    public final int A02;
    public final Queue A03;

    @Nullable
    public final V A00() {
        if (!(this instanceof AnonymousClass1rQ)) {
            return (V) this.A03.poll();
        }
        AnonymousClass1rQ r4 = (AnonymousClass1rQ) this;
        AnonymousClass1sU<V> r3 = (AnonymousClass1sU) r4.A03.poll();
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

    public C10151rM(int i, int i2, int i3) {
        boolean z = true;
        AnonymousClass0KU.A03(i > 0);
        AnonymousClass0KU.A03(i2 >= 0);
        AnonymousClass0KU.A03(i3 < 0 ? false : z);
        this.A01 = i;
        this.A02 = i2;
        this.A03 = new LinkedList();
        this.A00 = i3;
    }
}

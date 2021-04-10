package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class Fz<T> {
    @GuardedBy("this")
    public final HashMap<T, Fz<T>.BatchLock> A00 = new HashMap<>();

    public final synchronized Fz<T>.BatchLock A02(T t) {
        Fz<T>.BatchLock batchLock;
        HashMap<T, Fz<T>.BatchLock> hashMap = this.A00;
        batchLock = (AbstractC0081Fy) hashMap.get(t);
        if (batchLock == null) {
            batchLock = A03(t);
            hashMap.put(t, batchLock);
        }
        batchLock.A00++;
        return batchLock;
    }

    public abstract Fz<T>.BatchLock A03(T t);

    public static Fz<Object> A00(boolean z) {
        Fz<Object> fz;
        if (!z) {
            return C0271Yv.A01();
        }
        synchronized (Z7.class) {
            fz = Z7.A00;
            if (fz == null) {
                fz = new Z7();
                Z7.A00 = fz;
            }
        }
        return fz;
    }
}

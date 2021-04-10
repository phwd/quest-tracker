package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0GE  reason: invalid class name */
public abstract class AnonymousClass0GE<T> {
    @GuardedBy("this")
    public final HashMap<T, AnonymousClass0GE<T>.BatchLock> A00 = new HashMap<>();

    public abstract AnonymousClass0GE<T>.BatchLock A00(T t);
}

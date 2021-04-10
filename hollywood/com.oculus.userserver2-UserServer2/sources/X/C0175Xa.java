package X;

import java.util.ArrayDeque;
import java.util.Deque;
import okhttp3.RealCall;

/* renamed from: X.Xa  reason: case insensitive filesystem */
public final class C0175Xa {
    public final Deque<RealCall.AsyncCall> A00 = new ArrayDeque();
    public final Deque<RealCall.AsyncCall> A01 = new ArrayDeque();
    public final Deque<C0052Ec> A02 = new ArrayDeque();

    /* JADX WARN: Incorrect types in method signature: <T:Ljava/lang/Object;>(Ljava/util/Deque<TT;>;TT;Z)V */
    public static void A00(C0175Xa xa, Deque deque, Object obj) {
        synchronized (xa) {
            if (deque.remove(obj)) {
                xa.A01.size();
                xa.A02.size();
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
    }
}

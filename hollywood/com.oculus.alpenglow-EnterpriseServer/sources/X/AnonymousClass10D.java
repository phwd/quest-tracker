package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.10D  reason: invalid class name */
public abstract class AnonymousClass10D {
    public final String toString() {
        Object obj;
        AnonymousClass0yA r1 = (AnonymousClass0yA) this;
        if (r1 instanceof RunnableC08760xm) {
            obj = ((RunnableC08760xm) r1).A00;
        } else if (!(r1 instanceof RunnableC08770xn)) {
            obj = r1.A00();
        } else {
            obj = ((RunnableC08770xn) r1).A00;
        }
        return obj.toString();
    }
}

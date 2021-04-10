package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Hu  reason: invalid class name */
public abstract class AnonymousClass1Hu {
    public final String toString() {
        Object obj;
        AnonymousClass1XM r1 = (AnonymousClass1XM) this;
        if (r1 instanceof AnonymousClass1X8) {
            obj = ((AnonymousClass1X8) r1).A00;
        } else if (!(r1 instanceof AnonymousClass1X9)) {
            obj = r1.A00();
        } else {
            obj = ((AnonymousClass1X9) r1).A00;
        }
        return obj.toString();
    }
}

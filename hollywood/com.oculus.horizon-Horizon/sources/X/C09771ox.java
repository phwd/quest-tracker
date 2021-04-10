package X;

import javax.annotation.Nullable;

/* renamed from: X.1ox  reason: invalid class name and case insensitive filesystem */
public class C09771ox implements AbstractC10391sj {
    @Override // X.AbstractC10391sj
    public final boolean A8M() {
        return false;
    }

    @Override // X.AbstractC10391sj
    public final void A8G(C09961qf<Object> r6, @Nullable Throwable th) {
        String name;
        Object A01 = r6.A01();
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(System.identityHashCode(r6));
        if (A01 == null) {
            name = null;
        } else {
            name = A01.getClass().getName();
        }
        objArr[2] = name;
        C01080Kb.A02(AnonymousClass1qa.class, "Finalized without closing: %x %x (type = %s)", objArr);
    }
}

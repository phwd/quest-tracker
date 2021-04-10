package X;

import java.lang.reflect.Type;

/* renamed from: X.0gn  reason: invalid class name */
public final class AnonymousClass0gn extends AbstractC04730rA {
    @Override // X.AbstractC04730rA
    public final AbstractC02190iF A00(AbstractC02190iF r10, Type type, AnonymousClass0r8 r12, AnonymousClass0r9 r13) {
        if (!AbstractC05440vj.class.isAssignableFrom(r10._class)) {
            return r10;
        }
        AbstractC02190iF A06 = r10.A06(0);
        AbstractC02190iF A062 = r10.A06(1);
        if (A06 == null) {
            A06 = r13.A09(Object.class, null);
        }
        if (A062 == null) {
            A062 = r13.A09(Object.class, null);
        }
        return new AnonymousClass0C8(r10._class, A06, A062, null, null, false);
    }
}

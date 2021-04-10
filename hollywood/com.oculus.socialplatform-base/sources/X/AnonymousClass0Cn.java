package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0Cn  reason: invalid class name */
public final class AnonymousClass0Cn extends AnonymousClass0Os {
    public static final long serialVersionUID = 1;

    @Override // X.AnonymousClass0Os, X.AbstractC01880hF, X.AbstractC04520qa
    public final AbstractC04520qa A04(AbstractC02220iI r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0Cn(this, r2);
    }

    private final Object A00(AbstractC02280iQ r7, AbstractC02210iH r8, AnonymousClass0OD r9) throws IOException, C03620oC {
        JsonDeserializer<Object> A0C = A0C(r8);
        if (A0C != null) {
            if (r9 != null) {
                r9.A0F();
                r7 = r9.A0b(r7);
                r7.A0j();
            }
            return A0C.A0A(r7, r8);
        }
        Object A02 = AbstractC04520qa.A02(r7, this._baseType);
        if (A02 != null) {
            return A02;
        }
        if (r7.A0i() == EnumC03640oE.START_ARRAY) {
            return super.A07(r7, r8);
        }
        throw AbstractC02210iH.A00(r7, EnumC03640oE.FIELD_NAME, AnonymousClass006.A0C("missing property '", this._typePropertyName, "' that is to contain type id  (for class ", this._baseType._class.getName(), ")"));
    }

    @Override // X.AnonymousClass0Os, X.AbstractC01880hF, X.AbstractC04520qa
    public final EnumC03570nq A03() {
        return EnumC03570nq.PROPERTY;
    }

    @Override // X.AnonymousClass0Os, X.AbstractC04520qa
    public final Object A07(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        if (r3.A0i() == EnumC03640oE.START_ARRAY) {
            return A08(r3, r4);
        }
        return A09(r3, r4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r3 != null) goto L_0x003e;
     */
    @Override // X.AnonymousClass0Os, X.AbstractC04520qa
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A09(X.AbstractC02280iQ r6, X.AbstractC02210iH r7) throws java.io.IOException, X.C03620oC {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Cn.A09(X.0iQ, X.0iH):java.lang.Object");
    }

    public AnonymousClass0Cn(AbstractC02190iF r1, AbstractC04530qb r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass0Cn(AnonymousClass0Cn r1, AbstractC02220iI r2) {
        super(r1, r2);
    }
}

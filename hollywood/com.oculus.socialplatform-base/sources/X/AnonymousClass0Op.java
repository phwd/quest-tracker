package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0Op  reason: invalid class name */
public final class AnonymousClass0Op extends AbstractC01880hF implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AbstractC01880hF, X.AbstractC04520qa
    public final AbstractC04520qa A04(AbstractC02220iI r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0Op(this, r2);
    }

    private final Object A00(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        EnumC03640oE A0i = r6.A0i();
        EnumC03640oE r4 = EnumC03640oE.START_OBJECT;
        if (A0i == r4) {
            EnumC03640oE A0j = r6.A0j();
            EnumC03640oE r3 = EnumC03640oE.FIELD_NAME;
            if (A0j == r3) {
                String A0m = r6.A0m();
                JsonDeserializer<Object> A0D = A0D(r7, A0m);
                r6.A0j();
                if (this._typeIdVisible && r6.A0i() == r4) {
                    AnonymousClass0OD r1 = new AnonymousClass0OD(null);
                    r1.A0I();
                    r1.A0R(this._typePropertyName);
                    r1.A0U(A0m);
                    r6 = AnonymousClass0HV.A00(r1.A0b(r6), r6);
                    r6.A0j();
                }
                Object A0A = A0D.A0A(r6, r7);
                EnumC03640oE A0j2 = r6.A0j();
                EnumC03640oE r12 = EnumC03640oE.END_OBJECT;
                if (A0j2 == r12) {
                    return A0A;
                }
                throw AbstractC02210iH.A00(r6, r12, "expected closing END_OBJECT after type information and deserialized value");
            }
            throw AbstractC02210iH.A00(r6, r3, AnonymousClass006.A09("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
        }
        throw AbstractC02210iH.A00(r6, r4, AnonymousClass006.A07("need JSON Object to contain As.WRAPPER_OBJECT type information for class ", this._baseType._class.getName()));
    }

    @Override // X.AbstractC01880hF, X.AbstractC04520qa
    public final EnumC03570nq A03() {
        return EnumC03570nq.WRAPPER_OBJECT;
    }

    @Override // X.AbstractC04520qa
    public final Object A07(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public final Object A08(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public final Object A09(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public final Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(r2, r3);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0iF;LX/0qb;Ljava/lang/String;ZLjava/lang/Class<*>;)V */
    public AnonymousClass0Op(AbstractC02190iF r7, AbstractC04530qb r8, String str, boolean z) {
        super(r7, r8, str, z, null);
    }

    public AnonymousClass0Op(AnonymousClass0Op r1, AbstractC02220iI r2) {
        super(r1, r2);
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0Os  reason: invalid class name */
public class AnonymousClass0Os extends AbstractC01880hF implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AbstractC01880hF, X.AbstractC04520qa
    public AbstractC04520qa A04(AbstractC02220iI r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0Os(this, r2);
    }

    private final Object A01(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        String A5W;
        EnumC03640oE r1;
        boolean A0K = r6.A0K();
        if (!A0K) {
            if (this._defaultImpl == null) {
                throw AbstractC02210iH.A00(r6, EnumC03640oE.START_ARRAY, AnonymousClass006.A07("need JSON Array to contain As.WRAPPER_ARRAY type information for class ", this._baseType._class.getName()));
            }
            A5W = this._idResolver.A5W();
        } else {
            EnumC03640oE A0j = r6.A0j();
            EnumC03640oE r3 = EnumC03640oE.VALUE_STRING;
            if (A0j == r3) {
                A5W = r6.A0m();
                r6.A0j();
            } else {
                if (this._defaultImpl == null) {
                    throw AbstractC02210iH.A00(r6, r3, AnonymousClass006.A09("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
                }
                A5W = this._idResolver.A5W();
            }
        }
        JsonDeserializer<Object> A0D = A0D(r7, A5W);
        if (this._typeIdVisible && r6.A0i() == EnumC03640oE.START_OBJECT) {
            AnonymousClass0OD r12 = new AnonymousClass0OD(null);
            r12.A0I();
            r12.A0R(this._typePropertyName);
            r12.A0U(A5W);
            r6 = AnonymousClass0HV.A00(r12.A0b(r6), r6);
            r6.A0j();
        }
        Object A0A = A0D.A0A(r6, r7);
        if (!A0K || r6.A0j() == (r1 = EnumC03640oE.END_ARRAY)) {
            return A0A;
        }
        throw AbstractC02210iH.A00(r6, r1, "expected closing END_ARRAY after type information and deserialized value");
    }

    @Override // X.AbstractC01880hF, X.AbstractC04520qa
    public EnumC03570nq A03() {
        return EnumC03570nq.WRAPPER_ARRAY;
    }

    @Override // X.AbstractC04520qa
    public Object A07(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A01(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public final Object A08(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A01(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public Object A09(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A01(r2, r3);
    }

    @Override // X.AbstractC04520qa
    public final Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A01(r2, r3);
    }

    public AnonymousClass0Os(AbstractC02190iF r1, AbstractC04530qb r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass0Os(AnonymousClass0Os r1, AbstractC02220iI r2) {
        super(r1, r2);
    }
}

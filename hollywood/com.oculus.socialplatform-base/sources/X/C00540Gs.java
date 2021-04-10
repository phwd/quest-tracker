package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0Gs  reason: invalid class name and case insensitive filesystem */
public final class C00540Gs extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Cr _annotated;
    public final Method _getter;

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new C00540Gs(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new C00540Gs(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        A0A(obj, obj2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r4, AbstractC02210iH r5, Object obj) throws IOException, C03620oC {
        if (r4.A0i() != EnumC03640oE.VALUE_NULL) {
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke != null) {
                    this._valueDeserializer.A0C(r4, r5, invoke);
                    return;
                }
                throw new C02180iD(AnonymousClass006.A09("Problem deserializing 'setterless' property '", this._propName, "': get method returned null"));
            } catch (Exception e) {
                AbstractC01170Rz.A00(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._annotated;
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r1, AbstractC02210iH r2, Object obj) throws IOException, C03620oC {
        A08(r1, r2, obj);
        return obj;
    }

    public C00540Gs(C00540Gs r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public C00540Gs(C00540Gs r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public C00540Gs(AbstractC01960hi r2, AbstractC02190iF r3, AbstractC04520qa r4, AbstractC04760rD r5, AnonymousClass0Cr r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this._getter = r6.A00;
    }
}

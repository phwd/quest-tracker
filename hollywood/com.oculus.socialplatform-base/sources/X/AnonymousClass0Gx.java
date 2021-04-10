package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Constructor;

/* renamed from: X.0Gx  reason: invalid class name */
public final class AnonymousClass0Gx extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _creator;
    public final AbstractC01170Rz _delegate;

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Gx(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0Gx(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        return this._delegate.A07(obj, obj2);
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        this._delegate.A0A(obj, obj2);
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._delegate.A4N();
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r6, AbstractC02210iH r7, Object obj) throws IOException, C03620oC {
        Object obj2 = null;
        if (r6.A0i() == EnumC03640oE.VALUE_NULL) {
            C04380pt r0 = this._nullProvider;
            if (r0 != null) {
                obj2 = r0.A00(r7);
            }
        } else {
            AbstractC04520qa r1 = this._valueTypeDeserializer;
            if (r1 != null) {
                obj2 = this._valueDeserializer.A0B(r6, r7, r1);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                    this._valueDeserializer.A0C(r6, r7, obj2);
                } catch (Exception e) {
                    C04810rI.A05(e, AnonymousClass006.A0B("Failed to instantiate class ", this._creator.getDeclaringClass().getName(), ", problem: ", e.getMessage()));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        A0A(obj, obj2);
    }

    public AnonymousClass0Gx(AbstractC01170Rz r1, Constructor<?> constructor) {
        super(r1);
        this._delegate = r1;
        this._creator = constructor;
    }

    public AnonymousClass0Gx(AnonymousClass0Gx r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._delegate = r2._delegate.A02(jsonDeserializer);
        this._creator = r2._creator;
    }

    public AnonymousClass0Gx(AnonymousClass0Gx r2, String str) {
        super(r2, str);
        this._delegate = r2._delegate.A03(str);
        this._creator = r2._creator;
    }
}

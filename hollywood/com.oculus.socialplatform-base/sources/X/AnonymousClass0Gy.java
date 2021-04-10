package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.0Gy  reason: invalid class name */
public final class AnonymousClass0Gy extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final transient Field A00;
    public final AnonymousClass0Oy _annotated;

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Gy(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0Gy(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass0Gy(this, this._annotated.A00);
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        A07(obj, A05(r2, r3));
        return obj;
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        A0A(obj, A05(r2, r3));
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._annotated;
    }

    public AnonymousClass0Gy(AnonymousClass0Gy r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Gy(AnonymousClass0Gy r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Gy(AnonymousClass0Gy r6, Field field) {
        super(r6);
        this._annotated = r6._annotated;
        if (field != null) {
            this.A00 = field;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A0C("No Field passed for property '", r6._propName, "' (class ", r6.A4N().A0P().getName(), ")"));
    }

    public AnonymousClass0Gy(AbstractC01960hi r2, AbstractC02190iF r3, AbstractC04520qa r4, AbstractC04760rD r5, AnonymousClass0Oy r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}

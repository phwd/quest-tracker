package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0Gv  reason: invalid class name */
public final class AnonymousClass0Gv extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public final AnonymousClass0Cr _annotated;

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Gv(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0Gv(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            Object invoke = this.A00.invoke(obj, obj2);
            if (invoke != null) {
                return invoke;
            }
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.invoke(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass0Gv(this, this._annotated.A00);
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        A0A(obj, A05(r2, r3));
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._annotated;
    }

    public AnonymousClass0Gv(AnonymousClass0Gv r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Gv(AnonymousClass0Gv r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Gv(AnonymousClass0Gv r2, Method method) {
        super(r2);
        this._annotated = r2._annotated;
        this.A00 = method;
    }

    public AnonymousClass0Gv(AbstractC01960hi r2, AbstractC02190iF r3, AbstractC04520qa r4, AbstractC04760rD r5, AnonymousClass0Cr r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Constructor;

/* renamed from: X.8D  reason: invalid class name */
public final class AnonymousClass8D extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _creator;
    public final AbstractC0073Cr _delegate;

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8D(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8D(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        return this._delegate.A07(obj, obj2);
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        this._delegate.A0A(obj, obj2);
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._delegate.A2d();
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        return A07(obj, A05(ww, wn));
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Object obj2 = null;
        if (ww.A0Z() == EnumC0470q2.VALUE_NULL) {
            C0205Vn vn = this._nullProvider;
            if (vn != null) {
                obj2 = vn.A00(wn);
            }
        } else {
            V4 v4 = this._valueTypeDeserializer;
            if (v4 != null) {
                obj2 = this._valueDeserializer.A0C(ww, wn, v4);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                    this._valueDeserializer.A0A(ww, wn, obj2);
                } catch (Exception e) {
                    Mv.A04(e, AnonymousClass06.A06("Failed to instantiate class ", this._creator.getDeclaringClass().getName(), ", problem: ", e.getMessage()));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        A0A(obj, obj2);
    }

    public AnonymousClass8D(AbstractC0073Cr cr, Constructor<?> constructor) {
        super(cr);
        this._delegate = cr;
        this._creator = constructor;
    }

    public AnonymousClass8D(AnonymousClass8D r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._delegate = r2._delegate.A02(jsonDeserializer);
        this._creator = r2._creator;
    }

    public AnonymousClass8D(AnonymousClass8D r2, String str) {
        super(r2, str);
        this._delegate = r2._delegate.A03(str);
        this._creator = r2._creator;
    }
}

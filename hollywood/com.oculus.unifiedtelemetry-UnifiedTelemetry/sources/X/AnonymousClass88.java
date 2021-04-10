package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.88  reason: invalid class name */
public final class AnonymousClass88 extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final AnonymousClass7P _annotated;
    public final Method _getter;

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass88(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass88(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        A0A(obj, obj2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        if (ww.A0Z() != EnumC0470q2.VALUE_NULL) {
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke != null) {
                    this._valueDeserializer.A0A(ww, wn, invoke);
                    return;
                }
                throw new C0223Wj(AnonymousClass06.A05("Problem deserializing 'setterless' property '", this._propName, "': get method returned null"));
            } catch (Exception e) {
                AbstractC0073Cr.A00(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._annotated;
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A08(ww, wn, obj);
        return obj;
    }

    public AnonymousClass88(AnonymousClass88 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass88(AnonymousClass88 r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass88(WF wf, AbstractC0224Wl wl, V4 v4, N6 n6, AnonymousClass7P r6) {
        super(wf, wl, v4, n6);
        this._annotated = r6;
        this._getter = r6.A00;
    }
}

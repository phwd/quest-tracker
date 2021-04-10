package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0Es  reason: invalid class name */
public final class AnonymousClass0Es extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0EC _annotated;
    public final Method _getter;

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Es(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0Es(this, str);
    }

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    @Override // X.AbstractC02580aL, X.AbstractC01680Ku
    public final AbstractC02450Zr A41() {
        return this._annotated;
    }

    @Override // X.AbstractC01680Ku
    public final Object A07(Object obj, Object obj2) throws IOException {
        A0A(obj, obj2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r4, AbstractC02570aK r5, Object obj) throws IOException, C05910ld {
        if (r4.A0G() != EnumC05930lf.VALUE_NULL) {
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke != null) {
                    this._valueDeserializer.A0A(r4, r5, invoke);
                    return;
                }
                throw new AnonymousClass0aG(AnonymousClass006.A07("Problem deserializing 'setterless' property '", this._propName, "': get method returned null"));
            } catch (Exception e) {
                AbstractC01680Ku.A00(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r1, AbstractC02570aK r2, Object obj) throws IOException, C05910ld {
        A08(r1, r2, obj);
        return obj;
    }

    public AnonymousClass0Es(AnonymousClass0Es r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass0Es(AnonymousClass0Es r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass0Es(AbstractC02410Zn r2, AnonymousClass0aI r3, AnonymousClass0o3 r4, AbstractC07080oh r5, AnonymousClass0EC r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this._getter = r6.A00;
    }
}

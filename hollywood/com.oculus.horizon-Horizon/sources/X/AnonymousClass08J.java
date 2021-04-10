package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.08J  reason: invalid class name */
public final class AnonymousClass08J extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final AnonymousClass07O _annotated;
    public final Method _getter;

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08J(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08J(this, str);
    }

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    @Override // X.AnonymousClass0HD
    public final Object A07(Object obj, Object obj2) throws IOException {
        A0A(obj, obj2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r4, AbstractC04020gg r5, Object obj) throws IOException, AnonymousClass0jg {
        if (r4.A0a() != EnumC04820ji.VALUE_NULL) {
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke != null) {
                    this._valueDeserializer.A0A(r4, r5, invoke);
                    return;
                }
                throw new C03990gZ(AnonymousClass006.A07("Problem deserializing 'setterless' property '", this._propName, "': get method returned null"));
            } catch (Exception e) {
                AnonymousClass0HD.A00(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return this._annotated;
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r1, AbstractC04020gg r2, Object obj) throws IOException, AnonymousClass0jg {
        A08(r1, r2, obj);
        return obj;
    }

    public AnonymousClass08J(AnonymousClass08J r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass08J(AnonymousClass08J r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._getter = r2._getter;
    }

    public AnonymousClass08J(AnonymousClass0g5 r2, AbstractC04000gb r3, AnonymousClass0m9 r4, AbstractC06280mp r5, AnonymousClass07O r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this._getter = r6.A00;
    }
}

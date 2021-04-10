package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Constructor;

/* renamed from: X.08O  reason: invalid class name */
public final class AnonymousClass08O extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _creator;
    public final AnonymousClass0HD _delegate;

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08O(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08O(this, str);
    }

    @Override // X.AnonymousClass0HD
    public final Object A07(Object obj, Object obj2) throws IOException {
        return this._delegate.A07(obj, obj2);
    }

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        this._delegate.A0A(obj, obj2);
    }

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return this._delegate.A3p();
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r6, AbstractC04020gg r7, Object obj) throws IOException, AnonymousClass0jg {
        Object obj2 = null;
        if (r6.A0a() == EnumC04820ji.VALUE_NULL) {
            C05520lP r0 = this._nullProvider;
            if (r0 != null) {
                obj2 = r0.A00(r7);
            }
        } else {
            AnonymousClass0m9 r1 = this._valueTypeDeserializer;
            if (r1 != null) {
                obj2 = this._valueDeserializer.A0C(r6, r7, r1);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                    this._valueDeserializer.A0A(r6, r7, obj2);
                } catch (Exception e) {
                    C06330mu.A04(e, AnonymousClass006.A08("Failed to instantiate class ", this._creator.getDeclaringClass().getName(), ", problem: ", e.getMessage()));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        A0A(obj, obj2);
    }

    public AnonymousClass08O(AnonymousClass0HD r1, Constructor<?> constructor) {
        super(r1);
        this._delegate = r1;
        this._creator = constructor;
    }

    public AnonymousClass08O(AnonymousClass08O r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._delegate = r2._delegate.A02(jsonDeserializer);
        this._creator = r2._creator;
    }

    public AnonymousClass08O(AnonymousClass08O r2, String str) {
        super(r2, str);
        this._delegate = r2._delegate.A03(str);
        this._creator = r2._creator;
    }
}

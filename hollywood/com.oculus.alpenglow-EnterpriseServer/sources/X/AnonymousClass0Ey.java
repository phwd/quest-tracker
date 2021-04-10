package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Constructor;

/* renamed from: X.0Ey  reason: invalid class name */
public final class AnonymousClass0Ey extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _creator;
    public final AbstractC01680Ku _delegate;

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Ey(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0Ey(this, str);
    }

    @Override // X.AbstractC01680Ku
    public final Object A07(Object obj, Object obj2) throws IOException {
        return this._delegate.A07(obj, obj2);
    }

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        this._delegate.A0A(obj, obj2);
    }

    @Override // X.AbstractC02580aL, X.AbstractC01680Ku
    public final AbstractC02450Zr A41() {
        return this._delegate.A41();
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r6, AbstractC02570aK r7, Object obj) throws IOException, C05910ld {
        Object obj2 = null;
        if (r6.A0G() == EnumC05930lf.VALUE_NULL) {
            C06580nK r0 = this._nullProvider;
            if (r0 != null) {
                obj2 = r0.A00(r7);
            }
        } else {
            AnonymousClass0o3 r1 = this._valueTypeDeserializer;
            if (r1 != null) {
                obj2 = this._valueDeserializer.A0C(r6, r7, r1);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                    this._valueDeserializer.A0A(r6, r7, obj2);
                } catch (Exception e) {
                    C07130om.A05(e, AnonymousClass006.A08("Failed to instantiate class ", this._creator.getDeclaringClass().getName(), ", problem: ", e.getMessage()));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        A0A(obj, obj2);
    }

    public AnonymousClass0Ey(AbstractC01680Ku r1, Constructor<?> constructor) {
        super(r1);
        this._delegate = r1;
        this._creator = constructor;
    }

    public AnonymousClass0Ey(AnonymousClass0Ey r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._delegate = r2._delegate.A02(jsonDeserializer);
        this._creator = r2._creator;
    }

    public AnonymousClass0Ey(AnonymousClass0Ey r2, String str) {
        super(r2, str);
        this._delegate = r2._delegate.A03(str);
        this._creator = r2._creator;
    }
}

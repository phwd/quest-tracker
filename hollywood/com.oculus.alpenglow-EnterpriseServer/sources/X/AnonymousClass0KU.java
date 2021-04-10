package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0KU  reason: invalid class name */
public final class AnonymousClass0KU extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0EC _factory;
    public final C07160op<?> _resolver;

    public AnonymousClass0KU(C07160op<?> r2, AnonymousClass0EC r3) {
        super(r2._enumClass);
        this._resolver = r2;
        this._factory = r3;
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r5) throws AnonymousClass0aG {
        AnonymousClass0EC r0 = this._factory;
        if (r0 != null) {
            try {
                return r0.A0V(str);
            } catch (Exception e) {
                C07130om.A04(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            T t = this._resolver._enumsById.get(str);
            if (t == null) {
                C01260Fu r02 = r5._config;
                EnumC02560aJ r1 = EnumC02560aJ.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
                int mask = r1.getMask() & r02._deserFeatures;
                boolean z = false;
                if (mask != 0) {
                    z = true;
                }
                if (!z) {
                    throw r5.A0E(this._keyClass, str, "not one of values for Enum class");
                }
            }
            return t;
        }
    }
}

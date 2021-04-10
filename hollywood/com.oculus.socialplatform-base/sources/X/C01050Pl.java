package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Pl  reason: invalid class name and case insensitive filesystem */
public final class C01050Pl extends AbstractC02030hq {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Cr _factory;
    public final C04820rL<?> _resolver;

    public C01050Pl(C04820rL<?> r2, AnonymousClass0Cr r3) {
        super(r2._enumClass);
        this._resolver = r2;
        this._factory = r3;
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r5) throws C02180iD {
        AnonymousClass0Cr r0 = this._factory;
        if (r0 != null) {
            try {
                return r0.A0V(str);
            } catch (Exception e) {
                C04810rI.A04(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            T t = this._resolver._enumsById.get(str);
            if (t == null) {
                AnonymousClass0HU r02 = r5._config;
                EnumC02200iG r1 = EnumC02200iG.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
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

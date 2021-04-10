package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Gx  reason: invalid class name and case insensitive filesystem */
public final class C00880Gx extends AbstractC03840gD {
    public static final long serialVersionUID = 1;
    public final AnonymousClass07O _factory;
    public final C06360mx<?> _resolver;

    public C00880Gx(C06360mx<?> r2, AnonymousClass07O r3) {
        super(r2._enumClass);
        this._resolver = r2;
        this._factory = r3;
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r5) throws C03990gZ {
        AnonymousClass07O r0 = this._factory;
        if (r0 != null) {
            try {
                return r0.A0S(str);
            } catch (Exception e) {
                C06330mu.A03(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            T t = this._resolver._enumsById.get(str);
            if (t == null) {
                AnonymousClass08X r02 = r5._config;
                EnumC04010gf r1 = EnumC04010gf.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
                int mask = r1.getMask() & r02._deserFeatures;
                boolean z = false;
                if (mask != 0) {
                    z = true;
                }
                if (!z) {
                    r5.A0G(this._keyClass, str);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return t;
        }
    }
}

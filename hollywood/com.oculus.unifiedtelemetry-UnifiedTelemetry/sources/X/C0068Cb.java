package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.Cb  reason: case insensitive filesystem */
public final class C0068Cb extends WN {
    public static final long serialVersionUID = 1;
    public final AnonymousClass7P _factory;
    public final M9<?> _resolver;

    public C0068Cb(M9<?> m9, AnonymousClass7P r3) {
        super(m9._enumClass);
        this._resolver = m9;
        this._factory = r3;
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws C0223Wj {
        AnonymousClass7P r0 = this._factory;
        if (r0 != null) {
            try {
                return r0.A0S(str);
            } catch (Exception e) {
                Mv.A03(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            T t = this._resolver._enumsById.get(str);
            if (t != null || wn._config.A06(EnumC0225Wm.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return t;
            }
            throw wn.A0B(this._keyClass, str, "not one of values for Enum class");
        }
    }
}

package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0gD  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03840gD extends AbstractC05240kb implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _keyClass;

    @Override // X.AbstractC05240kb
    public final Object A00(String str, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        if (str == null) {
            return null;
        }
        try {
            Object A01 = A01(str, r5);
            if (A01 != null) {
                return A01;
            }
            if (this._keyClass.isEnum()) {
                AnonymousClass08X r0 = r5._config;
                EnumC04010gf r1 = EnumC04010gf.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
                int mask = r1.getMask() & r0._deserFeatures;
                boolean z = false;
                if (mask != 0) {
                    z = true;
                }
                if (z) {
                    return null;
                }
            }
            r5.A0G(this._keyClass, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (Exception e) {
            Class<?> cls = this._keyClass;
            AnonymousClass006.A05("not a valid representation: ", e.getMessage());
            r5.A0G(cls, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public abstract Object A01(String str, AbstractC04020gg v) throws Exception;

    public AbstractC03840gD(Class<?> cls) {
        this._keyClass = cls;
    }
}

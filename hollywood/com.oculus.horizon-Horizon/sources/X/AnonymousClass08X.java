package X;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.08X  reason: invalid class name */
public final class AnonymousClass08X extends AnonymousClass0HH<EnumC04010gf, AnonymousClass08X> implements Serializable {
    public static final long serialVersionUID = -4227480407273773599L;
    public final int _deserFeatures = AbstractC03910gQ.A00(EnumC04010gf.class);
    public final C03700fv _nodeFactory = C03700fv.A00;
    public final C06400n1<DeserializationProblemHandler> _problemHandlers = null;

    @Override // X.AbstractC03910gQ
    public final AbstractC04040gi A01() {
        if (A05(EnumC03960gV.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0GT.A00;
    }

    @Override // X.AbstractC03910gQ
    public final AbstractC05180kU A02(AbstractC04000gb r2) {
        return this._base._classIntrospector.A04(this, r2, this);
    }

    public AnonymousClass08X(C05350kv r2, AbstractC05920m7 r3, Map<C06210mi, Class<?>> map) {
        super(r2, r3, map);
    }

    @Override // X.AbstractC03910gQ
    public final AbstractC05820lu<?> A04() {
        AbstractC05820lu<?> A04 = super.A04();
        if (!A05(EnumC03960gV.AUTO_DETECT_SETTERS)) {
            A04 = A04.AA5(EnumC04680iv.NONE);
        }
        if (!A05(EnumC03960gV.AUTO_DETECT_CREATORS)) {
            A04 = A04.AA0(EnumC04680iv.NONE);
        }
        if (!A05(EnumC03960gV.AUTO_DETECT_FIELDS)) {
            return A04.AA1(EnumC04680iv.NONE);
        }
        return A04;
    }
}

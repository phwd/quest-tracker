package X;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.8M  reason: invalid class name */
public final class AnonymousClass8M extends D2<EnumC0225Wm, AnonymousClass8M> implements Serializable {
    public static final long serialVersionUID = -4227480407273773599L;
    public final int _deserFeatures = WZ.A00(EnumC0225Wm.class);
    public final W6 _nodeFactory = W6.A00;
    public final KT<DeserializationProblemHandler> _problemHandlers = null;

    @Override // X.WZ
    public final Wp A01() {
        if (A05(EnumC0220Wf.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return C8.A00;
    }

    @Override // X.WZ
    public final jm A02(AbstractC0224Wl wl) {
        return this._base._classIntrospector.A04(this, wl, this);
    }

    public final boolean A06(EnumC0225Wm wm) {
        if ((wm.getMask() & this._deserFeatures) != 0) {
            return true;
        }
        return false;
    }

    public AnonymousClass8M(dV dVVar, V6 v6, Map<ON, Class<?>> map) {
        super(dVVar, v6, map);
    }

    @Override // X.WZ
    public final VI<?> A04() {
        VI<?> A04 = super.A04();
        if (!A05(EnumC0220Wf.AUTO_DETECT_SETTERS)) {
            A04 = A04.A5l(pH.NONE);
        }
        if (!A05(EnumC0220Wf.AUTO_DETECT_CREATORS)) {
            A04 = A04.A5g(pH.NONE);
        }
        if (!A05(EnumC0220Wf.AUTO_DETECT_FIELDS)) {
            return A04.A5h(pH.NONE);
        }
        return A04;
    }
}

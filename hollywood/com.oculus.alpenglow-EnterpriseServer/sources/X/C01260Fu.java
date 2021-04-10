package X;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0Fu  reason: invalid class name and case insensitive filesystem */
public final class C01260Fu extends AnonymousClass0L1<EnumC02560aJ, C01260Fu> implements Serializable {
    public static final long serialVersionUID = -4227480407273773599L;
    public final int _deserFeatures = AnonymousClass0a7.A00(EnumC02560aJ.class);
    public final AnonymousClass0Zc _nodeFactory = AnonymousClass0Zc.A00;
    public final C07190ou<DeserializationProblemHandler> _problemHandlers = null;

    @Override // X.AnonymousClass0a7
    public final AbstractC02590aM A01() {
        if (A05(EnumC02540aC.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0K8.A00;
    }

    @Override // X.AnonymousClass0a7
    public final AbstractC06260mR A02(AnonymousClass0aI r2) {
        return this._base._classIntrospector.A06(this, r2, this);
    }

    public C01260Fu(C06420mr r2, AnonymousClass0o1 r3, Map<C07010oa, Class<?>> map) {
        super(r2, r3, map);
    }

    @Override // X.AnonymousClass0a7
    public final AbstractC06760no<?> A04() {
        AbstractC06760no<?> A04 = super.A04();
        if (!A05(EnumC02540aC.AUTO_DETECT_SETTERS)) {
            A04 = A04.A8u(EnumC05730kt.NONE);
        }
        if (!A05(EnumC02540aC.AUTO_DETECT_CREATORS)) {
            A04 = A04.A8p(EnumC05730kt.NONE);
        }
        if (!A05(EnumC02540aC.AUTO_DETECT_FIELDS)) {
            return A04.A8q(EnumC05730kt.NONE);
        }
        return A04;
    }
}

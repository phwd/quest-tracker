package X;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0HU  reason: invalid class name */
public final class AnonymousClass0HU extends AnonymousClass0SV<EnumC02200iG, AnonymousClass0HU> implements Serializable {
    public static final long serialVersionUID = -4227480407273773599L;
    public final int _deserFeatures;
    public final C01850hC _nodeFactory;
    public final C04860rQ<DeserializationProblemHandler> _problemHandlers;

    @Override // X.AbstractC02110i2
    public final AbstractC02230iJ A01() {
        if (A05(EnumC02160i9.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0Ou.A00;
    }

    @Override // X.AbstractC02110i2
    public final AbstractC04010oz A02(AbstractC02190iF r2) {
        return this._base._classIntrospector.A06(this, r2, this);
    }

    @Override // X.AbstractC02110i2
    public final AnonymousClass0qO<?> A04() {
        AnonymousClass0qO<?> A04 = super.A04();
        if (!A05(EnumC02160i9.AUTO_DETECT_SETTERS)) {
            A04 = A04.AB8(AnonymousClass0nR.NONE);
        }
        if (!A05(EnumC02160i9.AUTO_DETECT_CREATORS)) {
            A04 = A04.AB3(AnonymousClass0nR.NONE);
        }
        if (!A05(EnumC02160i9.AUTO_DETECT_FIELDS)) {
            return A04.AB4(AnonymousClass0nR.NONE);
        }
        return A04;
    }

    public AnonymousClass0HU(AnonymousClass0HU r2, int i, int i2) {
        super(r2, i);
        this._deserFeatures = i2;
        this._nodeFactory = r2._nodeFactory;
        this._problemHandlers = null;
    }

    public AnonymousClass0HU(AnonymousClass0HU r2, C04140pQ r3) {
        super(r2, r3);
        this._deserFeatures = r2._deserFeatures;
        this._nodeFactory = r2._nodeFactory;
        this._problemHandlers = null;
    }

    public AnonymousClass0HU(C04140pQ r2, AbstractC04510qY r3, Map<C04720r6, Class<?>> map) {
        super(r2, r3, map);
        this._deserFeatures = AbstractC02110i2.A00(EnumC02200iG.class);
        this._nodeFactory = C01850hC.A00;
        this._problemHandlers = null;
    }
}

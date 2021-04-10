package X;

import java.io.Serializable;
import java.util.Collections;

/* renamed from: X.r3  reason: case insensitive filesystem */
public abstract class AbstractC1032r3 implements PF, Serializable {
    public static final long serialVersionUID = 8891625428805876137L;
    public final OS _base;
    public final int _mapperFeatures;

    public AbstractC1020qp A01() {
        return this._base._annotationIntrospector;
    }

    public final O4 A02(AbstractC1024qt qtVar) {
        AbstractC1020qp qpVar;
        boolean A05 = A05(EnumC1027qy.USE_ANNOTATIONS);
        Class cls = qtVar._class;
        if (A05) {
            qpVar = A01();
        } else {
            qpVar = null;
        }
        return new C1046rL(this, qtVar, C1043rI.A00(cls, qpVar, this), Collections.emptyList());
    }

    public final AbstractC1024qt A03(Class cls) {
        return this._base._typeFactory.A09(cls, null);
    }

    public PN A04() {
        return this._base._visibilityChecker;
    }

    public final boolean A05(EnumC1027qy qyVar) {
        if ((qyVar.getMask() & this._mapperFeatures) != 0) {
            return true;
        }
        return false;
    }

    public AbstractC1032r3(OS os, int i) {
        this._base = os;
        this._mapperFeatures = i;
    }

    public static int A00(Class cls) {
        int i = 0;
        for (Enum r1 : (Enum[]) cls.getEnumConstants()) {
            OT ot = (OT) r1;
            if (ot.enabledByDefault()) {
                i |= ot.getMask();
            }
        }
        return i;
    }
}

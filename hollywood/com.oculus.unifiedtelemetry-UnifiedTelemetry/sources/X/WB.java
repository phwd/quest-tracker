package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect(creatorVisibility = pH.ANY, fieldVisibility = pH.PUBLIC_ONLY, getterVisibility = pH.PUBLIC_ONLY, isGetterVisibility = pH.PUBLIC_ONLY, setterVisibility = pH.ANY)
public class WB implements VI<WB>, Serializable {
    public static final WB A00 = new WB((JsonAutoDetect) WB.class.getAnnotation(JsonAutoDetect.class));
    public static final long serialVersionUID = -7073939237187922755L;
    public final pH _creatorMinLevel;
    public final pH _fieldMinLevel;
    public final pH _getterMinLevel;
    public final pH _isGetterMinLevel;
    public final pH _setterMinLevel;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final WB A5g(pH pHVar) {
        pH pHVar2 = pHVar;
        if (pHVar == pH.DEFAULT) {
            pHVar2 = A00._creatorMinLevel;
        }
        if (this._creatorMinLevel == pHVar2) {
            return this;
        }
        return new WB(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, pHVar2, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final WB A5h(pH pHVar) {
        pH pHVar2 = pHVar;
        if (pHVar == pH.DEFAULT) {
            pHVar2 = A00._fieldMinLevel;
        }
        if (this._fieldMinLevel == pHVar2) {
            return this;
        }
        return new WB(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, pHVar2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final WB A5i(pH pHVar) {
        pH pHVar2 = pHVar;
        if (pHVar == pH.DEFAULT) {
            pHVar2 = A00._getterMinLevel;
        }
        if (this._getterMinLevel == pHVar2) {
            return this;
        }
        return new WB(pHVar2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final WB A5j(pH pHVar) {
        pH pHVar2 = pHVar;
        if (pHVar == pH.DEFAULT) {
            pHVar2 = A00._isGetterMinLevel;
        }
        if (this._isGetterMinLevel == pHVar2) {
            return this;
        }
        return new WB(this._getterMinLevel, pHVar2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A04 */
    public final WB A5l(pH pHVar) {
        pH pHVar2 = pHVar;
        if (pHVar == pH.DEFAULT) {
            pHVar2 = A00._setterMinLevel;
        }
        if (this._setterMinLevel == pHVar2) {
            return this;
        }
        return new WB(this._getterMinLevel, this._isGetterMinLevel, pHVar2, this._creatorMinLevel, this._fieldMinLevel);
    }

    @Override // X.VI
    public final boolean A3B(CD cd) {
        return this._fieldMinLevel.isVisible(cd.A00);
    }

    @Override // X.VI
    public final boolean A3D(AnonymousClass7P r3) {
        return this._getterMinLevel.isVisible(r3.A00);
    }

    @Override // X.VI
    public final boolean A3E(AnonymousClass7P r3) {
        return this._isGetterMinLevel.isVisible(r3.A00);
    }

    @Override // X.VI
    public final boolean A3J(AnonymousClass7P r3) {
        return this._setterMinLevel.isVisible(r3.A00);
    }

    /* Return type fixed from 'X.VI' to match base method */
    @Override // X.VI
    public final WB A5f(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return A5i(jsonAutoDetect.getterVisibility()).A5j(jsonAutoDetect.isGetterVisibility()).A5l(jsonAutoDetect.setterVisibility()).A5g(jsonAutoDetect.creatorVisibility()).A5h(jsonAutoDetect.fieldVisibility());
        }
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[Visibility:");
        sb.append(" getter: ");
        sb.append(this._getterMinLevel);
        sb.append(", isGetter: ");
        sb.append(this._isGetterMinLevel);
        sb.append(", setter: ");
        sb.append(this._setterMinLevel);
        sb.append(", creator: ");
        sb.append(this._creatorMinLevel);
        sb.append(", field: ");
        sb.append(this._fieldMinLevel);
        sb.append("]");
        return sb.toString();
    }

    @Override // X.VI
    public final boolean A3A(WJ wj) {
        return this._creatorMinLevel.isVisible(wj.A0P());
    }

    public WB(pH pHVar, pH pHVar2, pH pHVar3, pH pHVar4, pH pHVar5) {
        this._getterMinLevel = pHVar;
        this._isGetterMinLevel = pHVar2;
        this._setterMinLevel = pHVar3;
        this._creatorMinLevel = pHVar4;
        this._fieldMinLevel = pHVar5;
    }

    public WB(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }
}

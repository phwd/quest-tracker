package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect(creatorVisibility = AnonymousClass0nR.ANY, fieldVisibility = AnonymousClass0nR.PUBLIC_ONLY, getterVisibility = AnonymousClass0nR.PUBLIC_ONLY, isGetterVisibility = AnonymousClass0nR.PUBLIC_ONLY, setterVisibility = AnonymousClass0nR.ANY)
/* renamed from: X.0hd  reason: invalid class name and case insensitive filesystem */
public class C01910hd implements AnonymousClass0qO<C01910hd>, Serializable {
    public static final C01910hd A00 = new C01910hd((JsonAutoDetect) C01910hd.class.getAnnotation(JsonAutoDetect.class));
    public static final long serialVersionUID = -7073939237187922755L;
    public final AnonymousClass0nR _creatorMinLevel;
    public final AnonymousClass0nR _fieldMinLevel;
    public final AnonymousClass0nR _getterMinLevel;
    public final AnonymousClass0nR _isGetterMinLevel;
    public final AnonymousClass0nR _setterMinLevel;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final C01910hd AB3(AnonymousClass0nR r7) {
        AnonymousClass0nR r4 = r7;
        if (r7 == AnonymousClass0nR.DEFAULT) {
            r4 = A00._creatorMinLevel;
        }
        if (this._creatorMinLevel == r4) {
            return this;
        }
        return new C01910hd(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, r4, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final C01910hd AB4(AnonymousClass0nR r7) {
        AnonymousClass0nR r5 = r7;
        if (r7 == AnonymousClass0nR.DEFAULT) {
            r5 = A00._fieldMinLevel;
        }
        if (this._fieldMinLevel == r5) {
            return this;
        }
        return new C01910hd(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, r5);
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final C01910hd AB5(AnonymousClass0nR r7) {
        AnonymousClass0nR r1 = r7;
        if (r7 == AnonymousClass0nR.DEFAULT) {
            r1 = A00._getterMinLevel;
        }
        if (this._getterMinLevel == r1) {
            return this;
        }
        return new C01910hd(r1, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final C01910hd AB6(AnonymousClass0nR r7) {
        AnonymousClass0nR r2 = r7;
        if (r7 == AnonymousClass0nR.DEFAULT) {
            r2 = A00._isGetterMinLevel;
        }
        if (this._isGetterMinLevel == r2) {
            return this;
        }
        return new C01910hd(this._getterMinLevel, r2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A04 */
    public final C01910hd AB8(AnonymousClass0nR r7) {
        AnonymousClass0nR r3 = r7;
        if (r7 == AnonymousClass0nR.DEFAULT) {
            r3 = A00._setterMinLevel;
        }
        if (this._setterMinLevel == r3) {
            return this;
        }
        return new C01910hd(this._getterMinLevel, this._isGetterMinLevel, r3, this._creatorMinLevel, this._fieldMinLevel);
    }

    @Override // X.AnonymousClass0qO
    public final boolean A5x(AnonymousClass0Oy r3) {
        return this._fieldMinLevel.isVisible(r3.A00);
    }

    @Override // X.AnonymousClass0qO
    public final boolean A60(AnonymousClass0Cr r3) {
        return this._getterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AnonymousClass0qO
    public final boolean A62(AnonymousClass0Cr r3) {
        return this._isGetterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AnonymousClass0qO
    public final boolean A6A(AnonymousClass0Cr r3) {
        return this._setterMinLevel.isVisible(r3.A00);
    }

    /* Return type fixed from 'X.0qO' to match base method */
    @Override // X.AnonymousClass0qO
    public final C01910hd AB2(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return AB5(jsonAutoDetect.getterVisibility()).AB6(jsonAutoDetect.isGetterVisibility()).AB8(jsonAutoDetect.setterVisibility()).AB3(jsonAutoDetect.creatorVisibility()).AB4(jsonAutoDetect.fieldVisibility());
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

    @Override // X.AnonymousClass0qO
    public final boolean A5u(AbstractC01990hm r3) {
        return this._creatorMinLevel.isVisible(r3.A0R());
    }

    /* Return type fixed from 'X.0qO' to match base method */
    @Override // X.AnonymousClass0qO
    public final C01910hd AB9(AnonymousClass0o1 r2, AnonymousClass0nR r3) {
        switch (r2.ordinal()) {
            case 0:
                return AB5(r3);
            case 1:
                return AB8(r3);
            case 2:
                return AB3(r3);
            case 3:
                return AB4(r3);
            case 4:
                return AB6(r3);
            case 5:
            default:
                return this;
            case 6:
                if (r3 == AnonymousClass0nR.DEFAULT) {
                    return A00;
                }
                return new C01910hd(r3);
        }
    }

    public C01910hd(AnonymousClass0nR r3) {
        if (r3 == AnonymousClass0nR.DEFAULT) {
            C01910hd r1 = A00;
            this._getterMinLevel = r1._getterMinLevel;
            this._isGetterMinLevel = r1._isGetterMinLevel;
            this._setterMinLevel = r1._setterMinLevel;
            this._creatorMinLevel = r1._creatorMinLevel;
            r3 = r1._fieldMinLevel;
        } else {
            this._getterMinLevel = r3;
            this._isGetterMinLevel = r3;
            this._setterMinLevel = r3;
            this._creatorMinLevel = r3;
        }
        this._fieldMinLevel = r3;
    }

    public C01910hd(AnonymousClass0nR r1, AnonymousClass0nR r2, AnonymousClass0nR r3, AnonymousClass0nR r4, AnonymousClass0nR r5) {
        this._getterMinLevel = r1;
        this._isGetterMinLevel = r2;
        this._setterMinLevel = r3;
        this._creatorMinLevel = r4;
        this._fieldMinLevel = r5;
    }

    public C01910hd(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }
}

package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect(creatorVisibility = EnumC04680iv.ANY, fieldVisibility = EnumC04680iv.PUBLIC_ONLY, getterVisibility = EnumC04680iv.PUBLIC_ONLY, isGetterVisibility = EnumC04680iv.PUBLIC_ONLY, setterVisibility = EnumC04680iv.ANY)
/* renamed from: X.0g1  reason: invalid class name and case insensitive filesystem */
public class C03740g1 implements AbstractC05820lu<C03740g1>, Serializable {
    public static final C03740g1 A00 = new C03740g1((JsonAutoDetect) C03740g1.class.getAnnotation(JsonAutoDetect.class));
    public static final long serialVersionUID = -7073939237187922755L;
    public final EnumC04680iv _creatorMinLevel;
    public final EnumC04680iv _fieldMinLevel;
    public final EnumC04680iv _getterMinLevel;
    public final EnumC04680iv _isGetterMinLevel;
    public final EnumC04680iv _setterMinLevel;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final C03740g1 AA0(EnumC04680iv r7) {
        EnumC04680iv r4 = r7;
        if (r7 == EnumC04680iv.DEFAULT) {
            r4 = A00._creatorMinLevel;
        }
        if (this._creatorMinLevel == r4) {
            return this;
        }
        return new C03740g1(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, r4, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final C03740g1 AA1(EnumC04680iv r7) {
        EnumC04680iv r5 = r7;
        if (r7 == EnumC04680iv.DEFAULT) {
            r5 = A00._fieldMinLevel;
        }
        if (this._fieldMinLevel == r5) {
            return this;
        }
        return new C03740g1(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, r5);
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final C03740g1 AA2(EnumC04680iv r7) {
        EnumC04680iv r1 = r7;
        if (r7 == EnumC04680iv.DEFAULT) {
            r1 = A00._getterMinLevel;
        }
        if (this._getterMinLevel == r1) {
            return this;
        }
        return new C03740g1(r1, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final C03740g1 AA3(EnumC04680iv r7) {
        EnumC04680iv r2 = r7;
        if (r7 == EnumC04680iv.DEFAULT) {
            r2 = A00._isGetterMinLevel;
        }
        if (this._isGetterMinLevel == r2) {
            return this;
        }
        return new C03740g1(this._getterMinLevel, r2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A04 */
    public final C03740g1 AA5(EnumC04680iv r7) {
        EnumC04680iv r3 = r7;
        if (r7 == EnumC04680iv.DEFAULT) {
            r3 = A00._setterMinLevel;
        }
        if (this._setterMinLevel == r3) {
            return this;
        }
        return new C03740g1(this._getterMinLevel, this._isGetterMinLevel, r3, this._creatorMinLevel, this._fieldMinLevel);
    }

    @Override // X.AbstractC05820lu
    public final boolean A4z(AnonymousClass0GX r3) {
        return this._fieldMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC05820lu
    public final boolean A51(AnonymousClass07O r3) {
        return this._getterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC05820lu
    public final boolean A53(AnonymousClass07O r3) {
        return this._isGetterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC05820lu
    public final boolean A57(AnonymousClass07O r3) {
        return this._setterMinLevel.isVisible(r3.A00);
    }

    /* Return type fixed from 'X.0lu' to match base method */
    @Override // X.AbstractC05820lu
    public final C03740g1 A9z(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return AA2(jsonAutoDetect.getterVisibility()).AA3(jsonAutoDetect.isGetterVisibility()).AA5(jsonAutoDetect.setterVisibility()).AA0(jsonAutoDetect.creatorVisibility()).AA1(jsonAutoDetect.fieldVisibility());
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

    @Override // X.AbstractC05820lu
    public final boolean A4x(AnonymousClass0g9 r3) {
        return this._creatorMinLevel.isVisible(r3.A0P());
    }

    public C03740g1(EnumC04680iv r1, EnumC04680iv r2, EnumC04680iv r3, EnumC04680iv r4, EnumC04680iv r5) {
        this._getterMinLevel = r1;
        this._isGetterMinLevel = r2;
        this._setterMinLevel = r3;
        this._creatorMinLevel = r4;
        this._fieldMinLevel = r5;
    }

    public C03740g1(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }
}

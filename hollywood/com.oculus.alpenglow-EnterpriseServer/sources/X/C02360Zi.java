package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect(creatorVisibility = EnumC05730kt.ANY, fieldVisibility = EnumC05730kt.PUBLIC_ONLY, getterVisibility = EnumC05730kt.PUBLIC_ONLY, isGetterVisibility = EnumC05730kt.PUBLIC_ONLY, setterVisibility = EnumC05730kt.ANY)
/* renamed from: X.0Zi  reason: invalid class name and case insensitive filesystem */
public class C02360Zi implements AbstractC06760no<C02360Zi>, Serializable {
    public static final C02360Zi A00 = new C02360Zi((JsonAutoDetect) C02360Zi.class.getAnnotation(JsonAutoDetect.class));
    public static final long serialVersionUID = -7073939237187922755L;
    public final EnumC05730kt _creatorMinLevel;
    public final EnumC05730kt _fieldMinLevel;
    public final EnumC05730kt _getterMinLevel;
    public final EnumC05730kt _isGetterMinLevel;
    public final EnumC05730kt _setterMinLevel;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final C02360Zi A8p(EnumC05730kt r7) {
        EnumC05730kt r4 = r7;
        if (r7 == EnumC05730kt.DEFAULT) {
            r4 = A00._creatorMinLevel;
        }
        if (this._creatorMinLevel == r4) {
            return this;
        }
        return new C02360Zi(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, r4, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final C02360Zi A8q(EnumC05730kt r7) {
        EnumC05730kt r5 = r7;
        if (r7 == EnumC05730kt.DEFAULT) {
            r5 = A00._fieldMinLevel;
        }
        if (this._fieldMinLevel == r5) {
            return this;
        }
        return new C02360Zi(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, r5);
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final C02360Zi A8r(EnumC05730kt r7) {
        EnumC05730kt r1 = r7;
        if (r7 == EnumC05730kt.DEFAULT) {
            r1 = A00._getterMinLevel;
        }
        if (this._getterMinLevel == r1) {
            return this;
        }
        return new C02360Zi(r1, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final C02360Zi A8s(EnumC05730kt r7) {
        EnumC05730kt r2 = r7;
        if (r7 == EnumC05730kt.DEFAULT) {
            r2 = A00._isGetterMinLevel;
        }
        if (this._isGetterMinLevel == r2) {
            return this;
        }
        return new C02360Zi(this._getterMinLevel, r2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A04 */
    public final C02360Zi A8u(EnumC05730kt r7) {
        EnumC05730kt r3 = r7;
        if (r7 == EnumC05730kt.DEFAULT) {
            r3 = A00._setterMinLevel;
        }
        if (this._setterMinLevel == r3) {
            return this;
        }
        return new C02360Zi(this._getterMinLevel, this._isGetterMinLevel, r3, this._creatorMinLevel, this._fieldMinLevel);
    }

    @Override // X.AbstractC06760no
    public final boolean A5P(AnonymousClass0KC r3) {
        return this._fieldMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC06760no
    public final boolean A5R(AnonymousClass0EC r3) {
        return this._getterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC06760no
    public final boolean A5T(AnonymousClass0EC r3) {
        return this._isGetterMinLevel.isVisible(r3.A00);
    }

    @Override // X.AbstractC06760no
    public final boolean A5Z(AnonymousClass0EC r3) {
        return this._setterMinLevel.isVisible(r3.A00);
    }

    /* Return type fixed from 'X.0no' to match base method */
    @Override // X.AbstractC06760no
    public final C02360Zi A8o(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return A8r(jsonAutoDetect.getterVisibility()).A8s(jsonAutoDetect.isGetterVisibility()).A8u(jsonAutoDetect.setterVisibility()).A8p(jsonAutoDetect.creatorVisibility()).A8q(jsonAutoDetect.fieldVisibility());
        }
        return this;
    }

    public final String toString() {
        return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
    }

    @Override // X.AbstractC06760no
    public final boolean A5O(AbstractC02450Zr r3) {
        return this._creatorMinLevel.isVisible(r3.A0R());
    }

    public C02360Zi(EnumC05730kt r1, EnumC05730kt r2, EnumC05730kt r3, EnumC05730kt r4, EnumC05730kt r5) {
        this._getterMinLevel = r1;
        this._isGetterMinLevel = r2;
        this._setterMinLevel = r3;
        this._creatorMinLevel = r4;
        this._fieldMinLevel = r5;
    }

    public C02360Zi(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }
}

package X;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect(creatorVisibility = EnumC0242Mp.ANY, fieldVisibility = EnumC0242Mp.PUBLIC_ONLY, getterVisibility = EnumC0242Mp.PUBLIC_ONLY, isGetterVisibility = EnumC0242Mp.PUBLIC_ONLY, setterVisibility = EnumC0242Mp.ANY)
/* renamed from: X.rS  reason: case insensitive filesystem */
public final class C1053rS implements PN, Serializable {
    public static final C1053rS A00 = new C1053rS((JsonAutoDetect) C1053rS.class.getAnnotation(JsonAutoDetect.class));
    public static final long serialVersionUID = -7073939237187922755L;
    public final EnumC0242Mp _creatorMinLevel;
    public final EnumC0242Mp _fieldMinLevel;
    public final EnumC0242Mp _getterMinLevel;
    public final EnumC0242Mp _isGetterMinLevel;
    public final EnumC0242Mp _setterMinLevel;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final C1053rS A5b(EnumC0242Mp mp) {
        EnumC0242Mp mp2 = mp;
        if (mp == EnumC0242Mp.DEFAULT) {
            mp2 = A00._creatorMinLevel;
        }
        if (this._creatorMinLevel == mp2) {
            return this;
        }
        return new C1053rS(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, mp2, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final C1053rS A5c(EnumC0242Mp mp) {
        EnumC0242Mp mp2 = mp;
        if (mp == EnumC0242Mp.DEFAULT) {
            mp2 = A00._fieldMinLevel;
        }
        if (this._fieldMinLevel == mp2) {
            return this;
        }
        return new C1053rS(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, mp2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final C1053rS A5d(EnumC0242Mp mp) {
        EnumC0242Mp mp2 = mp;
        if (mp == EnumC0242Mp.DEFAULT) {
            mp2 = A00._getterMinLevel;
        }
        if (this._getterMinLevel == mp2) {
            return this;
        }
        return new C1053rS(mp2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final C1053rS A5e(EnumC0242Mp mp) {
        EnumC0242Mp mp2 = mp;
        if (mp == EnumC0242Mp.DEFAULT) {
            mp2 = A00._isGetterMinLevel;
        }
        if (this._isGetterMinLevel == mp2) {
            return this;
        }
        return new C1053rS(this._getterMinLevel, mp2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    /* access modifiers changed from: private */
    /* renamed from: A04 */
    public final C1053rS A5g(EnumC0242Mp mp) {
        EnumC0242Mp mp2 = mp;
        if (mp == EnumC0242Mp.DEFAULT) {
            mp2 = A00._setterMinLevel;
        }
        if (this._setterMinLevel == mp2) {
            return this;
        }
        return new C1053rS(this._getterMinLevel, this._isGetterMinLevel, mp2, this._creatorMinLevel, this._fieldMinLevel);
    }

    @Override // X.PN
    public final boolean A3T(St st) {
        return this._fieldMinLevel.isVisible(st.A00);
    }

    @Override // X.PN
    public final boolean A3U(AnonymousClass0q r3) {
        return this._getterMinLevel.isVisible(r3.A00);
    }

    @Override // X.PN
    public final boolean A3X(AnonymousClass0q r3) {
        return this._isGetterMinLevel.isVisible(r3.A00);
    }

    @Override // X.PN
    public final boolean A3a(AnonymousClass0q r3) {
        return this._setterMinLevel.isVisible(r3.A00);
    }

    @Override // X.PN
    public final PN A5a(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return A5d(jsonAutoDetect.getterVisibility()).A5e(jsonAutoDetect.isGetterVisibility()).A5g(jsonAutoDetect.setterVisibility()).A5b(jsonAutoDetect.creatorVisibility()).A5c(jsonAutoDetect.fieldVisibility());
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

    @Override // X.PN
    public final boolean A3R(AbstractC1044rJ rJVar) {
        return this._creatorMinLevel.isVisible(rJVar.A0R());
    }

    public C1053rS(EnumC0242Mp mp, EnumC0242Mp mp2, EnumC0242Mp mp3, EnumC0242Mp mp4, EnumC0242Mp mp5) {
        this._getterMinLevel = mp;
        this._isGetterMinLevel = mp2;
        this._setterMinLevel = mp3;
        this._creatorMinLevel = mp4;
        this._fieldMinLevel = mp5;
    }

    public C1053rS(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.0Gw  reason: invalid class name */
public final class AnonymousClass0Gw extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final AbstractC01170Rz _backProperty;
    public final boolean _isContainer;
    public final AbstractC01170Rz _managedProperty;
    public final String _referenceName;

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Gw(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0Gw(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        Object A07 = this._managedProperty.A07(obj, obj2);
        if (obj2 != null) {
            if (!this._isContainer) {
                this._backProperty.A0A(obj2, obj);
            } else if (obj2 instanceof Object[]) {
                Object[] objArr = (Object[]) obj2;
                for (Object obj3 : objArr) {
                    if (obj3 != null) {
                        this._backProperty.A0A(obj3, obj);
                    }
                }
            } else if (obj2 instanceof Collection) {
                for (Object obj4 : (Collection) obj2) {
                    if (obj4 != null) {
                        this._backProperty.A0A(obj4, obj);
                    }
                }
            } else if (obj2 instanceof Map) {
                for (Object obj5 : ((Map) obj2).values()) {
                    if (obj5 != null) {
                        this._backProperty.A0A(obj5, obj);
                    }
                }
            } else {
                throw new IllegalStateException(AnonymousClass006.A0C("Unsupported container type (", obj2.getClass().getName(), ") when resolving reference '", this._referenceName, "'"));
            }
        }
        return A07;
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        A0A(obj, this._managedProperty.A05(r2, r3));
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._managedProperty.A4N();
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    public AnonymousClass0Gw(AbstractC01170Rz r8, String str, AbstractC01170Rz r10, AbstractC04760rD r11, boolean z) {
        super(r8._propName, r8.A59(), r8._wrapperName, r8._valueTypeDeserializer, r11, r8._isRequired);
        this._referenceName = str;
        this._managedProperty = r8;
        this._backProperty = r10;
        this._isContainer = z;
    }

    public AnonymousClass0Gw(AnonymousClass0Gw r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._referenceName = r2._referenceName;
        this._isContainer = r2._isContainer;
        this._managedProperty = r2._managedProperty;
        this._backProperty = r2._backProperty;
    }

    public AnonymousClass0Gw(AnonymousClass0Gw r2, String str) {
        super(r2, str);
        this._referenceName = r2._referenceName;
        this._isContainer = r2._isContainer;
        this._managedProperty = r2._managedProperty;
        this._backProperty = r2._backProperty;
    }
}

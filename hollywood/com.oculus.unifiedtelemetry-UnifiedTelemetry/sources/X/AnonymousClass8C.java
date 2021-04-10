package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.8C  reason: invalid class name */
public final class AnonymousClass8C extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final AbstractC0073Cr _backProperty;
    public final boolean _isContainer;
    public final AbstractC0073Cr _managedProperty;
    public final String _referenceName;

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8C(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8C(this, str);
    }

    @Override // X.AbstractC0073Cr
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
                throw new IllegalStateException(AnonymousClass06.A07("Unsupported container type (", obj2.getClass().getName(), ") when resolving reference '", this._referenceName, "'"));
            }
        }
        return A07;
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A0A(obj, this._managedProperty.A05(ww, wn));
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._managedProperty.A2d();
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        return A07(obj, A05(ww, wn));
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    public AnonymousClass8C(AbstractC0073Cr cr, String str, AbstractC0073Cr cr2, N6 n6, boolean z) {
        super(cr._propName, cr._type, cr._wrapperName, cr._valueTypeDeserializer, n6, cr._isRequired);
        this._referenceName = str;
        this._managedProperty = cr;
        this._backProperty = cr2;
        this._isContainer = z;
    }

    public AnonymousClass8C(AnonymousClass8C r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._referenceName = r2._referenceName;
        this._isContainer = r2._isContainer;
        this._managedProperty = r2._managedProperty;
        this._backProperty = r2._backProperty;
    }

    public AnonymousClass8C(AnonymousClass8C r2, String str) {
        super(r2, str);
        this._referenceName = r2._referenceName;
        this._isContainer = r2._isContainer;
        this._managedProperty = r2._managedProperty;
        this._backProperty = r2._backProperty;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.FailingDeserializer;
import java.io.IOException;
import java.io.Serializable;

public abstract class AQ implements AbstractC0123Rh, Serializable {
    public static final JsonDeserializer<Object> A00 = new FailingDeserializer();
    public static final long serialVersionUID = -1026580169193933453L;
    public final boolean _isRequired;
    public String _managedReferenceName;
    public final AnonymousClass0N _nullProvider;
    public final String _propName;
    public int _propertyIndex;
    public final RZ _type;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass7F _valueTypeDeserializer;
    public AnonymousClass9w _viewMatcher;
    public final AnonymousClass4a _wrapperName;

    public final Object A00(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 != AnonymousClass9p.VALUE_NULL) {
            return this._valueDeserializer.A03(rn, rd);
        }
        AnonymousClass0N r1 = this._nullProvider;
        if (r1 == null) {
            return null;
        }
        if (!r1._isPrimitive) {
            return r1._nullValue;
        }
        throw null;
    }

    public final String toString() {
        return AnonymousClass06.A04("[property '", this._propName, "']");
    }
}

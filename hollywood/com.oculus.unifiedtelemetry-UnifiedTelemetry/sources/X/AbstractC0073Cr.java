package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.FailingDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.Cr  reason: case insensitive filesystem */
public abstract class AbstractC0073Cr implements AbstractC0227Wo, Serializable {
    public static final JsonDeserializer<Object> A01 = new FailingDeserializer();
    public static final long serialVersionUID = -1026580169193933453L;
    public final transient N6 A00;
    public final boolean _isRequired;
    public String _managedReferenceName;
    public final C0205Vn _nullProvider;
    public final String _propName;
    public int _propertyIndex;
    public final AbstractC0224Wl _type;
    public JsonDeserializer<Object> _valueDeserializer;
    public final V4 _valueTypeDeserializer;
    public AbstractC0098Hw _viewMatcher;
    public final C0417hQ _wrapperName;

    public int A01() {
        return -1;
    }

    public abstract AbstractC0073Cr A02(JsonDeserializer<?> jsonDeserializer);

    public abstract AbstractC0073Cr A03(String str);

    public Object A04() {
        return null;
    }

    public abstract Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0;

    public abstract Object A07(Object obj, Object obj2) throws IOException;

    public abstract void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0;

    public abstract void A0A(Object obj, Object obj2) throws IOException;

    @Override // X.AbstractC0227Wo
    public abstract WJ A2d();

    public static final void A00(Exception exc) throws IOException {
        if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (!z) {
                while (exc2.getCause() != null) {
                    exc2 = exc2.getCause();
                }
                throw new C0223Wj(exc2.getMessage(), null, exc2);
            }
        }
        throw exc;
    }

    public final void A09(Exception exc, Object obj) throws IOException {
        String name;
        if (exc instanceof IllegalArgumentException) {
            if (obj == null) {
                name = "[NULL]";
            } else {
                name = obj.getClass().getName();
            }
            StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(this._propName);
            sb.append("' (expected type: ");
            sb.append(this._type);
            sb.append("; actual type: ");
            sb.append(name);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
            } else {
                message = " (no error message provided)";
            }
            sb.append(message);
            throw new C0223Wj(sb.toString(), null, exc);
        }
        A00(exc);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final boolean A0B(Class<?> cls) {
        AbstractC0098Hw hw = this._viewMatcher;
        if (hw == null || hw.A00(cls)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return AnonymousClass06.A05("[property '", this._propName, "']");
    }

    public final Object A05(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (ww.A0Z() == EnumC0470q2.VALUE_NULL) {
            C0205Vn vn = this._nullProvider;
            if (vn == null) {
                return null;
            }
            return vn.A00(wn);
        }
        V4 v4 = this._valueTypeDeserializer;
        if (v4 != null) {
            return this._valueDeserializer.A0C(ww, wn, v4);
        }
        return this._valueDeserializer.A09(ww, wn);
    }

    public AbstractC0073Cr(AbstractC0073Cr cr) {
        this._propertyIndex = -1;
        this._propName = cr._propName;
        this._type = cr._type;
        this._wrapperName = cr._wrapperName;
        this._isRequired = cr._isRequired;
        this.A00 = cr.A00;
        this._valueDeserializer = cr._valueDeserializer;
        this._valueTypeDeserializer = cr._valueTypeDeserializer;
        this._nullProvider = cr._nullProvider;
        this._managedReferenceName = cr._managedReferenceName;
        this._propertyIndex = cr._propertyIndex;
        this._viewMatcher = cr._viewMatcher;
    }

    public AbstractC0073Cr(AbstractC0073Cr cr, JsonDeserializer<?> jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = cr._propName;
        AbstractC0224Wl wl = cr._type;
        this._type = wl;
        this._wrapperName = cr._wrapperName;
        this._isRequired = cr._isRequired;
        this.A00 = cr.A00;
        this._valueTypeDeserializer = cr._valueTypeDeserializer;
        this._managedReferenceName = cr._managedReferenceName;
        this._propertyIndex = cr._propertyIndex;
        C0205Vn vn = null;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            jsonDeserializer = A01;
        } else {
            Object A08 = jsonDeserializer.A08();
            this._nullProvider = A08 != null ? new C0205Vn(wl, A08) : vn;
        }
        this._valueDeserializer = jsonDeserializer;
        this._viewMatcher = cr._viewMatcher;
    }

    public AbstractC0073Cr(AbstractC0073Cr cr, String str) {
        this._propertyIndex = -1;
        this._propName = str;
        this._type = cr._type;
        this._wrapperName = cr._wrapperName;
        this._isRequired = cr._isRequired;
        this.A00 = cr.A00;
        this._valueDeserializer = cr._valueDeserializer;
        this._valueTypeDeserializer = cr._valueTypeDeserializer;
        this._nullProvider = cr._nullProvider;
        this._managedReferenceName = cr._managedReferenceName;
        this._propertyIndex = cr._propertyIndex;
        this._viewMatcher = cr._viewMatcher;
    }

    public AbstractC0073Cr(WF wf, AbstractC0224Wl wl, V4 v4, N6 n6) {
        this(wf.A0D(), wl, wf.A07(), v4, n6, wf.A0E());
    }

    public AbstractC0073Cr(String str, AbstractC0224Wl wl, C0417hQ hQVar, V4 v4, N6 n6, boolean z) {
        String str2;
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            C0438jz jzVar = C0438jz.A00;
            synchronized (jzVar) {
                str2 = (String) jzVar.get(str);
                if (str2 == null) {
                    str2 = str.intern();
                    jzVar.put(str2, str2);
                }
            }
        }
        this._propName = str2;
        this._type = wl;
        this._wrapperName = hQVar;
        this._isRequired = z;
        this.A00 = n6;
        this._viewMatcher = null;
        this._nullProvider = null;
        this._valueTypeDeserializer = v4 != null ? v4.A04(this) : v4;
        this._valueDeserializer = A01;
    }
}

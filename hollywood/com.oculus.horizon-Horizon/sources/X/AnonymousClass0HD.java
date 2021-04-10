package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.FailingDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0HD  reason: invalid class name */
public abstract class AnonymousClass0HD implements AbstractC04030gh, Serializable {
    public static final JsonDeserializer<Object> A01 = new FailingDeserializer();
    public static final long serialVersionUID = -1026580169193933453L;
    public final transient AbstractC06280mp A00;
    public final boolean _isRequired;
    public String _managedReferenceName;
    public final C05520lP _nullProvider;
    public final String _propName;
    public int _propertyIndex;
    public final AbstractC04000gb _type;
    public JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0m9 _valueTypeDeserializer;
    public AbstractC06490nE _viewMatcher;
    public final C05280kg _wrapperName;

    public int A01() {
        return -1;
    }

    public abstract AnonymousClass0HD A02(JsonDeserializer<?> jsonDeserializer);

    public abstract AnonymousClass0HD A03(String str);

    public Object A04() {
        return null;
    }

    public abstract Object A06(AbstractC04100gp v, AbstractC04020gg v2, Object obj) throws IOException, AnonymousClass0jg;

    public abstract Object A07(Object obj, Object obj2) throws IOException;

    public abstract void A08(AbstractC04100gp v, AbstractC04020gg v2, Object obj) throws IOException, AnonymousClass0jg;

    public abstract void A0A(Object obj, Object obj2) throws IOException;

    @Override // X.AbstractC04030gh
    public abstract AnonymousClass0g9 A3p();

    public static final void A00(Exception exc) throws IOException {
        if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (!z) {
                while (exc2.getCause() != null) {
                    exc2 = exc2.getCause();
                }
                throw new C03990gZ(exc2.getMessage(), null, exc2);
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
            throw new C03990gZ(sb.toString(), null, exc);
        }
        A00(exc);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final boolean A0B(Class<?> cls) {
        AbstractC06490nE r0 = this._viewMatcher;
        if (r0 == null || r0.A00(cls)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return AnonymousClass006.A07("[property '", this._propName, "']");
    }

    public final Object A05(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        if (r3.A0a() == EnumC04820ji.VALUE_NULL) {
            C05520lP r0 = this._nullProvider;
            if (r0 == null) {
                return null;
            }
            return r0.A00(r4);
        }
        AnonymousClass0m9 r1 = this._valueTypeDeserializer;
        if (r1 != null) {
            return this._valueDeserializer.A0C(r3, r4, r1);
        }
        return this._valueDeserializer.A09(r3, r4);
    }

    public AnonymousClass0HD(AnonymousClass0HD r2) {
        this._propertyIndex = -1;
        this._propName = r2._propName;
        this._type = r2._type;
        this._wrapperName = r2._wrapperName;
        this._isRequired = r2._isRequired;
        this.A00 = r2.A00;
        this._valueDeserializer = r2._valueDeserializer;
        this._valueTypeDeserializer = r2._valueTypeDeserializer;
        this._nullProvider = r2._nullProvider;
        this._managedReferenceName = r2._managedReferenceName;
        this._propertyIndex = r2._propertyIndex;
        this._viewMatcher = r2._viewMatcher;
    }

    public AnonymousClass0HD(AnonymousClass0HD r4, JsonDeserializer<?> jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = r4._propName;
        AbstractC04000gb r2 = r4._type;
        this._type = r2;
        this._wrapperName = r4._wrapperName;
        this._isRequired = r4._isRequired;
        this.A00 = r4.A00;
        this._valueTypeDeserializer = r4._valueTypeDeserializer;
        this._managedReferenceName = r4._managedReferenceName;
        this._propertyIndex = r4._propertyIndex;
        C05520lP r1 = null;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            jsonDeserializer = A01;
        } else {
            Object A08 = jsonDeserializer.A08();
            this._nullProvider = A08 != null ? new C05520lP(r2, A08) : r1;
        }
        this._valueDeserializer = jsonDeserializer;
        this._viewMatcher = r4._viewMatcher;
    }

    public AnonymousClass0HD(AnonymousClass0HD r2, String str) {
        this._propertyIndex = -1;
        this._propName = str;
        this._type = r2._type;
        this._wrapperName = r2._wrapperName;
        this._isRequired = r2._isRequired;
        this.A00 = r2.A00;
        this._valueDeserializer = r2._valueDeserializer;
        this._valueTypeDeserializer = r2._valueTypeDeserializer;
        this._nullProvider = r2._nullProvider;
        this._managedReferenceName = r2._managedReferenceName;
        this._propertyIndex = r2._propertyIndex;
        this._viewMatcher = r2._viewMatcher;
    }

    public AnonymousClass0HD(AnonymousClass0g5 r8, AbstractC04000gb r9, AnonymousClass0m9 r10, AbstractC06280mp r11) {
        this(r8.A0D(), r9, r8.A07(), r10, r11, r8.A0E());
    }

    public AnonymousClass0HD(String str, AbstractC04000gb r4, C05280kg r5, AnonymousClass0m9 r6, AbstractC06280mp r7, boolean z) {
        String str2;
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            C05130kO r1 = C05130kO.A00;
            synchronized (r1) {
                str2 = (String) r1.get(str);
                if (str2 == null) {
                    str2 = str.intern();
                    r1.put(str2, str2);
                }
            }
        }
        this._propName = str2;
        this._type = r4;
        this._wrapperName = r5;
        this._isRequired = z;
        this.A00 = r7;
        this._viewMatcher = null;
        this._nullProvider = null;
        this._valueTypeDeserializer = r6 != null ? r6.A04(this) : r6;
        this._valueDeserializer = A01;
    }
}

package X;

import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* renamed from: X.Wn  reason: case insensitive filesystem */
public abstract class AbstractC0226Wn extends jk implements Serializable {
    public static final long serialVersionUID = -7727373309391091315L;
    public transient AbstractC0232Ww A00;
    public transient DateFormat A01;
    public transient N3 A02;
    public transient J9 A03;
    public final Zj _cache;
    public final AnonymousClass8M _config;
    public final ZD _factory;
    public final int _featureFlags;
    public final AbstractC0435iB _injectableValues;
    public final Class<?> _view;

    public abstract JsonDeserializer<Object> A07(VV vv, Object obj) throws C0223Wj;

    public abstract AbstractC0420hV A0E(VV vv, Object obj) throws C0223Wj;

    public abstract C0200Vg A0F(Object obj, pp<?> ppVar);

    public static final C0223Wj A00(AbstractC0232Ww ww, EnumC0470q2 q2Var, String str) {
        StringBuilder sb = new StringBuilder("Unexpected token (");
        sb.append(ww.A0Z());
        sb.append("), expected ");
        sb.append(q2Var);
        sb.append(": ");
        sb.append(str);
        return C0223Wj.A00(ww, sb.toString());
    }

    public final JsonDeserializer<Object> A05(AbstractC0224Wl wl) throws C0223Wj {
        JsonDeserializer<?> A002 = this._cache.A00(this, this._factory, wl);
        if (A002 == null) {
            return null;
        }
        if (A002 instanceof Zy) {
            A002 = ((Zy) A002).A1g(this, null);
        }
        V4 A0L = this._factory.A0L(this._config, wl);
        if (A0L != null) {
            return new TypeWrappedDeserializer(A0L.A04(null), A002);
        }
        return A002;
    }

    public final JsonDeserializer<Object> A06(AbstractC0224Wl wl, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<Object> A002 = this._cache.A00(this, this._factory, wl);
        if (A002 == null || !(A002 instanceof Zy)) {
            return A002;
        }
        return ((Zy) A002).A1g(this, wo);
    }

    public final C0223Wj A08(Class<?> cls) {
        return A09(cls, this.A00.A0Z());
    }

    public final C0223Wj A0A(Class<?> cls, String str) {
        return C0223Wj.A00(this.A00, AnonymousClass06.A06("Can not construct instance of ", cls.getName(), ", problem: ", str));
    }

    public final C0223Wj A0B(Class<?> cls, String str, String str2) {
        return new CG(AnonymousClass06.A08("Can not construct Map key of type ", cls.getName(), " from String \"", A02(str), "\": ", str2), this.A00.A0O(), str, cls);
    }

    public final C0223Wj A0C(Class<?> cls, Throwable th) {
        pw A0O;
        AbstractC0232Ww ww = this.A00;
        String A06 = AnonymousClass06.A06("Can not construct instance of ", cls.getName(), ", problem: ", th.getMessage());
        if (ww == null) {
            A0O = null;
        } else {
            A0O = ww.A0O();
        }
        return new C0223Wj(A06, A0O, th);
    }

    public final C0223Wj A0D(String str, Class<?> cls, String str2) {
        String str3;
        AbstractC0232Ww ww = this.A00;
        String name = cls.getName();
        try {
            str3 = A02(ww.A0d());
        } catch (Exception unused) {
            str3 = "[N/A]";
        }
        return new CG(AnonymousClass06.A08("Can not construct instance of ", name, " from String value '", str3, "': ", str2), ww.A0O(), str, cls);
    }

    public final N3 A0G() {
        N3 n3 = this.A02;
        if (n3 != null) {
            return n3;
        }
        N3 n32 = new N3();
        this.A02 = n32;
        return n32;
    }

    public final J9 A0H() {
        J9 j9 = this.A03;
        if (j9 == null) {
            return new J9();
        }
        this.A03 = null;
        return j9;
    }

    public final Date A0I(String str) throws IllegalArgumentException {
        try {
            DateFormat dateFormat = this.A01;
            if (dateFormat == null) {
                dateFormat = (DateFormat) this._config._base._dateFormat.clone();
                this.A01 = dateFormat;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(AnonymousClass06.A06("Failed to parse Date value '", str, "': ", e.getMessage()));
        }
    }

    public final void A0J(J9 j9) {
        int length;
        J9 j92 = this.A03;
        if (j92 != null) {
            Object[] objArr = j9.A03;
            if (objArr == null) {
                length = 0;
            } else {
                length = objArr.length;
            }
            Object[] objArr2 = j92.A03;
            if (objArr2 != null && length < objArr2.length) {
                return;
            }
        }
        this.A03 = j9;
    }

    public final void A0K(Object obj) {
        StringBuilder sb = new StringBuilder("No 'injectableValues' configured, can not inject value with id [");
        sb.append(obj);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public final boolean A0L(EnumC0225Wm wm) {
        if ((wm.getMask() & this._featureFlags) != 0) {
            return true;
        }
        return false;
    }

    private final String A01(Class<?> cls) {
        if (cls.isArray()) {
            return AnonymousClass06.A04(A01(cls.getComponentType()), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        return cls.getName();
    }

    public static final String A02(String str) {
        int length = str.length();
        if (length > 500) {
            return AnonymousClass06.A05(str.substring(0, ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS), "]...[", str.substring(length - ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS));
        }
        return str;
    }

    public final C0223Wj A09(Class<?> cls, EnumC0470q2 q2Var) {
        String name;
        String name2;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isArray()) {
                name2 = AnonymousClass06.A04(A01(componentType.getComponentType()), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            } else {
                name2 = componentType.getName();
            }
            name = AnonymousClass06.A04(name2, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        } else {
            name = cls.getName();
        }
        AbstractC0232Ww ww = this.A00;
        StringBuilder sb = new StringBuilder("Can not deserialize instance of ");
        sb.append(name);
        sb.append(" out of ");
        sb.append(q2Var);
        sb.append(" token");
        return C0223Wj.A00(ww, sb.toString());
    }

    @Override // X.jk
    public final /* bridge */ /* synthetic */ WZ A04() {
        return this._config;
    }

    public AbstractC0226Wn(AbstractC0226Wn wn, AnonymousClass8M r3, AbstractC0232Ww ww, AbstractC0435iB iBVar) {
        this._cache = wn._cache;
        this._factory = wn._factory;
        this._config = r3;
        this._featureFlags = r3._deserFeatures;
        this._view = r3._view;
        this.A00 = ww;
        this._injectableValues = iBVar;
    }

    public AbstractC0226Wn(ZD zd) {
        this._factory = zd;
        this._cache = new Zj();
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }
}

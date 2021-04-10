package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* renamed from: X.0aK  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02570aK extends AbstractC06270mS implements Serializable {
    public static final long serialVersionUID = -7727373309391091315L;
    public transient AnonymousClass0aT A00;
    public transient DateFormat A01;
    public transient C07110ok A02;
    public transient C07230oy A03;
    public final AnonymousClass0n5 _cache;
    public final C01260Fu _config;
    public final AbstractC06540n6 _factory;
    public final int _featureFlags;
    public final AbstractC06280mT _injectableValues;
    public final Class<?> _view;

    public abstract JsonDeserializer<Object> A0A(AbstractC06640nb v, Object obj) throws AnonymousClass0aG;

    public abstract AnonymousClass0mY A0H(AbstractC06640nb v, Object obj) throws AnonymousClass0aG;

    public abstract C06590nP A0I(Object obj, AnonymousClass0lR<?> v);

    public static final AnonymousClass0aG A00(AnonymousClass0aT r2, EnumC05930lf r3, String str) {
        return AnonymousClass0aG.A00(r2, "Unexpected token (" + r2.A0G() + "), expected " + r3 + ": " + str);
    }

    @Override // X.AbstractC06270mS
    public final /* bridge */ /* synthetic */ AnonymousClass0a7 A06() {
        return this._config;
    }

    @Override // X.AbstractC06270mS
    public final C07040od A07() {
        return this._config._base._typeFactory;
    }

    public final JsonDeserializer<Object> A08(AnonymousClass0aI r5) throws AnonymousClass0aG {
        JsonDeserializer<?> A002 = this._cache.A00(this, this._factory, r5);
        if (A002 == null) {
            return null;
        }
        if (A002 instanceof AbstractC06520n2) {
            A002 = ((AbstractC06520n2) A002).A1w(this, null);
        }
        AnonymousClass0o3 A0L = this._factory.A0L(this._config, r5);
        if (A0L != null) {
            return new TypeWrappedDeserializer(A0L.A04(null), A002);
        }
        return A002;
    }

    public final JsonDeserializer<Object> A09(AnonymousClass0aI r3, AbstractC02580aL r4) throws AnonymousClass0aG {
        JsonDeserializer<Object> A002 = this._cache.A00(this, this._factory, r3);
        if (A002 == null || !(A002 instanceof AbstractC06520n2)) {
            return A002;
        }
        return ((AbstractC06520n2) A002).A1w(this, r4);
    }

    public final AnonymousClass0aG A0B(Class<?> cls) {
        return A0C(cls, this.A00.A0G());
    }

    public final AnonymousClass0aG A0D(Class<?> cls, String str) {
        return AnonymousClass0aG.A00(this.A00, AnonymousClass006.A08("Can not construct instance of ", cls.getName(), ", problem: ", str));
    }

    public final AnonymousClass0aG A0E(Class<?> cls, String str, String str2) {
        return new AnonymousClass0KG(AnonymousClass006.A0A("Can not construct Map key of type ", cls.getName(), " from String \"", A02(str), "\": ", str2), this.A00.A0E(), str, cls);
    }

    public final AnonymousClass0aG A0F(Class<?> cls, Throwable th) {
        C05880lZ A0E;
        AnonymousClass0aT r4 = this.A00;
        String A08 = AnonymousClass006.A08("Can not construct instance of ", cls.getName(), ", problem: ", th.getMessage());
        if (r4 == null) {
            A0E = null;
        } else {
            A0E = r4.A0E();
        }
        return new AnonymousClass0aG(A08, A0E, th);
    }

    public final AnonymousClass0aG A0G(String str, Class<?> cls, String str2) {
        String str3;
        AnonymousClass0aT r1 = this.A00;
        String name = cls.getName();
        try {
            str3 = A02(r1.A0P());
        } catch (Exception unused) {
            str3 = "[N/A]";
        }
        return new AnonymousClass0KG(AnonymousClass006.A0A("Can not construct instance of ", name, " from String value '", str3, "': ", str2), r1.A0E(), str, cls);
    }

    public final C07110ok A0J() {
        C07110ok r0 = this.A02;
        if (r0 != null) {
            return r0;
        }
        C07110ok r02 = new C07110ok();
        this.A02 = r02;
        return r02;
    }

    public final C07230oy A0K() {
        C07230oy r1 = this.A03;
        if (r1 == null) {
            return new C07230oy();
        }
        this.A03 = null;
        return r1;
    }

    public final Date A0L(String str) throws IllegalArgumentException {
        try {
            DateFormat dateFormat = this.A01;
            if (dateFormat == null) {
                dateFormat = (DateFormat) this._config._base._dateFormat.clone();
                this.A01 = dateFormat;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Failed to parse Date value '", str, "': ", e.getMessage()));
        }
    }

    public final void A0M(C07230oy r4) {
        int length;
        C07230oy r2 = this.A03;
        if (r2 != null) {
            Object[] objArr = r4.A03;
            if (objArr == null) {
                length = 0;
            } else {
                length = objArr.length;
            }
            Object[] objArr2 = r2.A03;
            if (objArr2 != null && length < objArr2.length) {
                return;
            }
        }
        this.A03 = r4;
    }

    public final void A0N(Object obj) {
        throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
    }

    public final boolean A0O(EnumC02560aJ r3) {
        if ((r3.getMask() & this._featureFlags) != 0) {
            return true;
        }
        return false;
    }

    private final String A01(Class<?> cls) {
        if (cls.isArray()) {
            return AnonymousClass006.A05(A01(cls.getComponentType()), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        return cls.getName();
    }

    public static final String A02(String str) {
        int length = str.length();
        if (length > 500) {
            return AnonymousClass006.A07(str.substring(0, 500), "]...[", str.substring(length - 500));
        }
        return str;
    }

    public final AnonymousClass0aG A0C(Class<?> cls, EnumC05930lf r6) {
        String name;
        String name2;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isArray()) {
                name2 = AnonymousClass006.A05(A01(componentType.getComponentType()), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            } else {
                name2 = componentType.getName();
            }
            name = AnonymousClass006.A05(name2, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        } else {
            name = cls.getName();
        }
        AnonymousClass0aT r2 = this.A00;
        return AnonymousClass0aG.A00(r2, "Can not deserialize instance of " + name + " out of " + r6 + " token");
    }

    public AbstractC02570aK(AbstractC02570aK r2, C01260Fu r3, AnonymousClass0aT r4, AbstractC06280mT r5) {
        this._cache = r2._cache;
        this._factory = r2._factory;
        this._config = r3;
        this._featureFlags = r3._deserFeatures;
        this._view = r3._view;
        this.A00 = r4;
        this._injectableValues = r5;
    }

    public AbstractC02570aK(AbstractC06540n6 r2) {
        this._factory = r2;
        this._cache = new AnonymousClass0n5();
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }
}

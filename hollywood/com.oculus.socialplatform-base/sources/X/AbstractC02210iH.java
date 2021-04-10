package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* renamed from: X.0iH  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02210iH extends AnonymousClass0p0 implements Serializable {
    public static final long serialVersionUID = -7727373309391091315L;
    public transient AbstractC02280iQ A00;
    public transient DateFormat A01;
    public transient C04790rG A02;
    public transient AnonymousClass0rU A03;
    public final C04240pe _cache;
    public final AnonymousClass0HU _config;
    public final AbstractC04250pf _factory;
    public final int _featureFlags;
    public final AbstractC04020p1 _injectableValues;
    public final Class<?> _view;

    public abstract JsonDeserializer<Object> A0A(AnonymousClass0qA v, Object obj) throws C02180iD;

    public abstract AnonymousClass0p6 A0I(AnonymousClass0qA v, Object obj) throws C02180iD;

    public abstract C04430py A0J(Object obj, AbstractC03600nz<?> v);

    public static final C02180iD A00(AbstractC02280iQ r2, EnumC03640oE r3, String str) {
        StringBuilder sb = new StringBuilder("Unexpected token (");
        sb.append(r2.A0i());
        sb.append("), expected ");
        sb.append(r3);
        sb.append(": ");
        sb.append(str);
        return C02180iD.A00(r2, sb.toString());
    }

    @Override // X.AnonymousClass0p0
    public final AnonymousClass0r9 A07() {
        return this._config._base._typeFactory;
    }

    public final JsonDeserializer<Object> A08(AbstractC02190iF r5) throws C02180iD {
        JsonDeserializer<?> A002 = this._cache.A00(this, this._factory, r5);
        if (A002 == null) {
            return null;
        }
        if (A002 instanceof AbstractC04230pb) {
            A002 = ((AbstractC04230pb) A002).A2O(this, null);
        }
        AbstractC04520qa A0M = this._factory.A0M(this._config, r5);
        if (A0M != null) {
            return new TypeWrappedDeserializer(A0M.A04(null), A002);
        }
        return A002;
    }

    public final JsonDeserializer<Object> A09(AbstractC02190iF r3, AbstractC02220iI r4) throws C02180iD {
        JsonDeserializer<Object> A002 = this._cache.A00(this, this._factory, r3);
        if (A002 == null || !(A002 instanceof AbstractC04230pb)) {
            return A002;
        }
        return ((AbstractC04230pb) A002).A2O(this, r4);
    }

    public final C02180iD A0B(Class<?> cls) {
        return A0C(cls, this.A00.A0i());
    }

    public final C02180iD A0D(Class<?> cls, String str) {
        return C02180iD.A00(this.A00, AnonymousClass006.A0B("Can not construct instance of ", cls.getName(), ", problem: ", str));
    }

    public final C02180iD A0E(Class<?> cls, String str, String str2) {
        return new AnonymousClass0P3(AnonymousClass006.A0D("Can not construct Map key of type ", cls.getName(), " from String \"", A02(str), "\": ", str2), this.A00.A0W(), str, cls);
    }

    public final C02180iD A0F(Class<?> cls, Throwable th) {
        AnonymousClass0o8 A0W;
        AbstractC02280iQ r4 = this.A00;
        String A0B = AnonymousClass006.A0B("Can not construct instance of ", cls.getName(), ", problem: ", th.getMessage());
        if (r4 == null) {
            A0W = null;
        } else {
            A0W = r4.A0W();
        }
        return new C02180iD(A0B, A0W, th);
    }

    public final C02180iD A0G(String str, Class<?> cls, String str2) {
        String str3;
        AbstractC02280iQ r1 = this.A00;
        String name = cls.getName();
        try {
            str3 = A02(r1.A0m());
        } catch (Exception unused) {
            str3 = "[N/A]";
        }
        return new AnonymousClass0P3(AnonymousClass006.A0D("Can not construct instance of ", name, " from String value '", str3, "': ", str2), r1.A0W(), str, cls);
    }

    public final AnonymousClass0p6 A0H(AbstractC02190iF r3) throws C02180iD {
        AnonymousClass0p6 A0K = this._factory.A0K(this, r3);
        if (A0K == null) {
            StringBuilder sb = new StringBuilder("Can not find a (Map) Key deserializer for type ");
            sb.append(r3);
            throw new C02180iD(sb.toString());
        }
        if (A0K instanceof AbstractC04280pi) {
            ((AbstractC04280pi) A0K).A9N(this);
        }
        return A0K;
    }

    public final C04790rG A0K() {
        C04790rG r0 = this.A02;
        if (r0 != null) {
            return r0;
        }
        C04790rG r02 = new C04790rG();
        this.A02 = r02;
        return r02;
    }

    public final AnonymousClass0rU A0L() {
        AnonymousClass0rU r1 = this.A03;
        if (r1 == null) {
            return new AnonymousClass0rU();
        }
        this.A03 = null;
        return r1;
    }

    public final Date A0M(String str) throws IllegalArgumentException {
        try {
            DateFormat dateFormat = this.A01;
            if (dateFormat == null) {
                dateFormat = (DateFormat) this._config._base._dateFormat.clone();
                this.A01 = dateFormat;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Failed to parse Date value '", str, "': ", e.getMessage()));
        }
    }

    public final void A0N(AnonymousClass0rU r4) {
        int length;
        AnonymousClass0rU r2 = this.A03;
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

    public final void A0O(Object obj) {
        StringBuilder sb = new StringBuilder("No 'injectableValues' configured, can not inject value with id [");
        sb.append(obj);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public final boolean A0P(EnumC02200iG r3) {
        if ((r3.getMask() & this._featureFlags) != 0) {
            return true;
        }
        return false;
    }

    private final String A01(Class<?> cls) {
        if (cls.isArray()) {
            return AnonymousClass006.A07(A01(cls.getComponentType()), "[]");
        }
        return cls.getName();
    }

    public static final String A02(String str) {
        int length = str.length();
        if (length > 500) {
            return AnonymousClass006.A09(str.substring(0, 500), "]...[", str.substring(length - 500));
        }
        return str;
    }

    @Override // X.AnonymousClass0p0
    public final /* bridge */ /* synthetic */ AbstractC02110i2 A06() {
        return this._config;
    }

    public final C02180iD A0C(Class<?> cls, EnumC03640oE r6) {
        String name;
        String name2;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isArray()) {
                name2 = AnonymousClass006.A07(A01(componentType.getComponentType()), "[]");
            } else {
                name2 = componentType.getName();
            }
            name = AnonymousClass006.A07(name2, "[]");
        } else {
            name = cls.getName();
        }
        AbstractC02280iQ r2 = this.A00;
        StringBuilder sb = new StringBuilder("Can not deserialize instance of ");
        sb.append(name);
        sb.append(" out of ");
        sb.append(r6);
        sb.append(" token");
        return C02180iD.A00(r2, sb.toString());
    }

    public AbstractC02210iH(AbstractC04250pf r2) {
        this._factory = r2;
        this._cache = new C04240pe();
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }

    public AbstractC02210iH(AbstractC02210iH r2, AbstractC04250pf r3) {
        this._cache = r2._cache;
        this._factory = r3;
        this._config = r2._config;
        this._featureFlags = r2._featureFlags;
        this._view = r2._view;
        this.A00 = r2.A00;
        this._injectableValues = null;
    }
}

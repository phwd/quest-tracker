package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* renamed from: X.0gg  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04020gg extends AbstractC05190kV implements Serializable {
    public static final long serialVersionUID = -7727373309391091315L;
    public transient DateFormat A00;
    public transient C06310ms A01;
    public transient C06440n5 A02;
    public final C05450lA _cache = new C05450lA();
    public final AnonymousClass08X _config = null;
    public final AbstractC05460lB _factory;
    public final int _featureFlags = 0;
    public final AbstractC05200kW _injectableValues = null;
    public final Class<?> _view = null;

    public abstract JsonDeserializer<Object> A06(AbstractC05680lg v, Object obj) throws C03990gZ;

    public final C03990gZ A08(Class<?> cls, String str) {
        return C03990gZ.A00(null, AnonymousClass006.A08("Can not construct instance of ", cls.getName(), ", problem: ", str));
    }

    public final C03990gZ A09(Class<?> cls, Throwable th) {
        return new C03990gZ(AnonymousClass006.A08("Can not construct instance of ", cls.getName(), ", problem: ", th.getMessage()), null, th);
    }

    public abstract AbstractC05240kb A0A(AbstractC05680lg v, Object obj) throws C03990gZ;

    public abstract C05570lU A0B(Object obj, AbstractC04750jT<?> v);

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;Ljava/lang/String;)LX/0gZ; */
    public final void A0G(Class cls, String str) {
        int length = str.length();
        if (length > 500) {
            AnonymousClass006.A07(str.substring(0, 500), "]...[", str.substring(length - 500));
        }
        throw null;
    }

    public static final C03990gZ A00(AbstractC04100gp r2, EnumC04820ji r3, String str) {
        StringBuilder sb = new StringBuilder("Unexpected token (");
        sb.append(r2.A0a());
        sb.append("), expected ");
        sb.append(r3);
        sb.append(": ");
        sb.append(str);
        return C03990gZ.A00(r2, sb.toString());
    }

    public final JsonDeserializer<Object> A04(AbstractC04000gb r5) throws C03990gZ {
        JsonDeserializer<?> A002 = this._cache.A00(this, this._factory, r5);
        if (A002 == null) {
            return null;
        }
        if (A002 instanceof AbstractC05430l6) {
            A002 = ((AbstractC05430l6) A002).A21(this, null);
        }
        AnonymousClass0m9 A0L = this._factory.A0L(this._config, r5);
        if (A0L != null) {
            return new TypeWrappedDeserializer(A0L.A04(null), A002);
        }
        return A002;
    }

    public final JsonDeserializer<Object> A05(AbstractC04000gb r3, AbstractC04030gh r4) throws C03990gZ {
        JsonDeserializer<Object> A002 = this._cache.A00(this, this._factory, r3);
        if (A002 == null || !(A002 instanceof AbstractC05430l6)) {
            return A002;
        }
        return ((AbstractC05430l6) A002).A21(this, r4);
    }

    public final C06310ms A0C() {
        C06310ms r0 = this.A01;
        if (r0 != null) {
            return r0;
        }
        C06310ms r02 = new C06310ms();
        this.A01 = r02;
        return r02;
    }

    public final C06440n5 A0D() {
        C06440n5 r1 = this.A02;
        if (r1 == null) {
            return new C06440n5();
        }
        this.A02 = null;
        return r1;
    }

    public final Date A0E(String str) throws IllegalArgumentException {
        try {
            DateFormat dateFormat = this.A00;
            if (dateFormat == null) {
                dateFormat = (DateFormat) this._config._base._dateFormat.clone();
                this.A00 = dateFormat;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Failed to parse Date value '", str, "': ", e.getMessage()));
        }
    }

    public final void A0F(C06440n5 r4) {
        int length;
        C06440n5 r2 = this.A02;
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
        this.A02 = r4;
    }

    public final void A0H(Object obj) {
        StringBuilder sb = new StringBuilder("No 'injectableValues' configured, can not inject value with id [");
        sb.append(obj);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public final boolean A0I(EnumC04010gf r3) {
        if ((r3.getMask() & this._featureFlags) != 0) {
            return true;
        }
        return false;
    }

    public AbstractC04020gg(AbstractC05460lB r2) {
        this._factory = r2;
    }

    private final String A01(Class<?> cls) {
        if (cls.isArray()) {
            return AnonymousClass006.A05(A01(cls.getComponentType()), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        return cls.getName();
    }

    @Override // X.AbstractC05190kV
    public final /* bridge */ /* synthetic */ AbstractC03910gQ A03() {
        return this._config;
    }

    public final C03990gZ A07(Class<?> cls, EnumC04820ji r6) {
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
        StringBuilder sb = new StringBuilder("Can not deserialize instance of ");
        sb.append(name);
        sb.append(" out of ");
        sb.append(r6);
        sb.append(" token");
        return C03990gZ.A00(null, sb.toString());
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0i6  reason: invalid class name and case insensitive filesystem */
public class C02130i6 extends AbstractC03650oF implements AbstractC03700oK, Serializable {
    public static final AbstractC02190iF A00 = AnonymousClass0C7.A00(AbstractC02170iC.class);
    public static final AbstractC03660oG A01 = new C02240iK();
    public static final AbstractC02230iJ A02;
    public static final AnonymousClass0qO<?> A03;
    public static final C04140pQ DEFAULT_BASE;
    public static final AnonymousClass0qG DEFAULT_INTROSPECTOR;
    public static final long serialVersionUID = 1;
    public AnonymousClass0HU _deserializationConfig;
    public AnonymousClass0S0 _deserializationContext;
    public AbstractC04020p1 _injectableValues;
    public final AnonymousClass0iU _jsonFactory;
    public final HashMap<C04720r6, Class<?>> _mixInAnnotations;
    public final ConcurrentHashMap<AbstractC02190iF, JsonDeserializer<Object>> _rootDeserializers;
    public final C04930rZ _rootNames;
    public AnonymousClass0HM _serializationConfig;
    public AbstractC04630qr _serializerFactory;
    public AnonymousClass0Oe _serializerProvider;
    public AbstractC04510qY _subtypeResolver;
    public AnonymousClass0r9 _typeFactory;

    static {
        C01970hj r1 = C01970hj.A00;
        DEFAULT_INTROSPECTOR = r1;
        AnonymousClass0Ov r2 = new AnonymousClass0Ov();
        A02 = r2;
        C01910hd r3 = C01910hd.A00;
        A03 = r3;
        DEFAULT_BASE = new C04140pQ(r1, r2, r3, null, AnonymousClass0r9.A02, null, C04940ra.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), AnonymousClass0o3.A01);
    }

    @Override // X.AbstractC03650oF
    public final void A02(AbstractC02300iS r4, Object obj) throws IOException, C02310iT, C02180iD {
        AnonymousClass0HM r2 = this._serializationConfig;
        if (r2.A06(AnonymousClass0i4.INDENT_OUTPUT)) {
            r4.A08();
        }
        if (!r2.A06(AnonymousClass0i4.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            A05(r2).A0J(r4, obj);
            if (r2.A06(AnonymousClass0i4.FLUSH_AFTER_WRITE_VALUE)) {
                r4.flush();
                return;
            }
            return;
        }
        Closeable closeable = (Closeable) obj;
        try {
            A05(r2).A0J(r4, obj);
            if (r2.A06(AnonymousClass0i4.FLUSH_AFTER_WRITE_VALUE)) {
                r4.flush();
            }
            closeable.close();
        } catch (Throwable th) {
            if (closeable != null) {
                try {
                    closeable.close();
                    throw th;
                } catch (IOException unused) {
                    throw th;
                }
            } else {
                throw th;
            }
        }
    }

    public JsonDeserializer<Object> A03(AbstractC02210iH r3, AbstractC02190iF r4) throws C02180iD {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(r4);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> A08 = r3.A08(r4);
        if (A08 != null) {
            this._rootDeserializers.put(r4, A08);
            return A08;
        }
        StringBuilder sb = new StringBuilder("Can not find a deserializer for type ");
        sb.append(r4);
        throw new C02180iD(sb.toString());
    }

    public final AnonymousClass04L A04() {
        return new AnonymousClass04L(this._deserializationConfig._nodeFactory);
    }

    public AnonymousClass0Oe A05(AnonymousClass0HM r3) {
        return this._serializerProvider.A0I(r3, this._serializerFactory);
    }

    @Override // X.AbstractC03650oF
    public final AnonymousClass0iU A00() {
        return this._jsonFactory;
    }

    @Override // X.AbstractC03650oF
    @Deprecated
    public final AnonymousClass0iU A01() {
        return this._jsonFactory;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A[SYNTHETIC, Splitter:B:22:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052 A[SYNTHETIC, Splitter:B:26:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A06(java.lang.Object r7) throws X.C03620oC {
        /*
        // Method dump skipped, instructions count: 145
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02130i6.A06(java.lang.Object):java.lang.String");
    }

    @Override // X.AbstractC03700oK
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }

    public C02130i6() {
        this(null, null, null);
    }

    public C02130i6(AnonymousClass0iU r5, AnonymousClass0Oe r6, AnonymousClass0S0 r7) {
        this._mixInAnnotations = new HashMap<>();
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (r5 == null) {
            this._jsonFactory = new AnonymousClass0Sk(this);
        } else {
            this._jsonFactory = r5;
            if (r5.A04() == null) {
                r5._objectCodec = this;
            }
        }
        C01890hH r2 = new C01890hH();
        this._subtypeResolver = r2;
        this._rootNames = new C04930rZ();
        this._typeFactory = AnonymousClass0r9.A02;
        C04140pQ r3 = DEFAULT_BASE;
        this._serializationConfig = new AnonymousClass0HM(r3, r2, this._mixInAnnotations);
        this._deserializationConfig = new AnonymousClass0HU(r3, this._subtypeResolver, this._mixInAnnotations);
        this._serializerProvider = new AnonymousClass0Ch();
        this._deserializationContext = new AnonymousClass0HA(AnonymousClass0SH.A00);
        this._serializerFactory = C00980Of.A00;
    }
}

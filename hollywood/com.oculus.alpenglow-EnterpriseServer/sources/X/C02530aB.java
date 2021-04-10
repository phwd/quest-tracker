package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0aB  reason: invalid class name and case insensitive filesystem */
public final class C02530aB extends AbstractC05940lg implements AbstractC05990ll, Serializable {
    public static final AnonymousClass0aI A00 = AnonymousClass0C9.A00(AnonymousClass0aF.class);
    public static final AbstractC05950lh A01 = new C02600aN();
    public static final AbstractC02590aM A02;
    public static final C06420mr A03;
    public static final AbstractC06700nh A04;
    public static final AbstractC06760no<?> A05;
    public static final long serialVersionUID = 1;
    public C01260Fu _deserializationConfig;
    public AbstractC01690Ky _deserializationContext;
    public final AbstractC06280mT _injectableValues;
    public final C02660aX _jsonFactory;
    public final HashMap<C07010oa, Class<?>> _mixInAnnotations;
    public final ConcurrentHashMap<AnonymousClass0aI, JsonDeserializer<Object>> _rootDeserializers;
    public final C07270p3 _rootNames;
    public AnonymousClass0FM _serializationConfig;
    public AbstractC06900oL _serializerFactory;
    public AnonymousClass0Ju _serializerProvider;
    public AnonymousClass0o1 _subtypeResolver;
    public C07040od _typeFactory;

    static {
        C02420Zo r1 = C02420Zo.A00;
        A04 = r1;
        AnonymousClass0K9 r2 = new AnonymousClass0K9();
        A02 = r2;
        C02360Zi r3 = C02360Zi.A00;
        A05 = r3;
        A03 = new C06420mr(r1, r2, r3, C07040od.A02, C07280p4.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), C05840lV.A01);
    }

    private final JsonDeserializer<Object> A00(AbstractC02570aK r3, AnonymousClass0aI r4) throws AnonymousClass0aG {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(r4);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> A08 = r3.A08(r4);
        if (A08 != null) {
            this._rootDeserializers.put(r4, A08);
            return A08;
        }
        throw new AnonymousClass0aG("Can not find a deserializer for type " + r4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (r3.length() > 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d9, code lost:
        if ((X.EnumC02560aJ.UNWRAP_ROOT_VALUE.getMask() & r2._deserFeatures) != 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00dd, code lost:
        r1 = (T) r7.A09(r10, r8);
     */
    @Override // X.AbstractC05940lg
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T A01(X.AnonymousClass0aT r10, X.AnonymousClass0mF<?> r11) throws java.io.IOException, X.C02630aU, X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 230
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02530aB.A01(X.0aT, X.0mF):java.lang.Object");
    }

    @Override // X.AbstractC05940lg
    public final void A02(AbstractC02640aV r5, Object obj) throws IOException, C02650aW, AnonymousClass0aG {
        AnonymousClass0FM r3 = this._serializationConfig;
        if (r3.A06(AnonymousClass0a9.INDENT_OUTPUT)) {
            r5.A08();
        }
        if (!r3.A06(AnonymousClass0a9.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            this._serializerProvider.A0G(r3, this._serializerFactory).A0H(r5, obj);
            if (r3.A06(AnonymousClass0a9.FLUSH_AFTER_WRITE_VALUE)) {
                r5.flush();
                return;
            }
            return;
        }
        Closeable closeable = (Closeable) obj;
        try {
            this._serializerProvider.A0G(r3, this._serializerFactory).A0H(r5, obj);
            if (r3.A06(AnonymousClass0a9.FLUSH_AFTER_WRITE_VALUE)) {
                r5.flush();
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

    public C02530aB() {
        this(null, null, null);
    }

    public C02530aB(C02660aX r5, AnonymousClass0Ju r6, AbstractC01690Ky r7) {
        this._mixInAnnotations = new HashMap<>();
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (r5 == null) {
            this._jsonFactory = new AnonymousClass0LC(this);
        } else {
            this._jsonFactory = r5;
            if (r5.A00() == null) {
                r5._objectCodec = this;
            }
        }
        C02350Zh r2 = new C02350Zh();
        this._subtypeResolver = r2;
        this._rootNames = new C07270p3();
        this._typeFactory = C07040od.A02;
        C06420mr r3 = A03;
        this._serializationConfig = new AnonymousClass0FM(r3, r2, this._mixInAnnotations);
        this._deserializationConfig = new C01260Fu(r3, this._subtypeResolver, this._mixInAnnotations);
        this._serializerProvider = new AnonymousClass0E0();
        this._deserializationContext = new AnonymousClass0F2(C01700Kz.A00);
        this._serializerFactory = AnonymousClass0Jv.A00;
    }
}

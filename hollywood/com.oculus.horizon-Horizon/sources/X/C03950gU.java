package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0gU  reason: invalid class name and case insensitive filesystem */
public final class C03950gU extends AnonymousClass0jj implements AbstractC04870jo, Serializable {
    public static final AbstractC04000gb A00 = AnonymousClass071.A00(AbstractC03980gY.class);
    public static final AbstractC04830jk A01 = new C04050gj();
    public static final AbstractC04040gi A02;
    public static final C05350kv A03;
    public static final AbstractC05740lm A04;
    public static final AbstractC05820lu<?> A05;
    public static final long serialVersionUID = 1;
    public AnonymousClass08X _deserializationConfig;
    public AnonymousClass0HE _deserializationContext;
    public AbstractC05200kW _injectableValues;
    public final C04140gt _jsonFactory;
    public final HashMap<C06210mi, Class<?>> _mixInAnnotations;
    public final ConcurrentHashMap<AbstractC04000gb, JsonDeserializer<Object>> _rootDeserializers;
    public final C06450nA _rootNames;
    public AnonymousClass08W _serializationConfig;
    public AbstractC06070mS _serializerFactory;
    public AnonymousClass0GH _serializerProvider;
    public AbstractC05920m7 _subtypeResolver;
    public C06240ml _typeFactory;

    static {
        C03780g6 r1 = C03780g6.A00;
        A04 = r1;
        AnonymousClass0GU r2 = new AnonymousClass0GU();
        A02 = r2;
        C03740g1 r3 = C03740g1.A00;
        A05 = r3;
        A03 = new C05350kv(r1, r2, r3, C06240ml.A02, C06460nB.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), AnonymousClass0jX.A01);
    }

    public C03950gU() {
        this(null);
    }

    public C03950gU(C04140gt r5) {
        this._mixInAnnotations = new HashMap<>();
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (r5 == null) {
            this._jsonFactory = new AnonymousClass0HK(this);
        } else {
            this._jsonFactory = r5;
            if (r5.A01() == null) {
                r5._objectCodec = this;
            }
        }
        C03730fz r2 = new C03730fz();
        this._subtypeResolver = r2;
        this._rootNames = new C06450nA();
        this._typeFactory = C06240ml.A02;
        C05350kv r3 = A03;
        this._serializationConfig = new AnonymousClass08W(r3, r2, this._mixInAnnotations);
        this._deserializationConfig = new AnonymousClass08X(r3, this._subtypeResolver, this._mixInAnnotations);
        this._serializerProvider = new AnonymousClass07F();
        this._deserializationContext = new AnonymousClass08S(AnonymousClass0HF.A00);
        this._serializerFactory = AnonymousClass0GI.A00;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.We  reason: case insensitive filesystem */
public final class C0219We extends AbstractC0471q3 implements q8, Serializable {
    public static final AbstractC0224Wl A00 = AnonymousClass73.A00(AbstractC0222Wi.class);
    public static final AbstractC0472q4 A01 = new C0228Wq();
    public static final Wp A02;
    public static final dV A03;
    public static final VP A04;
    public static final VI<?> A05;
    public static final long serialVersionUID = 1;
    public AnonymousClass8M _deserializationConfig;
    public Cs _deserializationContext;
    public final AbstractC0435iB _injectableValues;
    public final X0 _jsonFactory;
    public final HashMap<ON, Class<?>> _mixInAnnotations;
    public final ConcurrentHashMap<AbstractC0224Wl, JsonDeserializer<Object>> _rootDeserializers;
    public final C0100Ib _rootNames;
    public AnonymousClass8L _serializationConfig;
    public AbstractC0140Rw _serializerFactory;
    public AbstractC0064Bv _serializerProvider;
    public V6 _subtypeResolver;
    public NT _typeFactory;

    static {
        WG wg = WG.A00;
        A04 = wg;
        C9 c9 = new C9();
        A02 = c9;
        WB wb = WB.A00;
        A05 = wb;
        A03 = new dV(wg, c9, wb, NT.A02, IH.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), C0466pr.A01);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Wn;LX/Wl;)Lcom/fasterxml/jackson/databind/JsonDeserializer<Ljava/lang/Object;>; */
    public static final JsonDeserializer A00(C0219We we, AbstractC0226Wn wn, AbstractC0224Wl wl) throws C0223Wj {
        JsonDeserializer<Object> jsonDeserializer = we._rootDeserializers.get(wl);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> A052 = wn.A05(wl);
        if (A052 != null) {
            we._rootDeserializers.put(wl, A052);
            return A052;
        }
        StringBuilder sb = new StringBuilder("Can not find a deserializer for type ");
        sb.append(wl);
        throw new C0223Wj(sb.toString());
    }

    public C0219We() {
        this(null, null, null);
    }

    public C0219We(X0 x0, AbstractC0064Bv bv, Cs cs) {
        this._mixInAnnotations = new HashMap<>();
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (x0 == null) {
            this._jsonFactory = new DH(this);
        } else {
            this._jsonFactory = x0;
            if (x0.A00() == null) {
                x0._objectCodec = this;
            }
        }
        WA wa = new WA();
        this._subtypeResolver = wa;
        this._rootNames = new C0100Ib();
        this._typeFactory = NT.A02;
        dV dVVar = A03;
        this._serializationConfig = new AnonymousClass8L(dVVar, wa, this._mixInAnnotations);
        this._deserializationConfig = new AnonymousClass8M(dVVar, this._subtypeResolver, this._mixInAnnotations);
        this._serializerProvider = new AnonymousClass7H();
        this._deserializationContext = new AnonymousClass8H(C0074Ct.A00);
        this._serializerFactory = C0065Bw.A00;
    }
}

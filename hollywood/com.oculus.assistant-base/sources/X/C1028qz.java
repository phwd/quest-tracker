package X;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.qz  reason: case insensitive filesystem */
public final class C1028qz extends NY implements Nc, Serializable {
    public static final AbstractC1024qt A00 = fF.A00(OA.class);
    public static final NZ A01 = new C1019qo();
    public static final AbstractC1020qp A02;
    public static final PN A03;
    public static final OS DEFAULT_BASE;
    public static final PG DEFAULT_INTROSPECTOR;
    public static final long serialVersionUID = 1;
    public AnonymousClass2I _deserializationConfig;
    public AbstractC0371Uh _deserializationContext;
    public O7 _injectableValues;
    public final C1010qe _jsonFactory;
    public final HashMap _mixInAnnotations;
    public final ConcurrentHashMap _rootDeserializers;
    public final QH _rootNames;
    public AnonymousClass2H _serializationConfig;
    public AbstractC0285Ph _serializerFactory;
    public AbstractC0250No _serializerProvider;
    public PP _subtypeResolver;
    public C0300Pw _typeFactory;

    static {
        C1047rM rMVar = C1047rM.A00;
        DEFAULT_INTROSPECTOR = rMVar;
        Rw rw = new Rw();
        A02 = rw;
        C1053rS rSVar = C1053rS.A00;
        A03 = rSVar;
        DEFAULT_BASE = new OS(rMVar, rw, rSVar, C0300Pw.A02, QI.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), NQ.A01);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0053 A[SYNTHETIC, Splitter:B:26:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0058 A[SYNTHETIC, Splitter:B:30:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A00(java.lang.Object r8) {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1028qz.A00(java.lang.Object):java.lang.String");
    }

    public C1028qz() {
        this(null);
    }

    public C1028qz(C1010qe qeVar) {
        this._mixInAnnotations = new HashMap();
        this._rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);
        if (qeVar == null) {
            this._jsonFactory = new VC(this);
        } else {
            this._jsonFactory = qeVar;
            if (qeVar._objectCodec == null) {
                qeVar._objectCodec = this;
            }
        }
        C1054rT rTVar = new C1054rT();
        this._subtypeResolver = rTVar;
        this._rootNames = new QH();
        this._typeFactory = C0300Pw.A02;
        OS os = DEFAULT_BASE;
        this._serializationConfig = new AnonymousClass2H(os, rTVar, this._mixInAnnotations);
        this._deserializationConfig = new AnonymousClass2I(os, this._subtypeResolver, this._mixInAnnotations);
        this._serializerProvider = new C00172j();
        this._deserializationContext = new AnonymousClass2E(C0373Uj.A00);
        this._serializerFactory = PH.A00;
    }
}

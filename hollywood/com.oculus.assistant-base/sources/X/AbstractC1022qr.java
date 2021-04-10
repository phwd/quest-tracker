package X;

import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;

/* renamed from: X.qr  reason: case insensitive filesystem */
public abstract class AbstractC1022qr extends O6 implements Serializable {
    public static final long serialVersionUID = -7727373309391091315L;
    public transient DateFormat A00;
    public transient Q3 A01;
    public transient QE A02;
    public final C0265Oe _cache = new C0265Oe();
    public final AnonymousClass2I _config = null;
    public final AbstractC0266Of _factory;
    public final int _featureFlags = 0;
    public final O7 _injectableValues = null;
    public final Class _view = null;

    public final JsonDeserializer A09(P9 p9, Object obj) {
        if (obj != null) {
            if (!(obj instanceof JsonDeserializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonDeserializer.None.class || cls == OR.class)) {
                        if (JsonDeserializer.class.isAssignableFrom(cls)) {
                            obj = Q5.A02(cls, this._config.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<JsonDeserializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned deserializer definition of type ", obj.getClass().getName(), "; expected type JsonDeserializer or Class<JsonDeserializer> instead"));
                }
            }
            JsonDeserializer jsonDeserializer = (JsonDeserializer) obj;
            if (jsonDeserializer instanceof Oi) {
                ((Oi) jsonDeserializer).A4s(this);
            }
            return jsonDeserializer;
        }
        return null;
    }

    public final C1025qv A0B(Class cls, String str) {
        return C1025qv.A00(null, AnonymousClass08.A06("Can not construct instance of ", cls.getName(), ", problem: ", str));
    }

    public final C1025qv A0C(Class cls, Throwable th) {
        return new C1025qv(AnonymousClass08.A06("Can not construct instance of ", cls.getName(), ", problem: ", th.getMessage()), null, th);
    }

    public final OD A0E(P9 p9, Object obj) {
        if (obj != null) {
            if (!(obj instanceof OD)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == AbstractC1026qx.class || cls == OR.class)) {
                        if (OD.class.isAssignableFrom(cls)) {
                            obj = Q5.A02(cls, this._config.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<KeyDeserializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned key deserializer definition of type ", obj.getClass().getName(), "; expected type KeyDeserializer or Class<KeyDeserializer> instead"));
                }
            }
            OD od = (OD) obj;
            if (od instanceof Oi) {
                ((Oi) od).A4s(this);
            }
            return od;
        }
        return null;
    }

    public final C0277Oy A0F(Object obj, NN nn) {
        AbstractC0371Uh uh = (AbstractC0371Uh) this;
        AbstractC1008qc qcVar = (AbstractC1008qc) nn;
        NM nm = new NM(qcVar.getClass(), qcVar._scope, obj);
        LinkedHashMap linkedHashMap = uh.A00;
        if (linkedHashMap == null) {
            uh.A00 = new LinkedHashMap();
        } else {
            C0277Oy oy = (C0277Oy) linkedHashMap.get(nm);
            if (oy != null) {
                return oy;
            }
        }
        C0277Oy oy2 = new C0277Oy(obj);
        uh.A00.put(nm, oy2);
        return oy2;
    }

    public static final C1025qv A00(AbstractC1014qi qiVar, NX nx, String str) {
        StringBuilder sb = new StringBuilder("Unexpected token (");
        sb.append(qiVar.A0U());
        sb.append("), expected ");
        sb.append(nx);
        sb.append(": ");
        sb.append(str);
        return C1025qv.A00(qiVar, sb.toString());
    }

    public final JsonDeserializer A07(AbstractC1024qt qtVar) {
        JsonDeserializer A002 = this._cache.A00(this, this._factory, qtVar);
        if (A002 == null) {
            return null;
        }
        if (A002 instanceof AbstractC0264Od) {
            A002 = ((AbstractC0264Od) A002).A1X(this, null);
        }
        PR A0A = this._factory.A0A(this._config, qtVar);
        if (A0A != null) {
            return new TypeWrappedDeserializer(A0A.A03(null), A002);
        }
        return A002;
    }

    public final JsonDeserializer A08(AbstractC1024qt qtVar, O5 o5) {
        JsonDeserializer A002 = this._cache.A00(this, this._factory, qtVar);
        if (A002 == null || !(A002 instanceof AbstractC0264Od)) {
            return A002;
        }
        return ((AbstractC0264Od) A002).A1X(this, o5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        if (r1.hasNext() == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0036, code lost:
        r1 = r6._factoryConfig;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003c, code lost:
        if (r1.A00() == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        r1 = new X.Q2(r1._modifiers).iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.OD A0D(X.AbstractC1024qt r14) {
        /*
        // Method dump skipped, instructions count: 509
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1022qr.A0D(X.qt):X.OD");
    }

    public final Q3 A0G() {
        Q3 q3 = this.A01;
        if (q3 != null) {
            return q3;
        }
        Q3 q32 = new Q3();
        this.A01 = q32;
        return q32;
    }

    public final QE A0H() {
        QE qe = this.A02;
        if (qe == null) {
            return new QE();
        }
        this.A02 = null;
        return qe;
    }

    public final Date A0I(String str) {
        try {
            DateFormat dateFormat = this.A00;
            if (dateFormat == null) {
                dateFormat = (DateFormat) this._config._base._dateFormat.clone();
                this.A00 = dateFormat;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(AnonymousClass08.A06("Failed to parse Date value '", str, "': ", e.getMessage()));
        }
    }

    public final void A0J() {
        throw new NullPointerException("getCurrentToken");
    }

    public final void A0K(QE qe) {
        int length;
        QE qe2 = this.A02;
        if (qe2 != null) {
            Object[] objArr = qe.A03;
            if (objArr == null) {
                length = 0;
            } else {
                length = objArr.length;
            }
            Object[] objArr2 = qe2.A03;
            if (objArr2 != null && length < objArr2.length) {
                return;
            }
        }
        this.A02 = qe;
    }

    public final void A0L(Class cls) {
        try {
            throw new NullPointerException("getText");
        } catch (Exception unused) {
            throw new NullPointerException("getTokenLocation");
        }
    }

    public final void A0N(Object obj) {
        StringBuilder sb = new StringBuilder("No 'injectableValues' configured, can not inject value with id [");
        sb.append(obj);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public final boolean A0O(EnumC1023qs qsVar) {
        if ((qsVar.getMask() & this._featureFlags) != 0) {
            return true;
        }
        return false;
    }

    public AbstractC1022qr(AbstractC0266Of of) {
        this._factory = of;
    }

    private final String A01(Class cls) {
        if (cls.isArray()) {
            return AnonymousClass08.A04(A01(cls.getComponentType()), "[]");
        }
        return cls.getName();
    }

    public final C1025qv A0A(Class cls, NX nx) {
        String name;
        String name2;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isArray()) {
                name2 = AnonymousClass08.A04(A01(componentType.getComponentType()), "[]");
            } else {
                name2 = componentType.getName();
            }
            name = AnonymousClass08.A04(name2, "[]");
        } else {
            name = cls.getName();
        }
        StringBuilder sb = new StringBuilder("Can not deserialize instance of ");
        sb.append(name);
        sb.append(" out of ");
        sb.append(nx);
        sb.append(" token");
        return C1025qv.A00(null, sb.toString());
    }

    public final void A0M(Class cls, String str) {
        int length = str.length();
        if (length > 500) {
            AnonymousClass08.A05(str.substring(0, ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS), "]...[", str.substring(length - ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS));
        }
        throw new NullPointerException("getTokenLocation");
    }
}

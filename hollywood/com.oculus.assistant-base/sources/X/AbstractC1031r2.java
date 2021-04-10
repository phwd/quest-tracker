package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers$CalendarKeySerializer;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers$DateKeySerializer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;

/* renamed from: X.r2  reason: case insensitive filesystem */
public abstract class AbstractC1031r2 extends O6 {
    public static final JsonSerializer A00 = new FailingSerializer();
    public static final JsonSerializer A01 = new UnknownSerializer();
    public static final AbstractC1024qt A02 = new fF(Object.class);
    public final AnonymousClass2H _config;
    public DateFormat _dateFormat;
    public JsonSerializer _keySerializer;
    public final C0293Pp _knownSerializers;
    public JsonSerializer _nullKeySerializer;
    public JsonSerializer _nullValueSerializer;
    public final QH _rootNames;
    public final Class _serializationView;
    public final C0284Pg _serializerCache;
    public final AbstractC0285Ph _serializerFactory;
    public JsonSerializer _unknownTypeSerializer;

    public final JsonSerializer A09(P9 p9, Object obj) {
        if (obj != null) {
            if (!(obj instanceof JsonSerializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonSerializer.None.class || cls == OR.class)) {
                        if (JsonSerializer.class.isAssignableFrom(cls)) {
                            obj = Q5.A02(cls, this._config.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<JsonSerializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned serializer definition of type ", obj.getClass().getName(), "; expected type JsonSerializer or Class<JsonSerializer> instead"));
                }
            }
            JsonSerializer jsonSerializer = (JsonSerializer) obj;
            if (jsonSerializer instanceof AbstractC0282Pe) {
                ((AbstractC0282Pe) jsonSerializer).A4t(this);
            }
            return jsonSerializer;
        }
        return null;
    }

    public final C0294Pq A0C(Object obj, NN nn) {
        NN nn2;
        AbstractC0250No no = (AbstractC0250No) this;
        IdentityHashMap identityHashMap = no.A01;
        if (identityHashMap == null) {
            no.A01 = new IdentityHashMap();
        } else {
            C0294Pq pq = (C0294Pq) identityHashMap.get(obj);
            if (pq != null) {
                return pq;
            }
        }
        ArrayList arrayList = no.A00;
        if (arrayList == null) {
            arrayList = new ArrayList(8);
            no.A00 = arrayList;
        } else {
            int i = 0;
            int size = arrayList.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                nn2 = (NN) arrayList.get(i);
                AbstractC1008qc qcVar = (AbstractC1008qc) nn2;
                if (!(qcVar instanceof AnonymousClass2x)) {
                    if (nn.getClass() == qcVar.getClass() && ((AbstractC1008qc) nn)._scope == qcVar._scope) {
                        break;
                    }
                } else {
                    AnonymousClass2x r8 = (AnonymousClass2x) qcVar;
                    if (nn.getClass() == r8.getClass()) {
                        AnonymousClass2x r2 = (AnonymousClass2x) nn;
                        if (r2._scope == r8._scope && r2._property == r8._property) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            nn = nn2;
            C0294Pq pq2 = new C0294Pq(nn);
            no.A01.put(obj, pq2);
            return pq2;
        }
        arrayList.add(nn);
        C0294Pq pq22 = new C0294Pq(nn);
        no.A01.put(obj, pq22);
        return pq22;
    }

    public static final DateFormat A00(AbstractC1031r2 r2Var) {
        DateFormat dateFormat = r2Var._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) r2Var._config._base._dateFormat.clone();
        r2Var._dateFormat = dateFormat2;
        return dateFormat2;
    }

    public final JsonSerializer A07(AbstractC1024qt qtVar, O5 o5) {
        AbstractC0285Ph ph = this._serializerFactory;
        AnonymousClass2H r5 = this._config;
        JsonSerializer jsonSerializer = this._keySerializer;
        AbstractC1059rZ rZVar = (AbstractC1059rZ) ph;
        O4 A022 = r5.A02(r5.A03(qtVar._class));
        AbstractC0286Pi[] piArr = rZVar._factoryConfig._additionalKeySerializers;
        if (piArr.length > 0) {
            Iterator it = new Q2(piArr).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JsonSerializer A26 = ((AbstractC0286Pi) it.next()).A26(r5, qtVar, A022);
                if (A26 != null) {
                    jsonSerializer = A26;
                    break;
                }
            }
        }
        if (jsonSerializer == null) {
            Class cls = qtVar._class;
            if (cls == String.class) {
                jsonSerializer = C0296Ps.A01;
            } else {
                if (cls != Object.class) {
                    if (Date.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$DateKeySerializer.A00;
                    } else if (Calendar.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$CalendarKeySerializer.A00;
                    }
                }
                jsonSerializer = C0296Ps.A00;
            }
        }
        PZ[] pzArr = rZVar._factoryConfig._modifiers;
        if (pzArr.length > 0) {
            Iterator it2 = new Q2(pzArr).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        if (jsonSerializer instanceof AbstractC0282Pe) {
            ((AbstractC0282Pe) jsonSerializer).A4t(this);
        }
        if (jsonSerializer instanceof AbstractC0278Pa) {
            return ((AbstractC0278Pa) jsonSerializer).A1Y(this, o5);
        }
        return jsonSerializer;
    }

    public final JsonSerializer A08(AbstractC1024qt qtVar, O5 o5) {
        C0293Pp pp = this._knownSerializers;
        C0283Pf pf = pp.A00;
        if (pf == null) {
            pf = new C0283Pf(qtVar, false);
            pp.A00 = pf;
        } else {
            pf.A01 = qtVar;
            pf.A02 = null;
            pf.A03 = false;
            pf.A00 = qtVar.hashCode() - 1;
        }
        JsonSerializer A002 = pp.A01.A00(pf);
        if (A002 == null) {
            C0284Pg pg = this._serializerCache;
            synchronized (pg) {
                A002 = (JsonSerializer) pg.A01.get(new C0283Pf(qtVar, false));
            }
            if (A002 == null) {
                try {
                    A002 = this._serializerFactory.A02(this, qtVar);
                    if (A002 == null) {
                        return this._unknownTypeSerializer;
                    }
                    C0284Pg pg2 = this._serializerCache;
                    synchronized (pg2) {
                        if (pg2.A01.put(new C0283Pf(qtVar, false), A002) == null) {
                            pg2.A00 = null;
                        }
                        if (A002 instanceof AbstractC0282Pe) {
                            ((AbstractC0282Pe) A002).A4t(this);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    throw new C1025qv(e.getMessage(), null, e);
                }
            }
        }
        if (A002 instanceof AbstractC0278Pa) {
            return ((AbstractC0278Pa) A002).A1Y(this, o5);
        }
        return A002;
    }

    public final JsonSerializer A0A(Class cls, O5 o5) {
        C0293Pp pp = this._knownSerializers;
        C0283Pf pf = pp.A00;
        if (pf == null) {
            pf = new C0283Pf(cls, false);
            pp.A00 = pf;
        } else {
            pf.A01 = null;
            pf.A02 = cls;
            pf.A03 = false;
            pf.A00 = cls.getName().hashCode();
        }
        JsonSerializer A002 = pp.A01.A00(pf);
        if (A002 == null) {
            C0284Pg pg = this._serializerCache;
            synchronized (pg) {
                A002 = (JsonSerializer) pg.A01.get(new C0283Pf(cls, false));
            }
            if (A002 == null) {
                C0284Pg pg2 = this._serializerCache;
                AbstractC1024qt A03 = this._config.A03(cls);
                synchronized (pg2) {
                    A002 = (JsonSerializer) pg2.A01.get(new C0283Pf(A03, false));
                }
                if (A002 == null) {
                    try {
                        A002 = this._serializerFactory.A02(this, this._config.A03(cls));
                        if (A002 == null) {
                            return this._unknownTypeSerializer;
                        }
                        C0284Pg pg3 = this._serializerCache;
                        synchronized (pg3) {
                            if (pg3.A01.put(new C0283Pf(cls, false), A002) == null) {
                                pg3.A00 = null;
                            }
                            if (A002 instanceof AbstractC0282Pe) {
                                ((AbstractC0282Pe) A002).A4t(this);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new C1025qv(e.getMessage(), null, e);
                    }
                }
            }
        }
        if (A002 instanceof AbstractC0278Pa) {
            return ((AbstractC0278Pa) A002).A1Y(this, o5);
        }
        return A002;
    }

    public JsonSerializer A0B(Class cls, boolean z, O5 o5) {
        C0293Pp pp = this._knownSerializers;
        C0283Pf pf = pp.A00;
        if (pf == null) {
            pf = new C0283Pf(cls, true);
            pp.A00 = pf;
        } else {
            pf.A01 = null;
            pf.A02 = cls;
            pf.A03 = true;
            pf.A00 = cls.getName().hashCode() + 1;
        }
        JsonSerializer A002 = pp.A01.A00(pf);
        if (A002 == null) {
            C0284Pg pg = this._serializerCache;
            synchronized (pg) {
                A002 = (JsonSerializer) pg.A01.get(new C0283Pf(cls, true));
            }
            if (A002 == null) {
                JsonSerializer A0A = A0A(cls, o5);
                AbstractC0285Ph ph = this._serializerFactory;
                AnonymousClass2H r1 = this._config;
                PU A03 = ph.A03(r1, r1.A03(cls));
                if (A03 != null) {
                    A0A = new TypeWrappedSerializer(A03.A00(o5), A0A);
                }
                if (!z) {
                    return A0A;
                }
                C0284Pg pg2 = this._serializerCache;
                synchronized (pg2) {
                    if (pg2.A01.put(new C0283Pf(cls, true), A0A) == null) {
                        pg2.A00 = null;
                    }
                }
                return A0A;
            }
        }
        return A002;
    }

    public final void A0D(AbstractC1012qg qgVar) {
        this._nullValueSerializer.A0B(null, qgVar, this);
    }

    public final void A0E(Date date, AbstractC1012qg qgVar) {
        String format;
        if (this._config.A06(EnumC1030r1.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            format = String.valueOf(date.getTime());
        } else {
            format = A00(this).format(date);
        }
        qgVar.A0M(format);
    }

    public final void A0F(Date date, AbstractC1012qg qgVar) {
        if (this._config.A06(EnumC1030r1.WRITE_DATES_AS_TIMESTAMPS)) {
            qgVar.A0H(date.getTime());
        } else {
            qgVar.A0P(A00(this).format(date));
        }
    }

    public AbstractC1031r2() {
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        this._config = null;
        this._serializerFactory = null;
        this._serializerCache = new C0284Pg();
        this._knownSerializers = null;
        this._rootNames = new QH();
        this._serializationView = null;
    }

    public AbstractC1031r2(AbstractC1031r2 r2Var, AnonymousClass2H r5, AbstractC0285Ph ph) {
        C0293Pp pp;
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        if (r5 != null) {
            this._serializerFactory = ph;
            this._config = r5;
            C0284Pg pg = r2Var._serializerCache;
            this._serializerCache = pg;
            this._unknownTypeSerializer = r2Var._unknownTypeSerializer;
            this._keySerializer = r2Var._keySerializer;
            this._nullValueSerializer = r2Var._nullValueSerializer;
            this._nullKeySerializer = r2Var._nullKeySerializer;
            this._rootNames = r2Var._rootNames;
            synchronized (pg) {
                pp = pg.A00;
                if (pp == null) {
                    pp = new C0293Pp(new C0288Pk(pg.A01));
                    pg.A00 = pp;
                }
            }
            this._knownSerializers = new C0293Pp(pp.A01);
            this._serializationView = r5._view;
            return;
        }
        throw null;
    }
}

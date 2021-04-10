package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/* renamed from: X.0i3  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02120i3 extends AnonymousClass0p0 {
    public static final JsonSerializer<Object> A00 = new FailingSerializer();
    public static final JsonSerializer<Object> A01 = new UnknownSerializer();
    public static final AbstractC02190iF A02 = new AnonymousClass0C7(Object.class);
    public final AnonymousClass0HM _config;
    public DateFormat _dateFormat;
    public JsonSerializer<Object> _keySerializer;
    public final AnonymousClass0r0 _knownSerializers;
    public JsonSerializer<Object> _nullKeySerializer;
    public JsonSerializer<Object> _nullValueSerializer;
    public final C04930rZ _rootNames;
    public final Class<?> _serializationView;
    public final AnonymousClass0qq _serializerCache;
    public final AbstractC04630qr _serializerFactory;
    public JsonSerializer<Object> _unknownTypeSerializer;

    public abstract JsonSerializer<Object> A0A(AnonymousClass0qA v, Object obj) throws C02180iD;

    public abstract AnonymousClass0r1 A0D(Object obj, AbstractC03600nz<?> v);

    public final void A0F(Object obj, AbstractC02300iS r5) throws IOException, C03620oC {
        if (obj == null) {
            this._nullValueSerializer.serialize(null, r5, this);
        } else {
            A0C(obj.getClass(), true, null).serialize(obj, r5, this);
        }
    }

    public static final DateFormat A00(AbstractC02120i3 r1) {
        DateFormat dateFormat = r1._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) r1._config._base._dateFormat.clone();
        r1._dateFormat = dateFormat2;
        return dateFormat2;
    }

    @Override // X.AnonymousClass0p0
    public final AnonymousClass0r9 A07() {
        return this._config._base._typeFactory;
    }

    public final JsonSerializer<Object> A08(AbstractC02190iF r4, AbstractC02220iI r5) throws C02180iD {
        JsonSerializer<Object> A022 = this._serializerFactory.A02(this._config, r4, this._keySerializer);
        if (A022 instanceof AbstractC04620qo) {
            ((AbstractC04620qo) A022).A9O(this);
        }
        if (A022 instanceof AbstractC04600qk) {
            return ((AbstractC04600qk) A022).A2P(this, r5);
        }
        return A022;
    }

    public final JsonSerializer<Object> A09(AbstractC02190iF r6, AbstractC02220iI r7) throws C02180iD {
        AnonymousClass0r0 r2 = this._knownSerializers;
        AnonymousClass0qp r1 = r2.A00;
        if (r1 == null) {
            r1 = new AnonymousClass0qp(r6, false);
            r2.A00 = r1;
        } else {
            r1.A01 = r6;
            r1.A02 = null;
            r1.A03 = false;
            r1.A00 = r6.hashCode() - 1;
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            AnonymousClass0qq r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new AnonymousClass0qp(r6, false));
            }
            if (A002 == null) {
                try {
                    A002 = this._serializerFactory.A03(this, r6);
                    if (A002 == null) {
                        return this._unknownTypeSerializer;
                    }
                    AnonymousClass0qq r23 = this._serializerCache;
                    synchronized (r23) {
                        if (r23.A01.put(new AnonymousClass0qp(r6, false), A002) == null) {
                            r23.A00 = null;
                        }
                        if (A002 instanceof AbstractC04620qo) {
                            ((AbstractC04620qo) A002).A9O(this);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    throw new C02180iD(e.getMessage(), null, e);
                }
            }
        }
        if (A002 instanceof AbstractC04600qk) {
            return ((AbstractC04600qk) A002).A2P(this, r7);
        }
        return A002;
    }

    public final JsonSerializer<Object> A0B(Class<?> cls, AbstractC02220iI r8) throws C02180iD {
        AnonymousClass0r0 r2 = this._knownSerializers;
        AnonymousClass0qp r1 = r2.A00;
        if (r1 == null) {
            r1 = new AnonymousClass0qp(cls, false);
            r2.A00 = r1;
        } else {
            r1.A01 = null;
            r1.A02 = cls;
            r1.A03 = false;
            r1.A00 = cls.getName().hashCode();
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            AnonymousClass0qq r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new AnonymousClass0qp(cls, false));
            }
            if (A002 == null) {
                AnonymousClass0qq r3 = this._serializerCache;
                AbstractC02190iF A03 = this._config.A03(cls);
                synchronized (r3) {
                    A002 = r3.A01.get(new AnonymousClass0qp(A03, false));
                }
                if (A002 == null) {
                    try {
                        A002 = this._serializerFactory.A03(this, this._config.A03(cls));
                        if (A002 == null) {
                            return this._unknownTypeSerializer;
                        }
                        AnonymousClass0qq r23 = this._serializerCache;
                        synchronized (r23) {
                            if (r23.A01.put(new AnonymousClass0qp(cls, false), A002) == null) {
                                r23.A00 = null;
                            }
                            if (A002 instanceof AbstractC04620qo) {
                                ((AbstractC04620qo) A002).A9O(this);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new C02180iD(e.getMessage(), null, e);
                    }
                }
            }
        }
        if (A002 instanceof AbstractC04600qk) {
            return ((AbstractC04600qk) A002).A2P(this, r8);
        }
        return A002;
    }

    public JsonSerializer<Object> A0C(Class<?> cls, boolean z, AbstractC02220iI r8) throws C02180iD {
        AnonymousClass0r0 r2 = this._knownSerializers;
        AnonymousClass0qp r1 = r2.A00;
        if (r1 == null) {
            r1 = new AnonymousClass0qp(cls, true);
            r2.A00 = r1;
        } else {
            r1.A01 = null;
            r1.A02 = cls;
            r1.A03 = true;
            r1.A00 = cls.getName().hashCode() + 1;
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            AnonymousClass0qq r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new AnonymousClass0qp(cls, true));
            }
            if (A002 == null) {
                JsonSerializer<Object> A0B = A0B(cls, r8);
                AbstractC04630qr r23 = this._serializerFactory;
                AnonymousClass0HM r12 = this._config;
                AbstractC04550qd A04 = r23.A04(r12, r12.A03(cls));
                if (A04 != null) {
                    A0B = new TypeWrappedSerializer(A04.A00(r8), A0B);
                }
                if (!z) {
                    return A0B;
                }
                AnonymousClass0qq r24 = this._serializerCache;
                synchronized (r24) {
                    if (r24.A01.put(new AnonymousClass0qp(cls, true), A0B) == null) {
                        r24.A00 = null;
                    }
                }
                return A0B;
            }
        }
        return A002;
    }

    public final void A0E(AbstractC02300iS r3) throws IOException, C03620oC {
        this._nullValueSerializer.serialize(null, r3, this);
    }

    public final void A0G(Date date, AbstractC02300iS r4) throws IOException, C03620oC {
        String format;
        if (this._config.A06(AnonymousClass0i4.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            format = String.valueOf(date.getTime());
        } else {
            format = A00(this).format(date);
        }
        r4.A0R(format);
    }

    public final void A0H(Date date, AbstractC02300iS r4) throws IOException, C03620oC {
        if (this._config.A06(AnonymousClass0i4.WRITE_DATES_AS_TIMESTAMPS)) {
            r4.A0N(date.getTime());
        } else {
            r4.A0U(A00(this).format(date));
        }
    }

    @Override // X.AnonymousClass0p0
    public final /* bridge */ /* synthetic */ AbstractC02110i2 A06() {
        return this._config;
    }

    public AbstractC02120i3() {
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        this._config = null;
        this._serializerFactory = null;
        this._serializerCache = new AnonymousClass0qq();
        this._knownSerializers = null;
        this._rootNames = new C04930rZ();
        this._serializationView = null;
    }

    public AbstractC02120i3(AbstractC02120i3 r4, AnonymousClass0HM r5, AbstractC04630qr r6) {
        AnonymousClass0r0 r0;
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        if (r5 != null) {
            this._serializerFactory = r6;
            this._config = r5;
            AnonymousClass0qq r2 = r4._serializerCache;
            this._serializerCache = r2;
            this._unknownTypeSerializer = r4._unknownTypeSerializer;
            this._keySerializer = r4._keySerializer;
            this._nullValueSerializer = r4._nullValueSerializer;
            this._nullKeySerializer = r4._nullKeySerializer;
            this._rootNames = r4._rootNames;
            synchronized (r2) {
                r0 = r2.A00;
                if (r0 == null) {
                    r0 = new AnonymousClass0r0(new AnonymousClass0qv(r2.A01));
                    r2.A00 = r0;
                }
            }
            this._knownSerializers = new AnonymousClass0r0(r0.A01);
            this._serializationView = r5._view;
            return;
        }
        throw null;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/* renamed from: X.0a8  reason: invalid class name */
public abstract class AnonymousClass0a8 extends AbstractC06270mS {
    public static final JsonSerializer<Object> A00 = new FailingSerializer();
    public static final JsonSerializer<Object> A01 = new UnknownSerializer();
    public static final AnonymousClass0aI A02 = new AnonymousClass0C9(Object.class);
    public final AnonymousClass0FM _config;
    public DateFormat _dateFormat;
    public JsonSerializer<Object> _keySerializer;
    public final C06970oU _knownSerializers;
    public JsonSerializer<Object> _nullKeySerializer;
    public JsonSerializer<Object> _nullValueSerializer;
    public final C07270p3 _rootNames;
    public final Class<?> _serializationView;
    public final C06890oK _serializerCache;
    public final AbstractC06900oL _serializerFactory;
    public JsonSerializer<Object> _unknownTypeSerializer;

    public abstract JsonSerializer<Object> A09(AbstractC06640nb v, Object obj) throws AnonymousClass0aG;

    public abstract C06980oV A0C(Object obj, AnonymousClass0lR<?> v);

    public static final DateFormat A00(AnonymousClass0a8 r1) {
        DateFormat dateFormat = r1._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) r1._config._base._dateFormat.clone();
        r1._dateFormat = dateFormat2;
        return dateFormat2;
    }

    @Override // X.AbstractC06270mS
    public final /* bridge */ /* synthetic */ AnonymousClass0a7 A06() {
        return this._config;
    }

    @Override // X.AbstractC06270mS
    public final C07040od A07() {
        return this._config._base._typeFactory;
    }

    public final JsonSerializer<Object> A08(AnonymousClass0aI r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        C06970oU r2 = this._knownSerializers;
        C06880oJ r1 = r2.A00;
        if (r1 == null) {
            r1 = new C06880oJ(r6, false);
            r2.A00 = r1;
        } else {
            r1.A01 = r6;
            r1.A02 = null;
            r1.A03 = false;
            r1.A00 = r6.hashCode() - 1;
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            C06890oK r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new C06880oJ(r6, false));
            }
            if (A002 == null) {
                try {
                    A002 = this._serializerFactory.A03(this, r6);
                    if (A002 == null) {
                        return this._unknownTypeSerializer;
                    }
                    C06890oK r23 = this._serializerCache;
                    synchronized (r23) {
                        if (r23.A01.put(new C06880oJ(r6, false), A002) == null) {
                            r23.A00 = null;
                        }
                        if (A002 instanceof AbstractC06870oI) {
                            ((AbstractC06870oI) A002).A7U(this);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    throw new AnonymousClass0aG(e.getMessage(), null, e);
                }
            }
        }
        if (A002 instanceof AbstractC06840oE) {
            return ((AbstractC06840oE) A002).A1x(this, r7);
        }
        return A002;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;ZLX/0aL;)Lcom/fasterxml/jackson/databind/JsonSerializer<Ljava/lang/Object;>; */
    public final JsonSerializer A0A(Class cls, AbstractC02580aL r7) throws AnonymousClass0aG {
        C06970oU r2 = this._knownSerializers;
        C06880oJ r1 = r2.A00;
        if (r1 == null) {
            r1 = new C06880oJ((Class<?>) cls, true);
            r2.A00 = r1;
        } else {
            r1.A01 = null;
            r1.A02 = cls;
            r1.A03 = true;
            r1.A00 = cls.getName().hashCode() + 1;
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            C06890oK r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new C06880oJ((Class<?>) cls, true));
            }
            if (A002 == null) {
                JsonSerializer<Object> A0B = A0B(cls, r7);
                AbstractC06900oL r23 = this._serializerFactory;
                AnonymousClass0FM r12 = this._config;
                AnonymousClass0o6 A04 = r23.A04(r12, r12.A03(cls));
                if (A04 != null) {
                    A0B = new TypeWrappedSerializer(A04.A00(r7), A0B);
                }
                C06890oK r24 = this._serializerCache;
                synchronized (r24) {
                    if (r24.A01.put(new C06880oJ((Class<?>) cls, true), A0B) == null) {
                        r24.A00 = null;
                    }
                }
                return A0B;
            }
        }
        return A002;
    }

    public final JsonSerializer<Object> A0B(Class<?> cls, AbstractC02580aL r8) throws AnonymousClass0aG {
        C06970oU r2 = this._knownSerializers;
        C06880oJ r1 = r2.A00;
        if (r1 == null) {
            r1 = new C06880oJ(cls, false);
            r2.A00 = r1;
        } else {
            r1.A01 = null;
            r1.A02 = cls;
            r1.A03 = false;
            r1.A00 = cls.getName().hashCode();
        }
        JsonSerializer<Object> A002 = r2.A01.A00(r1);
        if (A002 == null) {
            C06890oK r22 = this._serializerCache;
            synchronized (r22) {
                A002 = r22.A01.get(new C06880oJ(cls, false));
            }
            if (A002 == null) {
                C06890oK r3 = this._serializerCache;
                AnonymousClass0aI A03 = this._config.A03(cls);
                synchronized (r3) {
                    A002 = r3.A01.get(new C06880oJ(A03, false));
                }
                if (A002 == null) {
                    try {
                        A002 = this._serializerFactory.A03(this, this._config.A03(cls));
                        if (A002 == null) {
                            return this._unknownTypeSerializer;
                        }
                        C06890oK r23 = this._serializerCache;
                        synchronized (r23) {
                            if (r23.A01.put(new C06880oJ(cls, false), A002) == null) {
                                r23.A00 = null;
                            }
                            if (A002 instanceof AbstractC06870oI) {
                                ((AbstractC06870oI) A002).A7U(this);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new AnonymousClass0aG(e.getMessage(), null, e);
                    }
                }
            }
        }
        if (A002 instanceof AbstractC06840oE) {
            return ((AbstractC06840oE) A002).A1x(this, r8);
        }
        return A002;
    }

    public final void A0D(AbstractC02640aV r3) throws IOException, C05910ld {
        this._nullValueSerializer.A0D(null, r3, this);
    }

    public final void A0E(Date date, AbstractC02640aV r4) throws IOException, C05910ld {
        String format;
        if (this._config.A06(AnonymousClass0a9.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            format = String.valueOf(date.getTime());
        } else {
            format = A00(this).format(date);
        }
        r4.A0P(format);
    }

    public final void A0F(Date date, AbstractC02640aV r4) throws IOException, C05910ld {
        if (this._config.A06(AnonymousClass0a9.WRITE_DATES_AS_TIMESTAMPS)) {
            r4.A0K(date.getTime());
        } else {
            r4.A0S(A00(this).format(date));
        }
    }

    public AnonymousClass0a8() {
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        this._config = null;
        this._serializerFactory = null;
        this._serializerCache = new C06890oK();
        this._knownSerializers = null;
        this._rootNames = new C07270p3();
        this._serializationView = null;
    }

    public AnonymousClass0a8(AnonymousClass0a8 r4, AnonymousClass0FM r5, AbstractC06900oL r6) {
        C06970oU r0;
        this._unknownTypeSerializer = A01;
        this._nullValueSerializer = NullSerializer.A00;
        this._nullKeySerializer = A00;
        if (r5 != null) {
            this._serializerFactory = r6;
            this._config = r5;
            C06890oK r2 = r4._serializerCache;
            this._serializerCache = r2;
            this._unknownTypeSerializer = r4._unknownTypeSerializer;
            this._keySerializer = r4._keySerializer;
            this._nullValueSerializer = r4._nullValueSerializer;
            this._nullKeySerializer = r4._nullKeySerializer;
            this._rootNames = r4._rootNames;
            synchronized (r2) {
                r0 = r2.A00;
                if (r0 == null) {
                    r0 = new C06970oU(new AnonymousClass0oP(r2.A01));
                    r2.A00 = r0;
                }
            }
            this._knownSerializers = new C06970oU(r0.A01);
            this._serializationView = r5._view;
            return;
        }
        throw null;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$DoubleSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$FloatSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$IntLikeSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$LongSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$ShortSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicBooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicIntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicLongSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$ClassSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$FileSerializer;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers$CalendarKeySerializer;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers$DateKeySerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* renamed from: X.0hB  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01840hB extends AbstractC04630qr implements Serializable {
    public static final HashMap<String, JsonSerializer<?>> A00;
    public static final HashMap<String, Class<? extends JsonSerializer<?>>> A01 = new HashMap<>();
    public final C04180pU _factoryConfig;

    public static final boolean A01(AnonymousClass0HM r3, AbstractC04010oz r4, AbstractC04550qd r5) {
        if (r5 != null) {
            return false;
        }
        EnumC04120pK A09 = r3.A01().A09(r4.A07());
        if (A09 != null) {
            return A09 == EnumC04120pK.STATIC;
        }
        return r3.A05(EnumC02160i9.USE_STATIC_TYPING);
    }

    @Override // X.AbstractC04630qr
    public abstract JsonSerializer<Object> A03(AbstractC02120i3 v, AbstractC02190iF v2) throws C02180iD;

    public abstract AbstractC04630qr A08(C04180pU v);

    public abstract Iterable<AbstractC04640qs> A09();

    static {
        AbstractMap abstractMap;
        HashMap<String, JsonSerializer<?>> hashMap = new HashMap<>();
        A00 = hashMap;
        hashMap.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.A00;
        HashMap<String, JsonSerializer<?>> hashMap2 = A00;
        hashMap2.put(StringBuffer.class.getName(), toStringSerializer);
        hashMap2.put(StringBuilder.class.getName(), toStringSerializer);
        hashMap2.put(Character.class.getName(), toStringSerializer);
        hashMap2.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers$IntegerSerializer numberSerializers$IntegerSerializer = new NumberSerializers$IntegerSerializer();
        hashMap2.put(Integer.class.getName(), numberSerializers$IntegerSerializer);
        hashMap2.put(Integer.TYPE.getName(), numberSerializers$IntegerSerializer);
        String name = Long.class.getName();
        NumberSerializers$LongSerializer numberSerializers$LongSerializer = NumberSerializers$LongSerializer.A00;
        hashMap2.put(name, numberSerializers$LongSerializer);
        hashMap2.put(Long.TYPE.getName(), numberSerializers$LongSerializer);
        String name2 = Byte.class.getName();
        NumberSerializers$IntLikeSerializer numberSerializers$IntLikeSerializer = NumberSerializers$IntLikeSerializer.A00;
        hashMap2.put(name2, numberSerializers$IntLikeSerializer);
        hashMap2.put(Byte.TYPE.getName(), numberSerializers$IntLikeSerializer);
        String name3 = Short.class.getName();
        NumberSerializers$ShortSerializer numberSerializers$ShortSerializer = NumberSerializers$ShortSerializer.A00;
        hashMap2.put(name3, numberSerializers$ShortSerializer);
        hashMap2.put(Short.TYPE.getName(), numberSerializers$ShortSerializer);
        String name4 = Float.class.getName();
        NumberSerializers$FloatSerializer numberSerializers$FloatSerializer = NumberSerializers$FloatSerializer.A00;
        hashMap2.put(name4, numberSerializers$FloatSerializer);
        hashMap2.put(Float.TYPE.getName(), numberSerializers$FloatSerializer);
        String name5 = Double.class.getName();
        NumberSerializers$DoubleSerializer numberSerializers$DoubleSerializer = NumberSerializers$DoubleSerializer.A00;
        hashMap2.put(name5, numberSerializers$DoubleSerializer);
        hashMap2.put(Double.TYPE.getName(), numberSerializers$DoubleSerializer);
        hashMap2.put(Boolean.TYPE.getName(), new BooleanSerializer());
        hashMap2.put(Boolean.class.getName(), new BooleanSerializer());
        NumberSerializers$NumberSerializer numberSerializers$NumberSerializer = new NumberSerializers$NumberSerializer();
        hashMap2.put(BigInteger.class.getName(), numberSerializers$NumberSerializer);
        hashMap2.put(BigDecimal.class.getName(), numberSerializers$NumberSerializer);
        hashMap2.put(Calendar.class.getName(), CalendarSerializer.A00);
        DateSerializer dateSerializer = DateSerializer.A00;
        hashMap2.put(Date.class.getName(), dateSerializer);
        hashMap2.put(Timestamp.class.getName(), dateSerializer);
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap3 = A01;
        hashMap3.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
        hashMap3.put(Time.class.getName(), SqlTimeSerializer.class);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(URL.class, toStringSerializer);
        hashMap4.put(URI.class, toStringSerializer);
        hashMap4.put(Currency.class, toStringSerializer);
        hashMap4.put(UUID.class, toStringSerializer);
        hashMap4.put(Pattern.class, toStringSerializer);
        hashMap4.put(Locale.class, toStringSerializer);
        hashMap4.put(Locale.class, toStringSerializer);
        hashMap4.put(AtomicReference.class, StdJdkSerializers$AtomicReferenceSerializer.class);
        hashMap4.put(AtomicBoolean.class, StdJdkSerializers$AtomicBooleanSerializer.class);
        hashMap4.put(AtomicInteger.class, StdJdkSerializers$AtomicIntegerSerializer.class);
        hashMap4.put(AtomicLong.class, StdJdkSerializers$AtomicLongSerializer.class);
        hashMap4.put(File.class, StdJdkSerializers$FileSerializer.class);
        hashMap4.put(Class.class, StdJdkSerializers$ClassSerializer.class);
        hashMap4.put(Void.TYPE, NullSerializer.class);
        for (Map.Entry entry : hashMap4.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                abstractMap = A00;
            } else if (value instanceof Class) {
                abstractMap = A01;
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Internal error: unrecognized value of type ", entry.getClass().getName()));
            }
            abstractMap.put(((Class) entry.getKey()).getName(), value);
        }
        A01.put(AnonymousClass0OD.class.getName(), TokenBufferSerializer.class);
    }

    @Override // X.AbstractC04630qr
    public final JsonSerializer<Object> A02(AnonymousClass0HM r4, AbstractC02190iF r5, JsonSerializer<Object> jsonSerializer) {
        AbstractC04010oz A02 = r4.A02(r4.A03(r5._class));
        AbstractC04640qs[] r1 = this._factoryConfig._additionalKeySerializers;
        if (r1.length > 0) {
            Iterator it = new C04780rF(r1).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JsonSerializer<?> A3B = ((AbstractC04640qs) it.next()).A3B(r4, r5, A02);
                if (A3B != null) {
                    jsonSerializer = A3B;
                    break;
                }
            }
        }
        if (jsonSerializer == null) {
            Class<?> cls = r5._class;
            if (cls == String.class) {
                jsonSerializer = C04710r5.A01;
            } else {
                if (cls != Object.class) {
                    if (Date.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$DateKeySerializer.A00;
                    } else if (Calendar.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$CalendarKeySerializer.A00;
                    }
                }
                jsonSerializer = C04710r5.A00;
            }
        }
        AbstractC04590qj[] r12 = this._factoryConfig._modifiers;
        if (r12.length > 0) {
            Iterator it2 = new C04780rF(r12).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return jsonSerializer;
    }

    @Override // X.AbstractC04630qr
    public final AbstractC04550qd A04(AnonymousClass0HM r6, AbstractC02190iF r7) {
        C02000hn A07 = r6.A02(r6.A03(r7._class)).A07();
        AbstractC02230iJ A012 = r6.A01();
        AbstractC04540qc<?> A0D = A012.A0D(r6, A07, r7);
        Collection<AnonymousClass0qX> collection = null;
        if (A0D == null) {
            A0D = r6._base._typeResolverBuilder;
            if (A0D == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A07, r6, A012);
        }
        return A0D.A1t(r6, r7, collection);
    }

    @Override // X.AbstractC04630qr
    public final AbstractC04630qr A05(AbstractC04590qj r5) {
        C04180pU r1 = this._factoryConfig;
        if (r5 != null) {
            return A08(new C04180pU(r1._additionalSerializers, r1._additionalKeySerializers, (AbstractC04590qj[]) C04790rG.A01(r1._modifiers, r5)));
        }
        throw new IllegalArgumentException("Can not pass null modifier");
    }

    @Override // X.AbstractC04630qr
    public final AbstractC04630qr A06(AbstractC04640qs r5) {
        C04180pU r1 = this._factoryConfig;
        if (r5 != null) {
            return A08(new C04180pU((AbstractC04640qs[]) C04790rG.A01(r1._additionalSerializers, r5), r1._additionalKeySerializers, r1._modifiers));
        }
        throw new IllegalArgumentException("Can not pass null Serializers");
    }

    public final JsonSerializer<Object> A07(AbstractC02120i3 r3, AnonymousClass0qA r4) throws C02180iD {
        Object A0a = r3._config.A01().A0a(r4);
        if (A0a == null) {
            return null;
        }
        JsonSerializer<Object> A0A = r3.A0A(r4, A0a);
        Object A0Z = r3._config.A01().A0Z(r4);
        if (A0Z != null) {
            r3.A05(A0Z);
        }
        return A0A;
    }

    public AbstractC01840hB(C04180pU r1) {
        this._factoryConfig = r1 == null ? new C04180pU() : r1;
    }

    public static <T extends AbstractC02190iF> T A00(AnonymousClass0HM r12, AnonymousClass0qA r13, T t) {
        AbstractC02230iJ A012 = r12.A01();
        if (t.A0N()) {
            Class<?> A0R = A012.A0R(r13, t.A05());
            if (A0R != null) {
                if (t instanceof AnonymousClass03D) {
                    try {
                        AnonymousClass0C8 r2 = (AnonymousClass0C8) t;
                        AbstractC02190iF r1 = r2._keyType;
                        if (A0R == r1._class) {
                            t = r2;
                        } else {
                            t = new AnonymousClass03D(r2._class, r1.A08(A0R), r2._valueType, r2._valueHandler, r2._typeHandler, r2._asStatic);
                        }
                    } catch (IllegalArgumentException e) {
                        StringBuilder sb = new StringBuilder("Failed to narrow key type ");
                        sb.append(t);
                        sb.append(" with key-type annotation (");
                        sb.append(A0R.getName());
                        sb.append("): ");
                        sb.append(e.getMessage());
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Illegal key-type annotation: type ");
                    sb2.append(t);
                    sb2.append(" is not a Map type");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
            Class<?> A0Q = A012.A0Q(r13, t.A04());
            if (A0Q != null) {
                try {
                    return (T) t.A0B(A0Q);
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb3 = new StringBuilder("Failed to narrow content type ");
                    sb3.append(t);
                    sb3.append(" with content-type annotation (");
                    sb3.append(A0Q.getName());
                    sb3.append("): ");
                    sb3.append(e2.getMessage());
                    throw new IllegalArgumentException(sb3.toString());
                }
            }
        }
        return t;
    }
}

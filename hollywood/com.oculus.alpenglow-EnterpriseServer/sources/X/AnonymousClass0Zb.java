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

/* renamed from: X.0Zb  reason: invalid class name */
public abstract class AnonymousClass0Zb extends AbstractC06900oL implements Serializable {
    public static final HashMap<String, JsonSerializer<?>> A00;
    public static final HashMap<String, Class<? extends JsonSerializer<?>>> A01 = new HashMap<>();
    public final C06460mv _factoryConfig = new C06460mv();

    public static final boolean A01(AnonymousClass0FM r3, AbstractC06260mR r4, AnonymousClass0o6 r5) {
        if (r5 != null) {
            return false;
        }
        EnumC06400ml A09 = r3.A01().A09(r4.A07());
        if (A09 != null) {
            return A09 == EnumC06400ml.STATIC;
        }
        return r3.A05(EnumC02540aC.USE_STATIC_TYPING);
    }

    @Override // X.AbstractC06900oL
    public abstract JsonSerializer<Object> A03(AnonymousClass0a8 v, AnonymousClass0aI v2) throws AnonymousClass0aG;

    public abstract Iterable<AbstractC06910oM> A06();

    static {
        HashMap hashMap;
        HashMap<String, JsonSerializer<?>> hashMap2 = new HashMap<>();
        A00 = hashMap2;
        hashMap2.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.A00;
        HashMap<String, JsonSerializer<?>> hashMap3 = A00;
        hashMap3.put(StringBuffer.class.getName(), toStringSerializer);
        hashMap3.put(StringBuilder.class.getName(), toStringSerializer);
        hashMap3.put(Character.class.getName(), toStringSerializer);
        hashMap3.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers$IntegerSerializer numberSerializers$IntegerSerializer = new NumberSerializers$IntegerSerializer();
        hashMap3.put(Integer.class.getName(), numberSerializers$IntegerSerializer);
        hashMap3.put(Integer.TYPE.getName(), numberSerializers$IntegerSerializer);
        String name = Long.class.getName();
        NumberSerializers$LongSerializer numberSerializers$LongSerializer = NumberSerializers$LongSerializer.A00;
        hashMap3.put(name, numberSerializers$LongSerializer);
        hashMap3.put(Long.TYPE.getName(), numberSerializers$LongSerializer);
        String name2 = Byte.class.getName();
        NumberSerializers$IntLikeSerializer numberSerializers$IntLikeSerializer = NumberSerializers$IntLikeSerializer.A00;
        hashMap3.put(name2, numberSerializers$IntLikeSerializer);
        hashMap3.put(Byte.TYPE.getName(), numberSerializers$IntLikeSerializer);
        String name3 = Short.class.getName();
        NumberSerializers$ShortSerializer numberSerializers$ShortSerializer = NumberSerializers$ShortSerializer.A00;
        hashMap3.put(name3, numberSerializers$ShortSerializer);
        hashMap3.put(Short.TYPE.getName(), numberSerializers$ShortSerializer);
        String name4 = Float.class.getName();
        NumberSerializers$FloatSerializer numberSerializers$FloatSerializer = NumberSerializers$FloatSerializer.A00;
        hashMap3.put(name4, numberSerializers$FloatSerializer);
        hashMap3.put(Float.TYPE.getName(), numberSerializers$FloatSerializer);
        String name5 = Double.class.getName();
        NumberSerializers$DoubleSerializer numberSerializers$DoubleSerializer = NumberSerializers$DoubleSerializer.A00;
        hashMap3.put(name5, numberSerializers$DoubleSerializer);
        hashMap3.put(Double.TYPE.getName(), numberSerializers$DoubleSerializer);
        hashMap3.put(Boolean.TYPE.getName(), new BooleanSerializer());
        hashMap3.put(Boolean.class.getName(), new BooleanSerializer());
        NumberSerializers$NumberSerializer numberSerializers$NumberSerializer = new NumberSerializers$NumberSerializer();
        hashMap3.put(BigInteger.class.getName(), numberSerializers$NumberSerializer);
        hashMap3.put(BigDecimal.class.getName(), numberSerializers$NumberSerializer);
        hashMap3.put(Calendar.class.getName(), CalendarSerializer.A00);
        DateSerializer dateSerializer = DateSerializer.A00;
        hashMap3.put(Date.class.getName(), dateSerializer);
        hashMap3.put(Timestamp.class.getName(), dateSerializer);
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap4 = A01;
        hashMap4.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
        hashMap4.put(Time.class.getName(), SqlTimeSerializer.class);
        HashMap hashMap5 = new HashMap();
        hashMap5.put(URL.class, toStringSerializer);
        hashMap5.put(URI.class, toStringSerializer);
        hashMap5.put(Currency.class, toStringSerializer);
        hashMap5.put(UUID.class, toStringSerializer);
        hashMap5.put(Pattern.class, toStringSerializer);
        hashMap5.put(Locale.class, toStringSerializer);
        hashMap5.put(Locale.class, toStringSerializer);
        hashMap5.put(AtomicReference.class, StdJdkSerializers$AtomicReferenceSerializer.class);
        hashMap5.put(AtomicBoolean.class, StdJdkSerializers$AtomicBooleanSerializer.class);
        hashMap5.put(AtomicInteger.class, StdJdkSerializers$AtomicIntegerSerializer.class);
        hashMap5.put(AtomicLong.class, StdJdkSerializers$AtomicLongSerializer.class);
        hashMap5.put(File.class, StdJdkSerializers$FileSerializer.class);
        hashMap5.put(Class.class, StdJdkSerializers$ClassSerializer.class);
        hashMap5.put(Void.TYPE, NullSerializer.class);
        for (Map.Entry entry : hashMap5.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                hashMap = A00;
            } else if (value instanceof Class) {
                hashMap = A01;
            } else {
                throw new IllegalStateException(AnonymousClass006.A05("Internal error: unrecognized value of type ", entry.getClass().getName()));
            }
            hashMap.put(((Class) entry.getKey()).getName(), value);
        }
        A01.put(C01570Jk.class.getName(), TokenBufferSerializer.class);
    }

    @Override // X.AbstractC06900oL
    public final JsonSerializer<Object> A02(AnonymousClass0FM r4, AnonymousClass0aI r5, JsonSerializer<Object> jsonSerializer) {
        AbstractC06260mR A02 = r4.A02(r4.A03(r5._class));
        AbstractC06910oM[] r1 = this._factoryConfig._additionalKeySerializers;
        if (r1.length > 0) {
            Iterator it = new C07100oj(r1).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JsonSerializer<?> A2m = ((AbstractC06910oM) it.next()).A2m(r4, r5, A02);
                if (A2m != null) {
                    jsonSerializer = A2m;
                    break;
                }
            }
        }
        if (jsonSerializer == null) {
            Class<?> cls = r5._class;
            if (cls == String.class) {
                jsonSerializer = C07000oZ.A01;
            } else {
                if (cls != Object.class) {
                    if (Date.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$DateKeySerializer.A00;
                    } else if (Calendar.class.isAssignableFrom(cls)) {
                        jsonSerializer = StdKeySerializers$CalendarKeySerializer.A00;
                    }
                }
                jsonSerializer = C07000oZ.A00;
            }
        }
        AbstractC06830oD[] r12 = this._factoryConfig._modifiers;
        if (r12.length > 0) {
            Iterator it2 = new C07100oj(r12).iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
        return jsonSerializer;
    }

    @Override // X.AbstractC06900oL
    public final AnonymousClass0o6 A04(AnonymousClass0FM r6, AnonymousClass0aI r7) {
        C02460Zs A07 = r6.A02(r6.A03(r7._class)).A07();
        AbstractC02590aM A012 = r6.A01();
        AnonymousClass0o5<?> A0D = A012.A0D(r6, A07, r7);
        Collection<AnonymousClass0o0> collection = null;
        if (A0D == null) {
            A0D = r6._base._typeResolverBuilder;
            if (A0D == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(A07, r6, A012);
        }
        return A0D.A1c(r6, r7, collection);
    }

    public final JsonSerializer<Object> A05(AnonymousClass0a8 r3, AbstractC06640nb r4) throws AnonymousClass0aG {
        Object A0a = r3._config.A01().A0a(r4);
        if (A0a == null) {
            return null;
        }
        JsonSerializer<Object> A09 = r3.A09(r4, A0a);
        Object A0Z = r3._config.A01().A0Z(r4);
        if (A0Z != null) {
            r3.A05(A0Z);
        }
        return A09;
    }

    public static <T extends AnonymousClass0aI> T A00(AnonymousClass0FM r12, AbstractC06640nb r13, T t) {
        StringBuilder sb;
        String str;
        IllegalArgumentException e;
        String str2;
        AbstractC02590aM A012 = r12.A01();
        if (t.A0N()) {
            Class<?> A0R = A012.A0R(r13, t.A05());
            if (A0R != null) {
                if (t instanceof C006506c) {
                    try {
                        AnonymousClass0CA r2 = (AnonymousClass0CA) t;
                        AnonymousClass0aI r1 = r2._keyType;
                        if (A0R == r1._class) {
                            t = r2;
                        } else {
                            t = new C006506c(r2._class, r1.A08(A0R), r2._valueType, r2._valueHandler, r2._typeHandler, r2._asStatic);
                        }
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        sb = new StringBuilder("Failed to narrow key type ");
                        sb.append(t);
                        str2 = " with key-type annotation (";
                        sb.append(str2);
                        sb.append(A0R.getName());
                        sb.append("): ");
                        str = e.getMessage();
                        sb.append(str);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    sb = new StringBuilder("Illegal key-type annotation: type ");
                    sb.append(t);
                    str = " is not a Map type";
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            A0R = A012.A0Q(r13, t.A04());
            if (A0R != null) {
                try {
                    return (T) t.A0B(A0R);
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    sb = new StringBuilder("Failed to narrow content type ");
                    sb.append(t);
                    str2 = " with content-type annotation (";
                    sb.append(str2);
                    sb.append(A0R.getName());
                    sb.append("): ");
                    str = e.getMessage();
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        return t;
    }
}

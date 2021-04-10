package X;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicBooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicIntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicLongSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$ClassSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$FileSerializer;
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
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* renamed from: X.rZ  reason: case insensitive filesystem */
public abstract class AbstractC1059rZ extends AbstractC0285Ph implements Serializable {
    public static final HashMap A00;
    public static final HashMap A01 = new HashMap();
    public final OW _factoryConfig = new OW();

    public static final boolean A01(AnonymousClass2H r3, O4 o4, PU pu) {
        JsonSerialize jsonSerialize;
        OM typing;
        if (pu != null) {
            return false;
        }
        AbstractC1020qp A012 = r3.A01();
        C1043rI rIVar = ((C1046rL) o4).A09;
        if (!(A012 instanceof Rw) || (jsonSerialize = (JsonSerialize) rIVar.A0L(JsonSerialize.class)) == null || (typing = jsonSerialize.typing()) == null) {
            return r3.A05(EnumC1027qy.USE_STATIC_TYPING);
        }
        return typing == OM.STATIC;
    }

    static {
        HashMap hashMap;
        HashMap hashMap2 = new HashMap();
        A00 = hashMap2;
        hashMap2.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.A00;
        HashMap hashMap3 = A00;
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
        HashMap hashMap4 = A01;
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
                throw new IllegalStateException(AnonymousClass08.A04("Internal error: unrecognized value of type ", entry.getClass().getName()));
            }
            hashMap.put(((Class) entry.getKey()).getName(), value);
        }
        A01.put(JD.class.getName(), TokenBufferSerializer.class);
    }

    public final JsonSerializer A04(AbstractC1031r2 r2Var, P9 p9) {
        Object rawSerializer;
        if (!(r2Var._config.A01() instanceof Rw)) {
            return null;
        }
        JsonSerialize jsonSerialize = (JsonSerialize) p9.A0L(JsonSerialize.class);
        if (jsonSerialize == null || (rawSerializer = jsonSerialize.using()) == JsonSerializer.None.class) {
            JsonRawValue jsonRawValue = (JsonRawValue) p9.A0L(JsonRawValue.class);
            if (jsonRawValue == null || !jsonRawValue.value()) {
                return null;
            }
            rawSerializer = new RawSerializer(p9.A0J());
        }
        if (rawSerializer == null) {
            return null;
        }
        JsonSerializer A09 = r2Var.A09(p9, rawSerializer);
        Object A0F = r2Var._config.A01().A0F(p9);
        if (A0F != null) {
            r2Var.A06(A0F);
        }
        return A09;
    }

    public static AbstractC1024qt A00(AnonymousClass2H r12, P9 p9, AbstractC1024qt qtVar) {
        Class cls;
        JsonSerialize jsonSerialize;
        Class contentAs;
        JsonSerialize jsonSerialize2;
        AbstractC1020qp A012 = r12.A01();
        if (qtVar.A0H()) {
            boolean z = A012 instanceof Rw;
            if (!z || (jsonSerialize2 = (JsonSerialize) p9.A0L(JsonSerialize.class)) == null || (cls = jsonSerialize2.keyAs()) == OR.class) {
                cls = null;
            }
            if (cls != null) {
                if (qtVar instanceof C00313p) {
                    try {
                        C0681fG fGVar = (C0681fG) qtVar;
                        AbstractC1024qt qtVar2 = fGVar._keyType;
                        if (cls == qtVar2._class) {
                            qtVar = fGVar;
                        } else {
                            qtVar = new C00313p(fGVar._class, qtVar2.A0A(cls), fGVar._valueType, fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
                        }
                    } catch (IllegalArgumentException e) {
                        StringBuilder sb = new StringBuilder("Failed to narrow key type ");
                        sb.append(qtVar);
                        sb.append(" with key-type annotation (");
                        sb.append(cls.getName());
                        sb.append("): ");
                        sb.append(e.getMessage());
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Illegal key-type annotation: type ");
                    sb2.append(qtVar);
                    sb2.append(" is not a Map type");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
            if (!(!z || (jsonSerialize = (JsonSerialize) p9.A0L(JsonSerialize.class)) == null || (contentAs = jsonSerialize.contentAs()) == OR.class || contentAs == null)) {
                try {
                    if (qtVar instanceof fF) {
                        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
                    } else if (qtVar instanceof C0681fG) {
                        C0681fG fGVar2 = (C0681fG) qtVar;
                        if (!(fGVar2 instanceof C00313p)) {
                            AbstractC1024qt qtVar3 = fGVar2._valueType;
                            if (contentAs != qtVar3._class) {
                                return new C0681fG(fGVar2._class, fGVar2._keyType, qtVar3.A0A(contentAs), fGVar2._valueHandler, fGVar2._typeHandler, fGVar2._asStatic);
                            }
                            return fGVar2;
                        }
                        AbstractC1024qt qtVar4 = fGVar2._valueType;
                        if (contentAs != qtVar4._class) {
                            return new C00313p(fGVar2._class, fGVar2._keyType, qtVar4.A0A(contentAs), fGVar2._valueHandler, fGVar2._typeHandler, fGVar2._asStatic);
                        }
                        return fGVar2;
                    } else if (!(qtVar instanceof C0682fH)) {
                        C0683fI fIVar = (C0683fI) qtVar;
                        AbstractC1024qt qtVar5 = fIVar._componentType;
                        if (contentAs != qtVar5._class) {
                            return C0683fI.A00(qtVar5.A0A(contentAs));
                        }
                        return fIVar;
                    } else {
                        C0682fH fHVar = (C0682fH) qtVar;
                        if (!(fHVar instanceof C00323q)) {
                            AbstractC1024qt qtVar6 = fHVar._elementType;
                            if (contentAs != qtVar6._class) {
                                return new C0682fH(fHVar._class, qtVar6.A0A(contentAs), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
                            }
                            return fHVar;
                        }
                        AbstractC1024qt qtVar7 = fHVar._elementType;
                        if (contentAs != qtVar7._class) {
                            return new C00323q(fHVar._class, qtVar7.A0A(contentAs), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
                        }
                        return fHVar;
                    }
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb3 = new StringBuilder("Failed to narrow content type ");
                    sb3.append(qtVar);
                    sb3.append(" with content-type annotation (");
                    sb3.append(contentAs.getName());
                    sb3.append("): ");
                    sb3.append(e2.getMessage());
                    throw new IllegalArgumentException(sb3.toString());
                }
            }
        }
        return qtVar;
    }
}

package X;

import com.facebook.common.json.FbSerializerProvider$1;
import com.facebook.common.json.FbSerializerProvider$2;
import com.facebook.common.json.FbSerializerProvider$3;
import com.facebook.common.json.FbSerializerProvider$4;
import com.facebook.common.json.FbSerializerProvider$5;
import com.facebook.common.json.FbSerializerProvider$6;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$DoubleSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$FloatSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$LongSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.0IH  reason: invalid class name */
public final class AnonymousClass0IH extends AnonymousClass0Oe {
    public static boolean A00;
    public static JsonSerializer A01 = new FbSerializerProvider$5();
    public static JsonSerializer A02 = new FbSerializerProvider$4();
    public static JsonSerializer A03 = new FbSerializerProvider$2();
    public static JsonSerializer A04 = new FbSerializerProvider$6();
    public static JsonSerializer A05 = new FbSerializerProvider$3();
    public static final ConcurrentMap<Class, JsonSerializer> A06 = new ConcurrentHashMap();
    public JsonSerializer mCollectionSerializer = new FbSerializerProvider$1(this);
    public boolean mHumanReadableFormatEnabled;
    public final AnonymousClass0J1 mJsonLogger;

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A0I(AnonymousClass0HM r7, AbstractC04630qr r8) {
        return new AnonymousClass0IH(this, r7, r8, this.mJsonLogger, false);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0081 */
    @Override // X.AbstractC02120i3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> A0C(java.lang.Class<?> r7, boolean r8, @javax.annotation.Nullable X.AbstractC02220iI r9) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0IH.A0C(java.lang.Class, boolean, X.0iI):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public AnonymousClass0IH(AbstractC02120i3 r4, AnonymousClass0HM r5, AbstractC04630qr r6, AnonymousClass0J1 r7, boolean z) {
        super(r4, r5, r6);
        this.mJsonLogger = r7;
        if (!A00) {
            ConcurrentMap<Class, JsonSerializer> concurrentMap = A06;
            concurrentMap.put(String.class, new StringSerializer());
            concurrentMap.put(Integer.class, new NumberSerializers$IntegerSerializer());
            concurrentMap.put(Long.class, new NumberSerializers$LongSerializer());
            concurrentMap.put(Boolean.class, new BooleanSerializer());
            concurrentMap.put(Float.class, new NumberSerializers$FloatSerializer());
            concurrentMap.put(Double.class, new NumberSerializers$DoubleSerializer());
            A00 = true;
        }
        this.mHumanReadableFormatEnabled = z;
    }
}

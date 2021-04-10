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

/* renamed from: X.2i  reason: invalid class name and case insensitive filesystem */
public final class C00162i extends AbstractC0250No {
    public static boolean A00;
    public static JsonSerializer A01 = new FbSerializerProvider$5();
    public static JsonSerializer A02 = new FbSerializerProvider$4();
    public static JsonSerializer A03 = new FbSerializerProvider$2();
    public static JsonSerializer A04 = new FbSerializerProvider$6();
    public static JsonSerializer A05 = new FbSerializerProvider$3();
    public static final ConcurrentMap A06 = new ConcurrentHashMap();
    public JsonSerializer mCollectionSerializer = new FbSerializerProvider$1(this);
    public boolean mHumanReadableFormatEnabled;
    public final CJ mJsonLogger = null;

    public C00162i(AbstractC1031r2 r2Var, AnonymousClass2H r6, AbstractC0285Ph ph) {
        super(r2Var, r6, ph);
        if (!A00) {
            ConcurrentMap concurrentMap = A06;
            concurrentMap.put(String.class, new StringSerializer());
            concurrentMap.put(Integer.class, new NumberSerializers$IntegerSerializer());
            concurrentMap.put(Long.class, new NumberSerializers$LongSerializer());
            concurrentMap.put(Boolean.class, new BooleanSerializer());
            concurrentMap.put(Float.class, new NumberSerializers$FloatSerializer());
            concurrentMap.put(Double.class, new NumberSerializers$DoubleSerializer());
            A00 = true;
        }
        this.mHumanReadableFormatEnabled = false;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0081 */
    @Override // X.AbstractC1031r2
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer A0B(java.lang.Class r7, boolean r8, X.O5 r9) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C00162i.A0B(java.lang.Class, boolean, X.O5):com.fasterxml.jackson.databind.JsonSerializer");
    }
}

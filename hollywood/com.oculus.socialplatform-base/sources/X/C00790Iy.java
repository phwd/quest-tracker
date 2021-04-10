package X;

import android.net.Uri;
import com.facebook.common.json.FbJsonDeserializer;
import com.facebook.common.json.UriDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.JacksonDeserializers$TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.0Iy  reason: invalid class name and case insensitive filesystem */
public final class C00790Iy {
    public static final FbJsonDeserializer A00 = new FbJsonDeserializer();
    public static final ConcurrentMap<Class<?>, JsonDeserializer> A01;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        A01 = concurrentHashMap;
        concurrentHashMap.put(AbstractC02170iC.class, JsonNodeDeserializer.A00);
        ConcurrentMap<Class<?>, JsonDeserializer> concurrentMap = A01;
        concurrentMap.put(String.class, StringDeserializer.A00);
        concurrentMap.put(Integer.class, AnonymousClass0q7.A00(Integer.class, Integer.class.getName()));
        concurrentMap.put(Long.class, AnonymousClass0q7.A00(Long.class, Long.class.getName()));
        concurrentMap.put(Boolean.class, AnonymousClass0q7.A00(Boolean.class, Boolean.class.getName()));
        concurrentMap.put(Float.class, AnonymousClass0q7.A00(Float.class, Float.class.getName()));
        concurrentMap.put(Double.class, AnonymousClass0q7.A00(Double.class, Double.class.getName()));
        concurrentMap.put(Uri.class, new UriDeserializer());
        concurrentMap.put(AnonymousClass0OD.class, JacksonDeserializers$TokenBufferDeserializer.A00);
        concurrentMap.put(Object.class, UntypedObjectDeserializer.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Class<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0072, code lost:
        if (r0 == null) goto L_0x0074;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> com.fasterxml.jackson.databind.JsonDeserializer<T> A00(java.lang.Class<T> r5) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C00790Iy.A00(java.lang.Class):com.fasterxml.jackson.databind.JsonDeserializer");
    }
}

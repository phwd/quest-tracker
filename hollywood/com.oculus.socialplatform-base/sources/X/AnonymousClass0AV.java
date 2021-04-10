package X;

import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0AV  reason: invalid class name */
public final class AnonymousClass0AV {
    public static Map<Class<?>, List<Constructor<? extends AnonymousClass0AN>>> A00 = new HashMap();
    public static Map<Class<?>, Integer> A01 = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
        if (r0.booleanValue() != false) goto L_0x007a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(java.lang.Class<?> r9) {
        /*
        // Method dump skipped, instructions count: 276
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0AV.A00(java.lang.Class):int");
    }

    public static AnonymousClass0AN A01(Constructor<? extends AnonymousClass0AN> constructor, Object obj) {
        try {
            constructor.newInstance(obj);
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}

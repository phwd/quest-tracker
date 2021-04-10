package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Od {
    public final BZ A00;
    public final Map<Integer, OR> A01 = new HashMap();
    public final Map<gz, OV> A02 = new HashMap();
    public final Map<gz, Ox> A03 = new HashMap();
    public final Map<Class<? extends Annotation>, PC> A04 = new LinkedHashMap();
    public final Set<Class<?>> A05 = new HashSet();
    public final List<? extends SV> A06;

    public static boolean A01(Class<?> cls) {
        if (PT.class.isAssignableFrom(cls) || AnonymousClass7z.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/BZ;Ljava/util/List<+LX/SV;>;Z)V */
    public Od(BZ bz, List list) {
        this.A00 = bz;
        this.A06 = list;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/OQ;Ljava/lang/Class<*>;)V */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0115, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0118, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0123, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0124, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0115 A[ExcHandler: IllegalAccessException (r1v27 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:17:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.Od r9, X.OQ r10, java.lang.Class r11) {
        /*
        // Method dump skipped, instructions count: 618
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Od.A00(X.Od, X.OQ, java.lang.Class):void");
    }
}

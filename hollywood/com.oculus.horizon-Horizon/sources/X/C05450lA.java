package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0lA  reason: invalid class name and case insensitive filesystem */
public final class C05450lA implements Serializable {
    public static final long serialVersionUID = 1;
    public final ConcurrentHashMap<AbstractC04000gb, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
    public final HashMap<AbstractC04000gb, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

    /* JADX WARNING: Code restructure failed: missing block: B:120:0x025a, code lost:
        if (r0 == null) goto L_0x025c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x028c, code lost:
        if (r4 != null) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0296, code lost:
        if ((r15._class.getModifiers() & 1536) != 0) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0298, code lost:
        r1 = "Can not find a Value deserializer for type ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x029a, code lost:
        r0 = new java.lang.StringBuilder(r1);
        r0.append(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02ab, code lost:
        throw new X.C03990gZ(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02ac, code lost:
        r1 = "Can not find a Value deserializer for abstract type ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x02af, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A00(X.AbstractC04020gg r13, X.AbstractC05460lB r14, X.AbstractC04000gb r15) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 726
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05450lA.A00(X.0gg, X.0lB, X.0gb):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }
}

package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class Zj implements Serializable {
    public static final long serialVersionUID = 1;
    public final ConcurrentHashMap<AbstractC0224Wl, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
    public final HashMap<AbstractC0224Wl, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }

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
        throw new X.C0223Wj(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02ac, code lost:
        r1 = "Can not find a Value deserializer for abstract type ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x02af, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A00(X.AbstractC0226Wn r13, X.ZD r14, X.AbstractC0224Wl r15) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 726
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Zj.A00(X.Wn, X.ZD, X.Wl):com.fasterxml.jackson.databind.JsonDeserializer");
    }
}

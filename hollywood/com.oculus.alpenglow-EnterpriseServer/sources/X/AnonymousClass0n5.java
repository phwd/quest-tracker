package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0n5  reason: invalid class name */
public final class AnonymousClass0n5 implements Serializable {
    public static final long serialVersionUID = 1;
    public final ConcurrentHashMap<AnonymousClass0aI, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
    public final HashMap<AnonymousClass0aI, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

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
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02ab, code lost:
        throw new X.AnonymousClass0aG(r1 + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02ac, code lost:
        r1 = "Can not find a Value deserializer for abstract type ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x02af, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A00(X.AbstractC02570aK r13, X.AbstractC06540n6 r14, X.AnonymousClass0aI r15) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 726
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0n5.A00(X.0aK, X.0n6, X.0aI):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }
}

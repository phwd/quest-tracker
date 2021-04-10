package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.0pe  reason: invalid class name and case insensitive filesystem */
public final class C04240pe implements Serializable {
    public static final long serialVersionUID = 1;
    public final ConcurrentHashMap<AbstractC02190iF, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
    public final HashMap<AbstractC02190iF, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x025a, code lost:
        if (r0 == null) goto L_0x025c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x028c, code lost:
        if (r4 != null) goto L_0x02c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0296, code lost:
        if ((r15._class.getModifiers() & 1536) != 0) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0298, code lost:
        r0 = new java.lang.StringBuilder("Can not find a Value deserializer for type ");
        r0.append(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02ab, code lost:
        throw new X.C02180iD(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x02ac, code lost:
        r0 = new java.lang.StringBuilder("Can not find a Value deserializer for abstract type ");
        r0.append(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02bf, code lost:
        throw new X.C02180iD(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x02c0, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A00(X.AbstractC02210iH r13, X.AbstractC04250pf r14, X.AbstractC02190iF r15) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 750
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04240pe.A00(X.0iH, X.0pf, X.0iF):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }
}

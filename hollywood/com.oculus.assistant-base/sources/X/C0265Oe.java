package X;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.Oe  reason: case insensitive filesystem */
public final class C0265Oe implements Serializable {
    public static final long serialVersionUID = 1;
    public final ConcurrentHashMap _cachedDeserializers = new ConcurrentHashMap(64, 0.75f, 2);
    public final HashMap _incompleteDeserializers = new HashMap(8);

    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:715:0x05d5 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:732:0x0aa0 */
    /* JADX DEBUG: Multi-variable search result rejected for r6v119, resolved type: com.fasterxml.jackson.databind.JsonDeserializer */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v112, types: [com.fasterxml.jackson.databind.deser.std.MapDeserializer] */
    /* JADX WARN: Type inference failed for: r6v118, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARN: Type inference failed for: r6v122, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0618, code lost:
        if (r0 != false) goto L_0x061a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:506:0x09e0, code lost:
        if (r6 == null) goto L_0x09f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:572:0x0a9e, code lost:
        if (r6 != null) goto L_0x0aa0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:573:0x0aa0, code lost:
        if (r6 == null) goto L_0x0aa2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:624:0x0b57, code lost:
        if (r6 != null) goto L_0x0aa0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:675:0x0c32, code lost:
        if (r6 != null) goto L_0x0c66;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:677:0x0c3c, code lost:
        if ((r28._class.getModifiers() & 1536) != 0) goto L_0x0c52;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:678:0x0c3e, code lost:
        r0 = new java.lang.StringBuilder("Can not find a Value deserializer for type ");
        r0.append(r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:679:0x0c51, code lost:
        throw new X.C1025qv(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:680:0x0c52, code lost:
        r0 = new java.lang.StringBuilder("Can not find a Value deserializer for abstract type ");
        r0.append(r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:681:0x0c65, code lost:
        throw new X.C1025qv(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:682:0x0c66, code lost:
        return r6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x030c  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x03c3  */
    /* JADX WARNING: Removed duplicated region for block: B:592:0x0ac6 A[Catch:{ all -> 0x0c98 }] */
    /* JADX WARNING: Removed duplicated region for block: B:594:0x0ad8 A[Catch:{ all -> 0x0c98 }] */
    /* JADX WARNING: Removed duplicated region for block: B:597:0x0ae8  */
    /* JADX WARNING: Removed duplicated region for block: B:604:0x0b07  */
    /* JADX WARNING: Removed duplicated region for block: B:628:0x0b69  */
    /* JADX WARNING: Removed duplicated region for block: B:671:0x0c26  */
    /* JADX WARNING: Removed duplicated region for block: B:687:0x0c7f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer A00(X.AbstractC1022qr r26, X.AbstractC0266Of r27, X.AbstractC1024qt r28) {
        /*
        // Method dump skipped, instructions count: 3250
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0265Oe.A00(X.qr, X.Of, X.qt):com.fasterxml.jackson.databind.JsonDeserializer");
    }
}

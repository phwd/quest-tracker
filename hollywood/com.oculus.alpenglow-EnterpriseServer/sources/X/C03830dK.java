package X;

/* renamed from: X.0dK  reason: invalid class name and case insensitive filesystem */
public class C03830dK implements AnonymousClass09d<AnonymousClass09X> {
    public final /* synthetic */ String A00;

    public C03830dK(String str) {
        this.A00 = str;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r1 >= r2.size()) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r2.get(r1).A6T(r5);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = 0;
     */
    @Override // X.AnonymousClass09d
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A6T(X.AnonymousClass09X r5) {
        /*
            r4 = this;
            java.lang.Object r3 = X.AnonymousClass09Y.A03
            monitor-enter(r3)
            X.06D<java.lang.String, java.util.ArrayList<X.09d<X.09X>>> r1 = X.AnonymousClass09Y.A01     // Catch:{ all -> 0x0029 }
            java.lang.String r0 = r4.A00     // Catch:{ all -> 0x0029 }
            java.lang.Object r2 = r1.get(r0)     // Catch:{ all -> 0x0029 }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x0029 }
            if (r2 != 0) goto L_0x0011
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            return
        L_0x0011:
            r1.remove(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            r1 = 0
        L_0x0016:
            int r0 = r2.size()
            if (r1 >= r0) goto L_0x0028
            java.lang.Object r0 = r2.get(r1)
            X.09d r0 = (X.AnonymousClass09d) r0
            r0.A6T(r5)
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0028:
            return
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03830dK.A6T(java.lang.Object):void");
    }
}

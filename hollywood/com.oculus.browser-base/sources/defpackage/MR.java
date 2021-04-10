package defpackage;

/* renamed from: MR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MR implements AbstractC5596xS0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8476a;

    public MR(String str) {
        this.f8476a = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r0 >= r2.size()) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        ((defpackage.AbstractC5596xS0) r2.get(r0)).a(r5);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = 0;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(defpackage.QR r5) {
        /*
            r4 = this;
            java.lang.Object r0 = defpackage.RR.c
            monitor-enter(r0)
            BW0 r1 = defpackage.RR.d     // Catch:{ all -> 0x002b }
            java.lang.String r2 = r4.f8476a     // Catch:{ all -> 0x002b }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x002b }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x002b }
            if (r2 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x0011:
            java.lang.String r3 = r4.f8476a     // Catch:{ all -> 0x002b }
            r1.remove(r3)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            r0 = 0
        L_0x0018:
            int r1 = r2.size()
            if (r0 >= r1) goto L_0x002a
            java.lang.Object r1 = r2.get(r0)
            xS0 r1 = (defpackage.AbstractC5596xS0) r1
            r1.a(r5)
            int r0 = r0 + 1
            goto L_0x0018
        L_0x002a:
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.MR.a(QR):void");
    }
}

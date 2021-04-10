package defpackage;

import java.io.File;
import java.util.List;

/* renamed from: Bk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0091Bk0 {

    /* renamed from: a  reason: collision with root package name */
    public final C1619aB f7753a;
    public final File b;
    public final List c;

    public C0091Bk0(C1619aB aBVar, File file, List list) {
        this.f7753a = aBVar;
        this.b = file;
        this.c = list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v1, resolved type: java.io.BufferedOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r5v3, resolved type: java.io.BufferedOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r5v4, resolved type: java.io.BufferedOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r5v6, resolved type: java.io.BufferedOutputStream */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r5, java.io.File r6) {
        /*
            r0 = 256(0x100, float:3.59E-43)
            r1 = 0
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0035 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0035 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0035 }
            r3.<init>(r5)     // Catch:{ all -> 0x0035 }
            r2.<init>(r3)     // Catch:{ all -> 0x0035 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0030 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0030 }
            r4 = 1
            r3.<init>(r6, r4)     // Catch:{ all -> 0x0030 }
            r5.<init>(r3)     // Catch:{ all -> 0x0030 }
        L_0x001a:
            int r6 = r2.read(r0)     // Catch:{ all -> 0x002d }
            r1 = -1
            if (r6 == r1) goto L_0x0026
            r1 = 0
            r5.write(r0, r1, r6)     // Catch:{ all -> 0x002d }
            goto L_0x001a
        L_0x0026:
            r2.close()
            r5.close()
            return
        L_0x002d:
            r6 = move-exception
            r1 = r5
            goto L_0x0032
        L_0x0030:
            r5 = move-exception
            r6 = r5
        L_0x0032:
            r5 = r1
            r1 = r2
            goto L_0x0038
        L_0x0035:
            r5 = move-exception
            r6 = r5
            r5 = r1
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            r1.close()
        L_0x003d:
            if (r5 == 0) goto L_0x0042
            r5.close()
        L_0x0042:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0091Bk0.a(java.io.File, java.io.File):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.io.File r3, java.util.List r4, java.lang.String r5) {
        /*
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x0041 }
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ all -> 0x0041 }
            r2 = 0
            r1.<init>(r3, r2)     // Catch:{ all -> 0x0041 }
            r0.<init>(r1)     // Catch:{ all -> 0x0041 }
            r0.write(r5)     // Catch:{ all -> 0x003f }
            r0.newLine()     // Catch:{ all -> 0x003f }
            java.lang.String r3 = "Content-Disposition: form-data; name=\"logcat\"; filename=\"logcat\""
            r0.write(r3)     // Catch:{ all -> 0x003f }
            r0.newLine()     // Catch:{ all -> 0x003f }
            java.lang.String r3 = "Content-Type: text/plain"
            r0.write(r3)     // Catch:{ all -> 0x003f }
            r0.newLine()     // Catch:{ all -> 0x003f }
            r0.newLine()     // Catch:{ all -> 0x003f }
            java.util.Iterator r3 = r4.iterator()     // Catch:{ all -> 0x003f }
        L_0x0028:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x003b
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x003f }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x003f }
            r0.write(r4)     // Catch:{ all -> 0x003f }
            r0.newLine()     // Catch:{ all -> 0x003f }
            goto L_0x0028
        L_0x003b:
            r0.close()
            return
        L_0x003f:
            r3 = move-exception
            goto L_0x0043
        L_0x0041:
            r3 = move-exception
            r0 = 0
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.close()
        L_0x0048:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0091Bk0.c(java.io.File, java.util.List, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File b() {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0091Bk0.b():java.io.File");
    }
}

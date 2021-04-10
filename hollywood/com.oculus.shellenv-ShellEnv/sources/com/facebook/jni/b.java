package com.facebook.jni;

final class b extends Thread {
    b(String str) {
        super(str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|(2:3|5)(1:6)|4) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:4:0x001a, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r2 = this;
        L_0x0000:
            java.lang.ref.ReferenceQueue r0 = com.facebook.jni.a.a()     // Catch:{ InterruptedException -> 0x0000 }
            java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
            com.facebook.jni.a$a r0 = (com.facebook.jni.a.AbstractC0000a) r0     // Catch:{ InterruptedException -> 0x0000 }
            r0.a()     // Catch:{ InterruptedException -> 0x0000 }
            com.facebook.jni.a$a r1 = com.facebook.jni.a.AbstractC0000a.a(r0)     // Catch:{ InterruptedException -> 0x0000 }
            if (r1 != 0) goto L_0x001a
            com.facebook.jni.a$c r1 = com.facebook.jni.a.b()     // Catch:{ InterruptedException -> 0x0000 }
            r1.a()     // Catch:{ InterruptedException -> 0x0000 }
        L_0x001a:
            com.facebook.jni.a.b.b(r0)     // Catch:{ InterruptedException -> 0x0000 }
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.jni.b.run():void");
    }
}

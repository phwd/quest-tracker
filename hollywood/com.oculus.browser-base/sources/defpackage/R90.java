package defpackage;

import android.os.Handler;
import android.os.Looper;

/* renamed from: R90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R90 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ U90 f8813a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public R90(U90 u90, Looper looper) {
        super(looper);
        this.f8813a = u90;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r3 >= r1) goto L_0x000b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r4 = r2[r3];
        r5 = r4.b.size();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r6 >= r5) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        r7 = (defpackage.T90) r4.b.get(r6);
        java.util.Objects.requireNonNull(r7);
        r7.b.onReceive(r11.c, r4.f8880a);
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r11) {
        /*
            r10 = this;
            int r0 = r11.what
            r1 = 1
            if (r0 == r1) goto L_0x0009
            super.handleMessage(r11)
            goto L_0x0017
        L_0x0009:
            U90 r11 = r10.f8813a
        L_0x000b:
            java.util.HashMap r0 = r11.d
            monitor-enter(r0)
            java.util.ArrayList r1 = r11.f     // Catch:{ all -> 0x004e }
            int r1 = r1.size()     // Catch:{ all -> 0x004e }
            if (r1 > 0) goto L_0x0018
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
        L_0x0017:
            return
        L_0x0018:
            S90[] r2 = new defpackage.S90[r1]     // Catch:{ all -> 0x004e }
            java.util.ArrayList r3 = r11.f     // Catch:{ all -> 0x004e }
            r3.toArray(r2)     // Catch:{ all -> 0x004e }
            java.util.ArrayList r3 = r11.f     // Catch:{ all -> 0x004e }
            r3.clear()     // Catch:{ all -> 0x004e }
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            r0 = 0
            r3 = r0
        L_0x0027:
            if (r3 >= r1) goto L_0x000b
            r4 = r2[r3]
            java.util.ArrayList r5 = r4.b
            int r5 = r5.size()
            r6 = r0
        L_0x0032:
            if (r6 >= r5) goto L_0x004b
            java.util.ArrayList r7 = r4.b
            java.lang.Object r7 = r7.get(r6)
            T90 r7 = (defpackage.T90) r7
            java.util.Objects.requireNonNull(r7)
            android.content.BroadcastReceiver r7 = r7.b
            android.content.Context r8 = r11.c
            android.content.Intent r9 = r4.f8880a
            r7.onReceive(r8, r9)
            int r6 = r6 + 1
            goto L_0x0032
        L_0x004b:
            int r3 = r3 + 1
            goto L_0x0027
        L_0x004e:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.R90.handleMessage(android.os.Message):void");
    }
}

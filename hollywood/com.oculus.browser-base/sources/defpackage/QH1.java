package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

/* renamed from: QH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class QH1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8752a;
    public final Object b = new Object();
    public final String c;
    public final String d;
    public final String e;
    public boolean f = false;
    public Object g;

    public QH1(Context context, String str, String str2) {
        this.f8752a = context;
        this.c = str;
        StringBuilder sb = new StringBuilder(str2.length() + 39);
        sb.append("com.google.android.gms.vision.dynamite");
        sb.append(".");
        sb.append(str2);
        this.d = sb.toString();
        this.e = "com.google.android.gms.vision.dynamite";
    }

    public final boolean a() {
        return e() != null;
    }

    public abstract Object b(PJ pj, Context context);

    public abstract void c();

    public final void d() {
        synchronized (this.b) {
            if (this.g != null) {
                try {
                    c();
                } catch (RemoteException e2) {
                    Log.e(this.c, "Could not finalize native handle", e2);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        android.util.Log.e(r5.c, "Error creating remote native handle", r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0017 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0015 A[ExcHandler: JJ | RemoteException (r1v14 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object e() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.b
            monitor-enter(r0)
            java.lang.Object r1 = r5.g     // Catch:{ all -> 0x0061 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0061 }
            return r1
        L_0x0009:
            r1 = 0
            android.content.Context r2 = r5.f8752a     // Catch:{ JJ -> 0x0017, RemoteException -> 0x0015 }
            MJ r3 = defpackage.PJ.i     // Catch:{ JJ -> 0x0017, RemoteException -> 0x0015 }
            java.lang.String r4 = r5.d     // Catch:{ JJ -> 0x0017, RemoteException -> 0x0015 }
            PJ r1 = defpackage.PJ.c(r2, r3, r4)     // Catch:{ JJ -> 0x0017, RemoteException -> 0x0015 }
            goto L_0x002a
        L_0x0015:
            r1 = move-exception
            goto L_0x0036
        L_0x0017:
            android.content.Context r2 = r5.f8752a     // Catch:{ JJ -> 0x0022, RemoteException -> 0x0015 }
            MJ r3 = defpackage.PJ.i     // Catch:{ JJ -> 0x0022, RemoteException -> 0x0015 }
            java.lang.String r4 = r5.e     // Catch:{ JJ -> 0x0022, RemoteException -> 0x0015 }
            PJ r1 = defpackage.PJ.c(r2, r3, r4)     // Catch:{ JJ -> 0x0022, RemoteException -> 0x0015 }
            goto L_0x002a
        L_0x0022:
            r2 = move-exception
            java.lang.String r3 = r5.c     // Catch:{ JJ -> 0x0035, RemoteException -> 0x0015 }
            java.lang.String r4 = "Error Loading module"
            android.util.Log.e(r3, r4, r2)     // Catch:{ JJ -> 0x0035, RemoteException -> 0x0015 }
        L_0x002a:
            if (r1 == 0) goto L_0x003d
            android.content.Context r2 = r5.f8752a     // Catch:{ JJ -> 0x0035, RemoteException -> 0x0015 }
            java.lang.Object r1 = r5.b(r1, r2)     // Catch:{ JJ -> 0x0035, RemoteException -> 0x0015 }
            r5.g = r1     // Catch:{ JJ -> 0x0035, RemoteException -> 0x0015 }
            goto L_0x003d
        L_0x0035:
            r1 = move-exception
        L_0x0036:
            java.lang.String r2 = r5.c
            java.lang.String r3 = "Error creating remote native handle"
            android.util.Log.e(r2, r3, r1)
        L_0x003d:
            boolean r1 = r5.f
            if (r1 != 0) goto L_0x0050
            java.lang.Object r2 = r5.g
            if (r2 != 0) goto L_0x0050
            java.lang.String r1 = r5.c
            java.lang.String r2 = "Native handle not yet available. Reverting to no-op handle."
            android.util.Log.w(r1, r2)
            r1 = 1
            r5.f = r1
            goto L_0x005d
        L_0x0050:
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r5.g
            if (r1 == 0) goto L_0x005d
            java.lang.String r1 = r5.c
            java.lang.String r2 = "Native handle is now available."
            android.util.Log.w(r1, r2)
        L_0x005d:
            java.lang.Object r1 = r5.g
            monitor-exit(r0)
            return r1
        L_0x0061:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.QH1.e():java.lang.Object");
    }
}

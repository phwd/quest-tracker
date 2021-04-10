package defpackage;

import android.accounts.AccountManagerCallback;
import org.chromium.base.Callback;

/* renamed from: v51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5197v51 implements AccountManagerCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11460a;

    public C5197v51(Callback callback) {
        this.f11460a = callback;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // android.accounts.AccountManagerCallback
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run(android.accounts.AccountManagerFuture r6) {
        /*
            r5 = this;
            org.chromium.base.Callback r0 = r5.f11460a
            java.lang.String r1 = "Auth"
            r2 = 1
            r3 = 0
            java.lang.Object r6 = r6.getResult()     // Catch:{ AuthenticatorException -> 0x0017, IOException -> 0x0015, OperationCanceledException -> 0x000d }
            android.os.Bundle r6 = (android.os.Bundle) r6     // Catch:{ AuthenticatorException -> 0x0017, IOException -> 0x0015, OperationCanceledException -> 0x000d }
            goto L_0x0022
        L_0x000d:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r4 = "Updating credentials was cancelled."
            defpackage.AbstractC1220Ua0.f(r1, r4, r6)
            goto L_0x0021
        L_0x0015:
            r6 = move-exception
            goto L_0x0018
        L_0x0017:
            r6 = move-exception
        L_0x0018:
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r4[r3] = r6
            java.lang.String r6 = "Error while update credentials: "
            defpackage.AbstractC1220Ua0.a(r1, r6, r4)
        L_0x0021:
            r6 = 0
        L_0x0022:
            if (r6 == 0) goto L_0x002d
            java.lang.String r1 = "accountType"
            java.lang.String r6 = r6.getString(r1)
            if (r6 == 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r2 = r3
        L_0x002e:
            if (r0 == 0) goto L_0x0037
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            r0.onResult(r6)
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5197v51.run(android.accounts.AccountManagerFuture):void");
    }
}

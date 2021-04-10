package defpackage;

import android.accounts.Account;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Set;

/* renamed from: KV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KV extends BaseGmsClient implements AbstractC2129d7, LV {
    public final Set B;
    public final Account C;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KV(android.content.Context r10, android.os.Looper r11, int r12, defpackage.C3800mv r13, defpackage.AbstractC0482Hx r14, defpackage.AbstractC0777Ms0 r15) {
        /*
            r9 = this;
            ZF1 r3 = defpackage.ZF1.a(r10)
            java.lang.Object r0 = defpackage.SV.c
            SV r4 = defpackage.SV.d
            java.lang.String r0 = "null reference"
            java.util.Objects.requireNonNull(r14, r0)
            java.util.Objects.requireNonNull(r15, r0)
            MB1 r6 = new MB1
            r6.<init>(r14)
            PB1 r7 = new PB1
            r7.<init>(r15)
            java.lang.String r8 = r13.f
            r0 = r9
            r1 = r10
            r2 = r11
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            android.accounts.Account r10 = r13.f10459a
            r9.C = r10
            java.util.Set r10 = r13.c
            java.util.Iterator r11 = r10.iterator()
        L_0x002d:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0048
            java.lang.Object r12 = r11.next()
            com.google.android.gms.common.api.Scope r12 = (com.google.android.gms.common.api.Scope) r12
            boolean r12 = r10.contains(r12)
            if (r12 == 0) goto L_0x0040
            goto L_0x002d
        L_0x0040:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Expanding scopes is not permitted, use implied scopes instead"
            r10.<init>(r11)
            throw r10
        L_0x0048:
            r9.B = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.KV.<init>(android.content.Context, android.os.Looper, int, mv, Hx, Ms0):void");
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account f() {
        return this.C;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Set k() {
        return this.B;
    }
}

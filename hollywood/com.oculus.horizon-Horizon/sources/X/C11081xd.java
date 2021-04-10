package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import retrofit.client.Defaults;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xd  reason: invalid class name and case insensitive filesystem */
public final class C11081xd extends AnonymousClass1lF {
    @Nullable
    public String mErrorSeverity;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C11081xd(java.lang.Throwable r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof X.AnonymousClass1lF
            r1 = 20000(0x4e20, float:2.8026E-41)
            if (r0 == 0) goto L_0x000b
            r0 = r3
            X.1lF r0 = (X.AnonymousClass1lF) r0
            int r1 = r0.mErrorCode
        L_0x000b:
            java.lang.String r0 = r3.getMessage()
            if (r0 == 0) goto L_0x0019
            java.lang.String r0 = r3.getMessage()
        L_0x0015:
            r2.<init>(r1, r0, r3)
            return
        L_0x0019:
            java.lang.String r0 = ""
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11081xd.<init>(java.lang.Throwable):void");
    }

    public C11081xd(int i, String str) {
        super(i, str);
    }

    public C11081xd(int i, String str, String str2, @Nullable Throwable th) {
        super(i, str, th);
        this.mErrorSeverity = str2;
    }

    public C11081xd(int i, String str, @Nullable Throwable th) {
        super(i, str, th);
    }

    public C11081xd(int i, Throwable th) {
        super(i, th);
    }

    public C11081xd(String str) {
        super((int) Defaults.READ_TIMEOUT_MILLIS, str);
    }
}

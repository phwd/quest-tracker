package X;

import android.content.pm.Signature;
import android.util.Base64;
import com.google.common.collect.ImmutableSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: X.0TR  reason: invalid class name */
public class AnonymousClass0TR {
    public final int A00;
    public final Signature A01;
    public final ImmutableSet<String> A02;
    public final boolean A03;

    public final String toString() {
        StringBuilder sb = new StringBuilder("TrustedCallerInfo{isTrusted=");
        sb.append(this.A03);
        sb.append(", uid=");
        sb.append(this.A00);
        sb.append(", signature=");
        Signature signature = this.A01;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] byteArray = signature.toByteArray();
            instance.update(byteArray, 0, byteArray.length);
            sb.append(Base64.encodeToString(instance.digest(), 11));
            sb.append(", packageNames=");
            sb.append(this.A02);
            sb.append('}');
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (ZIILandroid/content/pm/Signature;Ljava/util/Set<Ljava/lang/String;>;)V */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r1.A08() == false) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0TR(boolean r3, int r4, android.content.pm.Signature r5, java.util.Set r6) {
        /*
            r2 = this;
            r2.<init>()
            r2.A03 = r3
            r2.A00 = r4
            r2.A01 = r5
            boolean r0 = r6 instanceof com.google.common.collect.ImmutableSet
            if (r0 == 0) goto L_0x001d
            boolean r0 = r6 instanceof java.util.SortedSet
            if (r0 != 0) goto L_0x001d
            r1 = r6
            com.google.common.collect.ImmutableSet r1 = (com.google.common.collect.ImmutableSet) r1
            boolean r0 = r1.A08()
            if (r0 != 0) goto L_0x001d
        L_0x001a:
            r2.A02 = r1
            return
        L_0x001d:
            java.lang.Object[] r1 = r6.toArray()
            int r0 = r1.length
            com.google.common.collect.ImmutableSet r1 = com.google.common.collect.ImmutableSet.A06(r0, r1)
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0TR.<init>(boolean, int, android.content.pm.Signature, java.util.Set):void");
    }
}

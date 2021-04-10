package X;

import android.content.pm.Signature;
import android.util.Base64;
import com.facebook.internal.Utility;
import com.google.common.collect.ImmutableSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

/* renamed from: X.1E5  reason: invalid class name */
public class AnonymousClass1E5 {
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
            MessageDigest instance = MessageDigest.getInstance(Utility.HASH_ALGORITHM_SHA1);
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
    public AnonymousClass1E5(boolean z, int i, Signature signature, Set set) {
        this.A03 = z;
        this.A00 = i;
        this.A01 = signature;
        this.A02 = ImmutableSet.A08(set);
    }
}

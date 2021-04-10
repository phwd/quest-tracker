package X;

import androidx.annotation.NonNull;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/* renamed from: X.1aL  reason: invalid class name and case insensitive filesystem */
public interface AbstractC06491aL {
    public static final Charset A00 = Charset.forName("UTF-8");

    void AAv(@NonNull MessageDigest messageDigest);

    boolean equals(Object obj);

    int hashCode();
}

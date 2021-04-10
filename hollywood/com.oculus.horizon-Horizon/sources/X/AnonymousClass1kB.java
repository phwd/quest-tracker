package X;

import android.util.Base64;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.internal.Utility;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1kB  reason: invalid class name */
public final class AnonymousClass1kB {
    public static String A00(AnonymousClass1kC r3) throws UnsupportedEncodingException {
        byte[] bytes = r3.A4c().getBytes("UTF-8");
        try {
            MessageDigest instance = MessageDigest.getInstance(Utility.HASH_ALGORITHM_SHA1);
            instance.update(bytes, 0, bytes.length);
            return Base64.encodeToString(instance.digest(), 11);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

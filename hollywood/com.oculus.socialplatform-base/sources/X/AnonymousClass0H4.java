package X;

import android.util.Base64;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.secure.trustedapp.HashHelper;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0H4  reason: invalid class name */
public final class AnonymousClass0H4 {
    public static List<String> A01(AnonymousClass0H3 r2) {
        String A00;
        try {
            ArrayList arrayList = new ArrayList(1);
            if (r2.A68()) {
                A00 = r2.A5D();
            } else {
                A00 = A00(r2);
            }
            arrayList.add(A00);
            return arrayList;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String A00(AnonymousClass0H3 r3) throws UnsupportedEncodingException {
        byte[] bytes = r3.A5D().getBytes("UTF-8");
        try {
            MessageDigest instance = MessageDigest.getInstance(HashHelper.SHA1);
            instance.update(bytes, 0, bytes.length);
            return Base64.encodeToString(instance.digest(), 11);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

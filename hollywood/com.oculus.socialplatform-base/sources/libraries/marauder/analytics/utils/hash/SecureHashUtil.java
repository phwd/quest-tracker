package libraries.marauder.analytics.utils.hash;

import com.adobe.xmp.impl.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecureHashUtil {
    public static final byte[] HEX_CHAR_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    public static String convertToHex(byte[] bArr) throws UnsupportedEncodingException {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length << 1);
        for (byte b : bArr) {
            int i = b & Base64.INVALID;
            byte[] bArr2 = HEX_CHAR_TABLE;
            sb.append((char) bArr2[i >>> 4]);
            sb.append((char) bArr2[i & 15]);
        }
        return sb.toString();
    }

    public static String makeMD5Hash(String str) {
        try {
            return makeMD5Hash(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeMD5Hash(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr, 0, bArr.length);
            return convertToHex(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}

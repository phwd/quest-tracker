package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yh  reason: invalid class name and case insensitive filesystem */
public final class C09120yh {
    public static Map<String, String> A00(String... strArr) {
        int length = strArr.length;
        if (length % 2 == 0) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < length; i += 2) {
                String str = strArr[i];
                if (str == null) {
                    str = "";
                }
                String str2 = strArr[i + 1];
                if (str2 == null) {
                    str2 = "";
                }
                hashMap.put(str, str2);
            }
            return hashMap;
        }
        throw new IllegalArgumentException("Map must have an even (or zero) number of parameters");
    }
}

package X;

import com.facebook.acra.LogCatCollector;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class LU {
    public static final Charset A00 = Charset.forName(LogCatCollector.UTF_8_ENCODING);

    public static int A00(LV lv) {
        byte A002 = lv.A00();
        byte A003 = lv.A00();
        return (lv.A00() << 24) + ((lv.A00() & 255) << 16) + ((A003 & 255) << 8) + (A002 & 255);
    }

    public static long A01(LV lv) {
        long A002 = (long) lv.A00();
        long A003 = (long) lv.A00();
        long A004 = (long) lv.A00();
        long A005 = (long) lv.A00();
        long A006 = (long) lv.A00();
        long A007 = (long) lv.A00();
        return (((long) lv.A00()) << 56) + ((((long) lv.A00()) & 255) << 48) + ((A007 & 255) << 40) + ((A006 & 255) << 32) + ((A005 & 255) << 24) + ((A004 & 255) << 16) + ((A003 & 255) << 8) + (A002 & 255);
    }

    public static String A02(LV lv) {
        int A002 = A00(lv);
        String str = new String(lv.A02, lv.A00, A002 - 1, A00);
        int i = lv.A00 + A002;
        if (i <= lv.A01) {
            lv.A00 = i;
            return str;
        }
        throw new IllegalStateException();
    }

    public static Map A03(LV lv) {
        int A002 = A00(lv);
        HashMap hashMap = new HashMap(A002);
        for (int i = 0; i < A002; i++) {
            hashMap.put(A02(lv), A02(lv));
        }
        return hashMap;
    }

    public static boolean A04(LV lv) {
        if (lv.A00() != 0) {
            return true;
        }
        return false;
    }
}

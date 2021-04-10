package libcore.net;

import android.system.GaiException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import java.net.InetAddress;
import libcore.io.Libcore;

public class InetAddressUtils {
    public static InetAddress parseNumericAddressNoThrow(String str) {
        InetAddress[] inetAddressArr;
        StructAddrinfo structAddrinfo = new StructAddrinfo();
        structAddrinfo.ai_flags = OsConstants.AI_NUMERICHOST;
        try {
            inetAddressArr = Libcore.os.android_getaddrinfo(str, structAddrinfo, 0);
        } catch (GaiException unused) {
            inetAddressArr = null;
        }
        if (inetAddressArr == null) {
            return null;
        }
        return inetAddressArr[0];
    }

    public static InetAddress parseNumericAddressNoThrowStripOptionalBrackets(String str) {
        if (str.startsWith("[") && str.endsWith("]") && str.indexOf(58) != -1) {
            str = str.substring(1, str.length() - 1);
        }
        return parseNumericAddressNoThrow(str);
    }
}

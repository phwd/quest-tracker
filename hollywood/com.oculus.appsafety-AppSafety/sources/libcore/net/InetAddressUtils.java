package libcore.net;

import android.system.GaiException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import java.net.InetAddress;
import libcore.io.Libcore;

public class InetAddressUtils {
    private static final int NETID_UNSET = 0;

    private InetAddressUtils() {
    }

    public static boolean isNumericAddress(String address) {
        return parseNumericAddressNoThrow(address) != null;
    }

    public static InetAddress parseNumericAddress(String address) {
        InetAddress result = parseNumericAddressNoThrow(address);
        if (result != null) {
            return result;
        }
        throw new IllegalArgumentException("Not a numeric address: " + address);
    }

    public static InetAddress parseNumericAddressNoThrow(String address) {
        StructAddrinfo hints = new StructAddrinfo();
        hints.ai_flags = OsConstants.AI_NUMERICHOST;
        InetAddress[] addresses = null;
        try {
            addresses = Libcore.os.android_getaddrinfo(address, hints, 0);
        } catch (GaiException e) {
        }
        if (addresses == null) {
            return null;
        }
        return addresses[0];
    }

    public static InetAddress parseNumericAddressNoThrowStripOptionalBrackets(String address) {
        if (address.startsWith("[") && address.endsWith("]") && address.indexOf(58) != -1) {
            address = address.substring(1, address.length() - 1);
        }
        return parseNumericAddressNoThrow(address);
    }
}

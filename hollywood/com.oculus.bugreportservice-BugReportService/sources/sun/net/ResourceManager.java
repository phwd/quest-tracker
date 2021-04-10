package sun.net;

import java.net.SocketException;
import java.security.AccessController;
import java.util.concurrent.atomic.AtomicInteger;
import sun.security.action.GetPropertyAction;

public class ResourceManager {
    private static final int maxSockets;
    private static final AtomicInteger numSockets = new AtomicInteger(0);

    static {
        String str = (String) AccessController.doPrivileged(new GetPropertyAction("sun.net.maxDatagramSockets"));
        int i = 25;
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
            }
        }
        maxSockets = i;
    }

    public static void beforeUdpCreate() {
        if (System.getSecurityManager() != null && numSockets.incrementAndGet() > maxSockets) {
            numSockets.decrementAndGet();
            throw new SocketException("maximum number of DatagramSockets reached");
        }
    }

    public static void afterUdpClose() {
        if (System.getSecurityManager() != null) {
            numSockets.decrementAndGet();
        }
    }
}

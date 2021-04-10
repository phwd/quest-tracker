package java.net;

import java.io.PrintStream;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public class DefaultDatagramSocketImplFactory {
    static Class<?> prefixImplClass;

    DefaultDatagramSocketImplFactory() {
    }

    static {
        prefixImplClass = null;
        try {
            String prefix = (String) AccessController.doPrivileged(new GetPropertyAction("impl.prefix", null));
            if (prefix != null) {
                prefixImplClass = Class.forName("java.net." + prefix + "DatagramSocketImpl");
            }
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("Can't find class: java.net." + ((String) null) + "DatagramSocketImpl: check impl.prefix property");
        }
    }

    static DatagramSocketImpl createDatagramSocketImpl(boolean isMulticast) throws SocketException {
        Class<?> cls = prefixImplClass;
        if (cls == null) {
            return new PlainDatagramSocketImpl();
        }
        try {
            return (DatagramSocketImpl) cls.newInstance();
        } catch (Exception e) {
            throw new SocketException("can't instantiate DatagramSocketImpl");
        }
    }
}

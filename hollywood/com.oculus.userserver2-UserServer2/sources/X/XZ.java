package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public interface XZ {
    public static final XZ A00 = new C0057Em();

    List<InetAddress> lookup(String str) throws UnknownHostException;
}

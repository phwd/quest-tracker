package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class LF implements AbstractC0372dv {
    @Override // X.AbstractC0372dv
    public final List<InetAddress> lookup(String str) throws UnknownHostException {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}

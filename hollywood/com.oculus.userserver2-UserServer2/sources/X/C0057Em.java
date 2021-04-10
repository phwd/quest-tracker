package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.Em  reason: case insensitive filesystem */
public class C0057Em implements XZ {
    @Override // X.XZ
    public final List<InetAddress> lookup(String str) throws UnknownHostException {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}

package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.0RB  reason: invalid class name */
public class AnonymousClass0RB implements AbstractC06050ls {
    @Override // X.AbstractC06050ls
    public final List<InetAddress> lookup(String str) throws UnknownHostException {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}

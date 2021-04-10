package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.0N3  reason: invalid class name */
public class AnonymousClass0N3 implements AbstractC08440wa {
    @Override // X.AbstractC08440wa
    public final List<InetAddress> lookup(String str) throws UnknownHostException {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}

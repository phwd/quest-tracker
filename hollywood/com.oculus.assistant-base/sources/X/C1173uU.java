package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.uU  reason: case insensitive filesystem */
public final class C1173uU implements AbstractC0539bc {
    @Override // X.AbstractC0539bc
    public final List A3m(String str) {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}

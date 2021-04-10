package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* renamed from: X.dv  reason: case insensitive filesystem */
public interface AbstractC0372dv {
    public static final AbstractC0372dv A00 = new LF();

    List<InetAddress> lookup(String str) throws UnknownHostException;
}

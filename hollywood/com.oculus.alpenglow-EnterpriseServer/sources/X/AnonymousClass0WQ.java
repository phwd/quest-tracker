package X;

import java.io.IOException;
import java.net.InetAddress;

/* renamed from: X.0WQ  reason: invalid class name */
public class AnonymousClass0WQ extends AnonymousClass0Bd<InetAddress> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, InetAddress inetAddress) throws IOException {
        String hostAddress;
        InetAddress inetAddress2 = inetAddress;
        if (inetAddress2 == null) {
            hostAddress = null;
        } else {
            hostAddress = inetAddress2.getHostAddress();
        }
        r2.A0E(hostAddress);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final InetAddress A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return InetAddress.getByName(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}

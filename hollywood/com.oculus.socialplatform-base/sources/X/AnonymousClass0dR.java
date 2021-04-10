package X;

import java.io.IOException;
import java.net.InetAddress;

/* renamed from: X.0dR  reason: invalid class name */
public class AnonymousClass0dR extends AnonymousClass13Y<InetAddress> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, InetAddress inetAddress) throws IOException {
        String hostAddress;
        InetAddress inetAddress2 = inetAddress;
        if (inetAddress2 == null) {
            hostAddress = null;
        } else {
            hostAddress = inetAddress2.getHostAddress();
        }
        r2.A0D(hostAddress);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final InetAddress A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return InetAddress.getByName(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}

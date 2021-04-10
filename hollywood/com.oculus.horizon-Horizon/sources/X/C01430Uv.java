package X;

import java.io.IOException;
import java.net.InetAddress;

/* renamed from: X.0Uv  reason: invalid class name and case insensitive filesystem */
public class C01430Uv extends AnonymousClass0yl<InetAddress> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, InetAddress inetAddress) throws IOException {
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
    @Override // X.AnonymousClass0yl
    public final InetAddress A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return InetAddress.getByName(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}

package X;

import java.io.IOException;
import java.net.InetAddress;

/* renamed from: X.Si  reason: case insensitive filesystem */
public class C0149Si extends AbstractC0131Ob<InetAddress> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, InetAddress inetAddress) throws IOException {
        String hostAddress;
        InetAddress inetAddress2 = inetAddress;
        if (inetAddress2 == null) {
            hostAddress = null;
        } else {
            hostAddress = inetAddress2.getHostAddress();
        }
        mmVar.A0G(hostAddress);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final InetAddress A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return InetAddress.getByName(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}

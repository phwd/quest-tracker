package X;

import java.io.IOException;
import java.util.UUID;

/* renamed from: X.Sh  reason: case insensitive filesystem */
public class C0148Sh extends AbstractC0131Ob<UUID> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, UUID uuid) throws IOException {
        String obj;
        if (uuid == null) {
            obj = null;
        } else {
            obj = uuid.toString();
        }
        mmVar.A0G(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final UUID A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return UUID.fromString(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}

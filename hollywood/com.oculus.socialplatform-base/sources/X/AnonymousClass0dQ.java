package X;

import java.io.IOException;
import java.util.UUID;

/* renamed from: X.0dQ  reason: invalid class name */
public class AnonymousClass0dQ extends AnonymousClass13Y<UUID> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, UUID uuid) throws IOException {
        String obj;
        if (uuid == null) {
            obj = null;
        } else {
            obj = uuid.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final UUID A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return UUID.fromString(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}

package X;

import java.io.IOException;
import java.util.UUID;

/* renamed from: X.0Uu  reason: invalid class name and case insensitive filesystem */
public class C01420Uu extends AnonymousClass0yl<UUID> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, UUID uuid) throws IOException {
        String obj;
        if (uuid == null) {
            obj = null;
        } else {
            obj = uuid.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final UUID A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return UUID.fromString(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}

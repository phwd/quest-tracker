package X;

import java.io.IOException;
import java.util.UUID;

/* renamed from: X.0WP  reason: invalid class name */
public class AnonymousClass0WP extends AnonymousClass0Bd<UUID> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, UUID uuid) throws IOException {
        String uuid2;
        UUID uuid3 = uuid;
        if (uuid3 == null) {
            uuid2 = null;
        } else {
            uuid2 = uuid3.toString();
        }
        r2.A0E(uuid2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final UUID A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return UUID.fromString(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}

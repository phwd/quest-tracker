package X;

import com.facebook.msys.mci.CQLResultSet;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.26N  reason: invalid class name */
public class AnonymousClass26N implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass26O A00;

    public AnonymousClass26N(AnonymousClass26O r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        AnonymousClass294 r0;
        if (map != null) {
            CQLResultSet cQLResultSet = (CQLResultSet) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrParticipantListResultSetUserInfoKey"));
            AnonymousClass1Zb r2 = this.A00.A02;
            if (cQLResultSet == null) {
                r0 = null;
            } else {
                r0 = new AnonymousClass294(cQLResultSet);
            }
            r2.A03(new C149126j(r0));
            return;
        }
        throw null;
    }
}

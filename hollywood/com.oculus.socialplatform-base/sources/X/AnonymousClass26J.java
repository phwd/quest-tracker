package X;

import com.facebook.msys.mci.CQLResultSet;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.26J  reason: invalid class name */
public class AnonymousClass26J implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass26I A00;

    public AnonymousClass26J(AnonymousClass26I r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        AnonymousClass298 r0;
        if (map != null) {
            CQLResultSet cQLResultSet = (CQLResultSet) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrThreadListResultSetUserInfoKey"));
            AnonymousClass1Zb r2 = this.A00.A02;
            if (cQLResultSet == null) {
                r0 = null;
            } else {
                r0 = new AnonymousClass298(cQLResultSet);
            }
            r2.A03(new C149126j(r0));
            return;
        }
        throw null;
    }
}

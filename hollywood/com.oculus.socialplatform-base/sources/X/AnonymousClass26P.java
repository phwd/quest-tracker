package X;

import com.facebook.msys.mci.CQLResultSet;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.26P  reason: invalid class name */
public class AnonymousClass26P implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass26D A00;

    public AnonymousClass26P(AnonymousClass26D r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(AnonymousClass269.A00("MCAMailboxMessengerVrContactListUserInfoKey"));
            if (obj != null) {
                this.A00.A02.A03(new AnonymousClass293((CQLResultSet) obj));
                return;
            }
            throw null;
        }
        throw null;
    }
}

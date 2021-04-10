package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1a9  reason: invalid class name */
public class AnonymousClass1a9 implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass1a0 A00;

    public AnonymousClass1a9(AnonymousClass1a0 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxOTIDUserInfoKey"));
            if (obj != null) {
                this.A00.A02.A03(obj);
                return;
            }
            throw null;
        }
        throw null;
    }
}

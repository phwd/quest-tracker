package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1a8  reason: invalid class name */
public class AnonymousClass1a8 implements AnonymousClass1ZW {
    public final /* synthetic */ C06471Zz A00;

    public AnonymousClass1a8(C06471Zz r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxOTIDUserInfoKey"));
            if (obj != null) {
                this.A00.A03.A03(obj);
                return;
            }
            throw null;
        }
        throw null;
    }
}

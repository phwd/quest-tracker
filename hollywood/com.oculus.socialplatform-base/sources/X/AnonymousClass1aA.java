package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1aA  reason: invalid class name */
public class AnonymousClass1aA implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass1a1 A00;

    public AnonymousClass1aA(AnonymousClass1a1 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxThreadKeyUserInfoKey"));
            if (obj != null) {
                this.A00.A01.A03(obj);
                return;
            }
            throw null;
        }
        throw null;
    }
}

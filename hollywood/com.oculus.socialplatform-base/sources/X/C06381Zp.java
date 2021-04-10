package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1Zp  reason: invalid class name and case insensitive filesystem */
public class C06381Zp implements AnonymousClass1ZW {
    public final /* synthetic */ C06391Zq A00;

    public C06381Zp(C06391Zq r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxSetMailboxSyncParamsUserInfoKey"));
            if (obj != null) {
                this.A00.A01.A03(Boolean.valueOf(((Boolean) obj).booleanValue()));
                return;
            }
            throw null;
        }
        throw null;
    }
}

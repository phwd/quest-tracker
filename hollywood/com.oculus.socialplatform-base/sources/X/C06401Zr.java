package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1Zr  reason: invalid class name and case insensitive filesystem */
public class C06401Zr implements AnonymousClass1ZW {
    public final /* synthetic */ C06411Zs A00;

    public C06401Zr(C06411Zs r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxSetContactSyncParamsUserInfoKey"));
            if (obj != null) {
                this.A00.A01.A03(Boolean.valueOf(((Boolean) obj).booleanValue()));
                return;
            }
            throw null;
        }
        throw null;
    }
}

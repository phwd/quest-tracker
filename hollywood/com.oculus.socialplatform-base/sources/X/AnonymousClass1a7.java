package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1a7  reason: invalid class name */
public class AnonymousClass1a7 implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass1a3 A00;

    public AnonymousClass1a7(AnonymousClass1a3 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(C06461Zy.A00("MCAMailboxOperationSuccessUserInfoKey"));
            if (obj != null) {
                this.A00.A01.A03(Boolean.valueOf(((Boolean) obj).booleanValue()));
                return;
            }
            throw null;
        }
        throw null;
    }
}

package X;

import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1ZU  reason: invalid class name */
public class AnonymousClass1ZU implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass1ZS A00;

    public AnonymousClass1ZU(AnonymousClass1ZS r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        if (map != null) {
            Object obj = map.get(AnonymousClass1ZT.A00("MCAMailboxExperimentSetExperimentSyncParamsSuccessUserInfoKey"));
            if (obj != null) {
                this.A00.A01.A03(Boolean.valueOf(((Boolean) obj).booleanValue()));
                return;
            }
            throw null;
        }
        throw null;
    }
}

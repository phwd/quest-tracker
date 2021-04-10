package oculus.internal.license.store;

import java.security.PublicKey;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$DcjOpslNXivKahO4QPhY2mMPmok  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DcjOpslNXivKahO4QPhY2mMPmok implements Function {
    public static final /* synthetic */ $$Lambda$DcjOpslNXivKahO4QPhY2mMPmok INSTANCE = new $$Lambda$DcjOpslNXivKahO4QPhY2mMPmok();

    private /* synthetic */ $$Lambda$DcjOpslNXivKahO4QPhY2mMPmok() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((PublicKey) obj).getEncoded();
    }
}

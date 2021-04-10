package oculus.internal.license.store;

import java.security.cert.X509Certificate;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI implements Function {
    public static final /* synthetic */ $$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI INSTANCE = new $$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI();

    private /* synthetic */ $$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((X509Certificate) obj).getPublicKey();
    }
}

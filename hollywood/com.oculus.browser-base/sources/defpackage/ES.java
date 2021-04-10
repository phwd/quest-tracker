package defpackage;

import android.content.Context;
import java.util.Objects;

/* renamed from: ES  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ES extends AbstractC5765yS {
    public final /* synthetic */ KS b;

    public ES(KS ks) {
        this.b = ks;
    }

    @Override // defpackage.AbstractC5765yS
    public AbstractComponentCallbacksC3550lS a(ClassLoader classLoader, String str) {
        C3721mS mSVar = this.b.n;
        Context context = mSVar.G;
        Objects.requireNonNull(mSVar);
        return AbstractComponentCallbacksC3550lS.T(context, str, null);
    }
}

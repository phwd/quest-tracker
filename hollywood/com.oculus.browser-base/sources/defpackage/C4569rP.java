package defpackage;

import android.os.Bundle;
import java.util.Map;

/* renamed from: rP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4569rP extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f11199a;

    public C4569rP(Bundle bundle) {
        this.f11199a = bundle;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Bundle bundle = this.f11199a;
        int i = RunnableC0165Cr.F;
        Map c = ((AbstractC5249vP) obj).c();
        if (c != null) {
            for (Map.Entry entry : c.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }
}

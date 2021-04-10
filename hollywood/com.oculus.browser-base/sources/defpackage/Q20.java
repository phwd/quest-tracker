package defpackage;

import android.content.Intent;
import java.util.Objects;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Q20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q20 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final S20 f8734a;
    public final Intent b;

    public Q20(S20 s20, Intent intent) {
        this.f8734a = s20;
        this.b = intent;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        S20 s20 = this.f8734a;
        Intent intent = this.b;
        LoadUrlParams loadUrlParams = (LoadUrlParams) obj;
        Objects.requireNonNull(s20);
        s20.v(loadUrlParams.f10938a, null, loadUrlParams.f, 0, 0, false, false, null, intent);
    }
}

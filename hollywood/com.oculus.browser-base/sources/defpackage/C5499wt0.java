package defpackage;

import android.view.View;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: wt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5499wt0 extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        new B61(false).b(new LoadUrlParams("https://myactivity.google.com/myactivity/?utm_source=chrome_n", 0), 2, null);
    }
}

package defpackage;

import android.view.View;
import org.chromium.chrome.browser.firstrun.DataReductionProxyFirstRunFragment;

/* renamed from: RC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RC extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final DataReductionProxyFirstRunFragment f8816a;

    public RC(DataReductionProxyFirstRunFragment dataReductionProxyFirstRunFragment) {
        this.f8816a = dataReductionProxyFirstRunFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f8816a.e1();
    }
}

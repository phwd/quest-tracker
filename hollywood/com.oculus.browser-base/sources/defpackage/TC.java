package defpackage;

import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.firstrun.DataReductionProxyFirstRunFragment;

/* renamed from: TC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TC implements View.OnClickListener {
    public final /* synthetic */ DataReductionProxyFirstRunFragment F;

    public TC(DataReductionProxyFirstRunFragment dataReductionProxyFirstRunFragment) {
        this.F = dataReductionProxyFirstRunFragment;
    }

    public void onClick(View view) {
        DataReductionProxyFirstRunFragment dataReductionProxyFirstRunFragment = this.F;
        Objects.requireNonNull(dataReductionProxyFirstRunFragment);
        TQ.a(dataReductionProxyFirstRunFragment).r();
    }
}

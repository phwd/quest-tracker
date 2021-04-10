package defpackage;

import android.view.View;
import org.chromium.chrome.browser.datareduction.settings.DataReductionSiteBreakdownView;

/* renamed from: ZC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZC implements View.OnClickListener {
    public final /* synthetic */ DataReductionSiteBreakdownView F;

    public ZC(DataReductionSiteBreakdownView dataReductionSiteBreakdownView) {
        this.F = dataReductionSiteBreakdownView;
    }

    public void onClick(View view) {
        UC.a(26);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView = this.F;
        dataReductionSiteBreakdownView.F += 10;
        dataReductionSiteBreakdownView.d();
    }
}

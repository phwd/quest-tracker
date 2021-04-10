package defpackage;

import android.view.View;
import com.oculus.browser.R;
import java.util.Collections;
import org.chromium.chrome.browser.datareduction.settings.DataReductionSiteBreakdownView;

/* renamed from: WC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WC implements View.OnClickListener {
    public final /* synthetic */ DataReductionSiteBreakdownView F;

    public WC(DataReductionSiteBreakdownView dataReductionSiteBreakdownView) {
        this.F = dataReductionSiteBreakdownView;
    }

    public void onClick(View view) {
        UC.a(27);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView = this.F;
        dataReductionSiteBreakdownView.c(dataReductionSiteBreakdownView.K);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView2 = this.F;
        dataReductionSiteBreakdownView2.c(dataReductionSiteBreakdownView2.f10650J);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView3 = this.F;
        dataReductionSiteBreakdownView3.b(dataReductionSiteBreakdownView3.I);
        Collections.sort(this.F.L, new C1976cD(null));
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView4 = this.F;
        dataReductionSiteBreakdownView4.H.setContentDescription(dataReductionSiteBreakdownView4.getContext().getString(R.string.f50330_resource_name_obfuscated_RES_2131952350));
        this.F.d();
    }
}

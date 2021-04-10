package defpackage;

import android.view.View;
import com.oculus.browser.R;
import java.util.Collections;
import org.chromium.chrome.browser.datareduction.settings.DataReductionSiteBreakdownView;

/* renamed from: XC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XC implements View.OnClickListener {
    public final /* synthetic */ DataReductionSiteBreakdownView F;

    public XC(DataReductionSiteBreakdownView dataReductionSiteBreakdownView) {
        this.F = dataReductionSiteBreakdownView;
    }

    public void onClick(View view) {
        UC.a(25);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView = this.F;
        dataReductionSiteBreakdownView.c(dataReductionSiteBreakdownView.I);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView2 = this.F;
        dataReductionSiteBreakdownView2.c(dataReductionSiteBreakdownView2.K);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView3 = this.F;
        dataReductionSiteBreakdownView3.b(dataReductionSiteBreakdownView3.f10650J);
        Collections.sort(this.F.L, new C1805bD(null));
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView4 = this.F;
        dataReductionSiteBreakdownView4.H.setContentDescription(dataReductionSiteBreakdownView4.getContext().getString(R.string.f50320_resource_name_obfuscated_RES_2131952349));
        this.F.d();
    }
}

package defpackage;

import android.view.View;
import com.oculus.browser.R;
import java.util.Collections;
import org.chromium.chrome.browser.datareduction.settings.DataReductionSiteBreakdownView;

/* renamed from: YC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YC implements View.OnClickListener {
    public final /* synthetic */ DataReductionSiteBreakdownView F;

    public YC(DataReductionSiteBreakdownView dataReductionSiteBreakdownView) {
        this.F = dataReductionSiteBreakdownView;
    }

    public void onClick(View view) {
        UC.a(24);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView = this.F;
        dataReductionSiteBreakdownView.c(dataReductionSiteBreakdownView.I);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView2 = this.F;
        dataReductionSiteBreakdownView2.c(dataReductionSiteBreakdownView2.f10650J);
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView3 = this.F;
        dataReductionSiteBreakdownView3.b(dataReductionSiteBreakdownView3.K);
        Collections.sort(this.F.L, new C1625aD(null));
        DataReductionSiteBreakdownView dataReductionSiteBreakdownView4 = this.F;
        dataReductionSiteBreakdownView4.H.setContentDescription(dataReductionSiteBreakdownView4.getContext().getString(R.string.f50310_resource_name_obfuscated_RES_2131952348));
        this.F.d();
    }
}

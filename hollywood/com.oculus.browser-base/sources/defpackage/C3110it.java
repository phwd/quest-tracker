package defpackage;

import J.N;
import android.os.Handler;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.infobars.InfoBar;

/* renamed from: it  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3110it implements AbstractC2282e10 {
    public AbstractC0124Ca1 F;
    public Handler G;
    public Tab H;

    public final String a() {
        AbstractC1575Zv e = AbstractC1575Zv.e();
        if (e.g("survey_override_site_id")) {
            return e.f("survey_override_site_id");
        }
        return N.MOVY9QtZ("ChromeSurvey", "site-id");
    }

    @Override // defpackage.AbstractC2282e10
    public void b(int i) {
    }

    public final void c() {
        Tab tab = this.H;
        if (tab != null) {
            W w = C3649m10.F;
            C3649m10 m10 = (C3649m10) tab.M().c(C3649m10.class);
            if (m10 != null) {
                m10.K.c(this);
            }
            this.G.removeCallbacksAndMessages(null);
            NU0.f8549a.o("chrome_home_survey_info_bar_displayed", System.currentTimeMillis());
            this.H = null;
        }
    }

    @Override // defpackage.AbstractC2282e10
    public void d(C10 c10) {
        this.G.removeCallbacksAndMessages(null);
        if (c10 != null && ((InfoBar) c10).h() == 78) {
            this.G.postDelayed(new RunnableC2769gt(this), 5000);
        }
    }

    public final void e(int i) {
        AbstractC3364kK0.g("Android.Survey.SurveyFilteringResults", i, 8);
    }
}

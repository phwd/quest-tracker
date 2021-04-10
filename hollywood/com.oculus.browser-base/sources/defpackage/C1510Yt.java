package defpackage;

import J.N;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.chrome.browser.infobar.SyncErrorInfoBar;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Yt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1510Yt extends AbstractC0855Oa1 {
    public final /* synthetic */ AbstractActivityC2601fu c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1510Yt(AbstractActivityC2601fu fuVar, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = fuVar;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        N(false);
    }

    @Override // defpackage.AbstractC5783ya1
    public void D(List list, boolean z) {
        if (z) {
            AbstractC3364kK0.g("Android.NTP.Impression", 1, 2);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        N(true);
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        N(false);
    }

    public final void N(boolean z) {
        if (((AbstractC0246Ea1) this.c.P()).p() == 0) {
            if (QX.i()) {
                this.c.finish();
            } else if (z) {
                AbstractC3364kK0.g("Android.NTP.Impression", 1, 2);
            }
        }
        if (AbstractC4772sd1.b() && !((AbstractC3838n70) this.c.G1).C()) {
            if (((AbstractC0246Ea1) this.c.P()).p() == 0 || (!((AbstractC0246Ea1) this.c.P()).r() && ((AbstractC0246Ea1) this.c.P()).l(false).getCount() == 0)) {
                this.c.finish();
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        boolean z = false;
        if (i == 5 && !C5052uE.b()) {
            C1184Ti1.a(this.c, R.string.f56700_resource_name_obfuscated_RES_2131952987, 0).b.show();
        }
        WebContents l = tab.l();
        int i3 = SyncErrorInfoBar.f10685J;
        if (l != null) {
            int k = SyncErrorInfoBar.k();
            if (System.currentTimeMillis() - AbstractC3983nz.f10523a.getLong("sync_error_infobar_shown_shown_at_time", 0) > SyncErrorInfoBar.I) {
                z = true;
            }
            if (z && k != -1) {
                N.MWmaDLti(l);
            }
        }
    }
}

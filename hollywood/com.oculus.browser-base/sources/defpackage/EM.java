package defpackage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: EM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EM {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC4448qj f7956a;
    public final /* synthetic */ FM b;

    public EM(FM fm, AbstractC4448qj qjVar) {
        this.b = fm;
        this.f7956a = qjVar;
    }

    public XO a(boolean z, boolean z2) {
        FM fm = this.b;
        boolean z3 = fm.d;
        AbstractC4448qj qjVar = this.f7956a;
        if (fm.e == null) {
            fm.e = new GM(fm.c);
        }
        Profile b2 = Profile.b();
        SectionHeaderView sectionHeaderView = z3 ? (SectionHeaderView) LayoutInflater.from(fm.f8012a).inflate(R.layout.f39880_resource_name_obfuscated_RES_2131624297, (ViewGroup) null, false) : null;
        C4399qP qPVar = new C4399qP();
        ChromeActivity chromeActivity = fm.f8012a;
        View$OnClickListenerC5098uY0 U = chromeActivity.U();
        fm.f8012a.P();
        XO xo = new XO(chromeActivity, U, fm.f8012a.W0, null, sectionHeaderView, qPVar, z, fm, fm.e, b2, z2, qjVar);
        xo.o.setId(R.id.start_surface_explore_view);
        return xo;
    }
}

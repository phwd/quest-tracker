package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.infobar.DownloadProgressInfoBar;

/* renamed from: JH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JH implements DownloadProgressInfoBar.Client {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ KH f8281a;

    public JH(KH kh, HH hh) {
        this.f8281a = kh;
    }

    @Override // org.chromium.chrome.browser.infobar.DownloadProgressInfoBar.Client
    public void a(boolean z) {
        if (z) {
            KH kh = this.f8281a;
            Objects.requireNonNull(kh);
            AbstractC3535lK0.a("Android.Download.InfoBar.CloseButtonClicked");
            AbstractC3364kK0.g("Android.Download.InfoBar.CloseButtonClicked", kh.N, 4);
            KH kh2 = this.f8281a;
            if (!(kh2.j().f8213a == 0 || kh2.i() == null)) {
                ChromeActivity h = KH.h();
                if (((h == null || !(h instanceof AbstractActivityC2601fu)) ? null : (AbstractActivityC2601fu) h) != null) {
                    ChromeActivity h2 = KH.h();
                    Oj1 oj1 = ((C5285ve1) ((h2 == null || !(h2 instanceof AbstractActivityC2601fu)) ? null : (AbstractActivityC2601fu) h2).b1).I0;
                    Vr1 vr1 = oj1.O;
                    Resources resources = oj1.G.getResources();
                    vr1.a(new XY("IPH_DownloadInfoBarDownloadContinuing", resources.getString(R.string.f53250_resource_name_obfuscated_RES_2131952642), resources.getString(R.string.f53250_resource_name_obfuscated_RES_2131952642), true, false, true, oj1.L, new Fj1(oj1), new Ej1(oj1), new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
                }
            }
            this.f8281a.f(null, false, true, false);
        }
    }
}

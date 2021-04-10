package defpackage;

import J.N;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;

/* renamed from: Nj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Nj1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public long f8572a;
    public final /* synthetic */ Oj1 b;

    public Nj1(Oj1 oj1) {
        this.b = oj1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        OfflinePageBridge a2;
        if (!tab.p()) {
            long a3 = DataReductionProxySettings.d().a() - this.f8572a;
            Tm1 a4 = Um1.a(Profile.a(tab.l()));
            int i = (a3 > 0 ? 1 : (a3 == 0 ? 0 : -1));
            if (i > 0) {
                a4.notifyEvent("data_saved_page_load");
            }
            if (AbstractC5566xF0.a(tab)) {
                a4.notifyEvent("preview_page_load");
            }
            if (tab.isUserInteractable()) {
                Oj1 oj1 = this.b;
                Vr1 vr1 = oj1.O;
                Resources resources = oj1.G.getResources();
                vr1.a(new XY("IPH_DataSaverDetail", resources.getString(R.string.f53210_resource_name_obfuscated_RES_2131952638), resources.getString(R.string.f53200_resource_name_obfuscated_RES_2131952637), true, false, true, oj1.L, new Jj1(oj1), new Ij1(oj1), new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
                if (i > 0) {
                    Oj1 oj12 = this.b;
                    Activity activity = oj12.G;
                    DataReductionProxySettings d = DataReductionProxySettings.d();
                    VC vc = new VC(activity, N.Mp3ZSFr_(d.c, d));
                    if (vc.c != null) {
                        Kj1 kj1 = new Kj1(oj12, vc);
                        Vr1 vr12 = oj12.O;
                        Resources resources2 = oj12.G.getResources();
                        String str = vc.c;
                        vr12.a(new XY("IPH_DataSaverMilestonePromo", str == null ? resources2.getString(0) : str, str == null ? resources2.getString(0) : str, true, false, true, oj12.L, kj1, new Lj1(oj12), new Rect(0, 0, 0, resources2.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
                    }
                }
                if (AbstractC5566xF0.a(tab)) {
                    Oj1 oj13 = this.b;
                    Vr1 vr13 = oj13.O;
                    Resources resources3 = oj13.G.getResources();
                    View view = oj13.M;
                    Runnable runnable = ZY.f9350a;
                    vr13.a(new XY("IPH_PreviewsOmniboxUI", resources3.getString(R.string.f53470_resource_name_obfuscated_RES_2131952664), resources3.getString(R.string.f53460_resource_name_obfuscated_RES_2131952663), true, false, true, view, runnable, runnable, new Rect(0, 0, 0, resources3.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
                }
            }
            this.b.g(tab, "IPH_DownloadPage");
            Oj1 oj14 = this.b;
            Objects.requireNonNull(oj14);
            if (AbstractC5652xn1.a(tab, false) && N.M8WoKfWJ(tab.l())) {
                Vr1 vr14 = oj14.O;
                Resources resources4 = oj14.G.getResources();
                vr14.a(new XY("IPH_TranslateMenuButton", resources4.getString(R.string.f53560_resource_name_obfuscated_RES_2131952673), resources4.getString(R.string.f53550_resource_name_obfuscated_RES_2131952672), true, false, true, oj14.L, new Bj1(oj14), new Aj1(oj14), new Rect(0, 0, 0, resources4.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
            }
        } else if (DeviceFormFactor.b(this.b.H) || (a2 = OfflinePageBridge.a(Profile.a(tab.l()))) == null) {
        } else {
            if (N.Mvkx0jqI(a2.f10718a, a2, tab.l())) {
                Um1.a(Profile.a(tab.l())).notifyEvent("user_has_seen_dino");
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        this.f8572a = DataReductionProxySettings.d().a();
    }
}

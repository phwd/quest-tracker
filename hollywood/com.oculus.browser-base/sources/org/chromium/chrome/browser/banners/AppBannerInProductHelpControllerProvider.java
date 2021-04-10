package org.chromium.chrome.browser.banners;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppBannerInProductHelpControllerProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final Ip1 f10617a = new Ip1(C5541x7.class);
    public static WT b;

    public static String showInProductHelp(WebContents webContents) {
        Tm1 tm1 = (Tm1) b.apply(Profile.a(webContents));
        if (!tm1.wouldTriggerHelpUI("IPH_PwaInstallAvailable")) {
            StringBuilder i = AbstractC2531fV.i("Trigger state: ");
            i.append(tm1.getTriggerState("IPH_PwaInstallAvailable"));
            return i.toString();
        }
        WindowAndroid I = webContents.I();
        if (I == null) {
            return "No window";
        }
        C5541x7 x7Var = (C5541x7) f10617a.e(I.U);
        if (x7Var == null) {
            return "No controller";
        }
        Resources resources = x7Var.F.getResources();
        Vr1 vr1 = x7Var.f11590J;
        RunnableC5201v7 v7Var = new RunnableC5201v7(x7Var);
        RunnableC5371w7 w7Var = new RunnableC5371w7(x7Var);
        vr1.a(new XY("IPH_PwaInstallAvailable", resources.getString(R.string.f53480_resource_name_obfuscated_RES_2131952665), resources.getString(R.string.f53480_resource_name_obfuscated_RES_2131952665), true, false, true, (View) x7Var.H.get(), w7Var, v7Var, new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        return "";
    }
}

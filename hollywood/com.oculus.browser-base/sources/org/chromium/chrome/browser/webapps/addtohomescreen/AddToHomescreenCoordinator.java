package org.chromium.chrome.browser.webapps.addtohomescreen;

import android.app.Activity;
import android.content.Context;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.banners.AppBannerManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AddToHomescreenCoordinator {

    /* renamed from: a  reason: collision with root package name */
    public Context f10805a;
    public C2746gl0 b;
    public WindowAndroid c;
    public Tab d;

    public AddToHomescreenCoordinator(Tab tab, Context context, WindowAndroid windowAndroid, C2746gl0 gl0) {
        this.f10805a = context;
        this.c = windowAndroid;
        this.b = gl0;
        this.d = tab;
    }

    public static long initMvcAndReturnMediator(Tab tab) {
        Activity activity;
        C2746gl0 l0;
        WindowAndroid i = tab.i();
        if (i == null || (activity = (Activity) i.s0().get()) == null || !(activity instanceof ChromeActivity) || (l0 = ((ChromeActivity) activity).l0()) == null) {
            return 0;
        }
        return new AddToHomescreenCoordinator(tab, activity, i, l0).a().F;
    }

    public final AddToHomescreenMediator a() {
        WebContents webContents = null;
        UH0 uh0 = new UH0(UH0.c(AbstractC5869z3.k), null);
        AddToHomescreenMediator addToHomescreenMediator = new AddToHomescreenMediator(uh0, this.c);
        Tab tab = this.d;
        if (tab != null) {
            webContents = tab.l();
        }
        ZH0.a(uh0, new View$OnClickListenerC5699y3(this.f10805a, this.b, AppBannerManager.a(webContents), addToHomescreenMediator), new C5189v3());
        return addToHomescreenMediator;
    }
}

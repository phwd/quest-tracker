package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.url.GURL;

/* renamed from: Xi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1428Xi1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1550Zi1 f9229a;

    public C1428Xi1(C1550Zi1 zi1) {
        this.f9229a = zi1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        T20 t20;
        C1550Zi1 zi1 = this.f9229a;
        ToggleTabStackButton toggleTabStackButton = zi1.c;
        if (toggleTabStackButton != null && toggleTabStackButton.isShown() && !zi1.e.a() && zi1.g.get() != null && !((Boolean) zi1.g.get()).booleanValue() && (t20 = (T20) zi1.f.get()) != null && !AbstractC4397qO.a("IPH_TabSwitcherButton", "isMainIntentFromLauncher", t20.f8932a) && !AbstractC4397qO.a("IPH_TabSwitcherButton", "intentWithEffect", t20.b)) {
            Vr1 vr1 = zi1.d;
            Resources resources = zi1.b.getResources();
            vr1.a(new XY("IPH_TabSwitcherButton", resources.getString(R.string.f53540_resource_name_obfuscated_RES_2131952671), resources.getString(R.string.f53530_resource_name_obfuscated_RES_2131952670), true, false, true, zi1.c, new RunnableC1367Wi1(zi1), new RunnableC1306Vi1(zi1), new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        }
    }
}

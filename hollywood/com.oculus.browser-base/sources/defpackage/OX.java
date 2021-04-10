package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: OX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OX extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PX f8630a;

    public OX(PX px) {
        this.f8630a = px;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        T20 t20;
        PX px = this.f8630a;
        View view = px.b;
        if (view != null && view.isShown() && !px.d.a() && !AbstractC5154ur1.h(gurl) && !((Boolean) px.h.get()).booleanValue() && px.g.get() != null && !((Boolean) px.g.get()).booleanValue() && (t20 = (T20) px.f.get()) != null && !AbstractC4397qO.a("IPH_NewTabPageHomeButton", "isMainIntentFromLauncher", t20.f8932a) && !AbstractC4397qO.a("IPH_NewTabPageHomeButton", "intentWithEffect", t20.b)) {
            boolean a2 = AbstractC4907tO.a();
            int i = a2 ? R.string.f53430_resource_name_obfuscated_RES_2131952660 : R.string.f53450_resource_name_obfuscated_RES_2131952662;
            int i2 = a2 ? R.string.f53420_resource_name_obfuscated_RES_2131952659 : R.string.f53440_resource_name_obfuscated_RES_2131952661;
            Vr1 vr1 = px.c;
            Resources resources = px.f8696a.getResources();
            View view2 = px.b;
            Runnable runnable = ZY.f9350a;
            vr1.a(new XY("IPH_NewTabPageHomeButton", resources.getString(i), resources.getString(i2), true, true, true, view2, runnable, runnable, new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        }
    }
}

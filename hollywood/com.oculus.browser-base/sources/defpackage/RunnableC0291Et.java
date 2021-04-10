package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: Et  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0291Et implements Runnable {
    public final C5285ve1 F;

    public RunnableC0291Et(C5285ve1 ve1) {
        this.F = ve1;
    }

    public void run() {
        C1647aK0 ak0 = this.F.G0;
        Objects.requireNonNull(ak0);
        if (N.M09VlOh_("ReadLater") && !N.M09VlOh_("TabbedAppOverflowMenuThreeButtonActionbar")) {
            Vr1 vr1 = ak0.f9424a;
            Resources resources = ak0.c.getContext().getResources();
            View view = ak0.c;
            WJ0 wj0 = new WJ0(ak0);
            vr1.a(new XY("IPH_ReadLaterAppMenuBookmarkThisPage", resources.getString(R.string.f59960_resource_name_obfuscated_RES_2131953313), resources.getString(R.string.f59960_resource_name_obfuscated_RES_2131953313), true, false, true, view, new XJ0(ak0), wj0, new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        }
    }
}

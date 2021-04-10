package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.oculus.browser.R;
import org.chromium.chrome.browser.site_settings.CookieControlsServiceBridge;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* renamed from: d00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2109d00 implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public CookieControlsServiceBridge F;
    public final C1322Vq0 G = new C1322Vq0();
    public boolean H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9738J;

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != this.f9738J && compoundButton.getId() == R.id.cookie_controls_card_toggle) {
            N.MIu6BVKt(this.F.f10767a, z);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cookie_controls_card_managed_icon) {
            Bundle bundle = new Bundle();
            bundle.putString("category", QX0.p(8));
            Context context = view.getContext();
            String name = SingleCategorySettings.class.getName();
            Intent l = AbstractC2531fV.l(context, XS0.class);
            if (!(context instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            if (name != null) {
                l.putExtra("show_fragment", name);
            }
            l.putExtra("show_fragment_args", bundle);
            U20.q(context, l);
        }
    }
}

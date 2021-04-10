package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;

/* renamed from: wp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5486wp implements View.OnClickListener {
    public final ChosenObjectSettings F;
    public final String G;

    public View$OnClickListenerC5486wp(ChosenObjectSettings chosenObjectSettings, String str) {
        this.F = chosenObjectSettings;
        this.G = str;
    }

    public void onClick(View view) {
        this.F.m1(this.G);
    }
}

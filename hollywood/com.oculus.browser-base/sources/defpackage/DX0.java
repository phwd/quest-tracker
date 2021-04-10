package defpackage;

import android.content.DialogInterface;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: DX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DX0 implements DialogInterface.OnClickListener {
    public final SingleWebsiteSettings F;

    public DX0(SingleWebsiteSettings singleWebsiteSettings) {
        this.F = singleWebsiteSettings;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.v1();
    }
}

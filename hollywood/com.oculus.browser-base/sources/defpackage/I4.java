package defpackage;

import android.content.DialogInterface;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;

/* renamed from: I4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I4 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AllSiteSettings F;

    public I4(AllSiteSettings allSiteSettings) {
        this.F = allSiteSettings;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AllSiteSettings allSiteSettings = this.F;
        if (allSiteSettings.N0 != null) {
            AbstractC3535lK0.a("MobileSettingsStorageClearAll");
            int[] iArr = {allSiteSettings.N0.size()};
            for (int i2 = 0; i2 < allSiteSettings.N0.size(); i2++) {
                ((Fy1) allSiteSettings.N0.get(i2)).B0.a(allSiteSettings.G0.b, new G4(allSiteSettings, iArr));
            }
        }
    }
}

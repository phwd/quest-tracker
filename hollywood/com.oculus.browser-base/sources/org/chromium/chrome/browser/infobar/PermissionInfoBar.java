package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionInfoBar extends ConfirmInfoBar implements AbstractC3834n6 {
    public PermissionInfoBar(WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str3, null, str6, str7);
    }

    public static PermissionInfoBar create(WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        return new PermissionInfoBar(windowAndroid, iArr, i, str, str2, str3, str4, str5, str6, str7, z);
    }

    @Override // defpackage.C10, org.chromium.components.infobars.InfoBar
    public boolean d() {
        return this.G;
    }

    @Override // defpackage.AbstractC3834n6
    public void e() {
        j();
    }

    @Override // defpackage.AbstractC3834n6
    public void g() {
        i(1);
    }
}

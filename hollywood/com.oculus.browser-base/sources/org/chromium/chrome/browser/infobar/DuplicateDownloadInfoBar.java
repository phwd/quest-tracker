package org.chromium.chrome.browser.infobar;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DuplicateDownloadInfoBar extends ConfirmInfoBar {
    public DuplicateDownloadInfoBar(Context context, String str, boolean z, String str2, boolean z2, boolean z3) {
        super(R.drawable.f33280_resource_name_obfuscated_RES_2131231368, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, null, null, context.getString(R.string.f51820_resource_name_obfuscated_RES_2131952499), context.getString(R.string.f48470_resource_name_obfuscated_RES_2131952164));
    }

    public static InfoBar createInfoBar(String str, boolean z, String str2, boolean z2, boolean z3) {
        return new DuplicateDownloadInfoBar(ContextUtils.getApplicationContext(), str, z, str2, z2, z3);
    }
}

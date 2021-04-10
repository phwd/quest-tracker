package org.chromium.components.subresource_filter;

import android.widget.CompoundButton;
import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AdsBlockedInfoBar extends ConfirmInfoBar implements CompoundButton.OnCheckedChangeListener {
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10900J;

    public AdsBlockedInfoBar(int i, String str, String str2, String str3, String str4, String str5) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, null, null, null);
        this.I = str2;
        this.f10900J = str3;
    }

    public static InfoBar show(int i, String str, String str2, String str3, String str4, String str5) {
        return new AdsBlockedInfoBar(i, str, str2, str3, str4, str5);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        throw null;
    }
}

package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.components.infobars.ConfirmInfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillCreditCardFillingInfoBar extends ConfirmInfoBar {
    public final List I = new ArrayList();

    public AutofillCreditCardFillingInfoBar(int i, Bitmap bitmap, String str, String str2, String str3) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, bitmap, str, null, str2, str3);
    }

    public static AutofillCreditCardFillingInfoBar create(long j, int i, Bitmap bitmap, String str, String str2, String str3) {
        return new AutofillCreditCardFillingInfoBar(i, bitmap, str, str2, str3);
    }

    public final void addDetail(int i, String str, String str2) {
        this.I.add(new C3944nm(i, str, str2));
    }
}

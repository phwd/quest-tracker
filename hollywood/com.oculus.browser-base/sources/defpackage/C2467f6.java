package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* renamed from: f6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2467f6 extends Preference {
    public C2467f6(Context context) {
        super(context, null);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        int dimensionPixelSize = this.F.getResources().getDimensionPixelSize(R.dimen.f23940_resource_name_obfuscated_RES_2131166013);
        View x = tf0.x(16908294);
        ViewGroup.LayoutParams layoutParams = x.getLayoutParams();
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        x.setLayoutParams(layoutParams);
    }
}

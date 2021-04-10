package defpackage;

import android.content.res.Resources;
import com.oculus.browser.R;

/* renamed from: mj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3765mj0 implements Q31 {
    public final Resources F;

    public C3765mj0(Resources resources) {
        this.F = resources;
    }

    @Override // defpackage.Q31
    public Object get() {
        Resources resources = this.F;
        return Float.valueOf(Math.min((float) resources.getDimensionPixelSize(R.dimen.f20790_resource_name_obfuscated_RES_2131165698), ((float) resources.getDisplayMetrics().widthPixels) / 2.0f));
    }
}

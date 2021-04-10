package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: Ha0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0428Ha0 extends QX0 {
    public C0428Ha0(BrowserContextHandle browserContextHandle) {
        super(browserContextHandle, 9, "android.permission.ACCESS_COARSE_LOCATION");
    }

    @Override // defpackage.QX0
    public boolean h() {
        return C1159Ta0.a().e();
    }

    @Override // defpackage.QX0
    public Intent k(Context context) {
        if (h()) {
            return null;
        }
        return C1159Ta0.a().b();
    }

    @Override // defpackage.QX0
    public String l(Context context) {
        Resources resources = context.getResources();
        if (g(context)) {
            return resources.getString(R.string.f46820_resource_name_obfuscated_RES_2131951999);
        }
        return resources.getString(R.string.f46810_resource_name_obfuscated_RES_2131951998);
    }

    @Override // defpackage.QX0
    public boolean q(Context context) {
        if (g(context) && h()) {
            return false;
        }
        if (N.MJSt3Ocq(this.f8767a, 5) || N.MB23OvTV(this.f8767a, 5)) {
            return true;
        }
        return false;
    }
}

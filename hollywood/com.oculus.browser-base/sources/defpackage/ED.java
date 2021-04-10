package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;

/* renamed from: ED  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ED extends AbstractC0500Ie {
    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Context applicationContext = ContextUtils.getApplicationContext();
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(HD.a(applicationContext, AbstractC0456Hk.f8178a.b));
        PackageManager packageManager = applicationContext.getPackageManager();
        ResolveInfo e = PackageManagerUtils.e();
        NU0.f8549a.m("applink.chrome_default_browser", (e == null || e.match == 0 || !TextUtils.equals(applicationContext.getPackageName(), e.activityInfo.packageName)) ? false : true);
        String str = null;
        if (!(e == null || e.match == 0 || e.loadLabel(packageManager) == null)) {
            str = e.loadLabel(packageManager).toString();
        }
        arrayList.add(HD.a(applicationContext, str));
        return arrayList;
    }
}

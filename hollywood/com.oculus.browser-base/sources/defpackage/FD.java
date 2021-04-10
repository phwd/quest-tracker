package defpackage;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;

/* renamed from: FD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FD extends AbstractC2032cb {
    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Context applicationContext = ContextUtils.getApplicationContext();
        GD gd = new GD(null);
        ResolveInfo e = PackageManagerUtils.e();
        if (!(e == null || e.match == 0)) {
            gd.d = true;
            gd.b = TextUtils.equals(applicationContext.getPackageName(), e.activityInfo.applicationInfo.packageName);
            gd.c = (e.activityInfo.applicationInfo.flags & 1) != 0;
        }
        HashSet hashSet = new HashSet();
        List<ResolveInfo> b = PackageManagerUtils.b();
        if (b != null) {
            for (ResolveInfo resolveInfo : b) {
                if (hashSet.add(resolveInfo.activityInfo.applicationInfo.packageName)) {
                    if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                        if (TextUtils.equals(applicationContext.getPackageName(), resolveInfo.activityInfo.applicationInfo.packageName)) {
                            gd.f8075a = true;
                        }
                        gd.f++;
                    }
                }
            }
        }
        gd.e = hashSet.size();
        return gd;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String str;
        int i;
        GD gd = (GD) obj;
        if (gd != null) {
            AbstractC3364kK0.c(gd.f8075a ? "Mobile.DefaultBrowser.SystemBrowserCount.ChromeSystem" : "Mobile.DefaultBrowser.SystemBrowserCount.ChromeNotSystem", gd.f);
            if (!gd.d) {
                str = "Mobile.DefaultBrowser.BrowserCount.NoDefault";
            } else {
                str = gd.b ? "Mobile.DefaultBrowser.BrowserCount.ChromeDefault" : "Mobile.DefaultBrowser.BrowserCount.OtherDefault";
            }
            AbstractC3364kK0.c(str, gd.e);
            if (!gd.d) {
                i = 0;
            } else if (gd.b) {
                i = gd.c ? 1 : 2;
            } else {
                i = gd.c ? 3 : 4;
            }
            AbstractC3364kK0.g("Mobile.DefaultBrowser.State", i, 5);
        }
    }
}

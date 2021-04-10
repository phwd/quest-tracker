package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;
import org.chromium.base.ContextUtils;

/* renamed from: NB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NB0 extends AbstractC5079uP {
    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4096);
            if (packageInfo == null || packageInfo.requestedPermissions == null) {
                return null;
            }
            String str = "";
            int i = 0;
            String str2 = str;
            while (true) {
                String[] strArr = packageInfo.requestedPermissions;
                if (i < strArr.length) {
                    int i2 = packageInfo.requestedPermissionsFlags[i];
                    String str3 = strArr[i];
                    if ((i2 & 2) != 0) {
                        if (!TextUtils.isEmpty(str)) {
                            str = str + ", ";
                        }
                        str = str + str3;
                    } else {
                        if (!TextUtils.isEmpty(str2)) {
                            str2 = str2 + ", ";
                        }
                        str2 = str2 + str3;
                    }
                    i++;
                } else {
                    return AbstractC0417Gv.c(Pair.create("Granted Permissions", str), Pair.create("Not Granted or Requested Permissions", str2));
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}

package defpackage;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.policy.EnterpriseInfo;

/* renamed from: rL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4561rL extends AbstractC2032cb {
    public final /* synthetic */ EnterpriseInfo i;

    public C4561rL(EnterpriseInfo enterpriseInfo) {
        this.i = enterpriseInfo;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Context applicationContext = ContextUtils.getApplicationContext();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PackageManager packageManager = applicationContext.getPackageManager();
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) applicationContext.getSystemService("device_policy");
        boolean g = AbstractC1575Zv.e().g("force-device-ownership");
        boolean z = false;
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            if (devicePolicyManager.isProfileOwnerApp(packageInfo.packageName)) {
                z = true;
            }
            if (devicePolicyManager.isDeviceOwnerApp(packageInfo.packageName)) {
                g = true;
            }
            if (z && g) {
                break;
            }
        }
        AbstractC3364kK0.k("EnterpriseCheck.IsRunningOnManagedProfileDuration", SystemClock.elapsedRealtime() - elapsedRealtime);
        return new C4731sL(g, z);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        EnterpriseInfo enterpriseInfo = this.i;
        Objects.requireNonNull(enterpriseInfo);
        Object obj2 = ThreadUtils.f10596a;
        enterpriseInfo.c = (C4731sL) obj;
        EnterpriseInfo enterpriseInfo2 = this.i;
        Objects.requireNonNull(enterpriseInfo2);
        while (enterpriseInfo2.d.size() > 0) {
            ((Callback) enterpriseInfo2.d.remove()).onResult(enterpriseInfo2.c);
        }
    }
}

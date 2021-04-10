package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.service.DownloadTaskScheduler;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.components.download.internal.BatteryStatusListenerAndroid;

/* renamed from: NG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NG extends AbstractC4798sm0 {
    public int f;
    public boolean g;

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        DownloadTaskScheduler.scheduleTask(0, false, false, 0, 300, 600);
        DownloadTaskScheduler.scheduleTask(1, false, false, 0, 43200, 86400);
        if (CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative")) {
            DownloadTaskScheduler.scheduleTask(2, false, false, 0, 300, 86400);
        }
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        boolean z;
        boolean z2 = cf1.b.getBoolean("extra_battery_requires_charging");
        int i = cf1.b.getInt("extra_optimal_battery_percentage");
        int i2 = cf1.b.getInt("extra_task_type");
        this.f = i2;
        if (i2 == 2 || i2 == 3) {
            z = CachedFeatureFlags.isEnabled("ServiceManagerForDownload");
        } else {
            z = CachedFeatureFlags.isEnabled("ServiceManagerForBackgroundPrefetch");
        }
        this.g = z;
        return (z2 || BatteryStatusListenerAndroid.getBatteryPercentage() >= i) ? 0 : 1;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        DownloadManagerService.p().t();
        N.MBBY92FO(this, ProfileKey.a(), this.f, new MG(ne));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return N.MtXApQ3N(this, ProfileKey.a(), cf1.b.getInt("extra_task_type"));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean j() {
        return this.g;
    }
}

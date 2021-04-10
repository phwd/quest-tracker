package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.util.Calendar;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.offlinepages.prefetch.OfflineNotificationBackgroundTask;
import org.chromium.chrome.browser.offlinepages.prefetch.PrefetchedPagesNotifier;

/* renamed from: Gr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0410Gr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final OfflineNotificationBackgroundTask f8116a;

    public C0410Gr0(OfflineNotificationBackgroundTask offlineNotificationBackgroundTask) {
        this.f8116a = offlineNotificationBackgroundTask;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        OfflineNotificationBackgroundTask offlineNotificationBackgroundTask = this.f8116a;
        String str = (String) obj;
        offlineNotificationBackgroundTask.k();
        offlineNotificationBackgroundTask.f.a(false);
        if (!str.isEmpty()) {
            NU0.f8549a.o("prefetch_notification_shown_time", Calendar.getInstance().getTimeInMillis());
            if (N.M09VlOh_("PrefetchNotificationSchedulingIntegration")) {
                Context applicationContext = ContextUtils.getApplicationContext();
                N.McdTTGo3(String.format(applicationContext.getString(R.string.f56530_resource_name_obfuscated_RES_2131952970), applicationContext.getString(R.string.f46950_resource_name_obfuscated_RES_2131952012)), String.format(applicationContext.getString(R.string.f56520_resource_name_obfuscated_RES_2131952969), str));
            } else {
                PrefetchedPagesNotifier.b().d(str);
            }
        }
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 79);
    }
}

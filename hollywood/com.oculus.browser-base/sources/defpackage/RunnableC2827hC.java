package defpackage;

import com.oculus.browser.R;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: hC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2827hC implements Runnable {
    public void run() {
        Set set = CustomTabsConnection.f10648a;
        TraceEvent j0 = TraceEvent.j0("InitializeViewHierarchy");
        try {
            Bw1.a().b(ContextUtils.getApplicationContext(), R.layout.f37660_resource_name_obfuscated_RES_2131624075, R.layout.f37670_resource_name_obfuscated_RES_2131624076);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}

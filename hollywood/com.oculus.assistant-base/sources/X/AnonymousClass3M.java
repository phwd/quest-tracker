package X;

import android.app.Application;
import android.content.Intent;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.os.BugReporter2;
import org.json.JSONObject;

/* renamed from: X.3M  reason: invalid class name */
public final class AnonymousClass3M extends AbstractC1409yv {
    @Override // X.AbstractC1409yv
    public final void A02(AnonymousClass8F r8, JSONObject jSONObject) {
        YA ya;
        AssistantLogger assistantLogger = C00799i.A00;
        assistantLogger.logFulfillment("Starting bug report");
        YP.A00().A03();
        Application A00 = BX.A00();
        BugReporter2 createBugReport = BugReporter2.createBugReport(A00);
        if (createBugReport == null) {
            assistantLogger.logError("Unable to create bug report");
            return;
        }
        Intent launchIntent = createBugReport.getLaunchIntent(A00.getPackageName(), "com.oculus.vrshell");
        J2 A002 = J2.A00();
        synchronized (A002) {
            ya = A002.A00;
            if (ya == null) {
                X8 x8 = A002.A02;
                if (x8 == null) {
                    x8 = new X8(J2.A0D, J2.A0C, J2.A0E);
                    A002.A02 = x8;
                }
                ya = new YA(x8, A002.A08);
                A002.A00 = ya;
            }
        }
        ya.A00(launchIntent, A00);
    }
}

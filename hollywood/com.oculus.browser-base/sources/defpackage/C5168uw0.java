package defpackage;

import J.N;
import com.oculus.browser.PanelService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TimerTask;
import org.chromium.base.JavaExceptionReporter;

/* renamed from: uw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5168uw0 extends TimerTask {
    public long F = System.currentTimeMillis();
    public final /* synthetic */ Thread G;
    public final /* synthetic */ PanelService H;

    public C5168uw0(PanelService panelService, Thread thread) {
        this.H = panelService;
        this.G = thread;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis() - this.F;
        StackTraceElement[] stackTrace = this.G.getStackTrace();
        Throwable th = new Throwable();
        th.setStackTrace(stackTrace);
        AbstractC1220Ua0.f("PanelService", "Watchdog timer hung for " + currentTimeMillis + " ms. UI Thread stack:", th);
        if (currentTimeMillis >= 8000) {
            AbstractC1220Ua0.a("PanelService", "Watchdog timer hung for " + currentTimeMillis + "ms (exceeds MAX_INIT_TIME). Terminating app. UI Thread stack: ", th);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.print("Startup Hang. All thread stacks:\n");
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            if (!allStackTraces.containsKey(this.G)) {
                allStackTraces.put(this.G, stackTrace);
            }
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                Thread key = entry.getKey();
                printWriter.print(key.getId());
                printWriter.print(" (");
                printWriter.print(key.getName());
                printWriter.print("): ");
                th.setStackTrace(entry.getValue());
                AbstractC0754Mh1.f8495a.d(th, printWriter);
            }
            try {
                C5508ww0 ww0 = new C5508ww0(this.H, stringWriter.toString());
                int i = JavaExceptionReporter.f10588a;
                N.MLlibBXh(true, ww0);
                AbstractC1220Ua0.a("PanelService", "ReportJavaException did not crash, exiting now.", new Object[0]);
                System.exit(-1);
            } catch (Throwable th2) {
                AbstractC1220Ua0.a("PanelService", "ReportJavaException did not crash, exiting now.", new Object[0]);
                System.exit(-1);
                throw th2;
            }
        }
    }
}

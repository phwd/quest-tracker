package X;

import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class YO {
    public boolean A00 = true;
    public final QuickPerformanceLogger A01 = A3.A00;
    public final Set A02 = new HashSet();
    public final C1396yX A03 = new C1396yX();

    public final int A00() {
        if (!(this instanceof C1400ye)) {
            return 50790404;
        }
        return 50790402;
    }

    public final void A01() {
        String str = "warm";
        if (this.A00) {
            if (Build.VERSION.SDK_INT >= 24 && SystemClock.uptimeMillis() - Process.getStartUptimeMillis() < TimeUnit.SECONDS.toMillis(10)) {
                str = "cold";
            }
            this.A00 = false;
        }
        this.A01.markerAnnotate(A00(), "start_type", str);
    }

    public final void A02(String str) {
        Set set = this.A02;
        if (YR.A02(set, YQ.INTERACTION_STARTED)) {
            A03(2);
        }
        YR.A00(set, YQ.INTERACTION_STARTED);
        YQ yq = YQ.ALREADY_LOGGED_ATTENTION_SYSTEM;
        YR.A01(set, yq, yq);
        QuickPerformanceLogger quickPerformanceLogger = this.A01;
        int A002 = A00();
        quickPerformanceLogger.markerStart(A002);
        quickPerformanceLogger.markerAnnotate(A002, "assistant_app_version", this.A03.A01());
        C0139Dd.A0H("OculusAssistantAbstractVoiceLatencyLogger", "startMarker %d %s", Integer.valueOf(A002), str);
        quickPerformanceLogger.markerAnnotate(A002, "reason", str);
    }

    public final void A03(short s) {
        int A002 = A00();
        C0139Dd.A0F("OculusAssistantAbstractVoiceLatencyLogger", "endInteraction %d", Integer.valueOf(A002));
        Set set = this.A02;
        YQ yq = YQ.INTERACTION_STARTED;
        if (YR.A02(set, yq)) {
            YR.A01(set, yq);
            this.A01.markerEnd(A002, s);
        }
    }
}

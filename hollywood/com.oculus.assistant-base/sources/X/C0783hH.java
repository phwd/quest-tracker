package X;

import android.os.Build;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: X.hH  reason: case insensitive filesystem */
public final class C0783hH extends AbstractC00889s {
    public final Set A00 = new HashSet();

    public final void A06(String str) {
        Set set = this.A00;
        if (set.isEmpty()) {
            QuickPerformanceLogger quickPerformanceLogger = super.A00;
            int i = this.A02;
            if (quickPerformanceLogger.isMarkerOn(i)) {
                long currentMonotonicTimestamp = super.A00.currentMonotonicTimestamp();
                C0139Dd.A0B("AssistantStartupLatencyLogger", "Ending startup latency QPL measurement");
                super.A00.markerEnd(i, (short) 4, currentMonotonicTimestamp, TimeUnit.MILLISECONDS);
            }
            C0139Dd.A0B("AssistantStartupLatencyLogger", "Beginning startup latency QPL measurement");
            QuickPerformanceLogger quickPerformanceLogger2 = super.A00;
            quickPerformanceLogger2.markerStart(i, 0, quickPerformanceLogger2.currentMonotonicTimestamp(), TimeUnit.MILLISECONDS);
            C1396yX yXVar = AbstractC00889s.A03;
            if (yXVar != null) {
                A03(EnumC00899t.ASSISTANT_APP_VERSION, yXVar.A01());
            }
            String str2 = Build.SERIAL;
            if (str2 != null) {
                A03(EnumC00899t.BUILD_SERIAL, str2);
            }
        }
        if (set.contains(str)) {
            C0139Dd.A0P("AssistantStartupLatencyLogger", "StartupLayer %s has already been started, ignoring", str);
            return;
        }
        set.add(str);
        A05(A01(str, AnonymousClass09.A00));
    }

    public final void A07(String str) {
        Set set = this.A00;
        if (!set.contains(str)) {
            C0139Dd.A0P("AssistantStartupLatencyLogger", "Cannot end a StartupLayer which isn't currently started: %s ", str);
            return;
        }
        A05(A01(str, AnonymousClass09.A01));
        set.remove(str);
        if (set.isEmpty()) {
            long currentMonotonicTimestamp = super.A00.currentMonotonicTimestamp();
            C0139Dd.A0B("AssistantStartupLatencyLogger", "Ending startup latency QPL measurement");
            super.A00.markerEnd(this.A02, (short) 2, currentMonotonicTimestamp, TimeUnit.MILLISECONDS);
        }
    }

    public C0783hH() {
        super(50801291);
    }

    public static EnumC00909u A01(String str, Integer num) {
        int hashCode = str.hashCode();
        if (hashCode != 96801) {
            if (hashCode != 113722) {
                if (hashCode == 3403201 && str.equals("oacr")) {
                    if (num == AnonymousClass09.A00) {
                        return EnumC00909u.OACR_STARTUP_BEGIN;
                    }
                    return EnumC00909u.OACR_STARTUP_END;
                }
            } else if (str.equals("sdk")) {
                if (num == AnonymousClass09.A00) {
                    return EnumC00909u.SDK_STARTUP_BEGIN;
                }
                return EnumC00909u.SDK_STARTUP_END;
            }
        } else if (str.equals(ErrorReportingConstants.APP_NAME_KEY)) {
            if (num == AnonymousClass09.A00) {
                return EnumC00909u.APP_STARTUP_BEGIN;
            }
            return EnumC00909u.APP_STARTUP_END;
        }
        C0139Dd.A0A("AssistantStartupLatencyLogger", "Received an invalid StartupLayer, returning default");
        return EnumC00909u.APP_STARTUP_BEGIN;
    }
}

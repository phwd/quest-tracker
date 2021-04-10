package X;

import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.facebook.quicklog.QuickPerformanceLoggerProvider;

public final class A3 {
    public static final QuickPerformanceLogger A00;

    static {
        hM hMVar = new hM();
        C0914op opVar = new C0914op();
        AwakeTimeSinceBootClock awakeTimeSinceBootClock = AwakeTimeSinceBootClock.INSTANCE;
        C0832jW jWVar = C0832jW.A00;
        C0786hL hLVar = A4.A04;
        C0924oz ozVar = new C0924oz();
        C0919ou ouVar = C0919ou.A00;
        if (ouVar == null) {
            ouVar = new C0919ou();
            C0919ou.A00 = ouVar;
        }
        C0183Ip ip = new C0183Ip(hMVar, opVar, awakeTimeSinceBootClock, jWVar, hLVar, ozVar, ouVar, new C0940pH());
        IP[] ipArr = (IP[]) A4.A06.toArray(new IP[0]);
        ip.A02 = ipArr;
        C0916or orVar = A4.A05;
        ip.A00 = orVar;
        YE ye = new YE(ip.A0A, ip.A06, ip.A04, ip.A05, ip.A03, ip.A08, ip.A07, ipArr, orVar, ip.A01, ip.A09);
        QuickPerformanceLoggerProvider.mQuickPerformanceLogger = ye;
        A00 = ye;
    }
}

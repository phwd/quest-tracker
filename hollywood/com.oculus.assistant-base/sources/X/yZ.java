package X;

import android.app.Application;
import android.os.Build;
import com.facebook.flexiblesampling.SamplingResult;
import com.oculus.assistant.service.analytics.AssistantUploader;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public final class yZ {
    public static yZ A03;
    public w1 A00;
    public C1396yX A01 = new C1396yX();
    public final AnonymousClass6J A02;

    public final C00446t A01(String str, String str2, boolean z) {
        C00446t r4;
        AnonymousClass6H r0 = new AnonymousClass6H(str, str2, EnumC00486y.CLIENT_EVENT, z);
        AnonymousClass6J r6 = this.A02;
        String str3 = r0.A02;
        String str4 = r0.A01;
        EnumC00486y r3 = r0.A00;
        SamplingResult A002 = r6.A0A.A00();
        int i = A002.A00;
        if (i == 0 || SamplingResult.A03.nextInt(i) != 0) {
            r4 = r6.A0B;
        } else {
            r4 = AnonymousClass6J.A00(r6, str3, str4, r3);
            if (A002.A01) {
                r4.A02 = AnonymousClass75.HAS_DOWNLOADED_SAMPLING_POLICY.getTag() | r4.A02;
            }
        }
        if (r4.A05()) {
            r4.A03("app_version", this.A01.A01());
            r4.A02("build_num", Integer.valueOf(C1396yX.A00()));
            r4.A03("device_fingerprint", Build.DISPLAY);
            r4.A03("device_serial_number", Build.SERIAL);
            r4.A03("device_type", Build.HARDWARE);
        }
        return r4;
    }

    public final void A02(C00446t r8) {
        String str;
        String str2;
        boolean z = r8 instanceof C0685fM;
        if (!z) {
            str = r8.A07;
        } else {
            str = "SampledOutEventModule";
        }
        if (z) {
            str2 = "SampledOutEventName";
        } else if (r8.A08) {
            str2 = r8.A06;
        } else {
            throw new IllegalStateException("isSampled was not invoked, how can you have known?");
        }
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str, str2);
        C0847jr A04 = r8.A04();
        for (int i = 0; i < A04.A00; i++) {
            Object A06 = A04.A06(i);
            if (A04.A06(i) instanceof Float) {
                A06 = Double.valueOf((double) ((Number) A06).floatValue());
            } else if (A04.A06(i) == null) {
                continue;
            }
            if (i < 0 || i >= A04.A00) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
            analyticsEvent.setExtra((String) A04.A01.get(i << 1), A06);
        }
        A00().reportEvent(analyticsEvent, false);
    }

    public yZ() {
        Application A002 = BX.A00();
        AnonymousClass6I r3 = new AnonymousClass6I(A002);
        r3.A03 = new yY(this);
        r3.A04 = new C1397ya(0);
        r3.A02 = this.A01;
        r3.A01 = new C1398yc();
        r3.A05 = AssistantUploader.class;
        r3.A00 = new C0669en();
        this.A02 = new AnonymousClass6J(r3);
    }

    public static UnifiedTelemetryLogger A00() {
        if (C0398Vv.A00() < 19) {
            return UnifiedTelemetryLogger.getInstance();
        }
        return UnifiedTelemetryLogger.getInstance(BX.A00());
    }
}

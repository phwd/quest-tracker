package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.tigon.iface.AppNetSessionId;
import com.facebook.tigon.iface.FacebookLoggingRequestInfo;
import com.facebook.tigon.iface.RedirectRequestInfo;
import com.facebook.tigon.iface.RestrictiveLoggingStatus;
import com.facebook.tigon.iface.TigonBackupHostServiceInfo;
import com.facebook.tigon.iface.TigonLigerRequestInfo;
import com.facebook.tigon.iface.TigonSamplingConfigInfo;
import com.facebook.tigon.iface.TigonXProcessTrafficShapingCommunication;
import com.facebook.tigon.iface.TransientAnalyzerTracingInfo;
import com.facebook.tigon.iface.TriggeredLoggingInfo;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0k5  reason: invalid class name and case insensitive filesystem */
public final class C05600k5 {
    public static final C05590k4<?>[] A00;
    public static final C05590k4<AppNetSessionId> A01;
    public static final C05590k4<TigonBackupHostServiceInfo> A02;
    public static final C05590k4<FacebookLoggingRequestInfo> A03;
    public static final C05590k4<TigonLigerRequestInfo> A04;
    public static final C05590k4<RedirectRequestInfo> A05;
    public static final C05590k4<RestrictiveLoggingStatus> A06;
    public static final C05590k4<TigonSamplingConfigInfo> A07;
    public static final C05590k4<TransientAnalyzerTracingInfo> A08;
    public static final C05590k4<TriggeredLoggingInfo> A09;
    public static final C05590k4<TigonXProcessTrafficShapingCommunication> A0A;

    static {
        C05590k4<FacebookLoggingRequestInfo> r0 = new C05590k4<>();
        A03 = r0;
        C05590k4<TigonLigerRequestInfo> r1 = new C05590k4<>();
        A04 = r1;
        C05590k4<RedirectRequestInfo> r2 = new C05590k4<>();
        A05 = r2;
        C05590k4<TigonSamplingConfigInfo> r3 = new C05590k4<>();
        A07 = r3;
        C05590k4<TigonXProcessTrafficShapingCommunication> r4 = new C05590k4<>();
        A0A = r4;
        C05590k4<TigonBackupHostServiceInfo> r5 = new C05590k4<>();
        A02 = r5;
        C05590k4<TransientAnalyzerTracingInfo> r6 = new C05590k4<>();
        A08 = r6;
        C05590k4<TriggeredLoggingInfo> r7 = new C05590k4<>();
        A09 = r7;
        C05590k4<AppNetSessionId> r8 = new C05590k4<>();
        A01 = r8;
        C05590k4<RestrictiveLoggingStatus> r9 = new C05590k4<>();
        A06 = r9;
        A00 = new C05590k4[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }
}

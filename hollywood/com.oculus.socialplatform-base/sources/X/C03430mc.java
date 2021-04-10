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
/* renamed from: X.0mc  reason: invalid class name and case insensitive filesystem */
public final class C03430mc {
    public static final C03420mb<?>[] A00;
    public static final C03420mb<AppNetSessionId> A01;
    public static final C03420mb<TigonBackupHostServiceInfo> A02;
    public static final C03420mb<FacebookLoggingRequestInfo> A03;
    public static final C03420mb<TigonLigerRequestInfo> A04;
    public static final C03420mb<RedirectRequestInfo> A05;
    public static final C03420mb<RestrictiveLoggingStatus> A06;
    public static final C03420mb<TigonSamplingConfigInfo> A07;
    public static final C03420mb<TransientAnalyzerTracingInfo> A08;
    public static final C03420mb<TriggeredLoggingInfo> A09;
    public static final C03420mb<TigonXProcessTrafficShapingCommunication> A0A;

    static {
        C03420mb<FacebookLoggingRequestInfo> r0 = new C03420mb<>();
        A03 = r0;
        C03420mb<TigonLigerRequestInfo> r1 = new C03420mb<>();
        A04 = r1;
        C03420mb<RedirectRequestInfo> r2 = new C03420mb<>();
        A05 = r2;
        C03420mb<TigonSamplingConfigInfo> r3 = new C03420mb<>();
        A07 = r3;
        C03420mb<TigonXProcessTrafficShapingCommunication> r4 = new C03420mb<>();
        A0A = r4;
        C03420mb<TigonBackupHostServiceInfo> r5 = new C03420mb<>();
        A02 = r5;
        C03420mb<TransientAnalyzerTracingInfo> r6 = new C03420mb<>();
        A08 = r6;
        C03420mb<TriggeredLoggingInfo> r7 = new C03420mb<>();
        A09 = r7;
        C03420mb<AppNetSessionId> r8 = new C03420mb<>();
        A01 = r8;
        C03420mb<RestrictiveLoggingStatus> r9 = new C03420mb<>();
        A06 = r9;
        A00 = new C03420mb[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }
}

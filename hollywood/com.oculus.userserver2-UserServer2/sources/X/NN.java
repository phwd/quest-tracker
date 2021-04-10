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
public final class NN {
    public static final C0111Ng<?>[] A00;
    public static final C0111Ng<AppNetSessionId> A01;
    public static final C0111Ng<TigonBackupHostServiceInfo> A02;
    public static final C0111Ng<FacebookLoggingRequestInfo> A03;
    public static final C0111Ng<TigonLigerRequestInfo> A04;
    public static final C0111Ng<RedirectRequestInfo> A05;
    public static final C0111Ng<RestrictiveLoggingStatus> A06;
    public static final C0111Ng<TigonSamplingConfigInfo> A07;
    public static final C0111Ng<TransientAnalyzerTracingInfo> A08;
    public static final C0111Ng<TriggeredLoggingInfo> A09;
    public static final C0111Ng<TigonXProcessTrafficShapingCommunication> A0A;

    static {
        C0111Ng<FacebookLoggingRequestInfo> ng = new C0111Ng<>();
        A03 = ng;
        C0111Ng<TigonLigerRequestInfo> ng2 = new C0111Ng<>();
        A04 = ng2;
        C0111Ng<RedirectRequestInfo> ng3 = new C0111Ng<>();
        A05 = ng3;
        C0111Ng<TigonSamplingConfigInfo> ng4 = new C0111Ng<>();
        A07 = ng4;
        C0111Ng<TigonXProcessTrafficShapingCommunication> ng5 = new C0111Ng<>();
        A0A = ng5;
        C0111Ng<TigonBackupHostServiceInfo> ng6 = new C0111Ng<>();
        A02 = ng6;
        C0111Ng<TransientAnalyzerTracingInfo> ng7 = new C0111Ng<>();
        A08 = ng7;
        C0111Ng<TriggeredLoggingInfo> ng8 = new C0111Ng<>();
        A09 = ng8;
        C0111Ng<AppNetSessionId> ng9 = new C0111Ng<>();
        A01 = ng9;
        C0111Ng<RestrictiveLoggingStatus> ng10 = new C0111Ng<>();
        A06 = ng10;
        A00 = new C0111Ng[]{ng, ng2, ng3, ng4, ng5, ng6, ng7, ng8, ng9, ng10};
    }
}

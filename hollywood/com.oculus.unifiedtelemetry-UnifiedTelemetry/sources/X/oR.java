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
public final class oR {
    public static final oQ<?>[] A00;
    public static final oQ<AppNetSessionId> A01;
    public static final oQ<TigonBackupHostServiceInfo> A02;
    public static final oQ<FacebookLoggingRequestInfo> A03;
    public static final oQ<TigonLigerRequestInfo> A04;
    public static final oQ<RedirectRequestInfo> A05;
    public static final oQ<RestrictiveLoggingStatus> A06;
    public static final oQ<TigonSamplingConfigInfo> A07;
    public static final oQ<TransientAnalyzerTracingInfo> A08;
    public static final oQ<TriggeredLoggingInfo> A09;
    public static final oQ<TigonXProcessTrafficShapingCommunication> A0A;

    static {
        oQ<FacebookLoggingRequestInfo> oQVar = new oQ<>();
        A03 = oQVar;
        oQ<TigonLigerRequestInfo> oQVar2 = new oQ<>();
        A04 = oQVar2;
        oQ<RedirectRequestInfo> oQVar3 = new oQ<>();
        A05 = oQVar3;
        oQ<TigonSamplingConfigInfo> oQVar4 = new oQ<>();
        A07 = oQVar4;
        oQ<TigonXProcessTrafficShapingCommunication> oQVar5 = new oQ<>();
        A0A = oQVar5;
        oQ<TigonBackupHostServiceInfo> oQVar6 = new oQ<>();
        A02 = oQVar6;
        oQ<TransientAnalyzerTracingInfo> oQVar7 = new oQ<>();
        A08 = oQVar7;
        oQ<TriggeredLoggingInfo> oQVar8 = new oQ<>();
        A09 = oQVar8;
        oQ<AppNetSessionId> oQVar9 = new oQ<>();
        A01 = oQVar9;
        oQ<RestrictiveLoggingStatus> oQVar10 = new oQ<>();
        A06 = oQVar10;
        A00 = new oQ[]{oQVar, oQVar2, oQVar3, oQVar4, oQVar5, oQVar6, oQVar7, oQVar8, oQVar9, oQVar10};
    }
}

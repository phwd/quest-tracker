package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.Set;

public final class YP {
    public static YP A05;
    public QuickPerformanceLogger A00 = A3.A00;
    public YO A01 = null;
    public final C1396yX A02 = new C1396yX();
    public final C1399yd A03 = new C1399yd();
    public final C1400ye A04 = new C1400ye();

    public static YP A00() {
        YP yp = A05;
        if (yp != null) {
            return yp;
        }
        YP yp2 = new YP();
        A05 = yp2;
        return yp2;
    }

    public final void A01() {
        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "failInteraction");
        YO yo = this.A01;
        if (yo != null) {
            Set set = yo.A02;
            YQ yq = YQ.INTERACTION_STARTED;
            if (YR.A02(set, yq)) {
                YR.A01(set, yq);
                yo.A01.markerEnd(yo.A00(), 3);
            }
        }
    }

    public final void A02() {
        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", AnonymousClass08.A04("logAttentionSystemShown_", OacrConstants.AUTO_SPEECH_DOMAIN));
        C1400ye yeVar = this.A04;
        Set set = yeVar.A02;
        YQ yq = YQ.ALREADY_LOGGED_ATTENTION_SYSTEM;
        if (!YR.A02(set, yq)) {
            YR.A00(set, yq);
            yeVar.A01.markerPoint(yeVar.A00(), "attention_system");
        }
    }

    public final void A03() {
        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFulfillment");
        YO yo = this.A01;
        if (yo != null) {
            yo.A01.markerPoint(yo.A00(), "FULFILLMENT");
        }
    }

    public final void A04(String str) {
        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logStartVoiceCommand");
        C1400ye yeVar = this.A04;
        yeVar.A02(str);
        this.A01 = yeVar;
        yeVar.A01();
    }
}

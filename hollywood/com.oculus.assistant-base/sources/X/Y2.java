package X;

import android.os.Bundle;
import com.facebook.acra.CrashTimeDataCollector;
import java.util.ArrayList;

public final class Y2 {
    public static final Y1 A08 = new Y1();
    public final AbstractC0105Aj A00 = new C1369y4(this);
    public final AbstractC0105Aj A01 = new C1370y5(this);
    public final AbstractC0105Aj A02 = new C1371y6(this);
    public final AbstractC0105Aj A03 = new C1372y7(this);
    public final AbstractC0105Aj A04 = new C1373y8(this);
    public final C0112Aq A05 = C0112Aq.A00();
    public final C0446Yq A06;
    public final ArrayList A07 = new ArrayList();

    public Y2(C0446Yq yq) {
        C0514bB.A02(yq, "serverTextToSpeech");
        this.A06 = yq;
    }

    public static final void A00(Y2 y2, Integer num) {
        String str;
        C1201vG vGVar = new C1201vG(num);
        Bundle bundle = new Bundle();
        int i = C0390Vd.A00[vGVar.A00.intValue()];
        if (i == 1) {
            str = "SPEAKING";
        } else if (i != 2) {
            str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        } else {
            str = "INACTIVE";
        }
        bundle.putString("speakingState", str);
        y2.A05.A01(new C1256wC("SpeakingStateMessage", bundle, y2.A07));
    }
}

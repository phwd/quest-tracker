package X;

import android.os.Bundle;
import com.facebook.acra.CrashTimeDataCollector;
import java.util.ArrayList;

public final class YJ {
    public static final YI A08 = new YI();
    public final AbstractC0105Aj A00 = new C1385yM(this);
    public final AbstractC0105Aj A01 = new C1386yN(this);
    public final AbstractC0105Aj A02 = new C1387yO(this);
    public final AbstractC0105Aj A03 = new C1388yP(this);
    public final C0112Aq A04 = C0112Aq.A00();
    public final C0446Yq A05;
    public final ArrayList A06 = new ArrayList();
    public final ArrayList A07 = new ArrayList();

    public YJ(C0446Yq yq) {
        C0514bB.A02(yq, "serverTextToSpeech");
        this.A05 = yq;
    }

    public static final void A00(YJ yj, Integer num) {
        String str;
        C1214vU vUVar = new C1214vU(num);
        Bundle bundle = new Bundle();
        int i = Vk.A00[vUVar.A00.intValue()];
        if (i == 1) {
            str = "SPEAKING";
        } else if (i != 2) {
            str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        } else {
            str = "INACTIVE";
        }
        bundle.putString("speakingState", str);
        yj.A04.A01(new C1256wC("TTSSpeechStateMessage", bundle, yj.A07));
    }
}

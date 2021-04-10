package defpackage;

import android.content.SharedPreferences;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.components.variations.firstrun.VariationsSeedBridge;

/* renamed from: Ja  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0549Ja implements Runnable {
    public final String F;
    public final String G = Integer.toString(89);
    public final String H = "stable";
    public final /* synthetic */ C5138um0 I;

    public RunnableC0549Ja(C5138um0 um0, String str) {
        this.I = um0;
        this.F = str;
    }

    public void run() {
        C4987ts1 ts1;
        Object obj = C4987ts1.f11375a;
        synchronized (obj) {
            if (C4987ts1.b == null) {
                C4987ts1.b = new C4987ts1();
            }
            ts1 = C4987ts1.b;
        }
        String str = this.F;
        String str2 = this.G;
        String str3 = this.H;
        Objects.requireNonNull(ts1);
        synchronized (obj) {
            SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
            if (!sharedPreferences.getBoolean("variations_initialized", false)) {
                if (!VariationsSeedBridge.hasNativePref()) {
                    C4647rs1 a2 = ts1.a(0, str, str2, str3);
                    AbstractC3100ip1.f10165a.d("Variations.FirstRun.SeedFetchResult", a2.f11229a);
                    C4817ss1 ss1 = a2.b;
                    if (ss1 != null) {
                        VariationsSeedBridge.setVariationsFirstRunSeed(ss1.e, ss1.f11306a, ss1.b, ss1.c, ss1.d);
                    }
                    sharedPreferences.edit().putBoolean("variations_initialized", true).apply();
                }
            }
        }
        PostTask.b(Zo1.e, new RunnableC0488Ia(this), 0);
    }
}

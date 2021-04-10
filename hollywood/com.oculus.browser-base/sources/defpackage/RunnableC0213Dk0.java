package defpackage;

import android.content.Context;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: Dk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0213Dk0 implements Runnable {
    public static C5317vp0 F;
    public final Context G;
    public final WF0 H;

    public RunnableC0213Dk0(Context context, WF0 wf0, AbstractC0152Ck0 ck0) {
        this.G = context;
        this.H = wf0;
    }

    public void run() {
        if (F == null) {
            F = new C5317vp0();
        }
        if (NetworkChangeNotifier.b() && C0274Ek0.F == null) {
            C0274Ek0.F = new C0274Ek0(this.G, this.H, null);
        }
    }
}

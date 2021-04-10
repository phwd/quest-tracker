package defpackage;

import android.util.Log;
import com.oculus.browser.Experimentation;

/* renamed from: sM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4733sM implements Runnable {
    public final /* synthetic */ Experimentation F;

    public RunnableC4733sM(Experimentation experimentation) {
        this.F = experimentation;
    }

    public void run() {
        Log.i("Experimentation", "re-fetching experiment data");
        Experimentation experimentation = this.F;
        String str = Experimentation.f9698a;
        experimentation.i();
    }
}

package defpackage;

import android.util.Pair;
import android.view.accessibility.AccessibilityManager;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: Dm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Dm1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Lm1 f7908a;

    public Dm1(Lm1 lm1) {
        this.f7908a = lm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int round;
        Lm1 lm1 = this.f7908a;
        Pair pair = (Pair) obj;
        if (lm1.d == 3) {
            float floatValue = ((Float) pair.first).floatValue();
            AccessibilityManager accessibilityManager = (AccessibilityManager) ContextUtils.getApplicationContext().getSystemService("accessibility");
            if ((!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) && Om1.b != (round = Math.round(floatValue * 100.0f))) {
                Om1.b = round;
                Om1.f8648a.F(String.format("Trace buffer usage: %s%%", Integer.valueOf(round)));
                Om1.d(Om1.f8648a.c());
            }
            PostTask.b(Zo1.f9374a, new Gm1(lm1), 1000);
        }
    }
}

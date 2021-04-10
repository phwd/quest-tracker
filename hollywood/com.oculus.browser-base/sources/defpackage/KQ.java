package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import org.chromium.base.task.PostTask;

/* renamed from: KQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KQ implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ NQ F;

    public KQ(NQ nq) {
        this.F = nq;
    }

    public boolean onPreDraw() {
        PostTask.b(C3070if1.g, new JQ(this), 0);
        if (this.F.f8545a.get() == null) {
            return true;
        }
        ((View) this.F.f8545a.get()).getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}

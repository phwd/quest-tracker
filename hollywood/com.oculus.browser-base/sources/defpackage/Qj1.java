package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* renamed from: Qj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Qj1 extends AbstractC5364w41 {
    public final /* synthetic */ ToolbarControlContainer g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Qj1(ToolbarControlContainer toolbarControlContainer, Context context, AbstractC5194v41 v41) {
        super(context, v41);
        this.g = toolbarControlContainer;
    }

    @Override // defpackage.AbstractC5364w41
    public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
        ToolbarControlContainer toolbarControlContainer = this.g;
        int i = ToolbarControlContainer.G;
        if (toolbarControlContainer.e(motionEvent)) {
            return false;
        }
        AbstractC5130uj1 uj1 = this.g.I;
        if ((uj1 == null || !((Vl1) uj1).f9104a.R()) && !C3493l60.F.f(this.g.getContext(), this.g)) {
            return true;
        }
        return false;
    }
}

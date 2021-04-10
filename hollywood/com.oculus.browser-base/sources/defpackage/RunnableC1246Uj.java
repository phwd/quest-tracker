package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* renamed from: Uj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1246Uj implements Runnable {
    public final /* synthetic */ C1551Zj F;

    public RunnableC1246Uj(C1551Zj zj) {
        this.F = zj;
    }

    public void run() {
        int i = this.F.i() ? 0 : 4;
        DA da = this.F.L;
        if (da != null) {
            ToolbarControlContainer toolbarControlContainer = (ToolbarControlContainer) da;
            Objects.requireNonNull(toolbarControlContainer);
            if (toolbarControlContainer.getVisibility() != i) {
                ToolbarControlContainer toolbarControlContainer2 = (ToolbarControlContainer) this.F.L;
                Objects.requireNonNull(toolbarControlContainer2);
                toolbarControlContainer2.setVisibility(i);
                ToolbarControlContainer toolbarControlContainer3 = (ToolbarControlContainer) this.F.L;
                Objects.requireNonNull(toolbarControlContainer3);
                toolbarControlContainer3.requestLayout();
            }
        }
    }
}

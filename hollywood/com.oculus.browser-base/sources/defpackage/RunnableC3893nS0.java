package defpackage;

import android.view.ViewConfiguration;
import java.util.Objects;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* renamed from: nS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3893nS0 implements Runnable {
    public final /* synthetic */ SelectionPopupControllerImpl F;

    public RunnableC3893nS0(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.F = selectionPopupControllerImpl;
    }

    public void run() {
        Objects.requireNonNull(this.F);
        long defaultActionModeHideDuration = ViewConfiguration.getDefaultActionModeHideDuration();
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.F;
        selectionPopupControllerImpl.H.postDelayed(selectionPopupControllerImpl.P, defaultActionModeHideDuration - 1);
        SelectionPopupControllerImpl selectionPopupControllerImpl2 = this.F;
        if (selectionPopupControllerImpl2.e()) {
            selectionPopupControllerImpl2.R.hide(defaultActionModeHideDuration);
        }
    }
}

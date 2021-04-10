package defpackage;

import android.view.ViewTreeObserver;
import java.util.Objects;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: d71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ViewTreeObserver$OnGlobalLayoutListenerC2131d71 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final TabGridDialogView F;

    public ViewTreeObserver$OnGlobalLayoutListenerC2131d71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onGlobalLayout() {
        TabGridDialogView tabGridDialogView = this.F;
        Objects.requireNonNull(tabGridDialogView);
        if (!C3493l60.F.f(tabGridDialogView.F, tabGridDialogView)) {
            tabGridDialogView.n0 = tabGridDialogView.O.getWidth();
            tabGridDialogView.m0 = tabGridDialogView.O.getHeight();
        }
    }
}

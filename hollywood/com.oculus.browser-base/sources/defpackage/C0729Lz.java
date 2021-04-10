package defpackage;

import android.widget.PopupWindow;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: Lz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0729Lz implements PopupWindow.OnDismissListener {
    public final ContextualSearchManager F;

    public C0729Lz(ContextualSearchManager contextualSearchManager) {
        this.F = contextualSearchManager;
    }

    public void onDismiss() {
        this.F.P.a();
    }
}
